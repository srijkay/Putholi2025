/* ------------------------------------------------------------------------------
 *
 *  # Custom JS code
 *
 *  Place here all your custom js. Make sure it's loaded after app.js
 *
 * ---------------------------------------------------------------------------- */
var Login = function () {
    var handleForgetPassword = function () {
        jQuery('#forget').click(function () {
            jQuery('.login-form').hide();
            jQuery('.forget-form').show();

        });

        jQuery('#back').click(function () {
            jQuery('.login-form').show();
            jQuery('.forget-form').hide();
        });
    }
    return {
        //main function to initiate the module
        init: function () {
            handleForgetPassword();
            jQuery('.forget-form').hide();
        }

    };

}();