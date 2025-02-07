import { HttpClient } from '@angular/common/http';
import { Component, Injector, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { BaseComponent } from 'src/app/common/commonComponent';
declare var $: any;

@Component({
  selector: 'app-upload-deo-response',
  templateUrl: './upload-deo-response.component.html',
  styleUrls: ['./upload-deo-response.component.css']
})
export class UploadDeoResponseComponent extends BaseComponent implements OnInit {
  public responseData: any = {}
  modalRef: any;
  consolidateNo: any
  constructor(inj: Injector, private sanitizer: DomSanitizer, public _http: HttpClient) {
    super(inj)
    this.callStatusApi();
    this.getListofsports()
    this.getRoles()
    this.activatedRoute.params.subscribe((params) => {

      this.schoolId = params['id']
      let createdBy = params['id2']
      this.schoolListById(this.schoolId)
      this.getBeneficiaryData(createdBy)
      setTimeout(() => {
        this.consolidateApprovalHisrory(this.schoolInfo.consolidateRefInfo.consolidateId)
      }, 1000)

    });
  }
  uploadProof: any;
  schoolId: any;
  consolidateId: any
  schoolInfoList: any = {}
  ipAddress: any;
  username: any
  ngOnInit(): void {
    this._http.get<{ ip: string }>('https://jsonip.com')
      .subscribe(data => {
        this.ipAddress = data
      })

  }
  /****************************************************************************
   @PURPOSE      : open popup for showing the images
   @PARAMETERS   : form,formdata
   @RETURN       : NA
****************************************************************************/
  onPerson(personId, id) {
    this.showQuotationImages(id)
    this.modalRef = this.modalService.show(personId)
  }
  /*********************************************************************************/
  /****************************************************************************
       @PURPOSE      :To show the image of DEO response
       @PARAMETERS   : SchoolId
       @RETURN       : AttachmentDTO
     ****************************************************************************/
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
  showQuotationImages(id) {
    this.commonService.callApi('attachments/' + id + '/DR', '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;

      // Determine the file type based on the file extension
      const filename = successData[successData.length - 1].fileName;
      const fileExtension = filename.substr(filename.lastIndexOf('.') + 1).toLowerCase();

      if (fileExtension === 'png' || fileExtension === 'jpg' || fileExtension === 'jpeg' || fileExtension === 'gif') {
        this.base64Image = 'data:image/' + fileExtension + ';base64,' + successData[successData.length - 1].fileData;
        this.base64PDF = null
      } else if (fileExtension === 'pdf') {
        this.base64Image = null
        this.base64PDF = 'data:application/pdf;base64,' + successData[successData.length - 1].fileData;
      }
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /**********************************************************************************************/

  /****************************************************************************
     @PURPOSE      :To Approve / Reject School Details
     @PARAMETERS   : Formdata
     @RETURN       : NA
   ****************************************************************************/

  onSchoolForm(manageSchoolData) {
    manageSchoolData.type = "DEO Approval"
    manageSchoolData.schoolInfoId = this.schoolId;
    manageSchoolData.consolidateId = this.schoolInfo.consolidateRefInfo.consolidateId

    manageSchoolData.actionBy = this.getToken('username');
    manageSchoolData.role = this.getToken('role')

    this.commonService.callApi('schoolinfo/DEO/approval', manageSchoolData, 'post', false, false, 'REG').then(success => {
      let successData: any = success
      console.log(successData);
      if (successData.body.apiStatusCode === "SUCCESS") {
        this.router.navigate(["/main/pending-workflow"]);
        if (manageSchoolData.status == 'APR') {
          this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
        } else {
          this.toastr.warningToastr("Deo Response Rejected Successfully", "Rejected")
        }
      } else {
        this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
      }

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })

  }
  /***************************************************************************************/

  /****************************************************************************/

  /****************************************************************************
     @PURPOSE      : to submit uploaded file details
     @PARAMETERS   : form,formdata
     @RETURN       : NA
  ****************************************************************************/
  deoPreImages: any = {}
  uploadImage: any
  schoolInfoId: any
  uploadDeoImage(id) {
    const sendData = new FormData();
    this.deoPreImages.schoolInfoId = this.schoolId;
    console.log(this.deoPreImages);

    this.deoPreImages.requirementId = this.schoolInfo.consolidateRefInfo.consolidateId
    let data = JSON.stringify(this.deoPreImages)
    sendData.append('QuotationAttachmentsDTO', data)
    sendData.append('quotation', this.uploadImage[0])
    sendData.append('fileType', 'DR')
    console.log(data, sendData);

    this.commonService.callApi('attachments', sendData, 'post', true, false, 'REG').then(res => {
      let successData: any = res
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /****************************************************************************/
  isClickedOnce: boolean = false

  onSubmit(form, responseData) {
    console.log(responseData);
    if (form.valid) {
      if (!this.isClickedOnce) {
        this.isClickedOnce = true
        if (this.getToken('role') == 'ADMIN') {
          this.uploadDeoImage(this.schoolInfoId);
        }
        this.onSchoolForm(responseData);
      } else {
        console.log("invalid");

      }
    }
  }


  /****************************************************************************
     @PURPOSE      : to approval history
     @PARAMETERS   : consolidateId
     @RETURN       : NA
   ****************************************************************************/

  approvalHistDtlsDTOs: any = []
  consolidateApprovalHisrory(id) {

    this.commonService.callApi('consolidate/approval/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      console.log(successData);
      this.approvalHistDtlsDTOs = successData.schoolApprovalHistoryDetails;

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /***************************************************************************/

  /****************************************************************************
     @PURPOSE      : Retriving beneficiary Details
     @PARAMETERS   : username
     @RETURN       : NA
  ****************************************************************************/

  public profileData: any = {};
  getBeneficiaryData(username) {
    setTimeout(() => {
      this.commonService.callApi('authenticate/' + username, '', 'get', false, false, 'REG').then(success => {
        let successData: any = success;
        this.profileData = successData;
        this.profileData.fullName = this.profileData.firstName + " " + this.profileData.lastName
        console.log(this.profileData);
      }).catch(e => {
        this.toastr.errorToastr(e.message, 'Oops!')
      });
    }, 100)
  }
  /********************************************************************************/
}



