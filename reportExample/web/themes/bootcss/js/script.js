
var Dashboard = {

    settings: {
        columns: '.column',
        widgetSelector: '.widget',
        handleSelector: '.widget-head',
        contentSelector: '.widget-content',
        widgetDefault: {
            movable: true,
            removable: true,
            collapsible: true
        },
        widgetIndividual: {
            divID: {
                movable: false,
                removable: false,
                collapsible: false
            }
        }
    },

    init: function () {
        this.addWidgetControls();
        this.makeSortable();
    },

    getWidgetSettings: function (id) {
        var settings = this.settings;
        return (id && settings.widgetIndividual[id]) ? $.extend({}, settings.widgetDefault, settings.widgetIndividual[id]) : settings.widgetDefault;
    },

    addWidgetControls: function () {
        var Dashboard = this, settings = this.settings;

        $(settings.widgetSelector, $(settings.columns)).each(function () {
            var thisWidgetSettings = Dashboard.getWidgetSettings(this.id);
            var thisWidgetID = this.id;
            if ($('#' + this.id + ' div a').is('.remove') == false) {
                if (thisWidgetSettings.removable) {
                    $('<a href="#" class="remove">CLOSE</a>').mousedown(function (e) {
                        e.stopPropagation();
                    }).click(function () {
                        $('<div title="Remove Widget">Are you sure you want to remove the <span style="font-weight:bold;text-transform:uppercase;">' + $('#' + thisWidgetID).find('span').text() + '</span> widget?</div>').dialog({
                            resizable: false,
                            modal: true,
                            buttons: {
                                "Remove Widget": function () {
                                    $('#' + thisWidgetID).animate({
                                        opacity: 0
                                    }, function () {
                                        $(this).wrap('<div/>').parent().slideUp(function () {
                                            $(this).remove();
                                            
                                            //delete widget
                                        });
                                    });
                                    $(this).dialog("close");
                                },
                                Cancel: function () {
                                    $(this).dialog("close");
                                }
                            }
                        });
                        return false;
                    }).appendTo($(settings.handleSelector, this));
                }

                $('<a href="#" class="edit">EDIT</a>').mousedown(function (e) {
                    e.stopPropagation();
                }).click(function () {
                    Dashboard.configureWidget(thisWidgetID.replace('dw', ''));
                    return false;
                }).appendTo($(settings.handleSelector, this));

                if (thisWidgetSettings.collapsible) {
                    $('<a href="#" class="collapse">COLLAPSE</a>').mousedown(function (e) {
                        e.stopPropagation();
                    }).click(function () {
                        $(this).parents(settings.widgetSelector).toggleClass('collapsed');
                        var iframeID = 'iframe' + $(this).parent().parent().attr('id');
                        if (!$(this).parents(settings.widgetSelector).hasClass('collapsed'))
                            $('#' + iframeID).attr('src', $('#' + iframeID).attr('src'));
                        else
                            $('#' + iframeID).contents().find("div.container-fluid").html('');
                        Dashboard.savePreferences();
                        return false;
                    }).prependTo($(settings.handleSelector, this));
                }
            }
        });
    },

    attachStylesheet: function (href) {
        return $('<link href="' + href + '" rel="stylesheet" type="text/css" />').appendTo('head');
    },

    makeSortable: function () {
        var Dashboard = this,
            settings = this.settings,
            $sortableItems = (function () {
                var notSortable = '';
                $(settings.widgetSelector, $(settings.columns)).each(function (i) {
                    if (!Dashboard.getWidgetSettings(this.id).movable) {
                        if (!this.id) {
                            this.id = 'widget-no-id-' + i;
                        }
                        notSortable += '#' + this.id + ',';
                    }
                });
                if (notSortable.length == 0)
                    return $('> li', settings.columns);
                else
                    return $('> li:not(' + notSortable + ')', settings.columns);
            })();

        $sortableItems.find(settings.handleSelector).css({
            cursor: 'move'
        }).mousedown(function (e) {
            $sortableItems.css({ width: '' });
            $(this).parent().css({
                width: $(this).parent().width() + 'px'
            });
        }).mouseup(function () {
            if (!$(this).parent().hasClass('dragging')) {
                $(this).parent().css({ width: '' });
            } else {
                $(settings.columns).sortable('disable');
            }
        });

        $(settings.columns).sortable({
            items: $sortableItems,
            connectWith: $(settings.columns),
            handle: settings.handleSelector,
            placeholder: 'widget-placeholder',
            forcePlaceholderSize: true,
            revert: 300,
            delay: 100,
            opacity: 0.8,
            containment: 'document',
            start: function (e, ui) {
                $(ui.helper).addClass('dragging');
            },
            stop: function (e, ui) {
                $(ui.item).css({ width: '' }).removeClass('dragging');
                $(settings.columns).sortable('enable');
                Dashboard.savePreferences();
            }
        });
    },

    savePreferences: function () {
        var Dashboard = this,
				settings = this.settings,
				paramString = '';

        var columnPos = 0;

        $(settings.columns).each(function (i) {

            var sortOrder = 0;

            $(settings.widgetSelector, this).each(function (i) {
                var kpiName = $(this).attr('id');
                var kpiTitle = $(this).find('span').text();
                var kpiUrl = $(this).find('iframe').attr('src');
                var kpiColumnPos = columnPos;
                var kpiSortOrder = sortOrder++;
                var kpiExpanded = $(settings.contentSelector, this).css('display') === 'none' ? '0' : '1';

                paramString += kpiName + ':';
                paramString += kpiTitle + '|' + kpiUrl + '|' + kpiColumnPos + '|' + kpiSortOrder + '|' + kpiExpanded;
                paramString += ';';
            });

            columnPos++;

        });

        //save widget settings 
        //alert(paramString);
    },

    addWidget: function () {

        $('<div title="Add Widget" id="dialog_add_widget" style="overflow:hidden"><iframe src="widgets/addwidget.html" style="border:0px;width:100%;height:98%;" frameborder="0"></iframe></div>').dialog({
            resizable: false,
            width: 412,
            height: 375,
            modal: true,
            close: function (ev, ui) { $(this).remove(); }
        });

        $('#dialog_add_widget iframe').load(function () {
            $("#dialog_add_widget").dialog("option", "height", this.contentWindow.document.body.offsetHeight + 100);
        });

        return false;
    },

    addWidgetToDashboard: function (kpiTitle, kpiName) {
        //TODO: add widget

        //for demo purposes
        var rand = Math.floor(Math.random() * 10 + 1); ;

        var data = '<li id="' + kpiName + rand + '" class="widget"><div class="widget-head"><span>' + kpiTitle + '</span></div><div class="widget-content"><iframe id="iframe' + kpiName + rand + '" class="widget-iframe" style="overflow:hidden;" src="widgets/' + kpiName.toLowerCase() +'.html"></iframe></div></li>';

        if (window.parent.$('.container-fluid #columns').length > 0) {
            window.parent.$('.container-fluid #columns ul:first').append(data);
        }
        else {
            window.parent.$('.container-fluid').html('<div id="columns"><ul class="column">' + data + '</ul><ul class="column"></ul><ul class="column"></ul>');
        }

        window.parent.Dashboard.savePreferences();
        window.parent.Dashboard.addWidgetControls();
        window.parent.Dashboard.makeSortable();

        window.parent.$('#dialog_add_widget').remove();

        return false;
    },

    configureWidget: function (dwid) {

        $('<div title="Configure Widget" id="dialog_configure_widget" style="overflow:hidden"><iframe src="widgets/config.html" style="border:0px;width:100%;height:98%;" frameborder="0"></iframe></div>').dialog({
            resizable: false,
            width: 550,
            modal: true,
            buttons: {
                "Save Widget": function () {
                    $(this).dialog("close");
                },
                Cancel: function () {
                    $(this).dialog("close");
                }
            }
        });

        $('#dialog_configure_widget iframe').load(function () {
            $("#dialog_configure_widget").dialog("option", "height", this.contentWindow.document.body.offsetHeight + 150);
        });

        return false;
    }

};



