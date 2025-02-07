import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-announcement-view',
  templateUrl: './announcement-view.component.html',
  styleUrls: ['./announcement-view.component.css']
})
export class AnnouncementViewComponent extends BaseComponent implements OnInit {

  public announcementList: any = {};

  constructor(inj: Injector) {
    super(inj)
    this.activatedRoute.params.subscribe((params) => {
      let announceId: any = params['id']
      this.getAnnounceId(announceId);
    })
  }

  ngOnInit(): void {
  }

  /****************************************************************************
     @PURPOSE      : to get Announcement info based on announcementId
     @PARAMETERS   : announcementId
     @RETURN       : NA
   ****************************************************************************/
  getAnnounceId(id) {
    this.commonService.callApi('announcement/' + id, '', 'get', false, false, 'REG').then(success => {
      this.announcementList = success;
      console.log(this.announcementList);
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /*****************************************************************************************/
}