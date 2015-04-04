<%@ page import="${basepackage}.auto.model.*" %>
<#include "/macro.include"/> 
<#include "/custom.include"/> 
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign actionExtension = "do"> 
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=${className}Mo.TABLE_ALIAS%>信息</title>
	<link href="<@jspEl 'ctx'/>/statics/css/managerSystem.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="title01">
	<h2><%=${className}Mo.TABLE_ALIAS%>详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
	<#list table.columns as column>
	<#if !column.htmlHidden>
		<tr>	
			<td class="txtr" style="width: 188px"><%=${className}Mo.ALIAS_${column.constantName}%>:</td>	
			<td><#rt>
			<#compress>
			<#if column.isDateTimeColumn>
			<c:out value="<@jspEl 'model.'+column.columnNameLower+'String'/>"/>
			<#else>
			<c:out value="<@jspEl 'model.'+column.columnNameLower/>"/>
			</#if>
			</#compress>
			<#lt></td>
		</tr>
	</#if>
	</#list>
	</table>
	</body>
</html>