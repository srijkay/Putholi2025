import { Component, Injector, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { BaseComponent } from 'src/app/common/commonComponent';
import jsPDF from 'jspdf';

@Component({
  selector: 'app-track-details',
  templateUrl: './track-details.component.html',
  styleUrls: ['./track-details.component.css']
})
export class TrackDetailsComponent extends BaseComponent implements OnInit {

  public submitted: boolean = false;
  public trackDonationData: any = {}
  modalRef: BsModalRef;
  schoolDetails: any = {}
  consolidateRefInfo: any = []
  requirementData: any = []
  schoolData: any = []

  data: any = {
    pageNumber: 1,
    pageSize: 10
  }
  consolidateRefInfoId: any
  constructor(inj: Injector, private sanitizer: DomSanitizer) {
    super(inj)
    this.getAdjustableAmount()
    this.getSchoolStatus()
    this.getStatusList()
    this.getAllMasterList()
    this.getSportsDesc()
    this.getListofDistrict()
    this.activatedRoute.params.subscribe((params) => {
      this.consolidateRefInfoId = params['id']
    })
    this.getConsolidateInfo(this.data)
    this.getFundDetails(this.consolidateRefInfoId)
    this.getSchoolInfo(this.data)

  }

  consolidateList: any = []
  quotationDetails: any = []

  ngOnInit(): void {
    this.loadScript('assets/js/main.js');
  }



  consolidateDetails: any

  getConsolidateInfo(data) {
    delete data.status
    data.consolidateId = this.consolidateRefInfoId
    this.commonService.callApi('consolidate/search', data, 'post', false, true, 'LOG').then(success => {
      let successData: any = success;
      setTimeout(() => {
        this.consolidateDetails = successData.content
        this.consolidateDetails.forEach(e => {
          this.getSchoolList(e.schoolInfoId)

        });
      }, 100)
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!');
    })

  }

  receiptImage: any = []

  /****************************************************************************
      @PURPOSE      : To get School Name
      @PARAMETERS   : form,formdata
      @RETURN       : NA
   ***************************************************************************/
  requirementDetails: any
  consolidateInformation: any
  requirementInfo: any
  invoiceInfo: any
  estimatedAmount = 0
  invoiceDetails: any = []
  getSchoolList(id) {
    this.commonService.callApi('schoolinfo/' + id, '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success
      this.schoolDetails = successData

      this.schoolDetails.schoolType = this.Schoolstatus.find(x => x.code == this.schoolDetails.schoolType)
      this.schoolDetails.addressInfo.country = this.allActiveList.find(x => x.code == this.schoolDetails.addressInfo.country)
      this.schoolDetails.addressInfo.district = this.DistrictList.find(x => x.code == this.schoolDetails.addressInfo.district)
      this.schoolDetails.schoolStatus = this.statusList.find(x => x.code == this.schoolDetails.schoolStatus)
      this.schoolDetails.educationalDistrict = this.DistrictList.find(x => x.code == this.schoolDetails.educationalDistrict)

      this.consolidateInformation = this.schoolDetails.consolidateRefInfo

      this.consolidateInformation.forEach(element => {
        if (this.consolidateRefInfoId == element.consolidateId) {
          this.requirementInfo = element.requirementInfo.filter(x => x.reqStatus != "APR" && x.reqStatus != "ADMQUO" && x.reqStatus != 'REVQUO' && x.reqStatus != 'APRQUO' && x.reqStatus != 'REJQUO' && x.reqStatus != 'VOLACP' && x.active == "Y")
          this.getDashboardStatistics(element.consolidateId)

        }
      });

      let require = this.allActiveList.filter(o1 => this.requirementInfo.some(o2 => o1.code === o2.assetName))
      this.requirementInfo.forEach(function (checkbox) {
        require.forEach(e => {
          if (e.code == checkbox.assetName)
            checkbox.assetName = e.description
        });
      })

      let type = this.sportsDescription.filter(o1 => this.requirementInfo.some(o2 => o1.code === o2.assetType))
      this.requirementInfo.forEach(function (checkbox) {
        type.forEach(e => {
          if (e.code == checkbox.assetType)
            checkbox.assetType = e.description
        });
      })

      let list = this.allActiveList.filter(o1 => this.requirementInfo.some(o2 => o1.code === o2.requirementType))
      this.requirementInfo.forEach(function (checkbox) {
        list.forEach(e => {
          if (e.code == checkbox.requirementType)
            checkbox.requirementType = e.description
        });
      })

      let status = this.allActiveList.filter(o1 => this.requirementInfo.some(o2 => o1.code === o2.reqStatus))
      this.requirementInfo.forEach(function (checkbox) {
        status.forEach(e => {
          if (e.code == checkbox.reqStatus)
            checkbox.reqStatus = e.description
        });
      })

      setTimeout(() => {
        this.requirementInfo.forEach(e => {
          this.getAttachments(e.requirementId)
          this.getPostAttachments(e.requirementId)
        })
      }, 100)

      this.requirementInfo.forEach(e => {

        this.invoiceDetails = e.invoiceDetails
        for (let i = 0; i < e.invoiceDetails.length; i++) {
          this.getReceiptInfo(e.invoiceDetails[i].invoiceId)
        }
      });

      this.requirementInfo.forEach(e => {

        let sum = 0;
        let quotData: any = e.quotationInfo.filter(x => x.quotateStatus == 'QUOARV')
        for (let i = 0; i < quotData.length; i++) {
          sum = quotData[i].totalAmount;
        }
        this.estimatedAmount += sum
      });
      // this.getDashboardStatistics(this.consolidateInformation.consolidateId)

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });


  }



  /****************************************************************************
     @PURPOSE      : to get Announcement info based on announcementId
     @PARAMETERS   : announcementId
     @RETURN       : NA
****************************************************************************/
  fundRaisedDetails: any
  getFundDetails(id) {
    this.commonService.callApi('projectbook/' + id, '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success;
      this.fundRaisedDetails = successData.filter(f => f.remarks != "School Expense")
      this.getDonorDetails()
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /*****************************************************************************************/
  donorDetails: any = []
  getDonorDetails() {
    this.commonService.callApi('donorauthenticate', '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success;
      if (successData) {
        this.donorDetails = successData

        this.donorDetails.forEach(d => {
          this.fundRaisedDetails.forEach(e => {
            if (e.donorId == d.emailId) {
              e.name = d.firstName;
              e.organizationType = d.organizationType
            }
            if (e.donorId === 'srijay@yahoo.com') {
              e.name = 'Jagankumar'
            }

          });

        });
      }

      console.log(this.fundRaisedDetails);


    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }

  /****************************************************************************
      @PURPOSE      : open popup for showing the receipt images
      @PARAMETERS   : receiptImages,id
      @RETURN       : NA
   ****************************************************************************/
  invoice: any
  onReceiptTemplate(receiptImages, id) {
    setTimeout(() => {
      this.requirementInfo.forEach(e => {
        this.invoiceInfo = e.invoiceDetails[0]
        this.getReceiptInfo(this.invoiceInfo.invoiceId)

      });

    }, 100)

    this.modalRef = this.modalService.show(receiptImages, id)
  }
  /*********************************************************************************/
  base64Image: any
  transform() {
    if (this.base64Image) {
      return this.sanitizer.bypassSecurityTrustResourceUrl(this.base64Image);
    } else if (this.base64PDF) {
      return this.sanitizer.bypassSecurityTrustResourceUrl(this.base64PDF);
    }
  }
  /**************************************************************************
     @PURPOSE      : get List of receipt images by invoice id
     @PARAMETERS   : invoice ID, upload for
     @RETURN       : attachmentDTO
  ****************************************************************************/
  receiptList: any = {}
  base64PDF: any
  fileExtension: string;
  getReceiptInfo(id) {
    this.commonService.callApi('attachments/invoice/' + id + '/UR', '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success


      if (successData.length) {

        const filename = successData[0].fileName;
        this.fileExtension = filename.substr(filename.lastIndexOf('.') + 1).toLowerCase();


        if (this.fileExtension === 'png' || this.fileExtension === 'jpg' || this.fileExtension === 'jpeg') {
          this.base64Image = 'data:image/png;base64,' + successData[0].fileData;
          successData[0].fileData = 'data:image/png;base64,' + successData[0].fileData;
          this.base64PDF = null
          this.receiptList = successData[0]

        } else if (this.fileExtension === 'pdf') {
          this.base64Image = null
          this.base64PDF = 'data:application/pdf;base64,' + successData[0].fileData;
          successData[0].fileData = 'data:application/pdf;base64,' + successData[0].fileData;
          this.receiptList = successData[0]
        }
      }

      let data: any = []
      data.push(this.receiptList)
      for (let i = 0; i <= this.requirementInfo.length; i++) {
        data.forEach(d => {
          this.requirementInfo[i]?.invoiceDetails.forEach(e => {
            if (d.invoiceId == e.invoiceId) {
              e.receiptImage = d
              console.log(e);

            }
          });
        });
      }



    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });
  }

  /******************************************************************************/
  /****************************************************************************
    @PURPOSE      : To download receipt image
    @PARAMETERS   : NA
    @RETURN       : NA
  ****************************************************************************/

  download(id) {
    this.getReceiptInfo(id)
    setTimeout(() => {
      console.log(this.fileExtension);

      const src = this.receiptList.fileData;
      const link = document.createElement('a');
      link.href = src;
      link.download = 'Receipt_Image.' + this.fileExtension;
      link.click();
      link.remove()
    }, 200)

  }

  /***************************************************************/
  /****************************************************************************
    @PURPOSE      : To get all pre-work Images
    @PARAMETERS   : requirementId
    @RETURN       : ImagesDTO
  /***************************************************************************/
  requirementImages: any = []
  getAttachments(id) {
    this.commonService.callApi('attachments/' + id + '/PI', '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success
      for (let i = 0; i < successData.length; i++) {
        successData[i].fileData = 'data:image/png;base64,' + successData[i].fileData;
        this.requirementImages.push(successData[i])
      }
    })
  }
  /***************************************************************************/
  /****************************************************************************
   @PURPOSE      : To get all post-work Images
   @PARAMETERS   : requirementId
   @RETURN       : ImagesDTO
 /***************************************************************************/
  requirementPostImages: any = []
  getPostAttachments(id) {
    this.commonService.callApi('attachments/' + id + '/PO', '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success
      for (let i = 0; i < successData.length; i++) {
        successData[i].fileData = 'data:image/png;base64,' + successData[i].fileData;
        this.requirementPostImages.push(successData[i])
      }
    })
  }
  /***************************************************************************/



  dashboardStatistics: any = {}
  getDashboardStatistics(id) {
    this.commonService.callApi('dashboard/' + id, '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success;
      this.dashboardStatistics = successData;
      Math.round(Number(this.estimatedAmount = this.estimatedAmount - this.dashboardStatistics.collectedAmount) * 100)
    }).catch(e => {
      this.toastr.errorToastr(e.message);
    })
  }

  /****************************************************************************
      @PURPOSE      : To get School Status from schoolInfo search
      @PARAMETERS   : data
      @RETURN       : School Status
    ***************************************************************************/
  schoolInfo: any = []
  getSchoolInfo(data) {
    this.commonService.callApi('schoolinfo/search', data, 'post', false, true, 'LOG').then(success => {
      let successData: any = success
      this.schoolInfo = successData.content
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!');
    })
  }
  /*****************************************************************************/

  adjustableAmount: any = {}
  getAdjustableAmount() {
    this.commonService.callApi('config/Adjustable_Amount', '', 'get', false, true, 'LOG').then(success => {
      this.adjustableAmount = success;

    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
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
