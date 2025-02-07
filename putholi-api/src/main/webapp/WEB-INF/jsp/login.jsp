<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="/WEB-INF/iscTaglib.tld" prefix="isc"%>
<%-- <fmt:setLocale value="${sessionScope.currentUserPref.userLanguage}" /> --%>
<%-- <fmt:setBundle basename="com.crimsonlogic.tfb.shared.rb.tfbResources" />  --%>
<%@page contentType="text/html; charset=UTF-8" language="java"%>
<fmt:setBundle basename="com.crimsonlogic.bca.core.shared.rb.portalResources" />
<fmt:setLocale value="${param.lang}"/>

<html lang="${param.lang}" xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!-- <meta http-equiv="X-Frame-Options" content="deny"> -->
    <meta http-equiv="Content-Security-Policy" content="frame-ancestors 'none';" />
    <meta http-equiv="Cache-control" content="private">
<meta name="gwt:property" content="locale=${currentLocale}">

<title><fmt:message key = "portal.index.label.ofoq2" /></title>

<link href="../css/all.min.css" rel="stylesheet" type="text/css">						<!-- Font Awesome Minified CSS -->
    <!-- For RTL projects, use bootstrap.rtl instead
<link href="../css/bootstrap.rtl.min.css" rel="stylesheet" type="text/css">	<!-- Bootstrap RTL Minified CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"> <!-- Bootstrap Minified CSS -->
<link href="../css/cluxp.css" rel="stylesheet" type="text/css">							<!-- CL UX Practice R2-S2 Foundation CSS -->
<link href="../css/datatables.min.css" rel="stylesheet" type="text/css">			<!-- DataTables Minified CSS -->
<link href="../css/animate.min.css" rel="stylesheet" type="text/css">				<!-- Animate Minified CSS -->
<link href="../css/aos.min.css" rel="stylesheet" type="text/css">        		<!-- AOS: Animate on Scroll Minified CSS -->
<link href="../css/custom.css" rel="stylesheet" type="text/css">							<!-- Project specific CSS -->


<!-- End of CSS includes -->

<!-- Start of Deferred JS Includes -->
<!-- Delete what you do not need to use, including comments like this line -->
<script defer src="../js/jquery.min.js"></script>           <!-- jQuery Minified JS -->
<script defer src="../js/bootstrap.bundle.min.js"></script> <!-- Bootstrap Bundle Minified JS -->
<script defer src="../js/cluxp.js"></script>                <!-- CL UX Practice R2-S2 Foundation JS -->
<script defer src="../js/datatables.min.js"></script>       <!-- DataTables Minified JS -->
<script defer src="../js/aos.min.js"></script>              <!-- AOS: Animate on Scroll Minified JS -->
<script defer src="../js/faqs.js"></script>                 <!-- CL UX Practice R2-S2 Searchable FAQs JS -->
<script defer src="../js/custom.js"></script>               <!-- Project specific JS -->
<script defer src="../js/announcement.js"></script>
<!-- End of JS Includes -->
<!-- <link href="../css/rtl.css" rel="stylesheet" type="text/css">  -->
<script defer src="../js/cssloader.js"></script>

<script>
    var isomorphicDir = "../tfb/sc/";
</script>
<isc:loadISC cacheOnly="true" skin="Enterprise"
    modules="Analytics,Charts,Containers,Core,DataBinding,Drawing,Forms,Foundation,Grids,RealtimeMessaging"></isc:loadISC>
<script type="text/javascript">
        function launchApplication() {
            var d = new Date();
            winName = 'my_window' + d.getTime();
            window.open("/TFB2/cusLogin/signin.cl?lang=${param.lang}", winName);
        }


  window.onload = function () {
	 // pageDirection();
      getAnnouncementData(1, 4);
  };
    </script>

