import { Component, EventEmitter, Injector, Input, OnInit, Output, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { BaseComponent } from 'src/app/common/commonComponent';

@Component({
  selector: 'app-school-details',
  templateUrl: './school-details.component.html',
  styleUrls: ['./school-details.component.css']
})
export class SchoolDetailsComponent extends BaseComponent implements OnInit {
  @ViewChild('school') school: ModalDirective;
  @Output() modalStatus = new EventEmitter<any>();
  @Input() schoolDetails: any = []

  constructor(inj: Injector) {
    super(inj);
  }


  data: any = {
    pageNumber: 1,
    pageSize: 10
  }
  ngOnInit(): void {

    console.log(this.schoolDetails);

    let uniqueArray = new Set();
    for (const obj of this.schoolDetails) {
      if (!uniqueArray.has(obj.projectId)) {
        uniqueArray.add(obj.projectId)
        this.data.consolidateId = obj.projectId
        this.getConsolidateInfo(this.data)
      }
    }

  }


  ngAfterViewInit() {
    this.school.show()
  }

  clickCancel() {
    this.modalStatus.emit(false)
  }


  /****************************************************************************
    @PURPOSE      : To get consolidate Info
    @PARAMETERS   : form,formdata
    @RETURN       : NA
 ***************************************************************************/
  getConsolidateInfo(data) {
    this.commonService.callApi('consolidate/search', data, 'post', false, true, 'LOG').then(success => {
      let successData: any = success;
      successData.content.forEach(c => {
        this.getSchoolList(c.schoolInfoId)
      });


    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!');
    })

  }
  /*******************************************************************************/

  /****************************************************************************
      @PURPOSE      : To get School Name
      @PARAMETERS   : form,formdata
      @RETURN       : NA
  ***************************************************************************/
  schoolInformation: any = []
  getSchoolList(id) {
    this.commonService.callApi('schoolinfo/' + id, '', 'get', false, true, 'LOG').then(success => {
      let successData: any = success
      this.schoolInformation.push(successData)
      console.log(this.schoolInformation);
    }).catch(e => {
      this.toastr.errorToastr(e.message, 'Oops!')
    });

  }
  /*****************************************************************************************/
}
