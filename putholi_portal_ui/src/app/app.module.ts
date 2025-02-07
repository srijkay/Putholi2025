import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { NgSelectModule } from '@ng-select/ng-select';
import { NgxUiLoaderModule } from "ngx-ui-loader";
import { PaginationModule } from 'ngx-bootstrap/pagination';


import { PortalComponent } from './portal/portal.component';
import { PortfolioDetailsComponent } from './portal/portfolio-details/portfolio-details.component';
import { DonateNowComponent } from './portal/donate-now/donate-now.component';
import { NsplTextComponent } from './nspl-text/nspl-text.component';
import { NsplMultilineTextComponent } from './nspl-text/nspl-multiline-text/nspl-multiline-text.component';
import { NsplNumberComponent } from './nspl-text/nspl-number/nspl-number.component';
import { NsplCheckboxComponent } from './nspl-text/nspl-checkbox/nspl-checkbox.component';
import { NsplTableSortComponent } from './nspl-text/nspl-table-sort/nspl-table-sort.component';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CanAuthActivate, CanLoginActivate } from './common/auth.gaurd';
import { EnvServiceProvider } from './common/env.service.provider';
import { Broadcaster } from './common/BroadCaster';
import { DatePipe, DecimalPipe } from '@angular/common';
import { NsplAplhaWithSpaceDirective } from './common/directive/nspl-aplha-with-space.directive';
import { NsplAphaOnlyDirective } from './common/directive/nspl-apha-only.directive';
import { SummaryComponent } from './portal/summary/summary.component';
import { TrackDonationComponent } from './portal/track-donation/track-donation.component';
import { PreGalleryComponent } from './portal/pre-gallery/pre-gallery.component';
import { PostGalleryComponent } from './portal/post-gallery/post-gallery.component';
import { ModalModule } from 'ngx-bootstrap/modal';
import { TooltipModule } from 'ngx-bootstrap/tooltip';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { defineLocale, frLocale } from 'ngx-bootstrap/chronos';
import { BaseComponent } from './common/commonComponent';
import { HomeComponent } from './home/home.component';
import { ToastrModule } from 'ng6-toastr-notifications';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SchoolListComponent } from './portal/school-list/school-list.component';
import { CaptchaComponent } from './common/captcha/captcha.component';
import { ForgetPasswordComponent } from './portal/forget-password/forget-password.component';
import { UpdatePasswordComponent } from './portal/update-password/update-password.component';
import { IntlInputPhoneModule } from 'intl-input-phone';
import { TrackDetailsComponent } from './portal/track-details/track-details.component';
import { SuccessPaymentComponent } from './portal/success-payment/success-payment.component';
import { BlockCopyPasteDirective } from './common/block-copy-paste.directive';
import { PrivacyPolicyComponent } from './privacy-policy/privacy-policy.component';
import { RefundCancellationPolicyComponent } from './refund-cancellation-policy/refund-cancellation-policy.component';
import { TermsAndConditionComponent } from './terms-and-condition/terms-and-condition.component';
import { RegistrationPolicyComponent } from './registration-policy/registration-policy.component';

defineLocale('tm', frLocale);
// AoT requires an exported function for factories
export function HttpLoaderFactory(httpClient: HttpClient) {
  return new TranslateHttpLoader(httpClient, "./assets/i18n/", ".json");
}

@NgModule({
  declarations: [
    AppComponent,
    PortalComponent,
    PortfolioDetailsComponent,
    DonateNowComponent,
    NsplTextComponent,
    NsplMultilineTextComponent,
    NsplNumberComponent,
    NsplCheckboxComponent,
    NsplTableSortComponent,
    NsplAplhaWithSpaceDirective,
    NsplNumberComponent,
    NsplAphaOnlyDirective,
    SummaryComponent,
    TrackDonationComponent,
    PreGalleryComponent,
    PostGalleryComponent,
    BaseComponent,
    HomeComponent,
    SchoolListComponent,
    CaptchaComponent,
    ForgetPasswordComponent,
    UpdatePasswordComponent,
    TrackDetailsComponent,
    SuccessPaymentComponent,
    BlockCopyPasteDirective,
    PrivacyPolicyComponent,
    RefundCancellationPolicyComponent,
    TermsAndConditionComponent,
    RegistrationPolicyComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    IntlInputPhoneModule,
    NgxUiLoaderModule,
    NgSelectModule,
    HttpClientModule,
    ModalModule.forRoot(),
    TooltipModule.forRoot(),
    PaginationModule.forRoot(),
    NgbModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
  ],
  providers: [CanLoginActivate, CanAuthActivate, EnvServiceProvider, Broadcaster, DecimalPipe, DatePipe],
  bootstrap: [AppComponent]


})
export class AppModule { }
