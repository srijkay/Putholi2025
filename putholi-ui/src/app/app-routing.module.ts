import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/login.component';

import { CanLoginActivate, CanAuthActivate } from './common/auth.gaurd';
import { MainComponent } from './main/main.component';
import { DashboardComponent } from './main/dashboard/dashboard.component';
import { PendingWorkflowComponent } from './main/pending-workflow/pending-workflow.component';
import { ManageUserComponent } from './main/manage-user/manage-user.component';
import { ManageUserListComponent } from './main/manage-user/manage-user-list/manage-user-list.component';
import { ManageUserEditComponent } from './main/manage-user/manage-user-edit/manage-user-edit.component';
import { ManageUserViewComponent } from './main/manage-user/manage-user-view/manage-user-view.component';
import { ManageRoleComponent } from './main/manage-role/manage-role.component';
import { ManageRoleListComponent } from './main/manage-role/manage-role-list/manage-role-list.component';
import { CodeMaintenanceComponent } from './main/code-maintenance/code-maintenance.component';
import { CodeMaintenanceListComponent } from './main/code-maintenance/code-maintenance-list/code-maintenance-list.component';
import { CodeMaintenanceEditComponent } from './main/code-maintenance/code-maintenance-edit/code-maintenance-edit.component';
import { CodeMaintenanceViewComponent } from './main/code-maintenance/code-maintenance-view/code-maintenance-view.component';
import { ProductConfigurationComponent } from './main/product-configuration/product-configuration.component';
import { ProductConfigurationEditComponent } from './main/product-configuration/product-configuration-edit/product-configuration-edit.component';
import { ProductConfigurationViewComponent } from './main/product-configuration/product-configuration-view/product-configuration-view.component';
import { ApplicationConfigurationListComponent } from './main/application-configuration/application-configuration-list/application-configuration-list.component';
import { ApplicationConfigurationComponent } from './main/application-configuration/application-configuration.component';
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
import { RegisterComponent } from './register/register.component';
import { BeneficiaryRegisterComponent } from './beneficiary-register/beneficiary-register.component';
import { ManageBeneficiaryComponent } from './main/manage-beneficiary/manage-beneficiary.component';
import { ManageBeneficiaryListComponent } from './main/manage-beneficiary/manage-beneficiary-list/manage-beneficiary-list.component';
import { ManageBeneficiaryApprovalComponent } from './main/manage-beneficiary/manage-beneficiary-approval/manage-beneficiary-approval.component';
import { ManageSchoolComponent } from './main/manage-school/manage-school.component';
import { ManageSchoolListComponent } from './main/manage-school/manage-school-list/manage-school-list.component';
import { ManageSchoolEditComponent } from './main/manage-school/manage-school-edit/manage-school-edit.component';
import { ManageSchoolViewComponent } from './main/manage-school/manage-school-view/manage-school-view.component';
import { ManageRequirementComponent } from './main/manage-requirement/manage-requirement.component';
import { ManageRequirementListComponent } from './main/manage-requirement/manage-requirement-list/manage-requirement-list.component';
import { ManageRequirementEditComponent } from './main/manage-requirement/manage-requirement-edit/manage-requirement-edit.component';
import { ManageRoleEditComponent } from './main/manage-role/manage-role-edit/manage-role-edit.component';
import { ReviewInvoiceComponent } from './main/review-invoice/review-invoice.component';
import { ReferVolunteerComponent } from './main/refer-volunteer/refer-volunteer.component';
import { AssignedSchoolComponent } from './main/assigned-school/assigned-school.component';
import { AssignedSchoolListComponent } from './main/assigned-school/assigned-school-list/assigned-school-list.component';
import { AssignedSchoolViewComponent } from './main/assigned-school/assigned-school-view/assigned-school-view.component';
import { UploadQuotationComponent } from './main/upload-quotation/upload-quotation.component';
import { UploadReceiptComponent } from './main/upload-receipt/upload-receipt.component';
import { EmailSendToDEOComponent } from './main/email-send-to-deo/email-send-to-deo.component';
import { UploadDeoResponseComponent } from './main/upload-deo-response/upload-deo-response.component';
import { ReviewInvoiceListComponent } from './main/review-invoice/review-invoice-list/review-invoice-list.component';
import { AssignVolunteerComponent } from './main/assign-volunteer/assign-volunteer.component';
import { ChangeVolunteerComponent } from './main/change-volunteer/change-volunteer.component';
import { ChangeVolunteerListComponent } from './main/change-volunteer/change-volunteer-list/change-volunteer-list.component';
import { ReassignVolunteerComponent } from './main/change-volunteer/reassign-volunteer/reassign-volunteer.component';
import { FundAllotmentComponent } from './main/fund-allotment/fund-allotment.component';
import { FundProcessWorkOrderComponent } from './main/fund-process-work-order/fund-process-work-order.component';
import { MyProfileComponent } from './main/my-profile/my-profile.component';
import { ReviewInvoiceDetailsComponent } from './main/review-invoice/review-invoice-details/review-invoice-details.component';
import { EmailSettingsComponent } from './main/email-settings/email-settings.component';
import { EmailListComponent } from './main/email-settings/email-list/email-list.component';
import { EmailEditComponent } from './main/email-settings/email-edit/email-edit.component';
import { EmailViewComponent } from './main/email-settings/email-view/email-view.component';
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
import { ChangePasswordComponent } from './main/change-password/change-password.component';
import { ReviewQuotationComponent } from './main/review-quotation/review-quotation.component';
import { ReviewQuotationListComponent } from './main/review-quotation/review-quotation-list/review-quotation-list.component';
import { ReviewQuotationEditComponent } from './main/review-quotation/review-quotation-edit/review-quotation-edit.component';
import { FundAllotmentListComponent } from './main/fund-allotment/fund-allotment-list/fund-allotment-list.component';
import { FundAllotmentDetailsComponent } from './main/fund-allotment/fund-allotment-details/fund-allotment-details.component';
import { VolunteerRegistrationComponent } from './volunteer-registration/volunteer-registration.component';
import { SearchSchoolComponent } from './main/search-school/search-school.component';
import { SearchRequirementsComponent } from './main/search-requirements/search-requirements.component';
import { SearchSchoolDetailsComponent } from './main/search-school/search-school-details/search-school-details.component';
import { SearchRequrementDetailsComponent } from './main/search-requirements/search-requrement-details/search-requrement-details.component';
import { PaymentConfirmationComponent } from './payment-confirmation/payment-confirmation.component';
import { DeoCodeMaintenanceComponent } from './main/deo-code-maintenance/deo-code-maintenance.component';
import { DeoCodeListComponent } from './main/deo-code-maintenance/deo-code-list/deo-code-list.component';
import { DeoCodeEditComponent } from './main/deo-code-maintenance/deo-code-edit/deo-code-edit.component';
import { DeoCodeViewComponent } from './main/deo-code-maintenance/deo-code-view/deo-code-view.component';
import { SummaryComponent } from './summary/summary.component';
import { DeleteSchoolComponent } from './main/delete-school/delete-school.component';
import { DeleteSchoolViewComponent } from './main/delete-school/delete-school-view/delete-school-view.component';
import { ReportsComponent } from './main/reports/reports.component';
import { DeletedReportsComponent } from './main/deleted-reports/deleted-reports.component';
import { CollectionAmountReportComponent } from './main/collection-amount-report/collection-amount-report.component';
import { DonationAmountReportComponent } from './main/donation-amount-report/donation-amount-report.component';
import { ErrorPageComponent } from './error-page/error-page.component';
import { InvoicePaymentComponent } from './main/invoice-payment/invoice-payment.component';
import { InvoivePaymentListComponent } from './main/invoice-payment/invoive-payment-list/invoive-payment-list.component';
import { InvoiceDetailsComponent } from './main/upload-quotation/invoice-details/invoice-details.component';
import { InvoivePaymentViewComponent } from './main/invoice-payment/invoive-payment-view/invoive-payment-view.component';
import { InvoivePaymentEditComponent } from './main/invoice-payment/invoive-payment-edit/invoive-payment-edit.component';
import { FinancialStatusReportComponent } from './main/financial-status-report/financial-status-report.component';

