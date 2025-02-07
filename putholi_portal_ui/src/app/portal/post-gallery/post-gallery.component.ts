import { Component, Injector, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-post-gallery',
  templateUrl: './post-gallery.component.html',
  styleUrls: ['./post-gallery.component.css']
})
export class PostGalleryComponent extends BaseComponent implements OnInit {

  modalRef: BsModalRef;
  schoolDetails: any = {}

  constructor(inj: Injector) {
    super(inj)
  }
  ngOnInit(): void {
    this.schoolDetails = JSON.parse(this.getToken('schoolInformation'))
    this.onRequirement()

  }
  /**************************************************************************
   @PURPOSE      : Iterate requirementInfo from schoolDetails
   @PARAMETERS   : NA
   @RETURN       : requirementInfo
  ****************************************************************************/
  requirementInfo: any = []
  onRequirement() {
    this.schoolDetails.consolidateRefInfo.forEach(e => {
      this.requirementInfo = e.requirementInfo.filter(x => x.reqStatus == 'REDALL')
    });
    this.requirementInfo.forEach(e => {
      this.getAttachments(e.requirementId)
    });
    console.log(this.requirementInfo);
  }
  /****************************************************************************/
  /****************************************************************************
    @PURPOSE      : To get all post-work Images
    @PARAMETERS   : requirementId
    @RETURN       : ImagesDTO
  /***************************************************************************/
  requirementImages: any = []
  getAttachments(id) {
    this.commonService.callApi('attachments/' + id + '/RI', '', 'get', false, false, 'REG').then(success => {
      let successData: any = success
      for (let i = 0; i < successData.length; i++) {
        successData[i].fileData = 'data:image/png;base64,' + successData[i].fileData;
      }
      setTimeout(() => {
        console.log(this.requirementInfo);
        this.requirementInfo.forEach(function (checkbox) {
          successData.forEach(e => {
            if (e.requirementId == checkbox.requirementId)
              checkbox.fileData = e.fileData
          });
        })
      }, 100)
      this.requirementImages = this.requirementInfo;
    })
  }
  /***************************************************************************/

  /****************************************************************************
       @PURPOSE      : open popup for showing the images
       @PARAMETERS   : form,formdata
       @RETURN       : NA
  ****************************************************************************/
  onSchoolTemplate(schoolImages) {
    this.modalRef = schoolImages;

    schoolImages.show();
  }
  /*********************************************************************************/

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

  /******************************************************************/
}
