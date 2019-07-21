var tab_account = null;
var tab_speeddial = null;
var tab_bookmarks = null;
var tab_backups = null;

// get sections
var login_section = null;
var manual_speed = null;
var manual_bookmarks = null;
var restore_section = null;

//account open
function account_open() {
	tab_account.className += ' tab_pressed';
	login_section.className = 'section_open';

	tab_speeddial.className = 'tab tab_blue tab_speeddial';
	tab_bookmarks.className = 'tab tab_green tab_bookmarks';
	tab_backups.className = 'tab tab_yellow tab_backups';

	manual_speed.className = '';
	manual_bookmarks.className = '';
	restore_section.className = '';
}

//speed dial open
function speeddial_open() {
	tab_speeddial.className += ' tab_pressed';
	manual_speed.className = 'section_open';

	tab_account.className = 'tab tab_violet tab_account';
	tab_bookmarks.className = 'tab tab_green tab_bookmarks';
	tab_backups.className = 'tab tab_yellow tab_backups';

	login_section.className = '';
	manual_bookmarks.className = '';
	restore_section.className = '';
}

//bookmarks open
function bookmarks_open() {
	tab_bookmarks.className += ' tab_pressed';
	manual_bookmarks.className = 'section_open';

	tab_account.className = 'tab tab_violet tab_account';
	tab_speeddial.className = 'tab tab_blue tab_speeddial';
	tab_backups.className = 'tab tab_yellow tab_backups';

	login_section.className = '';
	manual_speed.className = '';
	restore_section.className = '';
}

//backups open
function backups_open() {
	tab_backups.className += ' tab_pressed';
	restore_section.className = 'section_open';

	tab_account.className = 'tab tab_violet tab_account';
	tab_speeddial.className = 'tab tab_blue tab_speeddial';
	tab_bookmarks.className = 'tab tab_green tab_bookmarks';

	login_section.className = '';
	manual_speed.className = '';
	manual_bookmarks.className = '';
}

document.addEventListener( "DOMContentLoaded", function(){
	// get tabs buttons
	tab_account = document.getElementById('tabHeadAccount');
	tab_speeddial = document.getElementById('tabHeadSync');
	tab_bookmarks = document.getElementById('tabHeadSyncBookmarks');
	tab_backups = document.getElementById('tabHeadBackupHistory');


	tab_account.addEventListener( "click", function(){
		fvdSynchronizer.Options.setTab( "account" );
	}, false );
	tab_speeddial.addEventListener( "click", function(){
		fvdSynchronizer.Options.setTab( "speeddial" );
	}, false );
	tab_bookmarks.addEventListener( "click", function(){
		fvdSynchronizer.Options.setTab( "bookmarks" );
	}, false );
	tab_backups.addEventListener( "click", function(){
		fvdSynchronizer.Options.setTab( "backups" );
	}, false );

	// get sections
	login_section = document.getElementById('login_section');
	manual_speed = document.getElementById('manual_speed');
	manual_bookmarks = document.getElementById('manual_bookmarks');
	restore_section = document.getElementById('restore_section');

  var howToUseBtn = document.querySelector("#topButtons .how-to-use-button");
  howToUseBtn.addEventListener("click", function() {
    var url = "https://everhelper.desk.com/customer/en/portal/topics/526842-eversync/articles";
    if(fvdSynchronizer.Utils.browserLocaleIs("ru")) {
      url = "https://everhelper.desk.com/customer/en/portal/topics/873013-eversync---%D0%92%D0%BE%D0%BF%D1%80%D0%BE%D1%81%D1%8B-%D0%B8-%D0%9E%D1%82%D0%B2%D0%B5%D1%82%D1%8B/articles";
    }
    window.open(url);
  }, false);
}, false );