<!--	<script type="text/javascript">
        function showDiv(arg, arg2) {
            for (i = 1; i <= 7; i++) {
                document.getElementById('div' + i).style.display = 'none';
                document.getElementById('divAnchor' + i).className = "";
            }
            document.getElementById(arg).style.display = 'block';
            document.getElementById(arg2).className = "active";

        }

        function Login() {
            var username = document.getElementById("username").value;
            if (username == "swuser") {
                window.location = "index.html";
            }

            else
                alert("Invalid username password");
        }
</script>
<script type="text/javascript">
        if (self == top) {
            var antiClickjack = document
                    .getElementById("antiClickjack");
            antiClickjack.parentNode.removeChild(antiClickjack);
        } else {
            top.location = self.location;
        }
    </script> -->

</head>

<body>

<div id="loader"><div class="loader-box animate__animated animate__fadeIn"><img src="../images/gridloader.svg" class="loader-object" alt="Loader">Please wait....</div></div>
    <div class="wrapper landing-page" id="page">


        <a href="javascript:" id="scroll-to-top" title="Scroll to Top"><span class="fa-solid fa-chevron-up"></span></a>
        <header class="navbar-container">
            <nav class="navbar navbar-expand-md">
                <div class="container">

                    <!-- Menu Logo -->
            <a class="navbar-brand" href="login.cl?lang=${param.lang}"><img src="../images/bahrain-customs-logo.png"  alt="Bahrain Customs Logo"/></a>
            <div class="navbar-toggler" onclick="menuMove(this)" data-bs-toggle="collapse" data-bs-target="#navbar-items" aria-controls="navbar-items" aria-expanded="false" aria-label="Toggle navigation">
                        <div class="menu-icon">
                            <div class="menu-bar1"></div>
                            <div class="menu-bar2"></div>
                            <div class="menu-bar3"></div>
                        </div>
                    </div>

                    <div class="navbar-collapse" id="navbar-items">

                        <!-- Menu Items -->
                        <ul class="navbar-nav me-auto">
                <li class="nav-item"><a class="nav-link" href="login.cl?lang=${param.lang}" title="Home"><fmt:message key="portal.index.label.home"/></a></li>
                <li class="nav-item"><a class="nav-link" href="about-us.cl?lang=${param.lang}" title="About OFOQ 2"><fmt:message key="portal.index.label.aboutOFOQ"/></a></li>
                <li class="nav-item"><a class="nav-link"  href="faq.cl?lang=${param.lang}" title="FAQs"><fmt:message key = "portal.index.label.faq"/></a></li>
                <li class="nav-item"><a class="nav-link"  href="contactUs.cl?lang=${param.lang}" title="Contact Us"><fmt:message key="portal.index.label.contactUs"/></a></li>
                        </ul>

                        <!-- Right Aligned Search -->
                        <div class="d-flex flex-column flex-md-row">
                            <ul class="navbar-nav ms-auto">
                                 <li class="nav-item">
                                                								  <a  id="english" class="nav-link"    href="?lang=en" title="English">English</a>
                                                								  <a  id="arabic" class="nav-link"  href="?lang=ar" title="Arabic">عربي</a>
                                                							</li>
                                                						</ul>
                 <button class="btn btn-primary ms-3" type="button" aria-label="Login" title="Login" onClick="launchApplication();"><fmt:message key = "portal.login.label.header" /></button>
                        </div>
                    </div>
                </div>
            </nav>

        </header>
        <main class="main-content">

            <div class="banner">
     <div id="carouselExampleIndicators" class="carousel slide carousel-fade" data-bs-ride="carousel" style="border">
                    <div class="carousel-indicators">
          <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
          <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                    </div>
        <div class="carousel-inner" style="margin-top: 150 px;">
                        <div class="carousel-item carousel-item1 active"></div>
                        <div class="carousel-item carousel-item2"></div>
                    </div>

                </div>

                <div class="banner-content">
                    <div class="container">
                        <div class="banner-content-bg">
      	<div class="banner-heading"><small class="d-block"><fmt:message key = "portal.index.label.welcomeTo"/></small><fmt:message key = "portal.index.label.bahrainTradePortal"/><small class="d-block mt-4 mb-3"><fmt:message key = "portal.index.label.welcomeMessage"/></small></div>

                            <div class="row">
      
      	<div class="col-4"><a href="#" class="card"><span class="icon"><i class="fa-thin fa-ship"></i></span><fmt:message key = "portal.index.label.trackShipment" /></a></div>
      	<div class="col-4"><a href="#" class="card"><span class="icon"><i class="fa-thin fa-nfc-magnifying-glass"></i></span><fmt:message key = "portal.index.label.hscode" /></a></div>

                            </div>
                        </div>
                        <div class="banner-content-bg bg-0 mt-5">
                              	<h1 class="text-center mb-4"><fmt:message key="portal.index.label.customs.procedures"/></h1>
                                    <div class="row">
                                	<div class="col-4"><a href="https://www.bahraincustoms.gov.bh/en/internal-decisions-and-circulations" class="card"><fmt:message key="portal.index.label.customs.policies"/><span class="icon"><i class="fa-thin fa-clipboard-list-check"></i></span></a></div>
                                	<div class="col-4"><a href="https://www.bahraincustoms.gov.bh/en/laws-and-regulations" class="card"><fmt:message key="portal.index.label.regulations"/><span class="icon"><i class="fa-thin fa-briefcase"></i></span></a></div>
                                	<div class="col-4"><a href="https://www.bahraincustoms.gov.bh/en/agreements" class="card"><fmt:message key="portal.index.label.agreements"/><span class="icon"><i class="fa-thin fa-file-pen"></i></span></a></div>
                                	</div>
                        </div>

                    </div>

                </div>
            </div>


            <div class="section announcements">
                <div class="container">
			<div class="heading"><fmt:message key = "portal.index.label.announcements" /></div>
                    <div class="row">
				<div id="message" class="row">  </div>  <!--Not remove, div is required for ajax call -->


				<div class="col-lg-12 button-center mt-5"><a href="<c:url value='/portal/announcement/1/0'/>" class="btn btn-secondary btn-action"><fmt:message key="portal.index.label.viewall"/></a></div>
                    </div>
                </div>

            </div>


            <div class="section">
                <div class="container">
	      <div class="heading text-center mb-4"><fmt:message key = "portal.index.label.stakeholders" /></div>
                    <div class="stakeholders">
                        <a href="#">Stakeholder - 1 <br>Logo</a>
                        <a href="#">Stakeholder - 2 <br>Logo</a>
                        <a href="#">Stakeholder - 3 <br>Logo</a>
                        <a href="#">Stakeholder - 4 <br>Logo</a>
                        <a href="#">Stakeholder - 5 <br>Logo</a>
                        <a href="#">Stakeholder - 6 <br>Logo</a>
	       </div>
	    </div>
                    </div>
                </div>



        </main>
