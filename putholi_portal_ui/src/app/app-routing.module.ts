import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CanAuthActivate, CanLoginActivate } from './common/auth.gaurd';
import { DonateNowComponent } from './portal/donate-now/donate-now.component';
import { PortalComponent } from './portal/portal.component';
import { PortfolioDetailsComponent } from './portal/portfolio-details/portfolio-details.component';
import { PostGalleryComponent } from './portal/post-gallery/post-gallery.component';
import { PreGalleryComponent } from './portal/pre-gallery/pre-gallery.component';
import { SummaryComponent } from './portal/summary/summary.component';
import { TrackDonationComponent } from './portal/track-donation/track-donation.component';
import { HomeComponent } from './home/home.component';
import { SchoolListComponent } from './portal/school-list/school-list.component';
import { ForgetPasswordComponent } from './portal/forget-password/forget-password.component';
import { UpdatePasswordComponent } from './portal/update-password/update-password.component';
import { TrackDetailsComponent } from './portal/track-details/track-details.component';
import { SuccessPaymentComponent } from './portal/success-payment/success-payment.component';
import { ErrorPageComponent } from './error-page/error-page.component';
import { PrivacyPolicyComponent } from './privacy-policy/privacy-policy.component';
import { RefundCancellationPolicyComponent } from './refund-cancellation-policy/refund-cancellation-policy.component';
import { TermsAndConditionComponent } from './terms-and-condition/terms-and-condition.component';
import { RegistrationPolicyComponent } from './registration-policy/registration-policy.component';


const routes: Routes = [
  { path: "", redirectTo: "home", pathMatch: "full" },
  {
    path: "home",
    component: HomeComponent,
    pathMatch: "full"
  },
  {
    path: "cancel",
    component: ErrorPageComponent,
    pathMatch: "full",
  },

  {
    path: "privacy-policy",
    component: PrivacyPolicyComponent,
    pathMatch: "full"
  },
  {
    path: "refund-cancellation",
    component: RefundCancellationPolicyComponent,
    pathMatch: "full"
  },
  {
    path: "terms-and-conditions",
    component: TermsAndConditionComponent,
    pathMatch: "full"
  },
  {
    path: "registration-policy",
    component: RegistrationPolicyComponent,
    pathMatch: "full"
  },
  {
    path: "portal",
    component: PortalComponent,
    children: [
      {
        path: "folioDetail/:id",
        component: PortfolioDetailsComponent,
        pathMatch: "full"
      },
      {
        path: "school-list",
        component: SchoolListComponent,
        pathMatch: "full"
      },
      {
        path: "donate/:id",
        component: DonateNowComponent,
        pathMatch: "full"
      },
      {
        path: "summary/:id",
        component: SummaryComponent,
        pathMatch: "full"
      },
      {
        path: "trackingdonation",
        component: TrackDonationComponent,
        pathMatch: "full"
      },
      {
        path: "pre-gallery/:id",
        component: PreGalleryComponent,
        pathMatch: "full"
      },
      {
        path: "post-gallery/:id",
        component: PostGalleryComponent,
        pathMatch: "full"
      },
      {
        path: "forget-password/:id",
        component: ForgetPasswordComponent,
        pathMatch: "full"
      },
      {
        path: "update-password/:id",
        component: UpdatePasswordComponent,
        pathMatch: "full"
      },
      {
        path: "track-details/:id",
        component: TrackDetailsComponent,
        pathMatch: "full"
      },

      {
        path: "success",
        component: SuccessPaymentComponent,
        pathMatch: "full"
      },
    ]
  },


  { path: "**", redirectTo: "/", pathMatch: "full" },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { scrollPositionRestoration: 'enabled' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
