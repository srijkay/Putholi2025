<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
</head>

<body>
<h4>Dear User</h4>

<#if isRejected=="ADD">
<h3> ${description} was added by ${roleName}. Kindly please proceed further</h3>
</#if>

<#if isRejected=="Quote">
<h3>Got the total contribution amount. Kindly please proceed further</h3>
</#if>

<#if isRejected=="CHANGE">
<h3>Change role request initiated by super user. Kindly please proceed further</h3>
</#if>

<#if isRejected=="DELETE">
<h3>${description} was Deleted by ${roleName}. Kindly please proceed further</h3>
</#if>

<#if isRejected=="true">
<h3> ${description} was Rejected by ${roleName}. Kindly please proceed further</h3>
</#if>

<#if isRejected== "false">
<h3>${description} was approved by ${roleName}. Kindly please proceed further</h3>
</#if>

<#if isRejected== "Completion">
<h3>${description} was approved and all requirements were closed</h3>
</#if>

<p>Thanks & Regards, </p>
<p>Putholi Project Team</p>
</body>

</html>