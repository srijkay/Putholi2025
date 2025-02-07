import { Component, Injector, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { BaseComponent } from 'src/app/common/commonComponent';


@Component({
  selector: 'app-manage-school-view',
  templateUrl: './manage-school-view.component.html',
  styleUrls: ['./manage-school-view.component.css']
})
export class ManageSchoolViewComponent extends BaseComponent implements OnInit {

  schoolId: any
  isClickedOnce: boolean = false
  constructor(inj: Injector, private sanitizer: DomSanitizer) {
    super(inj)
    this.callStatusApi();
    setTimeout(() => {
      this.getListofsports()
      this.getCountrList();
      this.getListofsports();
      this.getRoles()
      this.getListofDistrict()
      this.getSchoolTypeList();
    }, 500)
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
    setTimeout(() => {
      this.commonService.callApi('schoolinfo/approval/' + schoolId + '/School Approval', '', 'get', false, false, 'REG').then(success => {
        let data: any = success;
        this.successData = data.schoolInfo
        this.addressInfo = this.successData.addressInfo
        this.contactsInfo = this.successData.contactsInfo
        this.approvalHistDtlsDTOs = data.schoolApprovalHistoryDetails;
      }).catch(e => {
        this.toastr.errorToastr(e.message, 'Oops!')
      })
    }, 500)
  }
  /*****************************************************************************************/



  /****************************************************************************
     @PURPOSE      :To Approve / Reject School Details
     @PARAMETERS   : Formdata
     @RETURN       : NA
   ****************************************************************************/

  onSchoolForm(form, manageSchoolData) {
    if (form.valid) {
      if (!this.isClickedOnce) {
        this.isClickedOnce = true
        manageSchoolData.type = "School Approval"
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
        this.commonService.callApi('schoolinfo/approval', manageSchoolData, 'post', false, false, 'REG').then(success => {
          let successData: any = success

          if (successData.body.apiStatusCode === "SUCCESS") {
            this.router.navigate(["/main/manage-school"]);
            if (manageSchoolData.status == 'APR') {
              setTimeout(() => {
                this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
              }, 1000)
            } else {
              setTimeout(() => {
                this.toastr.warningToastr("School Details Rejected Successfully", "Rejected")
              }, 1000)
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
  /***************************************************************************************/

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
  //Call this method in the image source, it will sanitize it.
  base64Image: any;
  base64PDF: any;
  transform(): SafeResourceUrl {
    if (this.base64Image) {
      return this.sanitizer.bypassSecurityTrustResourceUrl(this.base64Image);
    } else if (this.base64PDF) {
      return this.sanitizer.bypassSecurityTrustResourceUrl(this.base64PDF);
    }
  }
  /**************************************************************************
        @PURPOSE      : get pre-images by attachment id
        @PARAMETERS   : attachment ID
        @RETURN       : pre-images
  ****************************************************************************/
  schoolImagesByAttachmentId(id) {
    this.commonService.callApi('attachments/attachment/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success

      // Determine the file type based on the file extension
      const filename = successData.fileName;
      const fileExtension = filename.substr(filename.lastIndexOf('.') + 1).toLowerCase();

      if (fileExtension === 'png' || fileExtension === 'jpg' || fileExtension === 'jpeg' || fileExtension === 'gif') {
        this.base64Image = 'data:image/' + fileExtension + ';base64,' + successData.fileData;
        this.base64PDF = null
      } else if (fileExtension === 'pdf') {
        this.base64Image = null
        this.base64PDF = 'data:application/pdf;base64,' + successData.fileData;
      }
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /***********************************************************************/
}
