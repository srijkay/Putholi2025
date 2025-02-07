import { Component, PLATFORM_ID, Injectable, NgZone, APP_ID, Inject, Injector } from '@angular/core';
import { isPlatformBrowser, isPlatformServer } from '@angular/common';
// import { BaseComponent } from './../common/commonComponent';
import { HttpClient, HttpHeaders, HttpResponse, HttpParams, } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { delay, map } from 'rxjs/operators';
import { EnvService } from '../common/env.service';
// import swal from 'sweetalert';
// import {NgxCoolDialogsService} from 'ngx-cool-dialogs'

@Injectable({
	providedIn: 'root'
})

export class CommonService {
	authorised: any = false;
	constructor(public _http: HttpClient, @Inject(PLATFORM_ID) platformId: Object, private envservice: EnvService) {
		// super(inj);
		this.platformId = platformId;
		this._apiUrl = this.envservice.apiUrl;
		// this._apiUrl=window.location.href.split('#')[0];
		// this._apiUrl = "http://localhost:8081";		
	}



	public _apiUrl;
	public platformId;

	public getToken(key) {
		if (isPlatformBrowser(this.platformId)) {
			return window.sessionStorage.getItem(key);
		}
	}
	public setToken(key, value) {
		if (isPlatformBrowser(this.platformId)) {
			window.sessionStorage.setItem(key, value);
		}
	}


	/*******************************************************************************************
	@PURPOSE      	: 	Call api.
	@Parameters 	: 	{
							url : <url of api>
							data : <data object (JSON)>
							method : String (get, post)
							isForm (Optional) : Boolean - to call api with form data
							isPublic (Optional) : Boolean - to call api without auth header
						}
	/*****************************************************************************************/
	callApi(url, data, method, isForm?, isPublic?, islogin?) {

		let headers;
		if (isPublic) {
			headers = new HttpHeaders({ 'Accept-Language': 'en', 'content-Type': 'application/json', 'Authorization': 'Basic Y3JpbXNvbjpjUmlNczBuJGRjMW5EMUE=', 'loggedUser': 'admin' });
		} else {

			headers = new HttpHeaders({ 'Accept-Language': localStorage.getItem('Language'), 'loggedUser': 'admin', 'Authorization': sessionStorage.getItem('accessToken') });
		}
		if (isForm) {
			headers = new HttpHeaders({ 'Accept-Language': localStorage.getItem('Language'), 'loggedUser': 'admin', 'Authorization': 'Basic Y3JpbXNvbjpjUmlNczBuJGRjMW5EMUE=' });
		}
		if (islogin === 'REG') {
			if (method === 'post') {
				return this._http.post(this._apiUrl + '/v1/api/' + url, data, { observe: 'response', headers })
					.toPromise()
					.then(res => {
						return res;
					});

			} else if (method == 'get') {
				return this._http.get(this._apiUrl + '/v1/api/' + url, { headers })
					.toPromise()
					.then(res => {
						return res;
					});
			} else if (method == 'put') {
				return this._http.put(this._apiUrl + '/v1/api/' + url, data, { headers, observe: 'response' })
					.toPromise()
					.then(res => {

						return res;
					});
			} else {
				return this._http.delete(this._apiUrl + '/v1/api/' + url, { headers, observe: 'response' })
					.toPromise()
					.then(res => {

						return res;
					});
			}
		} else if (islogin === 'LOG') {

			if (method == 'post') {


				return this._http.post(this._apiUrl + '/v1/api/' + url, data, { headers }).toPromise().then(res => {
					return res;
				});
			}

			else if (method == 'get') {
				return this._http.get(this._apiUrl + '/v1/api/' + url, { headers })
					.toPromise()
					.then(res => {
						return res;
					});
			}
		}
	}

