import { Component, OnInit, OnChanges, Input, Output, EventEmitter, Injector } from "@angular/core";
import { BaseComponent } from "../commonComponent";
// import { CaptchaService } from './captcha.service';
@Component({
  selector: "app-captcha",
  templateUrl: "./captcha.component.html",
  styleUrls: ["./captcha.component.css"],
})
export class CaptchaComponent extends BaseComponent implements OnInit {
  @Input("config") config: any = {};
  @Output() captcha: EventEmitter<any> = new EventEmitter();

  @Input()
  public required = false;

  @Input()
  public data: any = {};

  @Output() captchaCode = new EventEmitter();
  captch_input: any = null;
  code: any = null;
  resultCode: any = null;
  captchaData: any = {}
  constructor(inj: Injector) {
    super(inj)
  }
  ngOnInit() {


    if (this.config) {
      if (!this.config.font || !this.config.fontsize) {
        this.config["fontsize"] = "40px";
      }
      if (!this.config.font || !this.config.fontfamily) {
        this.config["fontfamily"] = "Arial";
      }
      if (!this.config.strokeColor) {
        this.config["strokeColor"] = "#f20c6c";
      }
      if (!this.config.length) {
        this.config["length"] = 6;
      }
      if (!this.config.cssClass) {
        this.config["cssClass"] = '';
      }

      if (!this.config.type) {
        this.config["type"] = 1;
      }

      if (!this.config.back || !this.config.backstroke) {
        this.config["backstroke"] = "#2F9688";
      }
      if (!this.config.back || !this.config.backsolid) {
        this.config["backsolid"] = "#f2efd2";
      }

      this.createCaptcha();
    }
  }
  createCaptcha() {
    this.captchaData.captch_input = ''
    switch (this.config.type) {
      case 1: // only alpha numaric degits to type
        let char =
          Math.random()
            .toString(24)
            .substring(2, this.config.length) +
          Math.random()
            .toString(24)
            .substring(2, 4);
        this.code = this.resultCode = char.toUpperCase();

        break;
      case 2: // solve the calculation 
        let num1 = Math.floor(Math.random() * 99);
        let num2 = Math.floor(Math.random() * 9);
        let operators = ['+', '-'];
        let operator = operators[(Math.floor(Math.random() * operators.length))];
        this.code = num1 + operator + num2 + '=?';
        this.resultCode = (operator == '+') ? (num1 + num2) : (num1 - num2);
        break;
    }
    setTimeout(() => {

      let captcahCanvas: any = document.getElementById("captcahCanvas");

      var ctx = captcahCanvas.getContext("2d");

      ctx.fillStyle = this.config.backsolid;
      ctx.fillRect(0, 0, captcahCanvas.width, captcahCanvas.height);
      ctx.beginPath();
      captcahCanvas.style.letterSpacing = 2 + "px";
      ctx.font = this.config.fontsize + " " + this.config.fontfamily;
      ctx.fillStyle = "black";
      ctx.textBaseline = "middle";
      ctx.fillText(this.code, 40, 50);
      if (this.config.backstroke) {
        ctx.strokeStyle = this.config.backstroke;
        for (var i = 0; i < 150; i++) {
          ctx.moveTo(Math.random() * 300, Math.random() * 300);
          ctx.lineTo(Math.random() * 300, Math.random() * 300);
        }
        ctx.stroke();
      }

      // this.captchaCode.emit(char);
    }, 100);
  }
  playCaptcha() {
    var msg = new SpeechSynthesisUtterance(this.code.split('').join(' '));
    msg.pitch = 1;
    window.speechSynthesis.speak(msg);
  }

  isValidCaptcha: boolean = false;
  checkCaptcha() {
    if (this.captchaData.captch_input != this.resultCode) {
      this.isValidCaptcha = false
      this.captcha.emit(false)
    } else {
      this.isValidCaptcha = true;
      this.captcha.emit(true)
    }
  }
}
