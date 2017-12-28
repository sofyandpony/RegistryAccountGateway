/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upraxis.registry.account.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author jesse
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class YAMLConfig {
    
    private Logback logback;

    public Logback getLogback() {
        return logback;
    }

    public void setLogback(Logback logback) {
        this.logback = logback;
    }
    
    public static class Logback {
        private Log log;

        public Log getLog() {
            return log;
        }

        public void setLog(Log log) {
            this.log = log;
        }
        public static class Log {
            private String directory;
            private String logFileName;
            private String logFileError;
            private String fileDirectory;

            public String getDirectory() {
                return directory;
            }

            public void setDirectory(String directory) {
                this.directory = directory;
            }

            public String getLogFileName() {
                return logFileName;
            }

            public void setLogFileName(String logFileName) {
                this.logFileName = logFileName;
            }

            public String getLogFileError() {
                return logFileError;
            }

            public void setLogFileError(String logFileError) {
                this.logFileError = logFileError;
            }

            public String getFileDirectory() {
                return fileDirectory;
            }

            public void setFileDirectory(String fileDirectory) {
                this.fileDirectory = fileDirectory;
            }
        }
    }
}
