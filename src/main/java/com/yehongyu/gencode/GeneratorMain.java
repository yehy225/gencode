package com.yehongyu.gencode;

import cn.org.rapid_framework.generator.Generator;
import cn.org.rapid_framework.generator.GeneratorFacade;
import cn.org.rapid_framework.generator.GeneratorProperties;

public class GeneratorMain {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Generator g = new Generator();
		g.addTemplateRootDir("template");
		g.setOutRootDir("/Users/yehongyu/Documents/work/generator-output");
		
		GeneratorFacade gf = new GeneratorFacade();
		gf.setGenerator(g);
		gf.deleteOutRootDir();	//删除生成器的输出目录
//		gf.generateByTable("record_message","record_login","activity","activity_enroll","contact","contact_relation","for_verify","group_apply","group_def","group_member","poll","poll_option","poll_pick");	//通过数据库表生成文件
		//打开文件夹
//		Runtime.getRuntime().exec("cmd.exe /c start " + GeneratorProperties.getRequiredProperty("outRoot"));
	}

}
