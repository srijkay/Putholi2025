import { Component, Injector, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-delete-school-view',
  templateUrl: './delete-school-view.component.html',
  styleUrls: ['./delete-school-view.component.css']
})
export class DeleteSchoolViewComponent extends BaseComponent implements OnInit {
  schoolId: any
  constructor(inj: Injector, private sanitizer: DomSanitizer) {
    super(inj)
    this.callStatusApi();
    this.getListofsports()
    this.getCountrList();
    this.getListofsports()
    this.getListofDistrict()
    this.getSchoolTypeList()
    this.getListofInfrastructure()
    this.activatedRoute.params.subscribe((params) => {
      this.schoolId = params['id']
      this.onSchoolView(this.schoolId)
      this.getSchoolImagesInfo(this.schoolId);
    })
  }
  public modalRef: BsModalRef;
  public submitted: boolean = false;
  public manageSchoolData: any = {}

  ngOnInit(): void {
  }



  /****************************************************************************
      @PURPOSE      : open popup for showing the images
      @PARAMETERS   : form,formdata
      @RETURN       : NA
   ****************************************************************************/
  onSchoolTemplate(schoolImages, id) {
    this.schoolImagesByAttachmentId(id)
    this.modalRef = this.modalService.show(schoolImages)
  }
  /*********************************************************************************/

  /****************************************************************************
     @PURPOSE      : to get school info based on schoolId
     @PARAMETERS   : schoolId
     @RETURN       : NA
   ****************************************************************************/
  public successData: any = {}
  public addressInfo: any = {}
  public contactsInfo: any = {}
  public approvalHistDtlsDTOs: any = []
  onSchoolView(schoolId) {
    this.commonService.callApi('schoolinfo/approval/' + schoolId + '/School Deletion', '', 'get', false, false, 'REG').then(success => {
      let data: any = success;
      this.successData = data.schoolInfo
      this.successData.schoolType = this.schoolTypeList.find(x => x.code == this.successData.schoolType)
      this.successData.educationalDistrict = this.DistrictList.find(x => x.code == this.successData.educationalDistrict)

      this.addressInfo = this.successData.addressInfo
      this.addressInfo.district = this.DistrictList.find(x => x.code == this.addressInfo.district)

      this.contactsInfo = this.successData.contactsInfo
      this.approvalHistDtlsDTOs = data.schoolApprovalHistoryDetails;
      let type = this.infrastructreList.filter(o1 => this.approvalHistDtlsDTOs.some(o2 => o1.code === o2.role))
      console.log(type);

      this.approvalHistDtlsDTOs.forEach(function (checkbox) {
        type.forEach(e => {
          if (e.code == checkbox.role)
            checkbox.role = e.description
        });
      })
      this.addressInfo.country = this.countryList.find(x => x.code == this.addressInfo.country)
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /*****************************************************************************************/

  /****************************************************************************
     @PURPOSE      :To Approve / Reject School Details
     @PARAMETERS   : Formdata
     @RETURN       : NA
   ****************************************************************************/
  isClickedOnce: boolean = false;
  onSchoolForm(form, manageSchoolData) {
    if (form.valid) {
      if (!this.isClickedOnce) {
        this.isClickedOnce = true
        manageSchoolData.type = "School Deletion"
        manageSchoolData.schoolInfoId = this.schoolId;
        if (this.getToken('role') == 'ADMIN') {
          manageSchoolData.actionBy = this.getToken('username');
          manageSchoolData.role = 'ADMIN'
        } else if (this.getToken('role') == 'REVIEW') {
          manageSchoolData.actionBy = this.getToken('username');
          manageSchoolData.role = 'REVIEW'
        } else if (this.getToken('role') == 'APPRV') {
          manageSchoolData.actionBy = this.getToken('username');
          manageSchoolData.role = 'APPRV'
        }
        this.commonService.callApi('schoolinfo/deletion', manageSchoolData, 'post', false, false, 'REG').then(success => {
          let successData: any = success
          console.log(successData);
          if (successData.body.apiStatusCode === "SUCCESS") {
            this.router.navigate(["/main/delete-school"]);
            if (manageSchoolData.status == 'APR') {
              this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
            } else {
              this.toastr.warningToastr("School Details Rejected Successfully", "Rejected")
            }
          } else {
            this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
          }

        }).catch(e => {
          this.toastr.errorToastr(e.message, 'Oops!')
        })
      }
    }
  }
  /**************************************************************************/

  /**************************************************************************
             @PURPOSE      : get List of school-images by school id
             @PARAMETERS   : school ID, upload for
             @RETURN       : AttachmentsDTO
      ****************************************************************************/
  imagesList: any = []
  public loading: boolean = true;
  getSchoolImagesInfo(id) {
    this.commonService.callApi('attachments/school/' + id + '/SI', '', 'get', false, false, 'REG').then(success => {
      this.imagesList = success
      this.loading = false
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /******************************************************************************/
  base64Image: any
  transform() {
    return this.sanitizer.bypassSecurityTrustResourceUrl(this.base64Image);
  }
  /**************************************************************************
        @PURPOSE      : get pre-images by attachment id
        @PARAMETERS   : attachment ID
        @RETURN       : pre-images
 ****************************************************************************/
  schoolImagesByAttachmentId(id) {
    this.commonService.callApi('attachments/attachment/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success
      this.base64Image = 'data:image/png;base64,' + successData.fileData;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /***********************************************************************/
}
