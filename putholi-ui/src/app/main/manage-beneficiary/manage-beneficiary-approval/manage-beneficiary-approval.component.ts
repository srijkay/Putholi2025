import { HttpClient } from '@angular/common/http';
import { Component, Injector, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-manage-beneficiary-approval',
  templateUrl: './manage-beneficiary-approval.component.html',
  styleUrls: ['./manage-beneficiary-approval.component.css']
})
export class ManageBeneficiaryApprovalComponent extends BaseComponent implements OnInit {

  username: any
  isClickedOnce: boolean = false
  constructor(inj: Injector, private sanitizer: DomSanitizer, public _http: HttpClient) {
    super(inj)
    this.callStatusApi();
    this.getStatusList();
    this.getRoles()
    this.getListofsports();
    this.getCountrList();
    this.getListofDistrict()
    this.activatedRoute.params.subscribe((params) => {
      console.log(params['username']);
      this.username = params['username']
      this.getBeneficiary(this.username)
    })
  }

  public submitted: boolean = false;
  public benApprovalData: any = {}
  public modalRef: BsModalRef;

  ipAddress: any;
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
  onPerson(personId) {
    this.modalRef = this.modalService.show(personId)
  }
  /*********************************************************************************/
  //Call this method in the image source, it will sanitize it.
  transform() {
    return this.sanitizer.bypassSecurityTrustResourceUrl(this.base64Image);
  }

  /****************************************************************************
    @PURPOSE      : to get beneficiary data
    @PARAMETERS   : username
    @RETURN       : BeneficiaryDTO
  ****************************************************************************/
  beneficiaryData: any = {}
  public approvalHistDtlsDTOs: any = [];
  base64Image: any
  country: any = {}
  getBeneficiary(username) {
    this.commonService.callApi('userapproval/' + username + '/Beneficiary', '', 'get', false, false, 'REG').then(success => {
      console.log(success);
      let successData: any = success
      this.beneficiaryData = success
      this.beneficiaryData = this.beneficiaryData.userRegisterDetails
      this.approvalHistDtlsDTOs = successData.approvalHistDtlsDTOs;
      setTimeout(() => {
        this.beneficiaryData.role = this.rolesList.find(x => x.code === this.beneficiaryData.role)
        this.beneficiaryData.district = this.DistrictList.find(x => x.code == this.beneficiaryData.district)
        let type = this.rolesList.filter(o1 => this.approvalHistDtlsDTOs.some(o2 => o1.code === o2.role))
        console.log(type);

        if(this.beneficiaryData.referredBy!=null){
          this.beneficiaryData.role.description="Reffered Volunteer"
        }

        this.approvalHistDtlsDTOs.forEach(function (checkbox) {
          type.forEach(e => {
            if (e.code == checkbox.role) {
              checkbox.role = e.description
            }
          });
        })
      }, 300)
      this.base64Image = 'data:image/png;base64,' + successData.pic;
      this.country = this.countryList.find(x => x.code == this.beneficiaryData.country)
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }
  /****************************************************************************/
  public status: any;
  setradio(event) {
    this.status = event
    this.setToken('status', event)
  }

  /****************************************************************************
   @PURPOSE      : to submit Approvals for beneficiary data
   @PARAMETERS   : Form data
   @RETURN       : NA
 ****************************************************************************/
  onBeneficiary(form, benApprovalData) {
    if (!this.isClickedOnce) {
      this.isClickedOnce = true
      // benApprovalData.status = this.status;
      console.log(benApprovalData.status);

      benApprovalData.username = this.username;
      benApprovalData.type = "Beneficiary";
      if (this.getToken('role') == 'ADMIN') {
        benApprovalData.actionBy = this.getToken('username');
        benApprovalData.role = 'ADMIN'
      } else if (this.getToken('role') == 'REVIEW') {
        benApprovalData.actionBy = this.getToken('username');
        benApprovalData.role = 'REVIEW'
      } else if (this.getToken('role') == 'APPRV') {
        benApprovalData.actionBy = this.getToken('username');
        benApprovalData.role = 'APPRV'
      }
      // benApprovalData.changeRequestRole = this.beneficiaryData.role.code
      this.commonService.callApi('userapproval', benApprovalData, 'post', false, false, 'REG').then(success => {
        let successData: any = success;
        if (successData.body.apiStatusCode === "SUCCESS") {
          this.router.navigate(["/main/manage-beneficiary"]);
          if (benApprovalData.status == 'APR') {
            this.toastr.successToastr(successData.body.apiStatusDesc, 'Success')
          } else {
            this.toastr.warningToastr("User Account Rejected Successfully", "Rejected")
          }

        } else {
          this.toastr.errorToastr(successData.body.apiStatusDesc, 'Error')
        }

      }).catch(e => {
        this.toastr.errorToastr(e.message, 'Oops!')
      })
    }
  }
  /****************************************************************************/


  sendMail(emailId) {
    this.commonService.callApi('authenticate/accountapproved/' + emailId + '/' + this.ipAddress.ip + '/' + this.beneficiaryData.userName, '', 'get', false, false, 'REG').then(success => {
      let successData = success
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    })
  }

}