/**
 * 全选/反选
 * @param {Object} oInput
 */
function selectedAllFunc(oInput){
	if(oInput.checked){
		$(oInput).parents("table").find("input[name='selected_id']").each(function(){
			//$(this).attr("checked",'true');
			$(this)[0].checked = true;
		});
	}else{
		$(oInput).parents("table").find("input[name='selected_id']").each(function(){
			//$(this).removeAttr("checked");
			$(this)[0].checked = false;
		});
	}
}

/**
 * 置为有效或者无效
 * @param {Object} flag 0:无效,1:有效
 */
function setValidOrInvalid(flag){
	//勾选的所有id
	var ids = "";
	$('input[name="selected_id"]:checked').each(function(){
		ids += $(this).attr('continentid')+",";
    });
	if(ids){
		ids = ids.substring(0,ids.length-1);
	}else{
		alert("请选择记录！");
		return;
	}
	if(flag == "0"){
		if(!window.confirm('是否确定"置为无效"')) return;
	}else{
		if(!window.confirm('是否确定"置为有效"')) return;
	}
	
	$.ajax({
	        url: validOrInvalidActionUrl,
	        dataType: 'json',
			data : {
					"ids":ids,
					"status":flag
					},
	        type: 'POST',
	        success: function (res) {
	            if(res.msg == 'suceess'){
					alert("操作成功！");
					window.location.href = mainActionUrl;
				}else if(res.msg == 'fail'){
					alert("操作失败！");
				}
	            //search();//刷新数据
	        }
		});	
}


