import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgSelectModule } from '@ng-select/ng-select';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CanLoginActivate, CanAuthActivate } from './common/auth.gaurd';

import { TooltipModule } from 'ngx-bootstrap/tooltip';
import { FileSelectModule } from '@progress/kendo-angular-upload';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { NgxUiLoaderModule } from "ngx-ui-loader";
import { ImageCropperModule } from 'ngx-image-cropper';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { EnvServiceProvider } from './common/env.service.provider';
import { NgPopupsModule } from 'ng-popups';
import { DatePipe, DecimalPipe, PathLocationStrategy } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { ModalModule } from 'ngx-bootstrap/modal';
import { TabsModule } from 'ngx-bootstrap/tabs';
import { ToastrModule } from 'ng6-toastr-notifications';
import { PaginationModule } from 'ngx-bootstrap/pagination';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { NgxSpinnerModule } from "ngx-spinner";
// import { CaptchaService } from './common/captcha/captcha.service';

// components
import { BaseComponent } from './common/commonComponent';
import { LoginComponent } from './login/login.component';
import { MainComponent } from './main/main.component';
import { HeaderComponent } from './main/header/header.component';
import { FooterComponent } from './main/footer/footer.component';
import { SidebarComponent } from './main/sidebar/sidebar.component';
import { DashboardComponent } from './main/dashboard/dashboard.component';
import { ManageSchoolComponent } from './main/manage-school/manage-school.component';
import { ManageSchoolViewComponent } from './main/manage-school/manage-school-view/manage-school-view.component';
import { ManageUserComponent } from './main/manage-user/manage-user.component';
import { ManageRoleComponent } from './main/manage-role/manage-role.component';
import { ManageRoleListComponent } from './main/manage-role/manage-role-list/manage-role-list.component';
import { ManageRoleEditComponent } from './main/manage-role/manage-role-edit/manage-role-edit.component';
import { ManageUserEditComponent } from './main/manage-user/manage-user-edit/manage-user-edit.component';
import { CodeMaintenanceComponent } from './main/code-maintenance/code-maintenance.component';
import { CodeMaintenanceListComponent } from './main/code-maintenance/code-maintenance-list/code-maintenance-list.component';
import { CodeMaintenanceEditComponent } from './main/code-maintenance/code-maintenance-edit/code-maintenance-edit.component';
import { CodeMaintenanceViewComponent } from './main/code-maintenance/code-maintenance-view/code-maintenance-view.component';
import { ProductConfigurationComponent } from './main/product-configuration/product-configuration.component';
import { ProductConfigurationEditComponent } from './main/product-configuration/product-configuration-edit/product-configuration-edit.component';
import { ProductConfigurationViewComponent } from './main/product-configuration/product-configuration-view/product-configuration-view.component';
import { ApplicationConfigurationComponent } from './main/application-configuration/application-configuration.component';
import { ApplicationConfigurationListComponent } from './main/application-configuration/application-configuration-list/application-configuration-list.component';
import { ApplicationConfigurationEditComponent } from './main/application-configuration/application-configuration-edit/application-configuration-edit.component';
import { ApplicationConfigurationViewComponent } from './main/application-configuration/application-configuration-view/application-configuration-view.component';
import { ApprovalWorkflowComponent } from './main/approval-workflow/approval-workflow.component';
import { ApprovalWorkflowListComponent } from './main/approval-workflow/approval-workflow-list/approval-workflow-list.component';
import { ApprovalWorkflowEditComponent } from './main/approval-workflow/approval-workflow-edit/approval-workflow-edit.component';
import { ApprovalWorkflowViewComponent } from './main/approval-workflow/approval-workflow-view/approval-workflow-view.component';
import { AnnouncementComponent } from './main/announcement/announcement.component';
import { AnnouncementListComponent } from './main/announcement/announcement-list/announcement-list.component';
import { AnnouncementEditComponent } from './main/announcement/announcement-edit/announcement-edit.component';
import { AnnouncementViewComponent } from './main/announcement/announcement-view/announcement-view.component';
import { ManageBeneficiaryComponent } from './main/manage-beneficiary/manage-beneficiary.component';
import { ManageBeneficiaryListComponent } from './main/manage-beneficiary/manage-beneficiary-list/manage-beneficiary-list.component';
import { ManageBeneficiaryApprovalComponent } from './main/manage-beneficiary/manage-beneficiary-approval/manage-beneficiary-approval.component';
import { ManageRequirementComponent } from './main/manage-requirement/manage-requirement.component';
import { ManageRequirementListComponent } from './main/manage-requirement/manage-requirement-list/manage-requirement-list.component';
import { ManageRequirementEditComponent } from './main/manage-requirement/manage-requirement-edit/manage-requirement-edit.component';
import { RegisterComponent } from './register/register.component';
import { BeneficiaryRegisterComponent } from './beneficiary-register/beneficiary-register.component';
import { ManageSchoolListComponent } from './main/manage-school/manage-school-list/manage-school-list.component';
import { PendingWorkflowComponent } from './main/pending-workflow/pending-workflow.component';
import { ReviewInvoiceComponent } from './main/review-invoice/review-invoice.component';
import { ReviewInvoiceListComponent } from './main/review-invoice/review-invoice-list/review-invoice-list.component';
import { ReferVolunteerComponent } from './main/refer-volunteer/refer-volunteer.component';
import { AssignedSchoolComponent } from './main/assigned-school/assigned-school.component';
import { AssignedSchoolListComponent } from './main/assigned-school/assigned-school-list/assigned-school-list.component';
import { AssignedSchoolViewComponent } from './main/assigned-school/assigned-school-view/assigned-school-view.component';
import { UploadQuotationComponent } from './main/upload-quotation/upload-quotation.component';
import { UploadReceiptComponent } from './main/upload-receipt/upload-receipt.component';
import { ManageSchoolEditComponent } from './main/manage-school/manage-school-edit/manage-school-edit.component';
import { EmailSendToDEOComponent } from './main/email-send-to-deo/email-send-to-deo.component';
import { UploadDeoResponseComponent } from './main/upload-deo-response/upload-deo-response.component';
import { AssignVolunteerComponent } from './main/assign-volunteer/assign-volunteer.component';
import { ChangeVolunteerComponent } from './main/change-volunteer/change-volunteer.component';
import { ChangeVolunteerListComponent } from './main/change-volunteer/change-volunteer-list/change-volunteer-list.component';
import { ReassignVolunteerComponent } from './main/change-volunteer/reassign-volunteer/reassign-volunteer.component';
import { FundAllotmentComponent } from './main/fund-allotment/fund-allotment.component';
import { FundProcessWorkOrderComponent } from './main/fund-process-work-order/fund-process-work-order.component';
import { MyProfileComponent } from './main/my-profile/my-profile.component';
import { NsplTextComponent } from './nspl-text/nspl-text.component';
import { NsplFileBrowseComponent } from './nspl-text/nspl-file-browse/nspl-file-browse.component';
import { NsplCheckboxComponent } from './nspl-text/nspl-checkbox/nspl-checkbox.component';


