import { Component, Injector, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { BaseComponent } from '../common/commonComponent';
declare var $: any
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent extends BaseComponent implements OnInit {

  public totalItem: any;
  pagesize: number = 10
  data: any = {
    pageNumber: 1,
    pageSize: this.pagesize
  }
  public submitted: boolean = false;
  public newsLetterData: any = {}
  modalRef: BsModalRef;
  public contactData: any = {}
  requirement: any = []

  constructor(inj: Injector) {
    super(inj)
    this.clearToken() 
  }

  ngOnInit(): void {
    this.loadScript('assets/js/main.js');
    window.onscroll = function () { scrollFunction() };
    function scrollFunction() {
      if (document.body.scrollTop > 100 || document.documentElement.scrollTop > 100) {
        document.getElementById("myDiv").style.display = "Block";
      } else {
        document.getElementById("myDiv").style.display = "none";
      }
      if (document.body.scrollTop > 100 || document.documentElement.scrollTop > 100) {
        document.getElementById("myHome").style.display = "Block";
      } else {
        document.getElementById("myHome").style.display = "Block";
      }
    }
    this.getSchoolImages()
    this.getSchoolInfo(this.data)
    this.getPostAttachments()
  }

  /****************************************************************************
   @PURPOSE      : To get all post-work Images
   @PARAMETERS   : requirementId
   @RETURN       : ImagesDTO
 /***************************************************************************/
  requirementPostImages: any = []
  getPostAttachments() {
    this.commonService.callApi('attachments/fectchbysixmonths' + '/PO', '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success
      for (let i = 0; i < successData.length; i++) {
        successData[i].fileData = 'data:image/jpeg;base64,' + successData[i].fileData

        this.requirementPostImages.push(successData[i])
      }
      this.getRequirements(this.requirementPostImages)
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!');
    })
  }
  /***************************************************************************/
  getRequirements(images) {
    this.commonService.callApi('requirement/allrequirements', '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success
      console.log(images, successData);

      images.forEach(element => {
        successData.forEach(f => {
          if (element.requirementId == f.requirementId) {
            element.schoolName = f.schoolName
            element.schoolInfoId = f.schoolInfoId
          }
        })
      });

      this.requirementPostImages = images


    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!');
    })
  }

  /****************************************************************************
    @PURPOSE      : To get all School Images
    @PARAMETERS   : form,formdata
    @RETURN       : NA
  ***************************************************************************/
  schoolImages: any = []
  getSchoolImages() {
    this.commonService.callApi('attachments/fectchby/SI', '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success
      for (let i = 0; i < successData.length; i++) {
        successData[i].fileData = 'data:image/png;base64,' + successData[i].fileData;
      }
      this.schoolImages = successData
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!');
    })
  }
  /*****************************************************************************/

  /****************************************************************************
      @PURPOSE      : To get School Images
      @PARAMETERS   : form,formdata
      @RETURN       : NA
  ***************************************************************************/
  schoolInfo: any = []
  getSchoolInfo(data) {
    this.commonService.callApi('schoolinfo/search', data, 'post', false, true, 'LOG').then(success => {
      let successData: any = success
      this.schoolInfo = successData.content
      console.log(this.schoolInfo);
      this.schoolInfo.forEach(element => {
        this.getAttachments(element.schoolInfoId)
      });

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!');
    })
  }
  /*****************************************************************************/
  getAttachments(id) {
    this.commonService.callApi('attachments/school/' + id + '/SI', '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success
      // console.log(successData);

    })
  }

  /****************************************************************************
    @PURPOSE      : To submit and details to emailID
    @PARAMETERS   : form,formdata
    @RETURN       : NA
  ***************************************************************************/
  onNews(form, newsLetterData) {
    console.log("form.valid", newsLetterData);
    if (form.valid) {
      console.log("news letter data is valid")
    } else {
      this.submitted = true;
      console.log("news letter data is invalid")
    }
  }
  /****************************************************************************
    @PURPOSE      : To submit the contact details
    @PARAMETERS   : form,formdata
    @RETURN       : NA
 ****************************************************************************/
  onContact(form, contactData) {
    console.log("form.valid", contactData);
    if (form.valid && this.isValidCaptcha) {
      this.commonService.callApi('contactus', contactData, 'post', false, true, 'LOG').then(success => {
        let successData: any = success;
        if (successData.apiStatusCode === "SUCCESS") {
          this.toastr.successToastr(successData.apiStatusDesc, 'Success')
          this.contactData = {}
          this.submitted = false
        } else {
          this.toastr.errorToastr(successData.apiStatusDesc, 'Error')
        }
      }).catch(e => {
        this.toastr.errorToastr(e.message, 'Oops!')
      })
    } else {
      this.submitted = true
    }
  }

  /****************************************************************************
     @PURPOSE      : open popup for showing the images
     @PARAMETERS   : form,formdata
     @RETURN       : NA
  ****************************************************************************/
  onHomeTemplate(homeImages) {
    this.modalRef = homeImages;
    homeImages.show();
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

  public loadScript(url: string) {

    const body = <HTMLDivElement>document.body;
    const script = document.createElement('script');
    script.innerHTML = '';
    script.src = url;
    script.async = false;
    script.defer = true;
    body.appendChild(script);
  }
}