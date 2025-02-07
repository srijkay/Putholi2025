(function (window) {
  window.__env = window.__env || {};

  // API url (LOCAL ENVIRONMENT)
  window.__env.apiUrl = 'http://localhost:9000/api';

  window.__env.adminUrl = "http://localhost:4200";

  window.__env.build_Version = '1.0.0',
    window.__env.build_Date = '2023-08-27T00:00:00.000+0000'

  window.__env.ccavenue_redirect_url = "https://test.ccavenue.com/transaction/transaction.do?command=initiateTransaction"


  // Whether or not to enable debug mode
  // Setting this to false will disable console output
  window.__env.enableDebug = true;
}(this));