/**
 * 设置排序
 */
function saveSn(){
	var reg = /^d+$/;
	//以逗号分隔
	var bzId = "";
	var bzSn = "";
	var errMsg = "";
	var idx = 1;
	$("input[name='sn']").each(function(index, domEle){
		if (!positiveInteger($(this).val())) {
			errMsg += idx+"：编码为\"" + $(this).attr("bzCode") + "\"的序号填写有误，只能输入正整数！"+"\n\n";
			idx++;
		}
		if (index == 0) {
			bzId += $(this).attr("bzId");
			bzSn += $(this).val()
		}
		else {
			bzId += "," + $(this).attr("bzId");
			bzSn += "," + $(this).val()
		}
	});
	if(errMsg != ""){alert(errMsg);return false;}
	if (!window.confirm("是否保存排序")) return false;
	
	$.ajax({
		url: saveSNActionURL,
		dataType: 'json',
		data: {
			"bzId": bzId,
			"bzSn": bzSn
		},
		type: 'POST',
		success: function(res){
			if (res.msg == 'suceess') {
				alert("操作成功！");
				window.location.href = mainActionUrl;
			}else{
				if (res.msg == 'fail') {
					alert("操作失败！");
				}
			}
		}
	});
}


/**
 * 正整数正则表达式
 * @param {Object} str
 */
function positiveInteger(str){
	var s = /^[0-9]*$/;
	return s.test(str);
}

var datatableLanguageSetting = {  
	"sProcessing": "正在加载中......",
	"sLengthMenu": "每页显示 _MENU_ 条记录",
	"sZeroRecords": "对不起，查询不到相关数据！",
	"sEmptyTable": "表中无数据存在！",
	"sInfoEmpty": "无记录！", 
	"sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
	"sInfoFiltered": "数据表中共为 _MAX_ 条记录",
	"sSearch": "搜索",
	"oPaginate": {
	    "sFirst": "首页",
	    "sPrevious": "上一页",
	    "sNext": "下一页",
	    "sLast": "末页"
	}
};
var datatableLengthMenu=[[10, 25, 50], [10, 25, 50]];  