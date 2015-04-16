package com.worldline.activiti.exercise;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.apache.camel.CamelContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCamel2Activiti {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("activiti.cfg.xml");
		ProcessEngine processEngine = applicationContext.getBean(ProcessEngine.class);
		CamelContext camel = applicationContext.getBean(CamelContext.class);
		RepositoryService repositoryService = processEngine.getRepositoryService();
		repositoryService.createDeployment().addClasspathResource("processes/camel2Activiti.bpmn").deploy();

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("vara", "value a");
		camel.createProducerTemplate().sendBody("direct:start", variables);

	}
}
