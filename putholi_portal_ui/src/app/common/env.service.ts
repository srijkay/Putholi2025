export class EnvService {

  // The values that are defined here are the default values that can
  // be overridden by env.js

  // API url
  public apiUrl = '';
  public build_Version='';
  public build_Date='';
  public ccavenue_redirect_url = '';


  // Whether or not to enable debug mode
  public enableDebug = true;
  public adminUrl: any ='';

  constructor() {
  }

}
