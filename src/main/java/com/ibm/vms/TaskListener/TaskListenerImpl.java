package com.ibm.vms.TaskListener;

import java.util.Arrays;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.JavaDelegate;

public class TaskListenerImpl implements JavaDelegate {  
    @Override  
    public void execute(DelegateExecution execution) {  
        execution.setVariable("pers", Arrays.asList("1", "2"));  

}
}