import { NsplVideoComponent } from './nspl-text/nspl-video/nspl-video.component';
import { NsplPercentageChartComponent } from './nspl-text/nspl-percentage-chart/nspl-percentage-chart.component';
import { UploadsModule } from '@progress/kendo-angular-upload';
import { NsplAplhaWithSpaceDirective } from './common/directive/nspl-aplha-with-space.directive';
import { NsplAlphaNumericDirective } from './common/directive/nspl-alpha-numeric.directive';
import { NsplAphaOnlyDirective } from './common/directive/nspl-apha-only.directive'
import { CaptchaComponent } from './common/captcha/captcha.component';
import { ManageUserListComponent } from './main/manage-user/manage-user-list/manage-user-list.component';
import { ManageUserViewComponent } from './main/manage-user/manage-user-view/manage-user-view.component';
import { PasswordStrengthComponent } from './common/password-strength/password-strength.component';
import { defineLocale, frLocale } from 'ngx-bootstrap/chronos';
import { EmailSettingsComponent } from './main/email-settings/email-settings.component';
import { EmailListComponent } from './main/email-settings/email-list/email-list.component';
import { EmailEditComponent } from './main/email-settings/email-edit/email-edit.component';
import { EmailViewComponent } from './main/email-settings/email-view/email-view.component';
import { ChangePasswordComponent } from './main/change-password/change-password.component';

