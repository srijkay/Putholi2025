import { Component, Injector, OnInit } from '@angular/core';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { BaseComponent } from 'src/app/common/commonComponent';
import { ImageCroppedEvent } from 'ngx-image-cropper';
declare var $: any;
@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent extends BaseComponent implements OnInit {
  public isEdit: boolean = false
  public profileData: any = {}

  bsConfig: Partial<BsDatepickerConfig>;
  imageChangedEvent: any = '';
  maxDate = new Date();

  colorTheme = 'theme-blue'
  datePickerConfig = {
    dateInputFormat: 'DD-MM-YYYY',
    containerClass: this.colorTheme,
    maxDate: new Date()
  }

  dateValidationConfig = {
    dateInputFormat: 'DD-MM-YYYY',
    containerClass: this.colorTheme,
    maxDate: new Date()

  }

  public genderDetails = [
    {
      'code': 'M',
      'name': "Male"
    },
    {
      'code': 'F',
      'name': "Female"
    }
  ]


  constructor(inj: Injector) {
    super(inj)
    this.getCountrList();
    this.getRoles();
    this.getListofsports()
    this.getListofDistrict()
  }

  ngOnInit(): void {
    this.getProfileData(this.getToken('username'));
  }



  /****************************************************************************
      @PURPOSE      : Retriving Profile data
      @PARAMETERS   : username
      @RETURN       : NA
  ****************************************************************************/


  public user: any = {};
  getProfileData(username) {
    this.ngxLoader.start();

    setTimeout(() => {
      this.commonService.callApi('authenticate/' + username, '', 'get', false, false, 'REG').then(success => {
        let successData: any = success;
        this.profileData = successData;
        this.profileData.fullName = this.profileData.firstName + " " + this.profileData.lastName
        if (this.profileData.pic != '') {
          this.user.photo = 'data:image/png;base64,' + successData.pic;
        }
        if (this.profileData.referredBy != null) {
          this.roleDetails.description = "Reffered Volunteer"
        }

        this.ngxLoader.stop();
      }).catch(e => {
        this.ngxLoader.stop();
        this.toastr.errorToastr(e.message, 'Oops!')
      });
    })

  }
  /********************************************************************************/


  /****************************************************************************
      @PURPOSE      : update profile details
      @PARAMETERS   : form,formdata
      @RETURN       : NA
   ****************************************************************************/

  submitForm(form, profileData) {

    if (form.valid) {
      this.profileData.updatedBy = this.getToken('username');
      profileData.userName = this.getToken('username')
      profileData.emailId = profileData.emailId.toLowerCase()
      profileData.country = "IND"
      profileData.state = 'TN'
      this.commonService.callApi('authenticate/updateuser', profileData, 'put', false, false, 'REG').then(success => {
        let successData: any = success;
        this.isEdit = false
        if (successData.apiStatusCode === "SUCCESS") {
          this.toastr.successToastr(successData.apiStatusDesc, 'Success')       
        } else {
          this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
        }

      }).catch(e => {
        this.toastr.errorToastr(e.message, 'Oops!')
      });
    }
  }
  /**************************************************************/


  /****************************************************************************
      @PURPOSE      : open model for choose picture
      @PARAMETERS   : form,formdata
      @RETURN       : NA
   ****************************************************************************/

  openfile(event: any) {
    event.preventDefault();
    let element: HTMLElement = document.getElementById('profile') as HTMLElement;
    element.click();
  }
  /********************************************************************/

  onEdit() {
    this.profileData.state = "Tamil Nadu"
    this.profileData.district = this.profileData.district.code
    this.isEdit = true;
  }
  onCancel() {
    this.isEdit = false;
    this.getProfileData(this.getToken('username'));
  }

  clearFile() {
    $('#profile').prop("value", "")
  }


  validateFile(name: String) {
    var ext = name.substring(name.lastIndexOf('.') + 1);
    if ((ext.toLowerCase() == 'jpg') || (ext.toLowerCase() == 'png') || (ext.toLowerCase() == 'jpeg')) {
      return true;
    }
    else {
      return false;
    }
  }


  modalRef: BsModalRef;
  fileChangeEvent(event, template) {
    this.imageChangedEvent = event;
    if (!this.validateFile(event.target.files[0].name)) {
      if (localStorage.getItem('Language') === 'en') {
        this.toastr.errorToastr("Selected file format is not supported", "Error");
        return 0;
      } else {
        this.toastr.errorToastr("Selected file format is not supported-tm", "Error");
        return 0;
      }
    }

    if (event.target.files[0].size > 1000000) {
      if (localStorage.getItem('Language') === 'en') {
        this.toastr.errorToastr("Selected Image is too large to upload!!!", "Error")
      } else {
        this.toastr.errorToastr("Selected Image is too large to upload-tm!!!", "Error");
        return 0;
      }
    } else {
      this.modalRef = this.modalService.show(template);
    }

  }

  showCropper = false;
  imageLoaded() {
    this.showCropper = true;
  }

  croppedImage: any = "";
  isExceed: boolean = false;
  imageCropped(event: ImageCroppedEvent) {

    this.croppedImage = event.base64;
    this.file = this.base64ToFile(event.base64);
    if (this.file.size > 1000000) {
      this.isExceed = true;
    } else {
      this.isExceed = false;
    }

  }

  public file: any;
  base64ToFile(base64Image: string): Blob {

    const split = base64Image.split(',');
    const type = split[0].replace('data:', '').replace(';base64', '');
    const byteString = atob(split[1]);
    const ab = new ArrayBuffer(byteString.length);
    const ia = new Uint8Array(ab);
    for (let i = 0; i < byteString.length; i += 1) {
      ia[i] = byteString.charCodeAt(i);
    }
    return new Blob([ab], { type });
  }



  submitcropped() {
    const sendData = new FormData();
    sendData.append('profilepic', this.file);
    sendData.append('username', this.getToken('username'))
    this.ngxLoader.start();
    this.commonService.callApi('attachment/profilepic', sendData, 'post', true, false, 'REG').then(success => {
      let successData: any = success;
      if (successData.body.apiStatusCode === 'SUCCESS') {
        this.toastr.successToastr(successData.body.apiStatusDesc, "Success");
        this.user.photo = 'data:image/png;base64,' + successData.body.refNo;
        this.setToken('profilePic', successData.body.refNo);
        this.modalRef.hide();
        this.ngxLoader.stop();

      } else {
        this.toastr.errorToastr(successData.body.apiStatusDesc, "Error")
        this.ngxLoader.stop();
      }
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
      this.ngxLoader.stop();
    });

  }


  /****************************************************************************
      @PURPOSE      : remove Profile picture
      @PARAMETERS   : NA
      @RETURN       : NA
   ****************************************************************************/
  removePhoto() {
    this.commonService.callApi('attachment/profilepic/' + this.getToken('username'), '', 'delete', false, false, 'RAI').then(success => {
      let successData: any = success;
      if (successData.body.apiStatusCode === 'SUCCESS') {
        this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
        this.user.photo = '';
        this.setToken('profilePic', "");
      }
    }).catch(e => {
      this.toastr.errorToastr(e.error.apiStatusDesc, 'Oops!')
    });
  }
  /************************************************************************************/

}
