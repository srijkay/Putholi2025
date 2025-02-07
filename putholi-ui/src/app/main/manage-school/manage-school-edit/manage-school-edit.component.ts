import { Component, Injector, OnInit, ViewChild } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { TabsetComponent } from 'ngx-bootstrap/tabs';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-manage-school-edit',
  templateUrl: './manage-school-edit.component.html',
  styleUrls: ['./manage-school-edit.component.css']
})
export class ManageSchoolEditComponent extends BaseComponent implements OnInit {
  @ViewChild('staticTabs', { static: true }) staticTabs: TabsetComponent;

  isRequirement: boolean = false
  reqEditId: any
  public submitted: boolean = false;
  public schoolEditData: any = {};
  isNew: any = false

  allowedExtensions = ['.png', '.jpg', '.jpeg']

  options: any = {
    backdrop: 'static',
    keyboard: false
  };

  constructor(inj: Injector, private sanitizer: DomSanitizer) {
    super(inj)
    this.callStatusApi();
    this.getListofDistrict()
    this.activatedRoute.params.subscribe((params) => {
      if (params['id'] === 'New') {
        if (this.getToken('schoolInfoId') == null && this.getToken('schoolInfoId') == undefined) {
          this.isNew = true;

        } else {
          this.schoolInfoId = this.getToken('schoolInfoId');
          this.isNew = false
          this.onSchoolView(this.schoolInfoId)
          this.getPreImagesInfo(this.schoolInfoId)
        }
      } else {
        console.log(params['id']);

        this.schoolInfoId = params['id']
        this.onSchoolView(this.schoolInfoId)
        this.getPreImagesInfo(this.schoolInfoId)
        this.isNew = false
      }
    })
  }

  ngOnInit(): void {
    this.staticTabs.tabs[1].disabled = true;

    this.addressInfo.country = 'IND'
    this.addressInfo.state = "Tamil Nadu"
    this.getSchoolTypeList();
    this.getCountrList();
  }

  quotateModelRef: BsModalRef
  clickPreImages(template, type, id) {
    if (type == 'VIEW') {
      this.quotationImagesByschoolId(id);
    }
    this.quotateModelRef = this.modalService.show(template, this.options)
  }

  addModelRef: BsModalRef
  isAddImage: boolean = false
  attachmentId: any
  isClickedOnce = false
  addSchoolImages(template, type, id) {
    this.isClickedOnce = false
    this.uploadImage = {}
    if (type == 'EDIT') {
      this.attachmentId = id
      this.quotationImagesByschoolId(id)
      this.isAddImage = false
    } else if (type == 'ADD') {
      this.isAddImage = true
    }
    this.addModelRef = this.modalService.show(template, this.options)
  }


  onClickPrevoius() {
    // this.onSchoolView(this.schoolInfoId)
    this.staticTabs.tabs[0].disabled = false
    this.staticTabs.tabs[0].active = true


  }




