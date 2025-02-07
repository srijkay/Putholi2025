import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { DomSanitizer } from '@angular/platform-browser';


@Component({
  selector: 'app-school-list',
  templateUrl: './school-list.component.html',
  styleUrls: ['./school-list.component.css']
})
export class SchoolListComponent extends BaseComponent implements OnInit {

  modalRef: BsModalRef;
  public totalItem: any;
  pagesize: number = 10
  data: any = {
    pageNumber: 1,
    pageSize: this.pagesize
  }
  schoolId: any
  constructor(inj: Injector, private sanitizer: DomSanitizer) {
    super(inj)
    this.loadScript('assets/js/main.js');

    window.onscroll = function () { scrollFunction() };
    function scrollFunction() {

    }
  }
  ngOnInit(): void {
    this.getrequirementInfo(this.data)
    this.getListofDistrict()
    
  }



  /****************************************************************************/



  /****************************************************************************
      @PURPOSE      : To get School Images
      @PARAMETERS   : form,formdata
      @RETURN       : NA
    ***************************************************************************/
  public number: 0;
  public size: 0;
  public noofelements: 0;
  public totalElements: 0;
  public loading: boolean = false;

  schoolList: any = []
  getrequirementInfo(data) {
    this.schoolList = []
    data.status = "QUOARV"
    this.loading = true
    this.ngxLoader.start();
    this.commonService.callApi('schoolinfo/search', data, 'post', false, true, 'LOG').then(success => {
      let successData: any = success

      this.totalItem = successData.totalElements;
      this.number = successData.number;
      this.size = successData.size;
      this.loading = false;
      this.ngxLoader.stop();
      this.noofelements = successData.numberOfElements;
      this.totalElements = successData.totalElements;
      if (successData.content.length) {
        this.schoolList = successData.content
        this.schoolList.forEach(e => {
          this.getAttachments(e.schoolInfoId)
        });
      } else {
        this.schoolImages = []
      }
    }).catch(e => {
      this.ngxLoader.stop();
      this.toastr.errorToastr(e.message, 'Oops!');
    })
  }




  /****************************************************************************
     @PURPOSE      : To get all School Images
     @PARAMETERS   : schoolInfoId
     @RETURN       : ImagesDTO
   /***************************************************************************/
  schoolImages: any = []
  searchText: any;
  getAttachments(id) {
    this.commonService.callApi('attachments/school/' + id + '/SI', '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success
      for (let i = 0; i < successData.length; i++) {
        successData[i].fileData = 'data:image/png;base64,' + successData[i].fileData;
      }
      setTimeout(() => {
        this.schoolList.forEach(function (checkbox) {
          successData.forEach(e => {
            if (e.schoolInfoId == checkbox.schoolInfoId)
              checkbox.fileData = e.fileData
          });
        })
      }, 100)
      this.schoolImages = this.schoolList
      console.log(this.schoolImages);

      this.schoolImages[0].district = this.DistrictList.find(x => x.code == this.schoolImages[0].district)
    })
  }
  /**********************************************************************/
  /****************************************************************************
      @PURPOSE      : filters.
      @PARAMETERS   : NA
      @RETURN       : NA
  ****************************************************************************/
  public currentPage = 1;
  public isFilterApplied: boolean = false;
  public filterObj: any = {};

  applyFilter() {

    if (JSON.stringify(this.filterObj) == "{}") {
      this.toastr.errorToastr("Please select any value data to filter", 'Oops!');
      return 0;
    }

    this.isFilterApplied = true;
    this.currentPage = 1;
    this.filterObj.pageNumber = 1;
    this.filterObj.pageSize = this.pagesize;
    this.getrequirementInfo(this.filterObj);
  }

  resetFilter() {
    this.isFilterApplied = false;
    this.currentPage = 1;
    this.filterObj = {};
    this.getrequirementInfo(this.data);
  }
  /****************************************************************************/

  /****************************************************************************
        @PURPOSE      : To retrive the roles List
        @PARAMETERS   : pageNumber,PageSize,loggedinuser
        @RETURN       : NA
    ****************************************************************************/
  public showBoundaryLinks = true;
  public rangeList = [5, 10, 25, 100];

  pageChanged(e) {
    if (this.isFilterApplied) {
      this.filterObj.pageNumber = e.page;
      this.filterObj.pageSize = e.itemsPerPage;
      this.getrequirementInfo(this.filterObj);

    } else {
      this.data.pageNumber = e.page;
      this.data.pageSize = e.itemsPerPage;
      this.getrequirementInfo(this.data);
    }
  }
  rangeChanged(e) {
    if (this.isFilterApplied) {
      this.filterObj.pageSize = e;
      this.getrequirementInfo(this.filterObj);
    } else {
      this.data.pageSize = e;
      this.getrequirementInfo(this.data);
    }
  }


  /****************************************************************************
     @PURPOSE      : captcha validation
     @PARAMETERS   : NA
     @RETURN       : NA
    ****************************************************************************/
  isValidCaptcha: boolean
  getCaptcha(event) {
    console.log(event);
    this.isValidCaptcha = event
  }

  public loadScript(url: string) {
    const body = <HTMLDivElement>document.body;
    const script = document.createElement('script');
    script.innerHTML = '';
    script.src = url;
    script.async = false;
    script.defer = true;
    body.appendChild(script);
  }

}
