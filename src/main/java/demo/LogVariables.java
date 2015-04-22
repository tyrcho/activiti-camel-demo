package demo;

import java.util.Map.Entry;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class LogVariables implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("in activiti");
		for (Entry<String, Object> entry : execution.getVariables().entrySet()) {
			System.out.println(entry.getKey() + " => " + entry.getValue());
		}
		execution.setVariable("varB", "set from activiti");
	}

}