defineLocale('tm', frLocale);
// AoT requires an exported function for factories
export function HttpLoaderFactory(httpClient: HttpClient) {
  // return new TranslateHttpLoader(httpClient);
  return new TranslateHttpLoader(httpClient, "./assets/i18n/", ".json");
}


import { SocialLoginModule, GoogleLoginProvider, FacebookLoginProvider } from 'angularx-social-login';
import { ManageRoleViewComponent } from './main/manage-role/manage-role-view/manage-role-view.component';
import { ManageMenuComponent } from './main/manage-menu/manage-menu.component';
import { ManageMenuListComponent } from './main/manage-menu/manage-menu-list/manage-menu-list.component';
import { ManageMenuDetailComponent } from './main/manage-menu/manage-menu-detail/manage-menu-detail.component';
import { ManageMenuViewComponent } from './main/manage-menu/manage-menu-view/manage-menu-view.component';
import { ManageModuleComponent } from './main/manage-module/manage-module.component';
import { ManageModuleListComponent } from './main/manage-module/manage-module-list/manage-module-list.component';
import { ManageModuleDetailComponent } from './main/manage-module/manage-module-detail/manage-module-detail.component';
import { ManageModuleViewComponent } from './main/manage-module/manage-module-view/manage-module-view.component';
import { UpdatePasswordComponent } from './update-password/update-password.component';
import { ReviewInvoiceDetailsComponent } from './main/review-invoice/review-invoice-details/review-invoice-details.component';
import { ReviewQuotationComponent } from './main/review-quotation/review-quotation.component';
import { ReviewQuotationListComponent } from './main/review-quotation/review-quotation-list/review-quotation-list.component';
import { ReviewQuotationEditComponent } from './main/review-quotation/review-quotation-edit/review-quotation-edit.component';
import { FundAllotmentListComponent } from './main/fund-allotment/fund-allotment-list/fund-allotment-list.component';
import { FundAllotmentDetailsComponent } from './main/fund-allotment/fund-allotment-details/fund-allotment-details.component';
import { VolunteerRegistrationComponent } from './volunteer-registration/volunteer-registration.component';
import { ViewQuotationComponent } from './main/upload-quotation/view-quotation/view-quotation.component';
import { QuotationDetailsComponent } from './main/upload-quotation/quotation-details/quotation-details.component';
import { InvoiceDetailsComponent } from './main/upload-quotation/invoice-details/invoice-details.component';
import { InvoiceViewComponent } from './main/upload-quotation/invoice-view/invoice-view.component';
import { NsplMultilineTextComponent } from './nspl-text/nspl-multiline-text/nspl-multiline-text.component';
import { NsplNumberComponent } from './nspl-text/nspl-number/nspl-number.component';
import { NsplTableSortComponent } from './nspl-text/nspl-table-sort/nspl-table-sort.component';
import { SearchSchoolComponent } from './main/search-school/search-school.component';
import { SearchRequirementsComponent } from './main/search-requirements/search-requirements.component';
import { SearchSchoolDetailsComponent } from './main/search-school/search-school-details/search-school-details.component';
import { SearchRequrementDetailsComponent } from './main/search-requirements/search-requrement-details/search-requrement-details.component';
import { PaymentConfirmationComponent } from './payment-confirmation/payment-confirmation.component';
import { DeoCodeMaintenanceComponent } from './main/deo-code-maintenance/deo-code-maintenance.component';
import { DeoCodeListComponent } from './main/deo-code-maintenance/deo-code-list/deo-code-list.component';
import { DeoCodeEditComponent } from './main/deo-code-maintenance/deo-code-edit/deo-code-edit.component';
import { DeoCodeViewComponent } from './main/deo-code-maintenance/deo-code-view/deo-code-view.component';
import { BlockCopyPasteDirective } from './common/block-copy-paste.directive';
import { PaymentDetailsComponent } from './main/upload-quotation/payment-details/payment-details.component';

