(function (window) {
  window.__env = window.__env || {};

  // API url (LOCAL ENVIRONMENT)
  // window.__env.apiUrl = 'http://localhost:9000/api';
  // window.__env.adminUrl = "http://localhost:4200";

  // API url (PROD ENVIRONMENT)
  window.__env.apiUrl = 'https://putholitrust.com/api';
  window.__env.adminUrl = "https://putholitrust.com/admin";

  window.__env.build_Version = '2.0.0',
  window.__env.build_Date = '2026-06-12T00:00:00.000+0000'

  //payment gateway redirect url for testing
  // window.__env.ccavenue_redirect_url = "https://test.ccavenue.com/transaction/transaction.do?command=initiateTransaction"

  //payment gateway redirect url for production
  window.__env.ccavenue_redirect_url = "https://secure.ccavenue.com/transaction/transaction.do?command=initiateTransaction"

  // Whether or not to enable debug mode
  // Setting this to false will disable console output
  window.__env.enableDebug = true;
}(this));
