import { HttpErrorResponse, HttpEvent, HttpHandler, HttpHeaderResponse, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable, Injector } from '@angular/core';
import { Router } from '@angular/router';
import { NgPopupsService } from 'ng-popups';
import { Observable, throwError } from 'rxjs';
import { catchError, finalize, retry, tap } from 'rxjs/operators';
import { CommonService } from './common.service';
import { TranslateService } from '@ngx-translate/core';
import { AlertService } from './_alert';


@Injectable()
export class CustomHttpInterceptor implements HttpInterceptor {
  showPopUp: boolean = false
  private translate: TranslateService
  constructor(private injector: Injector, private router: Router, private ngPopups: NgPopupsService, private cservice: CommonService, private alertToastr: AlertService) {
  }
  sessionExpired = false;
  showpopup: boolean = false
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    this.sessionExpired = false


    // Add Auth Token
    // In production you would get the token value from an auth service
    // const hardcodedToken = '1d38d128-0671-4121-8084-f6332a992a40';
    // req = req.clone({
    //   setHeaders: {
    //     Authorization: `Bearer ${hardcodedToken}`
    //   }
    // });

    return next.handle(req)


      .pipe(
        // Retry on failure
        retry(0),

        //handle success toaster
        tap((success: HttpHeaderResponse) => {
          if ((success.status === 200 || success.status === 201) && (success.headers.get('X-gatewayApp-alert'))) {
            this.translate = this.injector.get(TranslateService);
            this.alertToastr.success(this.translate.instant(success.headers.get('X-gatewayApp-alert')));
          }
        }),

        // Handle errors
        catchError((err: HttpErrorResponse) => {

          // TODO: Add error handling logic here
          var headers = Object.keys(err.headers).filter(function (header) {
            return header.indexOf('app-error', header.length - 'app-error'.length) !== -1 || header.indexOf('app-params', header.length - 'app-params'.length) !== -1;
          }).sort();
          this.translate = this.injector.get(TranslateService);
          if (err.status === 401 || err.status === 403) {
            if (!this.sessionExpired) {
              sessionStorage.accessToken ? this.cservice.getAccount() : ''
              if (this.cservice.isAuthenticated()) {
                delete localStorage.accessToken;
                delete sessionStorage.accessToken;
                this.sessionExpired = true
                var popup = this.ngPopups.alert(
                  'Session expired. You will be redirected to login page...!',
                  {
                    title: 'Alert',
                  });
                popup.subscribe((res) => {
                  if (res) {
                    this.cservice.clearPreviousValues()
                    this.cservice.getAccountCatch()
                    this.router.navigate(['/login']);
                  }
                });
              }
            }
          } else if (err.error == null) {
            if (err.status === 400) {
              this.alertToastr.error(this.translate.instant(err.headers.get('X-gatewayApp-error')))
            }
          }
          return throwError(err);
        }),




        // PROFILING
        finalize(() => {
          const profilingMsg = `${req.method} "${req.urlWithParams}"`;

        })
      );


  }
}