const routes: Routes = [
  { path: "", redirectTo: "login", pathMatch: "full" },
  {
    path: "login",
    component: LoginComponent,
    canActivate: [CanLoginActivate],
    pathMatch: "full"
  },
  {
    path: "update-password",
    component: UpdatePasswordComponent,
    pathMatch: "full"
  },
  {
    path: "register",
    component: RegisterComponent,
    pathMatch: "full"
  },

  {
    path: "volunteer-registration",
    component: VolunteerRegistrationComponent,
    pathMatch: "full"
  },

  {
    path: "beneficiary-register",
    component: BeneficiaryRegisterComponent,
    pathMatch: "full"
  },

  {
    path: "confirm-payment",
    component: PaymentConfirmationComponent,
    pathMatch: "full"
  },
  {
    path: "summary/:id",
    component: SummaryComponent,
    pathMatch: "full",
  },

  {
    path: "cancel",
    component: ErrorPageComponent,
    pathMatch: "full",
  },

  {
    path: "main",
    component: MainComponent,
    canActivate: [CanAuthActivate],
    children: [
      { path: "", redirectTo: "dashboard", pathMatch: "full" },
      {
        path: "dashboard",
        component: DashboardComponent,
        pathMatch: "full"
      },
      {
        path: "upload-quotation/:id/:id2",
        component: UploadQuotationComponent,
        pathMatch: "full",
      },
      {
        path: "change-password",
        component: ChangePasswordComponent,
        pathMatch: "full",
      },


      {
        path: "manage-user",
        component: ManageUserComponent,
        children: [
          { path: "", redirectTo: "user-list", pathMatch: "full" },
          {
            path: "user-list",
            component: ManageUserListComponent,
            pathMatch: "full"
          },
          {
            path: "user-edit/:username",
            component: ManageUserEditComponent,
            pathMatch: "full"
          }, {
            path: "user-view/:username/:type",
            component: ManageUserViewComponent,
            pathMatch: "full"
          },
        ]
      },

      {
        path: "reports",
        component: ReportsComponent,
        pathMatch: "full",
      },

      {
        path: "deleted-reports",
        component: DeletedReportsComponent,
        pathMatch: "full",
      },

      {
        path: "collection-reports",
        component: CollectionAmountReportComponent,
        pathMatch: "full",
      },

      {
        path: "donation-reports",
        component: DonationAmountReportComponent,
        pathMatch: "full",
      },

      {
        path: "role-mgnt",
        component: ManageRoleComponent,
        children: [
          { path: "", redirectTo: "role-list", pathMatch: "full" },
          {
            path: "role-list",
            component: ManageRoleListComponent,
            pathMatch: "full"
          },
          {
            path: "role-edit/:id",
            component: ManageRoleEditComponent,
            pathMatch: "full"
          },
          {
            path: "role-view/:id",
            component: ManageRoleViewComponent,
            pathMatch: "full"
          },
        ]
      },

      {
        path: "code-maintenance",
        component: CodeMaintenanceComponent,
        children: [
          { path: "", redirectTo: "codes-list", pathMatch: "full" },
          {
            path: "codes-list",
            component: CodeMaintenanceListComponent,
            pathMatch: "full"
          },
          {
            path: "codes-edit/:id",
            component: CodeMaintenanceEditComponent,
            pathMatch: "full"
          },
          {
            path: "codes-view/:id",
            component: CodeMaintenanceViewComponent,
            pathMatch: "full"
          }
        ]
      },

      {
        path: "deo-code-maintenance",
        component: DeoCodeMaintenanceComponent,
        children: [
          { path: "", redirectTo: "deo-code-list", pathMatch: "full" },
          {
            path: "deo-code-list",
            component: DeoCodeListComponent,
            pathMatch: "full"
          },
          {
            path: "deo-code-edit/:id",
            component: DeoCodeEditComponent,
            pathMatch: "full"
          },
          {
            path: "deo-code-view/:id",
            component: DeoCodeViewComponent,
            pathMatch: "full"
          },
        ]
      },

      {
        path: "product-config",
        component: ProductConfigurationComponent,
        children: [
          { path: "", redirectTo: "product-edit", pathMatch: "full" },

          {
            path: "product-edit",
            component: ProductConfigurationEditComponent,
            pathMatch: "full"
          }, {
            path: "product-view",
            component: ProductConfigurationViewComponent,
            pathMatch: "full"
          }
        ]
      },


      {
        path: "menu",
        component: ManageMenuComponent,
        children: [
          { path: "", redirectTo: "menu-list", pathMatch: "full" },
          {
            path: "menu-list",
            component: ManageMenuListComponent,
            pathMatch: "full"
          },
          {
            path: "menu-detail/:id",
            component: ManageMenuDetailComponent,
            pathMatch: "full"
          },
          {
            path: "menu-view/:id",
            component: ManageMenuViewComponent,
            pathMatch: "full"
          },
        ]
      },

      {
        path: "module",
        component: ManageModuleComponent,
        children: [
          { path: "", redirectTo: "module-list", pathMatch: "full" },
          {
            path: "module-list",
            component: ManageModuleListComponent,
            pathMatch: "full"
          },
          {
            path: "module-detail/:id",
            component: ManageModuleDetailComponent,
            pathMatch: "full"
          },
          {
            path: "module-view/:id",
            component: ManageModuleViewComponent,
            pathMatch: "full"
          },
        ]
      },


      {
        path: "appl-config",
        component: ApplicationConfigurationComponent,
        children: [
          { path: "", redirectTo: "appl-list", pathMatch: "full" },
          {
            path: "appl-list",
            component: ApplicationConfigurationListComponent,
            pathMatch: "full"
          },
          {
            path: "appl-edit/:id",
            component: ApplicationConfigurationEditComponent,
            pathMatch: "full"
          },
          {
            path: "appl-view/:id",
            component: ApplicationConfigurationViewComponent,
            pathMatch: "full"
          }
        ]
      },

      {
        path: "approval-workflow",
        component: ApprovalWorkflowComponent,
        children: [
          { path: "", redirectTo: "approval-workflow-list", pathMatch: "full" },
          {
            path: "approval-workflow-list",
            component: ApprovalWorkflowListComponent,
            pathMatch: "full"
          },
          {
            path: "approval-workflow-edit/:id",
            component: ApprovalWorkflowEditComponent,
            pathMatch: "full"
          },

          {
            path: "approval-workflow-view/:id",
            component: ApprovalWorkflowViewComponent,
            pathMatch: "full"
          },

        ]
      },

      {
        path: "search-school",
        component: SearchSchoolComponent,
        pathMatch: "full",
      },
      {
        path: "search-requirements",
        component: SearchRequirementsComponent,
        pathMatch: "full",
      },
      {
        path: "search-requirements-details/:id",
        component: SearchRequrementDetailsComponent,
        pathMatch: "full",
      },

      {
        path: "search-school-details/:id",
        component: SearchSchoolDetailsComponent,
        pathMatch: "full",
      },

      {
        path: "announcement",
        component: AnnouncementComponent,
        children: [
          { path: "", redirectTo: "announcement-list", pathMatch: "full" },
          {
            path: "announcement-list",
            component: AnnouncementListComponent,
            pathMatch: "full"
          },
          {
            path: "announcement-edit/:id",
            component: AnnouncementEditComponent,
            pathMatch: "full"
          },

          {
            path: "announcement-view/:id",
            component: AnnouncementViewComponent,
            pathMatch: "full"
          },

        ]
      },

      {
        path: "manage-beneficiary",
        component: ManageBeneficiaryComponent,
        children: [
          { path: "", redirectTo: "manage-beneficiary-list", pathMatch: "full" },
          {
            path: "manage-beneficiary-list",
            component: ManageBeneficiaryListComponent,
            pathMatch: "full"
          },
          {
            path: "manage-beneficiary-approval/:username",
            component: ManageBeneficiaryApprovalComponent,
            pathMatch: "full"
          }
        ]
      },

      {
        path: "manage-school",
        component: ManageSchoolComponent,
        children: [
          { path: "", redirectTo: "manage-school-list", pathMatch: "full" },
          {
            path: "manage-school-list",
            component: ManageSchoolListComponent,
            pathMatch: "full"
          },
          {
            path: "manage-school-edit/:id",
            component: ManageSchoolEditComponent,
            pathMatch: "full"
          },
          {
            path: "manage-school-view/:id",
            component: ManageSchoolViewComponent,
            pathMatch: "full"
          }
        ]
      },


      {
        path: "manage-requirement",
        component: ManageRequirementComponent,
        children: [
          { path: "", redirectTo: "require-list", pathMatch: "full" },
          {
            path: "require-list",
            component: ManageRequirementListComponent,
            pathMatch: "full"
          },
          {
            path: "require-edit/:id",
            component: ManageRequirementEditComponent,
            pathMatch: "full"
          },
        ]
      },

      {
        path: "pending-workflow",
        component: PendingWorkflowComponent,
        pathMatch: "full",
      },

      {
        path: "upload-receipt/:id/:id2",
        component: UploadReceiptComponent,
        pathMatch: "full",
      },

      {
        path: "email-send/:id/:id2",
        component: EmailSendToDEOComponent,
        pathMatch: "full",
      },

      {
        path: "deo-response/:id/:id2",
        component: UploadDeoResponseComponent,
        pathMatch: "full",
      },


      {
        path: "assign-volunteer/:id",
        component: AssignVolunteerComponent,
        pathMatch: "full",
      },

      {
        path: "review-invoice",
        component: ReviewInvoiceComponent,
        children: [
          { path: "", redirectTo: "review-invoice-list", pathMatch: "full" },
          {
            path: "review-invoice-list",
            component: ReviewInvoiceListComponent,
            pathMatch: "full"
          },
          {
            path: "review-invoice-details/:id/:id2",
            component: ReviewInvoiceDetailsComponent,
            pathMatch: "full"
          }

        ]
      },


      {
        path: "review-invoice",
        component: ReviewQuotationComponent,
        children: [
          { path: "", redirectTo: "review-invoice-list", pathMatch: "full" },
          {
            path: "review-invoice-list",
            component: ReviewInvoiceListComponent,
            pathMatch: "full"
          },
          {
            path: "review-invoice-details/:id/:id2",
            component: ReviewInvoiceDetailsComponent,
            pathMatch: "full"
          }

        ]
      },


      {
        path: "review-quotate",
        component: ReviewQuotationComponent,
        children: [
          { path: "", redirectTo: "review-quotate-list", pathMatch: "full" },
          {
            path: "review-quotate-list",
            component: ReviewQuotationListComponent,
            pathMatch: "full"
          },
          {
            path: "review-quotate-details/:id/:id2",
            component: ReviewQuotationEditComponent,
            pathMatch: "full"
          }

        ]
      },


      {
        path: "assign-school",
        component: AssignedSchoolComponent,
        children: [
          { path: "", redirectTo: "assign-school-list", pathMatch: "full" },
          {
            path: "assign-school-list",
            component: AssignedSchoolListComponent,
            pathMatch: "full"
          },
          {
            path: "assign-school-view/:id/:id2",
            component: AssignedSchoolViewComponent,
            pathMatch: "full"
          },
        ]
      },

      {
        path: "refer-volunteer",
        component: ReferVolunteerComponent,
        pathMatch: "full",
      },

      {
        path: "change-volunteer",
        component: ChangeVolunteerComponent,
        children: [
          { path: "", redirectTo: "change-volunteer-list", pathMatch: "full" },
          {
            path: "change-volunteer-list",
            component: ChangeVolunteerListComponent,
            pathMatch: "full"
          },
          {
            path: "assign-change-volunteer/:id",
            component: ReassignVolunteerComponent,
            pathMatch: "full"
          },
        ]
      },

      {
        path: "email-settings",
        component: EmailSettingsComponent,
        children: [
          { path: "", redirectTo: "email-list", pathMatch: "full" },
          {
            path: "email-list",
            component: EmailListComponent,
            pathMatch: "full"
          },
          {
            path: "email-edit/:id",
            component: EmailEditComponent,
            pathMatch: "full"
          },
          {
            path: "email-view/:id",
            component: EmailViewComponent,
            pathMatch: "full"
          },
        ]
      },


      {
        path: "fund-allotment",
        component: FundAllotmentComponent,
        children: [
          { path: "", redirectTo: "fund-allotment-list", pathMatch: "full" },
          {
            path: "fund-allotment-list",
            component: FundAllotmentListComponent,
            pathMatch: "full"
          },
          {
            path: "fund-allotment-details/:id",
            component: FundAllotmentDetailsComponent,
            pathMatch: "full"
          },
        ]
      },

      {
        path: "delete-school",
        component: DeleteSchoolComponent,
        pathMatch: "full",
      },
      {
        path: "delete-school-view/:id",
        component: DeleteSchoolViewComponent,
        pathMatch: "full"

      },


      {
        path: "fund-work-order/:id/:id2",
        component: FundProcessWorkOrderComponent,
        pathMatch: "full"
      },
      {
        path: "my-profile",
        component: MyProfileComponent,
        pathMatch: "full"
      },
      {
        path: "invoice-payment",
        component: InvoicePaymentComponent,
        children: [
          { path: "", redirectTo: "invoice-payment-list", pathMatch: "full" },
          {
            path: "invoice-payment-list",
            component: InvoivePaymentListComponent,
            pathMatch: "full"
          },
          {
            path: "invoice-payment-details/:id",
            component: InvoivePaymentEditComponent,
            pathMatch: "full"
          },
          {
            path: "invoice-payment-view/:id",
            component: InvoivePaymentViewComponent,
            pathMatch: "full"
          },
        ]
      },
      {
        path: "financial-status",
        component: FinancialStatusReportComponent,
        pathMatch: "full"
      },

    ]
  },

  { path: "**", redirectTo: "/login", pathMatch: "full" },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { scrollPositionRestoration: 'enabled' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
