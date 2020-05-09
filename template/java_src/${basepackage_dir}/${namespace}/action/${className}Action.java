<#include "/custom.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign actionExtension = "do">
package ${basepackage}.auto.action;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.util.ObjectUtils;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import ${basepackage}.base.action.BaseAction;
import ${basepackage}.auto.manager.${className}Manager;
import ${basepackage}.auto.model.${className}Mo;
import ${basepackage}.auto.query.${className}Query;


public class ${className}Action extends BaseAction implements Preparable,ModelDriven<${className}Mo>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String LIST_JSP= "${jspFileBasePath}/list.jsp";
	protected static final String CREATE_JSP = "${jspFileBasePath}/create.jsp";
	protected static final String EDIT_JSP = "${jspFileBasePath}/edit.jsp";
	protected static final String SHOW_JSP = "${jspFileBasePath}/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!${actionBasePath}/list.do";
	
	private ${className}Manager ${classNameLower}Manager;
	
	private ${className}Mo ${classNameLower}Mo;
	<#list table.compositeIdColumns as column>
	${column.javaType} id = null;
	</#list>
	private String[] items;
	
	public void prepare() throws Exception {
		if (ObjectUtils.isNullOrEmptyString(id)) {
			${classNameLower}Mo = new ${className}Mo();
		} else {
			${classNameLower}Mo = (${className}Mo)${classNameLower}Manager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void set${className}Manager(${className}Manager manager) {
		this.${classNameLower}Manager = manager;
	}	
	
	public ${className}Mo getModel() {
		return ${classNameLower}Mo;
	}
	
	<#list table.compositeIdColumns as column>
	public void set${column.columnName}(${column.javaType} val) {
		this.id = val;
	}
	</#list>	

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		
		${className}Query query = newQuery(${className}Query.class,DEFAULT_SORT_COLUMNS);
		Page<${className}Mo> page = ${classNameLower}Manager.findPage(query);
		
		getRequest().setAttribute("page", page);
		return LIST_JSP;
	}
	
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		return CREATE_JSP;
	}
	
	/** 保存新增对象 */
	public String save() {
		${classNameLower}Manager.save(${classNameLower}Mo);
		return writeAjaxResponse("1");
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		${classNameLower}Manager.update(this.${classNameLower}Mo);
		return writeAjaxResponse("1");
	}
	
	/**删除对象*/
	public String delete() {
		if(items!=null)
			for(String ids:items){
				String[] idArray = ids.split(",");
				for(String id:idArray)
				${classNameLower}Manager.removeById(Integer.valueOf(id));
			}
		return writeAjaxResponse("1");
	}
}