<!-- End of Main Content -->

<footer>
<!-- Start of Sitemap -->
  <div class="sitemap">
    <div class="container">
          <div class="row">
            <div class="col-lg-3 col-md-6 sitemap-content">
              <div class="footer-link-heading"><fmt:message key = "portal.index.label.usefullinks" /></div>
            <ul>
                <li><a href="https://www.interior.gov.bh"><fmt:message key = "portal.index.label.ministryofInterior" /></a></li>
                <li><a href="https://www.gcc-sg.org/ar-sa/Pages/default.aspx"><fmt:message key = "portal.index.label.gccInformationCenter" /></a></li>
                <li><a href="http://www.gso.org.sa/qms"><fmt:message key = "portal.index.label.gccIStandardOrganization" /></a></li>
                <li><a href="https://www.wto.org/"><fmt:message key = "portal.index.label.worldTradeOrganisation" /></a></li>
                <li><a href="https://www.tenderboard.gov.bh/"><fmt:message key = "portal.index.label.tenderBoard" /></a></li>
                <li><a href="https://www.nbr.gov.bh/"><fmt:message key = "portal.index.label.nationalBureauforRevenue" /></a></li>
                <li><a href="https://ddei5-0-ctp.trendmicro.com:443/wis/clicktime/v1/query?url=https%3a%2f%2fwww.bahrain.bh%2fwps%2fwcm%2fconnect%2fcc20ef28%2d552d%2d44b5%2d8af5%2d95566fdbe9f9%2fVision%252B2030%252BEnglish%252B%2528low%252Bresolution%2529.pdf%3fMOD%3dAJPERES%26CVID%3dok0S2gy&umid=A9E8A4E4-FD9C-FE05-920A-3B0DD134D10E&auth=24bf937bda0706e9662a14886ea24b4723860357-6bd7f4eeb8927da7381cb316aa2ea2bf40317a5a"><fmt:message key = "portal.index.label.bahrainVision" /></a></li>
            </ul>
            </div>
            <div class="col-lg-3 col-md-6  sitemap-content">
             <div class="footer-link-heading"><fmt:message key = "portal.index.label.otherresources" /></div>
            <ul>
                <li><a href="faq.cl?lang=${param.lang}"><fmt:message key = "portal.index.label.faq" /></a></li>
                <li><a href="accessibilityStatement.cl?lang=${param.lang}"><fmt:message key = "portal.index.label.accessibilityStatement" /></a></li>
                <li><a href="#"><fmt:message key = "portal.index.label.egovernmentPortal" /></a></li>
            </ul>
            </div>
            <div class="col-lg-6 col-md-12 sitemap-content sitemap-content-right">
              <div class="footer-link-heading"><fmt:message key = "portal.index.label.contactUs" /></div>
            <p><fmt:message key = "portal.index.label.address" /></p>
            <p class="footer-contact">
                <span class="phoneNo"><i class="fa-light fa-phone-rotary"></i>17389999</span>
                <span class="watsappNo"><a href="#" target="_blank"><i class="fa-brands fa-whatsapp"></i>17389999</a></span>
                <span class="emailId"><a href="mailto:customerservice@customs.gov.bh"><i class="fa-light fa-envelope"></i>customerservice@customs.gov.bh</a></span>
              </p>
            </div>
          </div>
    </div>
  </div>
  <!-- End of Sitemap -->

  <!-- Start of Footer Block -->
  <div class="footer">
    <div class="container">
      <div class="copyright">
      <a href="privacyPolicy.cl?lang=${param.lang}"><fmt:message key = "portal.index.label.policy" /> </a>
      <a href="#"><fmt:message key = "portal.index.label.termsOfUse" /></a><span class="fa-light fa-copyright"></span> <fmt:message key = "portal.index.label.copyright2022BahrainCustomsAffairs" />
    </div>
      <div class="social-link-block">
        <a href="https://www.youtube.com/channel/UCCIob99i8NY4a6Dpfn05rpQ" class="social-link youtube" title="Take me to our youtube page" target="_blank" rel="noreferrer"><span class="fa-brands fa-youtube"></span></a>
        <a href="https://twitter.com/Customs_bah" class="social-link twitter" title="Take me to our Twitter page" target="_blank" rel="noreferrer"><span class="fa-brands fa-twitter"></span></a>
        <a href="https://www.instagram.com/customs.bh/" class="social-link instagram" title="Take me to our Instagram page" target="_blank" rel="noreferrer"><span class="fa-brands fa-instagram"></span></a>
      </div>
      <div class="clearfix"></div>
    </div>
  </div>
  <!-- End of Footer Block -->
</footer>


    </div>
<!-- End of Loader Encased Body Wrapper -->

    <script type="text/javascript">
        function selectLanguage() {
            var locale = document.getElementById("locales").value;
            window.location.href = "/TFB2/cusLogin/login.cl?lang=" + locale;
        }
    </script>

</body>

</html>