  /****************************************************************************
    @PURPOSE      : to submit school information
    @PARAMETERS   : form,formdata
    @RETURN       : NA
 ****************************************************************************/
  public contactInfo: any = {};
  public addressInfo: any = {};
  onSchool(form, schoolEditData) {

    console.log(this.contactInfo);

    if (this.contactInfo.primaryEmail != null && this.contactInfo.primaryEmail != undefined && this.contactInfo.primaryEmail != "") {
      this.testEmailValidation(this.contactInfo.primaryEmail);
      if (this.Valid) {
        if (this.contactInfo.secondaryEmail != null && this.contactInfo.secondaryEmail != undefined && this.contactInfo.secondaryEmail != "") {
          this.testEmailValidation(this.contactInfo.secondaryEmail);
        }
      }
    }
    if (this.Valid == true) {
      if (this.staticTabs.tabs[0].active && this.getToken('schoolInfoId')) {
        this.isNew = false
        // this.getConsolidateDetails(this.consolidateId ? this.consolidateId : this.getToken('consolidateId'))
      }
      this.schoolEditData = schoolEditData
      this.staticTabs.tabs[1].disabled = false;
      this.staticTabs.tabs[1].active = true;
      this.staticTabs.tabs[0].disabled = true;
      if (!this.isNew) {
        this.getPreImagesInfo(this.schoolEditData.schoolInfoId);
      }

    } else {
      var error = "Invalid email Id"
      if (this.invalidEmails.length > 0) {
        this.toastr.errorToastr(error + " " + this.invalidEmails, 'Error');
      }
    }

  }
  /****************************************************************************/
  /****************************************************************************
      @PURPOSE      : to get school info based on schoolId
      @PARAMETERS   : schoolId
      @RETURN       : NA
  ****************************************************************************/
  onSchoolView(schoolId) {
    this.commonService.callApi('schoolinfo/' + schoolId, '', 'get', false, false, 'REG').then(success => {
      this.schoolEditData = success;
      this.addressInfo = this.schoolEditData.addressInfo
      this.contactInfo = this.schoolEditData.contactsInfo;
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /*************************************************************************/

  active: any = "N"
  saveSchoolImages(schoolEditData) {
    let apiMethod: any
    console.log(this.uploadImage);

    if (!this.isClickedOnce && this.uploadImage != undefined) {
      this.isClickedOnce = true



      if (this.preImages.length) {
        this.isNew = false
        this.onSchoolView(this.schoolInfoId ? this.schoolInfoId : this.getToken("schoolInfoId"))
      }
      this.contactInfo.secondaryEmail = this.contactInfo.secondaryEmail.toLowerCase().trim();
      this.contactInfo.primaryEmail = this.contactInfo.primaryEmail.toLowerCase().trim()

      if (this.isNew) {
        schoolEditData.active = "N"
        this.addressInfo.state = 'TN'
      
        this.schoolEditData.createdBy = this.getToken('username')
        this.schoolEditData.schoolStatus = "PENADM"
        this.addressInfo.createdBy = this.getToken('username')
        this.contactInfo.createdBy = this.getToken('username')
        apiMethod = "post"
        schoolEditData = {
          ...schoolEditData,
          'addressInfoDTO': this.addressInfo,
          'contactsInfoDTO': this.contactInfo
        }

      } else {
        if (schoolEditData.comments) {
          this.active = "Y"
        }
        schoolEditData = this.schoolEditData
        this.contactInfo.lastModifiedBy = this.getToken('username');
        this.addressInfo.lastModifiedBy = this.getToken('username');
        schoolEditData.lastModifiedBy = this.getToken('username')
        schoolEditData.active = this.active
        schoolEditData.schoolInfoId = this.schoolInfoId ? this.schoolInfoId : this.getToken('schoolInfoId')
        schoolEditData.schoolStatus = "PENADM"
        schoolEditData.addressInfoDTO = this.addressInfo
        schoolEditData.contactsInfoDTO = this.contactInfo
        delete schoolEditData.addressInfo
        delete schoolEditData.contactsInfo
        delete schoolEditData.consolidateRefInfo
        apiMethod = "put"
      }
      setTimeout(() => {


        this.commonService.callApi('schoolinfo', schoolEditData, apiMethod, false, false, 'REG').then(sucess => {
          let successData: any = sucess
          if (successData.body.apiStatusCode === "SUCCESS") {
            this.setToken("schoolInfoId", successData.body.id)

            if (!this.isGoToHome) {
              this.uploadPreImages(successData.body.id)
            }

          } else {
            this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
          }
        }).catch(e => {
          this.toastr.errorToastr(e.message, 'Oops!')
        });

      })
    }

  }
  /**************************************************************************
           @PURPOSE      : ADD/UPDATE images by SchoolInfoId
           @PARAMETERS   : School ID, image
           @RETURN       : NA
    *************************************************************************/
  schoolPreImages: any = {}
  uploadImage: any = {};
  fileType: any
  schoolInfoId: any
  uploadPreImages(id) {
    if (this.uploadImage[0] != undefined && this.uploadImage[0].type) {
      let apiMethod: any
      const sendData = new FormData();

      this.schoolPreImages.schoolInfoId = id;

      if (this.isAddImage) {
        apiMethod = 'post'
      } else {
        this.schoolPreImages.attachmentId = this.attachmentId;
        apiMethod = 'put'
      }
      let data = JSON.stringify(this.schoolPreImages);
      sendData.append('QuotationAttachmentsDTO', data);
      sendData.append('fileType ', 'SI')
      sendData.append('quotation', this.uploadImage[0])

      this.commonService.callApi('attachments', sendData, apiMethod, true, false, 'REG').then(success => {
        let successData: any = success;
        if (successData.body.apiStatusCode == "SUCCESS") {

          this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
          this.getPreImagesInfo(this.schoolInfoId ? this.schoolInfoId : this.getToken("schoolInfoId"))
          this.onSchoolView(this.schoolInfoId ? this.schoolInfoId : this.getToken("schoolInfoId"))
          if (this.addModelRef) {
            this.addModelRef.hide()
          }
        } else {
          this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
        }
      }).catch(e => {
        this.toastr.errorToastr(e.message, 'Oops!')
      });
    } else {
      this.getPreImagesInfo(this.schoolInfoId ? this.schoolInfoId : this.getToken("schoolInfoId"));
      if (this.addModelRef) {
        this.addModelRef.hide()
      }
    }

  }
  /**************************************************************************/
  /**************************************************************************
          @PURPOSE      : To get List of images by school id
          @PARAMETERS   : school ID, upload for
          @RETURN       : UploadQuotationDTO
   **************************************************************************/
  preImages: any = []
  public loading: boolean = true;
  getPreImagesInfo(id) {
    this.commonService.callApi('attachments/school/' + id + '/SI', '', 'get', false, false, 'REG').then(success => {
      let successData: any = success
      this.preImages = successData
      this.loading = false
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /**************************************************************************/
  /**************************************************************************
           @PURPOSE      : To view uploaded school images by attachmentId
           @PARAMETERS   : attachment ID
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
  quotationImagesByschoolId(id) {
    this.commonService.callApi('attachments/attachment/' + id, '', 'get', false, false, 'REG').then(success => {
      let successData: any = success;
      if (successData != null) {
        this.uploadImage = [
          { name: successData.fileName, size: successData.fileSize }
        ];

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
      }
    }).catch(e => {
      // Handle API call errors
      this.toastr.errorToastr(e.message, 'Oops!');
    });
  }


  /***************************************************************************/

  /**************************************************************************
        @PURPOSE      : delete uploaded images by schoolInfoId
        @PARAMETERS   : schoolInfoId
        @RETURN       : NA
 ****************************************************************************/
  deletePreImages(id) {

    this.commonService.callApi('attachments/' + id, '', 'delete', false, false, 'REG').then(success => {
      let successData: any = success;
      if (successData.body.apiStatusCode == "SUCCESS") {
        this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
        this.getPreImagesInfo(this.schoolInfoId ? this.schoolInfoId : this.getToken('schoolInfoId'));
        this.isNew = false
      } else {
        this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
      }
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }
  /*********************************************************************************/
  isGoToHome = false
  isClickedSubmitted: boolean = false
  gotoHome() {
    if (!this.isClickedSubmitted) {
      this.isClickedSubmitted = true
      if (this.preImages.length) {
        this.active = 'Y'
        this.isGoToHome = true
        this.isClickedOnce = false
        this.saveSchoolImages(this.schoolEditData);
        this.router.navigate(['/main/manage-school/manage-school-list']);
      } else {
        this.toastr.errorToastr("Please Upload the school image")
      }
    }
    setTimeout(() => {
      this.isClickedSubmitted = false
    }, 2000)
  }
  /******************************************************************************/

  invalidEmails: any = [];
  Valid = true
  validateEmail(email) {
    var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
    return re.test(email);
  }

  testEmailValidation(email) {
    var emails = email.split(',');
    this.invalidEmails = [];
    this.Valid = true;

    for (var i = 0; i < emails.length; i++) {
      if (!this.validateEmail(emails[i].trim())) {
        if (emails[i] == "") {
          this.toastr.errorToastr("Email ID cannot be empty", 'Error');
          this.Valid = false;
          break;
        }
        else {
          this.invalidEmails.push(emails[i].trim());
        }
      }
    }
    if (this.invalidEmails.length > 0) {
      this.Valid = false;
    }

  }


}