import { SummaryComponent } from './summary/summary.component';
import { DeleteSchoolComponent } from './main/delete-school/delete-school.component';
import { DeleteSchoolViewComponent } from './main/delete-school/delete-school-view/delete-school-view.component';
import { NsplShortNameDirective } from './common/directive/nspl-short-name.directive';
import { NsplTableExportComponent } from './nspl-text/nspl-table-export/nspl-table-export.component';
import { ReportsComponent } from './main/reports/reports.component';
import { DeletedReportsComponent } from './main/deleted-reports/deleted-reports.component';
import { CollectionAmountReportComponent } from './main/collection-amount-report/collection-amount-report.component';
import { DonationAmountReportComponent } from './main/donation-amount-report/donation-amount-report.component';
import { NsplShortnameDirective } from './common/directive/nspl-shortname.directive';
import { InvoicePaymentComponent } from './main/invoice-payment/invoice-payment.component';
import { InvoivePaymentListComponent } from './main/invoice-payment/invoive-payment-list/invoive-payment-list.component';
import { InvoivePaymentEditComponent } from './main/invoice-payment/invoive-payment-edit/invoive-payment-edit.component';
import { InvoivePaymentViewComponent } from './main/invoice-payment/invoive-payment-view/invoive-payment-view.component';
import { FinancialStatusReportComponent } from './main/financial-status-report/financial-status-report.component';
import { Broadcaster } from './common/BroadCaster';
import { CorporateDonationComponent } from './main/financial-status-report/corporate-donation/corporate-donation.component';
import { IndividualDonationComponent } from './main/financial-status-report/individual-donation/individual-donation.component';
import { SchoolDetailsComponent } from './main/financial-status-report/school-details/school-details.component';
import { TrustExpensesComponent } from './main/financial-status-report/trust-expenses/trust-expenses.component';
import { TrustMemberIncomeComponent } from './main/financial-status-report/trust-member-income/trust-member-income.component';

/** 
 * config takes two params
 * 1. Provider config array
 * 2. Boolean to auto logged
 */
// const CONFIG = new AuthServiceConfig([
//   {
//     id: GoogleLoginProvider.PROVIDER_ID,
//     provider: new GoogleLoginProvider('206670053648-mmj4intevjmenk2vq5ke0imf89miqgtp.apps.googleusercontent.com')
//   },
//   {
//     id: FacebookLoginProvider.PROVIDER_ID,
//     provider: new FacebookLoginProvider('739890587020702')
//   },
//   {
//     id: LinkedinLoginProvider.PROVIDER_ID,
//     provider: new LinkedinLoginProvider('8615wayvoh9bbf')
//   }
// ], true);

// export function provideConfig() {
//   return CONFIG;
// }


