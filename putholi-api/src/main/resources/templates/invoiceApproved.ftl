<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
</head>

<body>
<h4>Dear User</h4>

<#if rejected=="false">
<h3>Your ${invoiceId} Invoice Id was approved. Please wait for the payment to complete</h3>
</#if>

<#if rejected== "true">
<h3>Your ${invoiceId}Invoice was rejected. Kindly please login to your account and verify your details</h3>
</#if>

<#if rejected== "payment">
<h3>Payment Completed to Invoice Id ${invoiceId}. Kindly please proceed further and add your receipt</h3>
</#if>
<p>Thanks & Regards, </p>
<p>Putholi Project Team</p>
</body>

</html>