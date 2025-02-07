<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en" xmlns:th="http://www.thymeleaf.org">

<head>

    <style>
        body {
            flex: 1 1 auto;
            min-height: 1px;
            padding: 1.25rem;
        }

        td,
        th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        table {
            width: 100%;
        }

        .text-muted {
            color: #999 !important;
        }

        .row {
            display: flex;
            flex-wrap: wrap;
            margin-right: -0.625rem;
            width:300px;
        }

        .col-sm-4 {
            flex: 0 0 33.33333%;
            width: 100%;

        }

        .col-md-6 {
            flex: 0 0 50%;
            max-width: 100%;

        }

        .font-weight-600 {
            font-weight: 600 !important;
        }

        .col-sm-3{
            margin-right: 14px;
        }
    </style>

</head>

<body>

    <h3>Dear Sir/Madam</h3>
      
    <p style="text-indent: 60px;">We received below mentioned requirements from the school mentioned, we will try out best to fulfill the needs as mentioned here by approaching the larger audience through
     our trust and keep you updated with final status on this below mentioned requirement details. 
     Kindly go through below and provide your approval to take up this further.
    </p>
    <br/>
    
    <p style="text-indent: 60px;">Requesting your approval to proceed with below mentioned requirements for <b> ${schoolName}. </b> </p>

    <div class="row">
        <div class="col-md-6">
            <h3 class="text-muted">School Information</h3>
            <div class="row">
                <label class="col-sm-3 font-weight-600">Registration Number :</label>
                <div class="col-md-3 form-inline">
                    <p style="margin-top: 2px;">${registrationNumber}</p>
                </div>
            </div>

            <div class="row">
                <label class="col-sm-3 font-weight-600">School Name :</label>
                <div class="col-md-3 form-inline">
                    <p style="margin-top: 2px;">${schoolName}</p>
                </div>
            </div>

            <div class="row">
                <label class="col-sm-3 font-weight-600">School Type :</label>
                <div class="col-md-3 form-inline">
                    <p style="margin-top: 2px;">${schoolType}</p>
                </div>
            </div>

            <div class="row">
                <label class="col-sm-3 font-weight-600">Contact Number :</label>
                <div class="col-md-3 form-inline">
                    <p style="margin-top: 2px;">${contactNumber}</p>
                </div>
            </div>
            <div class="row">
                <label class="col-sm-3 font-weight-600">Locality :</label>
                <div class="col-md-3 form-inline">
                    <p style="margin-top: 2px;">${locality}</p>
                </div>
            </div>
            <div class="row">
                <label class="col-sm-3 font-weight-600">Pincode :</label>
                <div class="col-md-3 form-inline">
                    <p style="margin-top: 2px;">${pincode}</p>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <h3 class="text-muted">Beneficiary Information</h3>

            <div class="row">
                <label class="col-sm-3 font-weight-600">First Name :</label>
                <div class="col-md-3 form-inline">
                    <p style="margin-top: 0px;">${firstName}</p>
                </div>
            </div>
            <div class="row">
                <label class="col-sm-3 font-weight-600">Email :</label>
                <div class="col-md-3 form-inline">
                    <p style="margin-top: 0px;">${email}</p>
                </div>
            </div>
            <div class="row">
                <label class="col-sm-3 font-weight-600">Contact Number :</label>
                <div class="col-md-3 form-inline">
                    <p style="margin-top: 0px;">${phoneNumber}</p>
                </div>
            </div>
        </div>
    </div>

    <div class="col-md-6">
        <h3 class="text-muted">Requirement Information</h3>
    </div>
    <div class="row">
        <label class="col-sm-3 font-weight-600">Consolidate Reference Number :</label>
        <div class="col-md-2 form-inline">
            <p style="margin-top: 2px;">${consolidateId}</p>
        </div>
    </div>
    <table class="table-bordered">
        <tr>
            <th>Requirement Type</th>
            <th>Asset Type</th>
            <th>Asset Name</th>
            <th>Quantity</th>
                <th>Status</th>
        </tr>


        <#list requirementInfo as requirementInfo>
            <tr>
                <td>${requirementInfo.requirementType}</td>
                <td>${requirementInfo.assetType}</td>
                <td>${requirementInfo.assetName}</td>
                <td>${requirementInfo.quantity}</td>
                <td>${requirementInfo.reqStatus}</td>
            </tr>
        </#list>


    </table>
    <br />
    
    
 	
    <br />

    <p>${mailBody}</p>

    <p>
        <h3>Thanks & Regards, </h3>
        <h3>Putholi Team.</h3>
    </p>
</body>

</html>