	sentApi(url, data, method, isForm?, isPublic?, islogin?): Observable<HttpResponse<any>> {

		let headers;
		if (isPublic) {
			headers = new HttpHeaders({ 'Accept-Language': 'en-US', 'Authorization': 'Basic Y3JpbXNvbjpjUmlNczBuJGRjMW5EMUE=' });
		} else {

			headers = new HttpHeaders({ 'Accept-Language': 'en-US', 'Authorization': sessionStorage.getItem('accessToken') });


		}
		if (isForm) {
			headers = new HttpHeaders({ 'Accept-Language': 'en-US', 'Authorization': sessionStorage.getItem('accessToken') });
		}
		if (islogin === 'REG') {

			if (method == 'post') {
				return this._http.post<any>(this._apiUrl + '/v1/api/' + url, data, { observe: 'response', headers })

			}
			else if (method == 'put') {


				return this._http.put<any>(this._apiUrl + '/v1/api/' + url, data, { observe: 'response', headers })
			} else if (method == 'delete') {
				return this._http.delete<any>(this._apiUrl + '/v1/api/' + url, { observe: 'response', headers })
			} else if (method == 'get') {
				return this._http.get<any>(this._apiUrl + '/v1/api/' + url, { observe: 'response', headers })
			}
		}

	}



	downloadAttachment(url, data) {
		let corpId = this.getOrgId();
		let headers = new HttpHeaders({ 'Accept-Language': 'en-US', 'Authorization': sessionStorage.getItem('accessToken') });
		return this._http.post(this._apiUrl + '/api/' + url + '/' + corpId, data, {
			responseType: 'arraybuffer' as 'json', headers: headers, observe: 'response'
		});

	}


	getPdfDocument(data) {
		let corpId = this.getOrgId();
		let headers = new HttpHeaders({ 'Accept-Language': 'en-US', 'Authorization': sessionStorage.getItem('accessToken') });
		return this._http.get(this._apiUrl + '/api/' + 'orgId/' + corpId + '/report-view/' + data + '/pdf', {
			responseType: 'arraybuffer' as 'json', headers: headers, observe: 'response'
		});
	}


	downloadApproval(data) {
		let headers = new HttpHeaders({ 'Accept-Language': sessionStorage.getItem('Language'), 'Content-Type': 'application/json', 'Authorization': 'Basic Y3JpbXNvbjpjUmlNczBuJGRjMW5EMUE=' });
		return this._http.get(this._apiUrl + '/v1/api/application/download/' + data, {
			responseType: 'arraybuffer' as 'json', headers: headers,
		});
	}
	getPdfDocumentFromTask(data) {
		let corpId = this.getOrgId();
		let headers = new HttpHeaders({ 'Accept-Language': 'en-US', 'Authorization': sessionStorage.getItem('accessToken') });
		return this._http.get(this._apiUrl + '/api/' + 'orgId/' + corpId + '/report-view/task/' + data + '/pdf', {
			responseType: 'arraybuffer' as 'json', headers: headers, observe: 'response'
		});
	}


	/*******************************************************************************************
		@PURPOSE      	: 	Call api.
		@Parameters 	: 
	/*****************************************************************************************/

	checkAndSetSelectedOrganisation(selectedOrganisation) {
		if (this.getToken('corp ') == null) {
			this.setToken('corp', JSON.stringify(selectedOrganisation))

		} else {
			this.setToken('corp', JSON.stringify(selectedOrganisation))
		}
	}


	/*******************************************************************************************
		@PURPOSE      	: 	Call api.
		@Parameters 	: 
	/*****************************************************************************************/
	checkAndSetSelectedEntityOwnerType(selectedEntityOwnerType) {
		if (this.getToken('entityOwnerType') == null) {
			this.setToken('entityOwnerType', JSON.stringify(selectedEntityOwnerType))
		} else {
			this.setToken('entityOwnerType', JSON.stringify(selectedEntityOwnerType))
		}
	}
	/****************************************************************************/



