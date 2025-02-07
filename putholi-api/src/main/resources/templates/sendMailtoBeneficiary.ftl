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

        }

        .col-sm-4 {
            flex: 0 0 33.33333%;
            width: 33.33333%;

        }

        .col-md-6 {
            flex: 0 0 50%;
            max-width: 100%;

        }

        .font-weight-600 {
            font-weight: 600 !important;
        }

        .form-inline {
            margin-top: -13px !important;
        }
    </style>

</head>

<body>
    <h3>Dear ${UserName}</h3>

    <div class="row">
        <div class="col-md-6">
        <#if rejected==false>

           <p>This email is to notify you that,<br />
                The DEO is approved your school</p>
        </#if>

         <#if rejected== true>
               <p>This email is to notify you that,<br />
                The DEO is rejected your school</p>
          </#if>
            
        </div>
    </div>
    <br />
    <pre>
        <h3>Thanks & Regards, </h3>
        <h3>Putholi Team.</h3>
    </pre>
</body>

</html>