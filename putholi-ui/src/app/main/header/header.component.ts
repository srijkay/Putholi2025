import { Component, Injector, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/common/commonComponent';
import { TranslateService } from '@ngx-translate/core';
import { SocialUser } from 'ng-social-login-module';
declare var $: any;


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent extends BaseComponent implements OnInit {

  public homeInfo: any = {}
  public base64Image = 'data:image/png;base64,'
  constructor(inj: Injector, public translate: TranslateService) {
    super(inj);
    const browserLang = translate.getBrowserLang();
    this.homeInfo.lang = browserLang;
    this.translate.use(browserLang.match(/en|tm/) ? browserLang : 'en');
    localStorage.setItem("Language", browserLang.match(/tm|tm-TM/) ? 'tm' : 'en')
    this.setToken("Language", browserLang.match(/tm|tm-TM/) ? 'tm' : 'en')
  }

  userData: any = {}
  ngOnInit(): void {
    this.userData = JSON.parse(this.getToken('socialUser'))

  }

  translateLanguage(language) {
    this.setToken("Language", language);
    localStorage.setItem("Language", language);
    this.translate.use(language);
  }

}
