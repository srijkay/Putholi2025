import { Component, OnInit, PLATFORM_ID, Injector, NgZone, APP_ID, Input } from '@angular/core';
import { TransferState, makeStateKey, Title, Meta } from '@angular/platform-browser';
import { isPlatformBrowser, isPlatformServer } from '@angular/common';
import { Router, ActivatedRoute, NavigationStart } from "@angular/router";
import { CommonService } from './common.service';
import { ErrorMessages } from './errorMessages';
import { environment } from '../../environments/environment';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Broadcaster } from "../common/BroadCaster";
// import { ToastrService } from 'ngx-toastr';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { AlertService } from './_alert';
import { TranslateService } from '@ngx-translate/core';
import { MessagesData } from './errorMessagesdata';
import { entityConstants } from './entity.constants'
import { ToastrManager } from 'ng6-toastr-notifications';
// import { SocialAuthService } from 'angularx-social-login';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { EnvService } from './env.service';

declare var $: any;

@Component({
  selector: 'parent-comp',
  template: ``,
  providers: []
})

export class BaseComponent {
  public MSG = (<any>MessagesData)
  constructor(injector: Injector) {
    this.router = injector.get(Router)
    this.platformId = injector.get(PLATFORM_ID)
    this.appId = injector.get(APP_ID)
    this.commonService = injector.get(CommonService)
    this.errorMessage = injector.get(ErrorMessages)
    this.http = injector.get(HttpClient)
    this.titleService = injector.get(Title)
    this.metaService = injector.get(Meta)
    this.activatedRoute = injector.get(ActivatedRoute)
    this.modalService = injector.get(BsModalService)
    this.brodcaster = injector.get(Broadcaster);
    // this.toastr = injector.get(ToastrService);
    this.baseUrl = this.commonService._apiUrl;
    this.alertToastr = injector.get(AlertService)
    this.translate = injector.get(TranslateService)
    // this.translate.use('en');
    this.toastr = injector.get(ToastrManager);
    // this.SocialAuthService = injector.get(SocialAuthService);
    this.ngxLoader = injector.get(NgxUiLoaderService)

    this.env = injector.get(EnvService)
  }
  @Input() totalRecordCount: number;
  @Input() tableName: string;
  @Input() fileName: string;
  @Input() pageNo: number;
  @Input() noOfRecordsPerPage: number;
  monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun",
    "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
  ];
  public http: HttpClient;
  public activatedRoute: ActivatedRoute;
  public errorMessage: ErrorMessages
  public titleService: Title;
  public modalService: BsModalService
  public metaService: Meta
  public platformId: any;
  public appId: any;
  public brodcaster: Broadcaster;
  // public toastr: ToastrService
  public alertToastr: AlertService
  public router: Router;
  public commonService: CommonService;
  public baseUrl;
  public Environments = [];
  public Categories = [];
  public Status = [];
  public projects = [];
  public AlertGroups = [];
  public Fprojects = [];
  public FCategories = [];
  public FEnvironments = [];
  public FStatus = [];
  public translate: TranslateService;
  public entityConstants = (<any>entityConstants)
  public toastr: ToastrManager;
  // public SocialAuthService: SocialAuthService;
  public ngxLoader: NgxUiLoaderService
  public env: EnvService


  // *************************************************************//
  //@Purpose : We can use following function to use localstorage
  //*************************************************************//
  setToken(key, value) {
    if (isPlatformBrowser(this.platformId)) {
      window.sessionStorage.setItem(key, value);
    }
  }
  getToken(key) {
    if (isPlatformBrowser(this.platformId)) {
      return window.sessionStorage.getItem(key);
    }
  }
  removeToken(key) {
    if (isPlatformBrowser(this.platformId)) {
      window.sessionStorage.removeItem(key);
    }
  }
  clearToken() {
    if (isPlatformBrowser(this.platformId)) {
      window.sessionStorage.clear()
    }
  }
  //*************************************************************//


  /****************************************************************************
  @PURPOSE      : To restrict or allow some values in input.
  @PARAMETERS   : $event
  @RETURN       : Boolen
  ****************************************************************************/
  RestrictSpace(e) {
    if (e.keyCode == 32) {
      return false;
    } else {
      return true;
    }
  }

  AllowNumbers(e) {
    var input;
    if (e.metaKey || e.ctrlKey) {
      return true;
    }
    if (e.which === 32) {
      return false;
    }
    if (e.which === 0) {
      return true;
    }
    if (e.which < 33) {
      return true;
    }
    if (e.which === 43 || e.which === 45) {
      return true;
    }
    input = String.fromCharCode(e.which);
    return !!/[\d\s]/.test(input);
  }

  /****************************************************************************
  @PURPOSE      : Allows only charcters
  @PARAMETERS   : <field_name, errorObj?>
  @RETURN       : error message.
  ****************************************************************************/
  restriction(e, type?) {
    if (type === 'shortName') {
      let inp = String.fromCharCode(e.keyCode);
      if (/^[_'.A-Za-z]*$/.test(inp)) {
        return true;
      } else {
        e.preventDefault();
        return false;
      }
    } else if (type === 'AlphaOnly') {
      // let inp = String.fromCharCode(e.keyCode);
      e.replace(/[\s]/g, '');
      e.replace(/[^a-zA-Z]/g, "");
    }

  }


  omit_special_char(event) {
    var k;
    k = event.charCode;  //         k = event.keyCode;  (Both can be used)
    return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || (k >= 48 && k <= 57));
  }
  /****************************************************************************/

  logout() {
    this.clearToken();
    localStorage.removeItem("accessToken")
    sessionStorage.removeItem("accessToken");
    this.router.navigate(["/login"]);
    this.commonService._authenticated = false;
    // this.SocialAuthService.signOut()
  }

  /****************************************************************************
  @PURPOSE      : To show validation message
  @PARAMETERS   : <field_name, errorObj?>
  @RETURN       : error message.
  ****************************************************************************/
  showError(field, errorObj?) {
    return this.errorMessage.getError(field, errorObj)
  }
  /****************************************************************************/



  getProfile() {

    var url = this.getToken('profilePic');
    if (url == null || url == ' ' || url == 'null') {
      return 'assets/images/no-image-user.png';
    } else {
      return url;
    }
  }



  /****************************************************************************
//For Date Format (YYYY-MM-DD)
/****************************************************************************/
  convertDateFormat(date: Date) {
    try {
      var day = date.getUTCDate() + 1;
      var month = date.getMonth() + 1;
      var year = date.getFullYear();
      var displayDay;
      var displayMonth;
      if (day < 10) {
        displayDay = '0' + '' + day.toString();
      }
      else {
        displayDay = day;
      }
      if (month < 10) {
        displayMonth = '0' + '' + month.toString();
      }
      else {
        displayMonth = month;
      }
      return year + '-' + displayMonth + '-' + displayDay;
    } catch (e) {
      return date;
    }
  }
  /****************************************************************************/


  /****************************************************************************
   @PURPOSE      : To Retrive Master Visa types
   @PARAMETERS   : type
   @RETURN       : NA
****************************************************************************/
  getNoOfRecordsPerPageInPagination() {
    return 10;
  }
  /****************************************************************************/




  /****************************************************************************
     @PURPOSE      :To Download XLS/CSV
     @PARAMETERS   : type
     @RETURN       : NA
  ****************************************************************************/
  isExportMode = false;
  exportOption = '';
  pageIndex = 1;
  noOfRecordsPerPageCopy = 0;
  exportToFile(option) {
    if (this.totalRecordCount != null && this.totalRecordCount > 0) {
      this.exportOption = option;
      this.pageIndex = this.pageNo;
      this.noOfRecordsPerPage = this.noOfRecordsPerPage;
      this.noOfRecordsPerPageCopy = this.noOfRecordsPerPage;
      this.checkAndProcessExport();
    }
  }
  checkAndProcessExport() {
    if ((this.totalRecordCount > this.noOfRecordsPerPage)) {
      this.noOfRecordsPerPage = this.totalRecordCount;
      this.noOfRecordsPerPage = this.noOfRecordsPerPage;
      this.isExportMode = true;
      setTimeout(() => {
        this.processExport();
      })

    } else {
      this.processExport();
    }
  }
  ignoreColumn: any = '@';
  sheetName: any = '@';
  footer: '@';
  processExport() {
    this.sheetName = this.fileName;
    let todayDate = new Date();
    let fileName = this.fileName + "_" + todayDate.getDate() + "_" + this.monthNames[todayDate.getMonth()] + "_" + todayDate.getFullYear() + "_" + todayDate.getHours() + "_" + todayDate.getMinutes();

    if (this.exportOption == 'csv') {
      $('#' + this.tableName).tableExport({ type: 'csv', escape: 'false', fileName: fileName, ignoreColumn: this.ignoreColumn, sheetName: this.sheetName, footer: this.footer });
    } else if (this.exportOption == 'excel') {
      $('#' + this.tableName).tableExport({ type: 'excel', escape: 'false', fileName: fileName, ignoreColumn: this.ignoreColumn, sheetName: this.sheetName, footer: this.footer });
    }
    //  else if (exportOption == 'pdf') {
    //   $('#' +this.tableName).tableExport({ type: 'pdf', pdfFontSize: '7', escape: 'false', fileName: fileName, ignoreColumn:this.ignoreColumn });
    // } else if (exportOption == 'png') {
    //   $('#' +this.tableName).tableExport({ type: 'png', escape: 'false', fileName: fileName, ignoreColumn:this.ignoreColumn });
    // } else if (exportOption == 'doc') {
    //   $('#' +this.tableName).tableExport({ type: 'doc', escape: 'false', fileName: fileName, ignoreColumn:this.ignoreColumn });
    // } else if (exportOption == 'txt') {
    //   $('#' +this.tableName).tableExport({ type: 'txt', escape: 'false', fileName: fileName, ignoreColumn:this.ignoreColumn });
    // } else if (exportOption == 'powerpoint') {
    //   $('#' +this.tableName).tableExport({ type: 'powerpoint', escape: 'false', fileName: fileName, ignoreColumn:this.ignoreColumn });
    // }
    this.pageNo = this.pageIndex;
    this.noOfRecordsPerPage = this.noOfRecordsPerPageCopy;

  }
  /****************************************************************************/

  /****************************************************************************
   @PURPOSE      : for search filters
   @PARAMETERS   : array object, search data
   @RETURN       : search results
  ****************************************************************************/

  searchFilter: any
  onSearch(event: any, filterObj: any) {
    let value = event.target.value;
    this.searchFilter = filterObj.filter(c => JSON.stringify(c).toLowerCase().includes(value.toLowerCase()))
    if (value == '') {
      this.searchFilter = null
    }
  }

  /**************************************************************************************************/



  /****************************************************************************
@PURPOSE      : To get List of Roles
@PARAMETERS   : NA
@RETURN       : Roles List
****************************************************************************/
  rolesList: any = []
  public roleDetails: any = [];
  getRoles() {
    this.commonService.callApi('mastercode/active/ROLE', '', 'get', false, false, 'REG').then(success => {
      this.rolesList = success
      this.rolesList = this.rolesList.masterCodeResultDTOs;

      this.roleDetails = this.rolesList.find(x => x.code == this.getToken('role'));

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /****************************************************************************/



  /****************************************************************************
  @PURPOSE      : To get List of Countries
  @PARAMETERS   : NA
  @RETURN       : Country List
  ****************************************************************************/
  countryList: any = []
  getCountrList() {
    this.commonService.callApi('mastercode/active/CNTRY', '', 'get', false, true, 'LOG').then(success => {
      this.countryList = success;
      this.countryList = this.countryList.masterCodeResultDTOs;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /****************************************************************************/

  /****************************************************************************
  @PURPOSE      : To get List of Nationalities
  @PARAMETERS   : NA
  @RETURN       : Nationality List
****************************************************************************/
  nationalityList: any = []
  getNationalityList() {
    this.commonService.callApi('mastercode/active/NTNLT', '', 'get', false, false, 'REG').then(success => {
      this.nationalityList = success;
      this.nationalityList = this.nationalityList.masterCodeResultDTOs;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /****************************************************************************/



  /****************************************************************************
   @PURPOSE      : To Retrive Country codes
   @PARAMETERS   : type
   @RETURN       : NA
  ****************************************************************************/
  public currencyDetails: any;
  getCurrency(code) {
    this.commonService.callApi('mastercode/active/' + code, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      this.currencyDetails = successData.masterCodeResultDTOs;

    }
    ).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });

  }
  /****************************************************************************/



  /****************************************************************************
   @PURPOSE      : To get List of SchoolType
   @PARAMETERS   : NA
   @RETURN       : School List
  ****************************************************************************/
  public schoolTypeList = [];
  getSchoolTypeList() {
    this.commonService.callApi('mastercode/active/STY', '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      this.schoolTypeList = successData.masterCodeResultDTOs;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });

  }
  /****************************************************************************/

  /****************************************************************************
  @PURPOSE      : To get List of SchoolType
  @PARAMETERS   : NA
  @RETURN       : School List
 ****************************************************************************/
  public statusList = [];
  getStatusList() {
    this.commonService.callApi('mastercode/active/STS', '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success;
      this.statusList = successData.masterCodeResultDTOs;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });

  }
  /****************************************************************************/



  /****************************************************************************
   @PURPOSE      : To get List of bank details
   @PARAMETERS   : NA
   @RETURN       : Bank List
  ****************************************************************************/
  public bankList = [];
  getBankName() {
    this.commonService.callApi('mastercode/active/BANK', '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      this.bankList = successData.masterCodeResultDTOs;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });

  }
  /****************************************************************************/

  /****************************************************************************
  @PURPOSE      : To get List of code type description
  @PARAMETERS   : NA
  @RETURN       : Master code type active list
 ****************************************************************************/
  public sportsDescription = [];
  getSportsDesc() {
    this.commonService.callApi('mastercodetype/active', '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success;
      this.sportsDescription = successData.masterCodeResultDTOs;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /****************************************************************************/

  /****************************************************************************
 @PURPOSE      : To get List of School Status
 @PARAMETERS   : NA
 @RETURN       : School Status List
****************************************************************************/
  public Schoolstatus = [];
  getSchoolStatus() {
    this.commonService.callApi('mastercode/active/STY', '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success;
      this.Schoolstatus = successData.masterCodeResultDTOs;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /****************************************************************************/


  /****************************************************************************
   @PURPOSE      : To get List of sports details
   @PARAMETERS   : NA
   @RETURN       : Sports List
  ****************************************************************************/
  public allActiveList = [];
  getAllMasterList() {
    this.commonService.callApi('mastercode/active', '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success;
      this.allActiveList = successData.masterCodeResultDTOs;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /****************************************************************************/

  /****************************************************************************
   @PURPOSE      : To get List of Organization Type Details
   @PARAMETERS   : NA
   @RETURN       : Organization List
  ****************************************************************************/
  public organizationList = [];
  getOrganizationType() {
    this.commonService.callApi('mastercode/active/ORGN', '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success;
      this.organizationList = successData.masterCodeResultDTOs;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /****************************************************************************/

  /****************************************************************************
   @PURPOSE      : To get List of Entity Type Details
   @PARAMETERS   : NA
   @RETURN       : Entity List
  ****************************************************************************/
  public entityList = [];
  getEntityType() {
    this.commonService.callApi('mastercode/active/ENTY', '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success;
      this.entityList = successData.masterCodeResultDTOs;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /****************************************************************************/

  hasPermission(entity) {
    if (this.entityPermission == null) {
      // this.getEntityPermission();
    }
    var entityDetails = entity.split('.');
    if (entityDetails.length == 2) {
      return this.hasEntityActionPermission(entityDetails[0], entityDetails[1]);
    }
    else {
      return this.hasEntityPermission(entityDetails[0]);
    }
  }


  hasEntityActionPermission(entity, action) {
    if (this.hasEntityPermission(entity)) {
      if (this.entityPermission[this.getIndexOfEntity(entity)].entityActions.indexOf(action) > -1) {
        return true
      }
      else {
        return false;
      }
    }
    else {
      return false;
    }
  }


  entityPermission = [];
  hasEntityPermission(entity) {

    if (this.getIndexOfEntity(entity) > -1) {
      return true;
    }
    else {
      return false;
    }
  }



  getIndexOfEntity(entity) {
    this.entityPermission = JSON.parse(this.getToken('permission'));
    for (var i = 0; i <= this.entityPermission.length - 1; i++) {
      if (this.entityPermission[i].entityName == entity) {
        return i;
      }
    }

    return -1;
  }

  checkAndSetSelectedConfigurationSettings(selectedConfigSettings) {
    if (this.getToken('configSettings') == null) {
      this.setConfigurationSettings(selectedConfigSettings);
    } else {
      this.setConfigurationSettings(this.getToken('configSettings'));
    }
  }

  setConfigurationSettings(selectedConfigSettings) {
    this.setToken('configSettings', selectedConfigSettings)
  }

  /****************************************************************************
    @PURPOSE      : To get List of district list 
    @PARAMETERS   : NA
    @RETURN       : Master code type active list
   ****************************************************************************/
  public DistrictList = [];
  getListofDistrict() {
    this.commonService.callApi('mastercode/active/DIST', '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success;
      this.DistrictList = successData.masterCodeResultDTOs;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /****************************************************************************/

  /**************************************************************************
        @PURPOSE      : Get School Info details 
        @PARAMETERS   : school name
        @RETURN       : NA
 ****************************************************************************/
  schoolInfo: any = {}
  requirementList: any = [];
  schoolListById(id) {
    this.getCountrList();
    this.getSchoolTypeList();
    this.commonService.callApi("schoolinfo/" + id, '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success
      this.schoolInfo = successData;
      if (this.schoolInfo.consolidateRefInfo) {
        // this.schoolInfo.consolidateRefInfo = this.schoolInfo.consolidateRefInfo.find(c => c.status != 'OPEREQ')
        this.requirementList = this.schoolInfo.consolidateRefInfo.requirementInfo
      }
      this.schoolInfo.schoolType = this.schoolTypeList.find(x => x.code == this.schoolInfo.schoolType)
      this.schoolInfo.country = this.countryList.find(x => x.code == this.schoolInfo.addressInfo.country)
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /***************************************************************************************/
}



