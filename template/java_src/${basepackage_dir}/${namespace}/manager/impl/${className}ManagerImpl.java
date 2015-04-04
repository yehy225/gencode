<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.auto.manager.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${basepackage}.base.dao.BaseDao;
import ${basepackage}.base.manager.impl.BaseManagerImpl;
import ${basepackage}.auto.manager.${className}Manager;
import ${basepackage}.auto.dao.${className}Dao;
import ${basepackage}.auto.model.${className}Mo;


@Service("${classNameLower}Manager")
@Transactional
public class ${className}ManagerImpl extends BaseManagerImpl<${className}Mo,${table.idColumn.javaType}> implements ${className}Manager{

	private ${className}Dao ${classNameLower}Dao;

	public void set${className}Dao(${className}Dao dao) {
		this.${classNameLower}Dao = dao;
	}
	
	public BaseDao<${className}Mo, ${table.idColumn.javaType}> getBaseDao() {
		return this.${classNameLower}Dao;
	}
	
<#list table.columns as column>
	<#if column.unique && !column.pk>
	@Transactional(readOnly=true)
	public ${className}Mo getBy${column.columnName}(${column.javaType} v) {
		return ${classNameLower}Dao.getBy${column.columnName}(v);
	}	
	
	</#if>
</#list>
}