@NgModule({
  declarations: [

    AppComponent,
    BaseComponent,
    LoginComponent,
    MainComponent,
    HeaderComponent,
    FooterComponent,
    SidebarComponent,
    DashboardComponent,
    ManageSchoolComponent,
    RegisterComponent,
    ManageSchoolViewComponent,
    ManageSchoolEditComponent,
    ManageUserComponent,
    ManageUserListComponent,
    ManageUserEditComponent,
    ManageUserViewComponent,
    ManageRoleComponent,
    ManageRoleListComponent,
    ManageRoleEditComponent,
    CodeMaintenanceComponent,
    CodeMaintenanceListComponent,
    CodeMaintenanceEditComponent,
    CodeMaintenanceViewComponent,
    ProductConfigurationComponent,
    ProductConfigurationEditComponent,
    ProductConfigurationViewComponent,
    ApplicationConfigurationComponent,
    ApplicationConfigurationListComponent,
    ApplicationConfigurationEditComponent,
    ApplicationConfigurationViewComponent,
    ApprovalWorkflowComponent,
    ApprovalWorkflowListComponent,
    ApprovalWorkflowEditComponent,
    ApprovalWorkflowViewComponent,
    AnnouncementComponent,
    AnnouncementListComponent,
    AnnouncementEditComponent,
    AnnouncementViewComponent,
    ManageBeneficiaryComponent,
    ManageBeneficiaryListComponent,
    ManageBeneficiaryApprovalComponent,
    ManageRequirementComponent,
    ManageRequirementListComponent,
    ManageRequirementEditComponent,
    BeneficiaryRegisterComponent,
    ManageSchoolListComponent,
    PendingWorkflowComponent,
    ReviewInvoiceComponent,
    ReviewInvoiceListComponent,
    ReferVolunteerComponent,
    AssignedSchoolComponent,
    AssignedSchoolListComponent,
    AssignedSchoolViewComponent,
    UploadQuotationComponent,
    UploadReceiptComponent,
    EmailSendToDEOComponent,
    UploadDeoResponseComponent,
    AssignVolunteerComponent,
    ChangeVolunteerComponent,
    ChangeVolunteerListComponent,
    ReassignVolunteerComponent,
    FundAllotmentComponent,
    FundProcessWorkOrderComponent,
    MyProfileComponent,
    NsplTextComponent,
    NsplFileBrowseComponent,
    NsplCheckboxComponent,
    NsplVideoComponent,
    CaptchaComponent,
    NsplPercentageChartComponent,
    NsplAplhaWithSpaceDirective,
    NsplAlphaNumericDirective,
    NsplAphaOnlyDirective,
    PasswordStrengthComponent,
    ReviewInvoiceDetailsComponent,
    EmailSettingsComponent,
    EmailListComponent,
    EmailEditComponent,
    EmailViewComponent,
    ManageRoleViewComponent,
    ManageMenuComponent,
    ManageMenuListComponent,
    ManageMenuDetailComponent,
    ManageMenuViewComponent,
    ManageModuleListComponent,
    ManageModuleDetailComponent,
    ManageModuleViewComponent,
    ManageModuleComponent,
    UpdatePasswordComponent,
    ChangePasswordComponent,
    ReviewQuotationComponent,
    ReviewQuotationListComponent,
    ReviewQuotationEditComponent,
    FundAllotmentListComponent,
    FundAllotmentDetailsComponent,
    VolunteerRegistrationComponent,
    ViewQuotationComponent,
    QuotationDetailsComponent,
    InvoiceDetailsComponent,
    InvoiceViewComponent,
    NsplMultilineTextComponent,
    NsplNumberComponent,
    NsplTableSortComponent,
    SearchSchoolComponent,
    SearchRequirementsComponent,
    SearchSchoolDetailsComponent,
    SearchRequrementDetailsComponent,
    PaymentConfirmationComponent,
    DeoCodeMaintenanceComponent,
    DeoCodeListComponent,
    DeoCodeEditComponent,
    DeoCodeViewComponent,
    BlockCopyPasteDirective,
    PaymentDetailsComponent,
    SummaryComponent,
    DeleteSchoolComponent,
    DeleteSchoolViewComponent,
    NsplShortNameDirective,
    NsplTableExportComponent,
    ReportsComponent,
    DeletedReportsComponent,
    CollectionAmountReportComponent,
    DonationAmountReportComponent,
    NsplShortnameDirective,
    InvoicePaymentComponent,
    InvoivePaymentListComponent,
    InvoivePaymentEditComponent,
    InvoivePaymentViewComponent,
    FinancialStatusReportComponent,
    SchoolDetailsComponent,
    IndividualDonationComponent,
    CorporateDonationComponent,
    TrustExpensesComponent,
    TrustMemberIncomeComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    NgSelectModule,
    FormsModule,
    HttpClientModule,
    NgxSpinnerModule,
    BrowserAnimationsModule,
    TooltipModule.forRoot(),
    NgxUiLoaderModule,
    ModalModule.forRoot(),
    ToastrModule.forRoot(),
    TabsModule.forRoot(),
    PaginationModule.forRoot(),
    BsDatepickerModule.forRoot(),
    NgPopupsModule.forRoot(),
    UploadsModule,
    FileSelectModule,
    NgbModule,
    ImageCropperModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
    SocialLoginModule
  ],
  providers: [CanLoginActivate, CanAuthActivate, EnvServiceProvider, Broadcaster, DecimalPipe,DatePipe,


    {
      provide: 'SocialAuthServiceConfig',
      useValue: {
        autoLogin: true, //keeps the user signed in
        providers: [
          {
            id: GoogleLoginProvider.PROVIDER_ID,
            provider: new GoogleLoginProvider('148517665605-jspahbqleats6lvlag9kasc2c11b5g7o.apps.googleusercontent.com') // your client id
            // 71907190629-evnoc2b31uoo71hiluv2ct663m6vhirc.apps.googleusercontent.com
          },
          {
            id: FacebookLoginProvider.PROVIDER_ID,
            provider: new FacebookLoginProvider('739890587020702')
          },

          // {provide: LocationStrategy, useClass: PathLocationStrategy}
        ]
      }
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
