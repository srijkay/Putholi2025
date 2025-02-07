import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-email-edit',
  templateUrl: './email-edit.component.html',
  styleUrls: ['./email-edit.component.css']
})
export class EmailEditComponent extends BaseComponent implements OnInit {

  constructor(inj: Injector) {
    super(inj)
  }

  public submitted: boolean = false;
  public emailSettingData: any = {};

  userId: any
  isNew: any = false

  ngOnInit(): void {
    this.emailSettingData = {}
    this.activatedRoute.params.subscribe((params) => {
      console.log(params['id']);

      if (params['id'] === 'New') {
        this.isNew = true;
      } else {
        this.userId = params['id'];
        this.isNew = false

      
      }
    })
  }

  /****************************************************************************
    @PURPOSE      : To submit email settings details
    @PARAMETERS   : form,formdata
    @RETURN       : NA
 ****************************************************************************/

  onEmail(form, emailSettingData) {
    console.log("form.valid", emailSettingData);

    if (form.valid) {
      console.log("email settings data is valid")


    } else {
      this.submitted = true;
      console.log("email settings data is invalid")
    }
  }

  password: any = "password";
  show = false;

  onClick(type) {
    if (type === 'secret') {
      if (this.password === 'password') {
        this.password = 'text';
        this.show = true;
      } else {
        this.password = 'password';
        this.show = false;
      }
    }
  }



  //Announce Role List
  public protocolList = [
    {
      "name": "Ssl",
      "code": "Ssl"
    },
    {
      "name": "Start tls",
      "code": "Start tls"
    },
  ]

  public statusList = [
    {
      "name": "Active",
      "code": "Active"
    },
    {
      "name": "In active",
      "code": "In active"
    },
  ]
}
