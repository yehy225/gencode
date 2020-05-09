<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.auto.manager;

import ${basepackage}.base.manager.BaseManager;
import ${basepackage}.auto.model.${className}Mo;

public interface ${className}Manager extends BaseManager<${className}Mo, ${table.idColumn.javaType}>{
	
	<#list table.columns as column>
		<#if column.unique && !column.pk>
		@Transactional(readOnly=true)
		public ${className}Mo getBy${column.columnName}(${column.javaType} v);
		
		</#if>
	</#list>
}
