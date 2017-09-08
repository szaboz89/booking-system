package com.szabodev.booking.service.util;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
@Scope
public class BookingConfiguration {

    private static Logger logger = LoggerFactory.getLogger(BookingConfiguration.class);
    private static final String PROPERTY_FILE_NAME = "booking.properties";
    private static final String LOGBACK_PATH_PROPERTY = "logbackPath";

    private Properties properties = new Properties();

    public BookingConfiguration() {
        loadProperties();
    }

    private void loadProperties() {
        Properties systemProperties = System.getProperties();
        String propFileValue = systemProperties.getProperty(PROPERTY_FILE_NAME);
        System.out.println("************ BOOKING APPLICATION STARTING ************");
        System.out.println("************ Prop file path: " + propFileValue);
        try {
            properties.load(new FileInputStream(propFileValue));
            loadLogbackProperties();
            logger.debug("************ Property file path: " + propFileValue);
        } catch (IOException e) {
            logger.error("Properties can not be loaded", e);
            e.printStackTrace();
        }
    }

    private void loadLogbackProperties() {
        String logbackProperty = getProperty(LOGBACK_PATH_PROPERTY);
        if (logbackProperty != null) {
            try {
                LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
                loggerContext.reset();
                JoranConfigurator configurator = new JoranConfigurator();
                InputStream configStream = new FileInputStream(logbackProperty);
                configurator.setContext(loggerContext);
                loggerContext.reset();
                configurator.doConfigure(configStream);
                configStream.close();
                logger = LoggerFactory.getLogger(BookingConfiguration.class);
                logger.info("************ BOOKING APPLICATION STARTED ************");
                logger.debug("************ Logback property path: " + logbackProperty);
            } catch (IOException | JoranException e) {
                e.printStackTrace();
            }
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
