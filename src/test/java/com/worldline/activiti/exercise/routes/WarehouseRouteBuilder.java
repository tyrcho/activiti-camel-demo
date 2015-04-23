package com.worldline.activiti.exercise.routes;

import java.util.Map.Entry;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class WarehouseRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("activiti:activiti2Camel:sendListOfProducts?copyVariablesFromProperties=true").process(logProcessor);
		from("direct:start").to("activiti:camel2Activiti?var.return.exampleCamelReturnValue&var.return.vara").process(logProcessor);
	}

	Processor logProcessor = new Processor() {
		@Override
		public void process(Exchange exchange) throws Exception {
			System.out.println("camel properties");
			for (Entry<String, Object> entry : exchange.getProperties().entrySet()) {
				System.out.println(entry.getKey() + " => " + entry.getValue());
			}
			exchange.getProperties().put("varB", "a value from camel");
		}
	};
}
