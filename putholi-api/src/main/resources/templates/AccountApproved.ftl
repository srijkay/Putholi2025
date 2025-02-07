<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
</head>

<body>
<h4>Dear ${UserName}</h4>

<#if rejected==false>

<h3>Your account was approved. Kindly please login into our application</h3>
</#if>

<#if rejected== true>
<h3>Your account was Rejected. Kindly please contact to admin</h3>
</#if>
<p>Thanks & Regards, </p>
<p>Putholi Project Team</p>
</body>

</html>