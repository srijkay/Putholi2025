import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-announcement-edit',
  templateUrl: './announcement-edit.component.html',
  styleUrls: ['./announcement-edit.component.css']
})
export class AnnouncementEditComponent extends BaseComponent implements OnInit {

  public announcementData: any = {};
  userId: any
  isNew: any = false

  colorTheme = 'theme-blue';
  effectiveValue = {
    dateInputFormat: 'DD-MM-YYYY',
    containerClass: this.colorTheme,
    maxDate: new Date(),
  }

  expiryValue = {
    dateInputFormat: 'DD-MM-YYYY',
    containerClass: this.colorTheme,
    maxDate: new Date(),
  }

  constructor(inj: Injector) {
    super(inj)
  }

  ngOnInit(): void {
    this.getRoles();
    this.activatedRoute.params.subscribe((params) => {
      if (params['id'] === 'New') {
        this.isNew = true;
      } else {
        this.userId = params['id'];
        this.isNew = false;
        this.getAnnounceId(this.userId);
      }
    })
  }

  /****************************************************************************
    @PURPOSE      : To submit announcement details
    @PARAMETERS   : form,formdata
    @RETURN       : NA
 ****************************************************************************/
  onAnnouncement(form, announcementData) {
    if (this.isNew) {
      if (form.valid) {
        this.announcementData.createdBy = "Admin";
        this.commonService.callApi("announcement", announcementData, 'post', false, false, 'REG').then(success => {
          let successData: any = success;
          if (successData.body.apiStatusCode === "SUCCESS") {
            this.router.navigate(["/main/announcement/announcement-list"]);
            this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
          } else {
            this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
          }
        }).catch(e => {
          this.toastr.errorToastr(e.message, 'Oops!')
        })

      }
    } else {
      this.announcementData.lastModifiedBy = "admin";
      this.commonService.callApi("announcement", announcementData, 'put', false, false, 'REG').then(success => {
        let successData: any = success;
        if (successData.body.apiStatusCode === "SUCCESS") {
          this.router.navigate(["/main/announcement/announcement-list"]);
          this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
        } else {
          this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
        }
      }).catch(e => {
        this.toastr.errorToastr(e.message, 'Oops!')
      })

    }
  }
  /****************************************************************************
      @PURPOSE      : to get Announcement info based on announcementId
      @PARAMETERS   : announcementId
      @RETURN       : NA
 ****************************************************************************/
  getAnnounceId(id) {
    this.commonService.callApi('announcement/' + id, '', 'get', false, false, 'REG').then(success => {
      this.announcementData = success;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /*****************************************************************************************/
}