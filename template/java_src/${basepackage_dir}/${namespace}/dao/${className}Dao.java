<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.auto.dao;

import ${basepackage}.base.dao.BaseDao;
import ${basepackage}.auto.model.${className}Mo;

public interface ${className}Dao extends BaseDao<${className}Mo, ${table.idColumn.javaType}>{
	
	<#list table.columns as column>
	<#if column.unique && !column.pk>
	public ${className}Mo getBy${column.columnName}(${column.javaType} v);
	</#if>
	</#list>

}
