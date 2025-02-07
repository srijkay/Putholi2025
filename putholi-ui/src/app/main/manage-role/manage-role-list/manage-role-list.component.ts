import { Component, Injector, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-manage-role-list',
  templateUrl: './manage-role-list.component.html',
  styleUrls: ['./manage-role-list.component.css']
})
export class ManageRoleListComponent extends BaseComponent implements OnInit {
  public totalItem: any;
  pagesize: number = 10
  data: any = {
    pageNumber: 1,
    pageSize: this.pagesize
  }

  public statusList = [
    {
      "name": "Active",
      "code": "Y"
    },
    {
      "name": "Inactive",
      "code": "N"
    },
  ]

  constructor(inj: Injector ) {
    super(inj)
  }

  public modalRef: BsModalRef;

  options: any = {
    backdrop: 'static',
    keyboard: false
  };

  ngOnInit(): void {
    this.getRolesList(this.data);
  }


  /****************************************************************************
       @PURPOSE      : Search Role details
       @PARAMETERS   : pageno. pagesize
       @RETURN       : RoleDetailsDto
  ****************************************************************************/

  rolesList: any = []
  public number: 0;
  public size: 0;
  public noofelements: 0;
  public totalElements: 0;
  public loading: boolean = false;
  getRolesList(data) {
    this.loading = true
    this.ngxLoader.start()
    setTimeout(() => {
      this.commonService.callApi('role/search', data, 'post', false, false, 'REG').then(success => {
        console.log(success);
        let successData: any = success
        console.log(successData.content);

        this.totalItem = successData.body.totalElements;
        this.number = successData.body.number;
        this.size = successData.body.size;
        this.loading = false;
        this.ngxLoader.stop();
        this.noofelements = successData.body.numberOfElements;
        this.totalElements = successData.body.totalElements;
        if (successData.body.content.length) {
          this.rolesList = successData.body.content;
          console.log(this.rolesList);

        } else {
          this.rolesList = []
        }
      }).catch(e => {
        this.ngxLoader.stop()
        this.toastr.errorToastr(e.message, 'Oops!')
      });
    }, 1000)
  }

  /*********************************************************************************/


  /****************************************************************************
       @PURPOSE      : To Open Modal
       @PARAMETERS   : form,formdata
       @RETURN       : NA
  ****************************************************************************/

  onRoleDes(roleDelete) {
    this.modalRef = this.modalService.show(roleDelete, this.options)
    console.log(this.modalRef);

  }
  /*****************************************************************************/


  /****************************************************************************
      @PURPOSE      : filters.
      @PARAMETERS   : NA
      @RETURN       : NA
  ****************************************************************************/

  applyFilter() {
    if (JSON.stringify(this.filterObj) == "{}") {
      if (localStorage.getItem('Language') === 'en') {
        this.toastr.errorToastr("Please Select Any Value  Data to Filter", 'Oops!');
        return 0;
      } else {
        this.toastr.errorToastr("Veuillez filtrer par categorie", 'Oops!');
        return 0;
      }

    }
    this.isFilterApplied = true;
    this.currentPage = 1;
    this.filterObj.pageNumber = 1;
    this.filterObj.pageSize = this.pagesize;
    this.getRolesList(this.filterObj);
  }

  resetFilter() {
    this.isFilterApplied = false;
    this.currentPage = 1;
    this.filterObj = {};
    this.getRolesList(this.data);
  }
  /****************************************************************************/

  /****************************************************************************
      @PURPOSE      : To retrive the roles List
      @PARAMETERS   : pageNumber,PageSize,loggedinuser
      @RETURN       : NA
   ****************************************************************************/
  public currentPage = 1;
  public showBoundaryLinks = false;
  public rangeList = [5, 10, 25, 100];
  public isFilterApplied: boolean = false;
  public filterObj: any = {};

  pageChanged(e) {
    if (this.isFilterApplied) {
      this.filterObj.pageNumber = e.page;
      this.filterObj.pageSize = e.itemsPerPage;
      this.getRolesList(this.filterObj);
    } else {
      this.data.pageNumber = e.page;
      this.data.pageSize = e.itemsPerPage;
      this.getRolesList(this.data);
    }
  }
  rangeChanged(e) {
    if (this.isFilterApplied) {
      this.filterObj.pageSize = e;
      this.getRolesList(this.filterObj);
    } else {
      this.data.pageSize = e;
      this.getRolesList(this.data);
    }
  }
  /****************************************************************************/


}