	/******************************************************************************************
		@PURPOSE      	: 	Call api.
		@Parameters 	: 
	/*****************************************************************************************/
	getOrgId() {
		if (this.getToken('corp') != null) {
			let corp = JSON.parse(this.getToken('corp'));
			return corp.id
		}
	}
	/****************************************************************************/
	/******************************************************************************************
		@PURPOSE      	: 	Call api.
		@Parameters 	: 
	/*****************************************************************************************/
	getUser() {
		if (this.getToken('user') != null) {
			let user = JSON.parse(this.getToken('user'));
			return user.login
		}
	}
	/****************************************************************************/


	/******************************************************************************************
		@PURPOSE      	: 	Call api.
		@Parameters 	: 
	/*****************************************************************************************/
	getOrganisation() {
		if (this.getToken('corp') != null) {
			let corp = JSON.parse(this.getToken('corp'));
			return corp.organisationType.shortName
		}
	}
	/****************************************************************************/


	/******************************************************************************************
		@PURPOSE      	: 	Call api.
		@Parameters 	: 
	/*****************************************************************************************/
	getSystemSettings() {
		if (this.getToken('systemSettings') != null) {
			let syssettings = JSON.parse(this.getToken('systemSettings'))
			return syssettings;
		}
	}
	/****************************************************************************/


	/******************************************************************************************
		@PURPOSE      	: 	Call api.
		@Parameters 	: 
	/*****************************************************************************************/
	getConfigSettings() {
		if (this.getToken('configSettings') != null) {
			let configSettings = JSON.parse(this.getToken('configSettings'))
			return configSettings
		}
	}
	/****************************************************************************/






	/*****************************************************************************************/
	// @PURPOSE      	: 	To show server error
	/*****************************************************************************************/
	// public swal = swal;
	showServerError(e) {
		//this.coolDialogs.alert('Whoa boy, be careful!');
		// this.coolDialogs.alert(e.error.errorDescription, {
		// 	theme: 'default',
		// 	okButtonText: 'OK',
		// 	title: 'Response Description'
		//   });
		//swal("Response Description",e.error.errorDescription);
		// console.log('Internal server error',e)
	}
	/****************************************************************************/

	/****************************************************************************
	   @PURPOSE      :get application wide settings
	   @PARAMETERS   :NA
	   @RETURN       : list og=f settings
	 ****************************************************************************/

	userAdminSettings: any = {}
	getAdminSettings() {
		this.callApi('configuration-settings', '', 'get', false, false, 'CCILST').then(success => {
			let SuccessData: any = success;
			this.userAdminSettings = SuccessData
			return this.userAdminSettings;
		}).catch(e => {

		})

	}
	/******************************************************************************************/

	screenLog(entityName, entityRecordId, action) {
		this.callApi('screen-log/' + entityName + '/' + entityRecordId + '/' + action, '', 'get', false, false, 'CCILST').then(success => {
			let SuccessData = success
		})
	}



	clearPreviousValues() {
		sessionStorage.entityOwnerType = null;
		sessionStorage.permission = null;
		sessionStorage.corp = null;
		sessionStorage.user = null;
		sessionStorage.configSettings = null;
		sessionStorage.country = null;
		sessionStorage.systemSettings = null;
	}

	/****************************************************************************
	  @PURPOSE      :Get account Details
	  @PARAMETERS   : form,formdata
	  @RETURN       : NA
	****************************************************************************/

	getAccount() {
		this.callApi('account', {}, 'get', false, false, 'CCILST').then(success => {
			let resData: any = success;
			this.setToken('user', JSON.stringify(resData));
			this.checkAndSetSelectedOrganisation(resData.organisation);
			this.checkAndSetSelectedEntityOwnerType(resData.entityOwnerType);
			this.getAccountThen(resData)

		}).catch(e => {
			this.getAccountCatch()
			e.status === 401 || e.status === 403 ? this._authenticated = true : this._authenticated = false

		});

	}
	/**********************************************************************************************/
	_identity: any
	_authenticated: boolean = false

	getAccountThen(account) {
		this._identity = account;
		this._authenticated = true;
	}

	getAccountCatch() {
		this._identity = null;
		this._authenticated = false;
	}

	isAuthenticated() {
		return this._authenticated;
	}
}


