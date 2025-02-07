
import { Component, OnInit, Injector } from '@angular/core';
import { BaseComponent } from '../../common/commonComponent';
declare var $: any;
@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent extends BaseComponent implements OnInit {
  public base64Image = 'data:image/png;base64,'

  constructor(inj: Injector) {
    super(inj)
    this.getProfileData(this.getToken('username'));
  }

  userData: any = {}

  ngOnInit(): void {
    this.getRoles();

    var selector = '.nav li';
    $(selector).on('click', function () {
      $(selector).removeClass('active');
      $(this).addClass('active');
    });


    $('.sidebar').on('click', 'li > a', function (e) {

      var parent = $(this).parent().parent();
      var the = $(this);

      parent.children('li.nav-item-open').children('a').children('span').removeClass('nav-item-open');
      parent.children('li.nav-item-open').children('.nav-group-sub').slideUp(200);
      parent.children('li.nav-item-open').removeClass('nav-item-open');

      var sub = $(this).next();
      var slideSpeed = 200;


      if (sub.is(":visible")) {
        $('span', $(this)).removeClass("nav-item-open");
        $(this).parent().removeClass("nav-item-open");
        sub.slideUp(slideSpeed);
      } else {
        $('span', $(this)).addClass("nav-item-open");
        $(this).parent().addClass("nav-item-open");
        sub.slideDown(slideSpeed);
      }

      e.preventDefault();
    });


    const sidebarMainElement = $('.sidebar-main'),
      sidebarMainToggler = $('.sidebar-main-resize'),
      resizeClass = 'sidebar-main-resized',
      unfoldClass = 'sidebar-main-unfold';


    // Define variables
    const unfoldDelay = 150;
    let timerStart,
      timerFinish;

    // Toggle classes on click
    sidebarMainToggler.on('click', function (e) {
      sidebarMainElement.toggleClass(resizeClass);
      !sidebarMainElement.hasClass(resizeClass) && sidebarMainElement.removeClass(unfoldClass);
    });

    // Add class on mouse enter
    sidebarMainElement.on('mouseenter', function () {
      clearTimeout(timerFinish);
      timerStart = setTimeout(function () {
        sidebarMainElement.hasClass(resizeClass) && sidebarMainElement.addClass(unfoldClass);
      }, unfoldDelay);
    });

    // Remove class on mouse leave
    sidebarMainElement.on('mouseleave', function () {
      clearTimeout(timerStart);
      timerFinish = setTimeout(function () {
        sidebarMainElement.removeClass(unfoldClass);
      }, unfoldDelay);
    });


    const sidebarElement = $('.sidebar-main'),
      sidebarMainRestElements = $('.sidebar:not(.sidebar-main):not(.sidebar-component)'),
      sidebarMainDesktopToggler = $('.sidebar-main-toggle'),
      sidebarMainMobileToggler = $('.sidebar-mobile-main-toggle'),
      sidebarCollapsedClass = 'sidebar-collapsed',
      sidebarMobileExpandedClass = 'sidebar-mobile-expanded';

    // On desktop
    sidebarMainDesktopToggler.on('click', function (e) {
      e.preventDefault();
      sidebarElement.toggleClass(sidebarCollapsedClass);
    });


    // On mobile views
    sidebarMainMobileToggler.on('click', function (e) {
      console.log('Sidebar click toggler touched');
      e.preventDefault();
      sidebarElement.toggleClass(sidebarMobileExpandedClass);
      sidebarMainRestElements.removeClass(sidebarMobileExpandedClass);
    });




    //remove the mobile-expand-class when we click the sidebar menu in mobile view
    $('.nav').on('click', 'li .active', function () {
      sidebarElement.toggleClass(sidebarMobileExpandedClass);
      sidebarMainRestElements.removeClass(sidebarMobileExpandedClass);
    });





    // Toggle secondary sidebar Elements
    const sidebarSecondaryElement = $('.sidebar-secondary'),
      sidebarSecondaryRestElements = $('.sidebar:not(.sidebar-secondary):not(.sidebar-component)'),
      sidebarSecondaryDesktopToggler = $('.sidebar-secondary-toggle'),
      sidebarSecondaryMobileToggler = $('.sidebar-mobile-secondary-toggle')


    // On desktop
    sidebarSecondaryDesktopToggler.on('click', function (e) {
      e.preventDefault();
      sidebarSecondaryElement.toggleClass(sidebarCollapsedClass);
    });



    // Toggle right sidebar
    const sidebarRightElement = $('.sidebar-right'),
      sidebarRightRestElements = $('.sidebar:not(.sidebar-right):not(.sidebar-component)'),
      sidebarRightDesktopToggler = $('.sidebar-right-toggle'),
      sidebarRightMobileToggler = $('.sidebar-mobile-right-toggle')

    // On desktop
    sidebarRightDesktopToggler.on('click', function (e) {
      e.preventDefault();
      sidebarRightElement.toggleClass(sidebarCollapsedClass);
    });

    // On mobile
    sidebarRightMobileToggler.on('click', function (e) {
      e.preventDefault();
      sidebarRightElement.toggleClass(sidebarMobileExpandedClass);
      sidebarRightRestElements.removeClass(sidebarMobileExpandedClass);
    });



    // Toggle component sidebar Elements
    const sidebarComponentElement = $('.sidebar-component'),
      sidebarComponentMobileToggler = $('.sidebar-mobile-component-toggle')

    // Toggle classes
    sidebarComponentMobileToggler.on('click', function (e) {
      e.preventDefault();
      sidebarComponentElement.toggleClass(sidebarMobileExpandedClass);
    });

  }



  /****************************************************************************
     @PURPOSE      : Retriving Profile data
     @PARAMETERS   : username
     @RETURN       : NA
 ****************************************************************************/
  userProfileData: any = {}

  getProfileData(username) {
    this.ngxLoader.start();

    setTimeout(() => {
      this.commonService.callApi('authenticate/' + username, '', 'get', false, false, 'REG').then(success => {
        let successData: any = success;

        this.userProfileData = successData

        if (this.userProfileData.referredBy == null) {
          this.userProfileData.role = this.roleDetails.description
        } else {
          this.userProfileData.role = "Referred Volunteer"
        }

        this.ngxLoader.stop();
      }).catch(e => {
        this.ngxLoader.stop();
        this.toastr.errorToastr(e.message, 'Oops!')
      });
    })

  }
  /********************************************************************************/


}
