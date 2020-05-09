<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.auto.manager;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.org.rapid_framework.test.context.TestMethodContext;

import ${basepackage}.BaseTestCase;
import ${basepackage}.auto.manager.${className}Manager;
import ${basepackage}.auto.model.${className}Mo;

public class ${className}ManagerTest extends BaseTestCase{

	private ${className}Manager manager;
	
	@Autowired
	public void set${className}Manager(${className}Manager manager) {
		this.manager = manager;
	}

    @Override
    protected String[] getDbUnitDataFiles() {
        //通过 TestMethodContext.getMethodName() 可以得到当前正在运行的测试方法名称
        return new String[]{"classpath:testdata/common.xml","classpath:testdata/${className}.xml",
                            "classpath:testdata/${className}_"+TestMethodContext.getMethodName()+".xml"};
    }

	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void crud() {

		${className}Mo obj = new${className}();
		manager.save(obj);
		
		manager.update(obj);
		
	<#if table.compositeId>
		assertNotNull(manager.getById(obj.getId()));
		
		manager.removeById(obj.getId());
	<#else>
//		Assert.assertNotNull(obj.get${table.idColumn.columnName}());
		
		manager.removeById(obj.get${table.idColumn.columnName}());
	</#if>
	
	}
	
	public static ${className}Mo new${className}() {
		${className}Mo obj = new ${className}Mo();
		
		<#list table.columns as column>
	  		<#if column.isNotIdOrVersionField>
	  			<#if column.isDateTimeColumn>
	  	obj.set${column.columnName}(new ${column.javaType}(System.currentTimeMillis()));
	  			<#else>
	  	obj.set${column.columnName}(new ${column.javaType}("1"));
	  			</#if>
			</#if>
		</#list>
		return obj;
	}
}
