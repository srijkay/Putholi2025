import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';
import jsPDF from 'jspdf';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-assigned-school-view',
  templateUrl: './assigned-school-view.component.html',
  styleUrls: ['./assigned-school-view.component.css']
})
export class AssignedSchoolViewComponent extends BaseComponent implements OnInit {
  public assignSchoolData: any = {};
  schoolId: any
  constructor(inj: Injector, private sanitizer: DomSanitizer) {
    super(inj)
    this.callStatusApi();
    setTimeout(() => {
      this.getListofsports()
      this.getSportsDesc()
      this.getStatusList()
      this.getRoles()
    }, 300)

    this.activatedRoute.params.subscribe((params) => {
      this.schoolId = params['id']
      this.onSchoolView(this.schoolId, params['id2'])
      this.schoolListById(this.schoolId)

      this.showQuotationImages(params['id2'])
    });
  }

  ngOnInit(): void {

  }

  /****************************************************************************
      @PURPOSE      : to get school info based on schoolId
      @PARAMETERS   : schoolId
      @RETURN       : NA
    ****************************************************************************/
  public successData: any = {}
  public addressInfo: any = {}
  public contactsInfo: any = {}
  public approvalHistDtlsDTOs: any = []
  onSchoolView(schoolId, id) {
    this.commonService.callApi('schoolinfo/approval/' + schoolId + '/Volunteer Approval', '', 'get', false, false, 'REG').then(success => {
      let data: any = success;

      this.approvalHistDtlsDTOs = data.schoolApprovalHistoryDetails.filter(x => x.consolidateId == id);

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /*****************************************************************************************/

  /****************************************************************************
      @PURPOSE      : To submit the volunteer approval
      @PARAMETERS   : form,formdata
      @RETURN       : NA
   ****************************************************************************/
  consolidateData: any = {}
  isClickedOnce: boolean = false
  assignSchool(form, assignSchoolData) {
    if (form.valid) {
      if (!this.isClickedOnce) {
        this.isClickedOnce = true

        assignSchoolData.schoolInfoId = this.schoolInfo.schoolInfoId
        assignSchoolData.consolidateId = this.schoolInfo.consolidateRefInfo.consolidateId
        assignSchoolData.type = 'Volunteer Approval'

        assignSchoolData.actionBy = this.getToken('username');
        assignSchoolData.role = this.getToken('role')

        console.log(assignSchoolData);

        this.commonService.callApi('schoolinfo/volunteer/approval', assignSchoolData, 'post', false, false, 'REG').then(success => {
          let successData: any = success
          if (successData.body.apiStatusCode === "SUCCESS") {
            this.router.navigate(["/main/assign-school"]);
            if (assignSchoolData.status == 'APR') {
              this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
            } else {
              this.toastr.warningToastr("Assigned Volunteer Details rejected Successfully", "Rejected")
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

  /****************************************************************************
    @PURPOSE      : To convert image to pdf
    @PARAMETERS   : NA
    @RETURN       : NA
  ****************************************************************************/

  // getBase64Image(img) {
  //   var canvas = document.createElement('canvas');
  //   canvas.classList.add('myStyle');
  //   canvas.width = 20000;
  //   canvas.height = 200;
  //   var ctx = canvas.getContext('2d');
  //   ctx.drawImage(img, 0, 0);
  //   var dataURL = canvas.toDataURL('image/png');
  //   return { img: dataURL, width: canvas.width, height: canvas.height };
  // }

  download() {
    const src = this.base64Image || this.base64PDF;
    if (src) {
      const link = document.createElement('a');
      link.href = src;
      link.download = 'Deo Image'; 
      link.click();
      link.remove();
    } 
  }
  

  /***************************************************************/
  /****************************************************************************
        @PURPOSE      :To show the image of DEO response
        @PARAMETERS   : SchoolId
        @RETURN       : AttachmentDTO
  ****************************************************************************/
  imageList: any = {}

  //Call this method in the image source, it will sanitize it.
  base64Image: any;
  base64PDF: any;

  showQuotationImages(id) {
    this.commonService.callApi('attachments/' + id + '/DR', '', 'get', false, false, 'REG').then(success => {
      let successData: any = success
      successData = successData[successData.length - 1]
      this.imageList = successData

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
    })
  }
  /**********************************************************************************************/

}
