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
    <h3>Dear User</h3>
<#if rejected=="false">
  <p>Approved Requirements</p>
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

</#if>

<#if rejected== "true">
 <p>Rejected Requirements</p>
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
</#if>


<#if rejected== "COMPLETED">
 <#list requirementInfo as requirementInfo>
<h3>Your requirements for asset type: ${requirementInfo.assetType}, asset name: ${requirementInfo.assetName}
 approvals are successfully completed.</h3>
  </#list>
 </#if>
         
      
</table>



    <p>
        <h3>Thanks & Regards, </h3>
        <h3>Putholi Team.</h3>
    </p>
</body>

</html>