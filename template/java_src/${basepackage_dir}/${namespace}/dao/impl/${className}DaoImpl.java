<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.auto.dao.impl;

import org.springframework.stereotype.Repository;

import ${basepackage}.base.dao.impl.BaseDaoImpl;
import ${basepackage}.auto.dao.${className}Dao;
import ${basepackage}.auto.model.${className}Mo;


@Repository("${classNameLower}Dao")
public class ${className}DaoImpl extends BaseDaoImpl<${className}Mo,${table.idColumn.javaType}> implements ${className}Dao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "${className}";
	}
	
	@Override
	protected void prepareObjectForSave(${className}Mo entity) {

	}
	
	@Override
	protected void prepareObjectForUpdate(${className}Mo entity){
		
	}

	<#list table.columns as column>
	<#if column.unique && !column.pk>
	public ${className} getBy${column.columnName}(${column.javaType} v) {
		return (${className})getSqlSession().selectOne("${className}.getBy${column.columnName}",v);
	}	
	
	</#if>
	</#list>
}
