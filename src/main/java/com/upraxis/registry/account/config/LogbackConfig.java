/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upraxis.registry.account.config;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import java.io.IOException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 *
 * @author Jesse Ballera
 */
@Configuration
public class LogbackConfig implements ApplicationListener<ContextRefreshedEvent> {
    
    @Autowired
    YAMLConfig yAMLConfig;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent e) {
        try {
            configureLogback();
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private void configureLogback() throws IOException {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        try {
            JoranConfigurator jc = new JoranConfigurator();
            jc.setContext(context);
            context.reset(); // override default configuration
            // inject the name of the current application as "application-name"
            // property of the LoggerContext
            context.putProperty("LOG_DIR", yAMLConfig.getLogback().getLog().getDirectory());
            context.putProperty("LOG_NAME_FILE", yAMLConfig.getLogback().getLog().getLogFileName());
            context.putProperty("LOG_NAME_ERROR", yAMLConfig.getLogback().getLog().getLogFileError());

            ClassLoader classLoader = getClass().getClassLoader();
            jc.doConfigure(classLoader.getResourceAsStream(yAMLConfig.getLogback().getLog().getFileDirectory()));
        } catch (JoranException e) {
            e.printStackTrace(System.out);
        }
    }
}

