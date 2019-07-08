package com.ibm.vms.config;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ibm.vms.config.EventListener;

@Configuration
@EnableTransactionManagement
@MapperScan("com.ibm.vms.dao.mapper")
public class ActivitiConfig  {

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private EventListener eventListener;

    /**
     * 初始化配置，创建流程引擎表
     *
     * @return
     */
    @Bean
    public StandaloneProcessEngineConfiguration processEngineConfiguration() {
        StandaloneProcessEngineConfiguration configuration = new StandaloneProcessEngineConfiguration();
        configuration.setDataSource(dataSource);
        // 设置是否自动更新
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        configuration.setAsyncExecutorActivate(false);

        //configuration.setCustomMybatisMappers(Collections.singleton(ProcessMapper.class));
        //configuration.setActiviti5CustomMybatisXMLMappers(Collections.singleton("classpath:mapper/ProcessMapper.xml"));
        // 注册全局监听器
        List<ActivitiEventListener>  activitiEventListener=new ArrayList<>();
        activitiEventListener.add(eventListener );
        configuration.setEventListeners(activitiEventListener);
        return configuration;
    }

    /**
     * Process Engine
     *
     * @return
     */
    @Bean
    public ProcessEngine getProcessEngine() {
        return processEngineConfiguration().buildProcessEngine();
    }

    /**
     * Repository Service
     *
     * @return
     */
    @Bean
    public RepositoryService getRepositoryService() {
        return getProcessEngine().getRepositoryService();
    }

    /**
     * Runtime Service
     *
     * @return
     */
    @Bean
    public RuntimeService getRuntimeService() {
        return getProcessEngine().getRuntimeService();
    }

    /**
     * Form Service
     *
     * @return
     */
    @Bean
    public FormService getFormService() {
        return getProcessEngine().getFormService();
    }

    /**
     * Task Service
     *
     * @return
     */
    @Bean
    public TaskService getTaskService() {
        return getProcessEngine().getTaskService();
    }

    /**
     * History Service
     *
     * @return
     */
    @Bean
    public HistoryService getHistoryService() {
        return getProcessEngine().getHistoryService();
    }

    /**
     * Identity Service
     *
     * @return
     */
    @Bean
    public IdentityService getIdentityService() {
        return getProcessEngine().getIdentityService();
    }

    /**
     * managment service
     *
     * @return
     */
    @Bean
    public ManagementService getManagementService() {
        return getProcessEngine().getManagementService();
    }

}

