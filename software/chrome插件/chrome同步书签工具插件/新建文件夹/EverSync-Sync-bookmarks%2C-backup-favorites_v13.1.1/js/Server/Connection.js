if (window == chrome.extension.getBackgroundPage()) {

	(function(){

		var Errors = fvdSynchronizer.Errors;

		function SyncRequest(aData, aCallback, form){

			aData = aData || {};

			function encodeRequest(requestData){
				return JSON.stringify(requestData);
			}

			function decodeResponse(responseData){
				return JSON.parse(responseData);
			}

			aData._client_software = "chrome_addon";

			var data = encodeRequest(aData);

			var req = new XMLHttpRequest();
      var url = "https://sync.everhelper.me";
			if( form && aData.action ){
				url += "/" + aData.action;
			}

			req.open('POST', url, true);

			req.setRequestHeader( "EverHelper-Token", fvdSynchronizer.Server.Connection.getCurrentToken() );

			req.onload = function() {

				try {
					var response = decodeResponse(req.responseText);
				}
				catch (ex) {
					console.log("Malformed response: " + req.responseText + "(" + ex + ")\n");

					aCallback(Errors.ERROR_SERVER_RESPONSE_MALFORMED);
					return;
				}

				if( response && response.errorCode ) {

					if( response.errorCode == fvdSynchronizer.Errors.ERROR_AUTH_FAILED &&
						aData.action != "user_exists" && aData.action != "user:logout" ){

						fvdSynchronizer.Server.Sync.activityState( function( state ){

							if( state == "logged" ){

								fvdSynchronizer.Server.Sync.setActivityState( "not_logged" );
								fvdSynchronizer.Dialogs.alertWindow(
									_("dlg_alert_wrong_login_password_title"),
									_("dlg_alert_wrong_login_password_text"),
									{
									single: true
								});

							}

						} );

					}

				}

				aCallback(response.errorCode, response.body);
			};

			req.onerror = function() {
				console.log("Request error\n", arguments);

				aCallback(Errors.ERROR_CONNECTION_ERROR);
			};

			if(form) {
				req.send(form);
			}
			else{
				req.send(data);
			}
		}

		var Connection = function() {

			var currentToken = "";

			this.setCurrentToken = function( token ){
				currentToken = token;
			};

			this.getCurrentToken = function(){
				return currentToken;
			};

			this.request = function(data, callback, form){
				new SyncRequest(data, callback, form);
			};

			this.simpleRequest = function( url, method, data, callback ){

				var req = new XMLHttpRequest();
				req.open( method, url );

				req.onload = function(){
					callback( JSON.parse( req.responseText ) );
				};

				req.onerror = function(){
					callback( null );
				};

				req.send( data );
			};

			this.get = function( url, callback ){
				return this.simpleRequest( url, "GET", null, callback );
			};

			this.post = function( url, dataString, callback ){
				return this.simpleRequest( url, "POST", dataString, callback );
			};


		};

		this.Connection = new Connection();

	}).apply(fvdSynchronizer.Server);

}
else{
	fvdSynchronizer.Server.Connection = chrome.extension.getBackgroundPage().fvdSynchronizer.Server.Connection;
}
