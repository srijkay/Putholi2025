<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
</head>

<body>
<h4>Dear Sir/Madam</h4>

<#if type=="REQ">
<h3> The requirements are approved, Kindly please proceed further</h3>
</#if>

<#if type=="DEO">
<h3> DEO is approved, Kindly please proceed further</h3>
</#if>

<p>Thanks & Regards, </p>
<p>Putholi Project Team</p>
</body>

</html>