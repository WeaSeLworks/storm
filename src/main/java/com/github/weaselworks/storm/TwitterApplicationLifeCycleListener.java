package com.github.weaselworks.storm;

import com.github.weaselworks.storm.route.RouteBuilder;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import javax.servlet.ServletContextListener;

@WebListener
public class TwitterApplicationLifeCycleListener implements ServletContextListener {

    private CamelContext camelContext;
    Logger logger = LoggerFactory.getLogger(CamelContext.class);

    public void contextInitialized(ServletContextEvent event) {
        logger.debug("context initialised");
        camelContext = new DefaultCamelContext();
        try
        {
            camelContext.addRoutes(new RouteBuilder());
            camelContext.start();
        }
        catch(Exception e)
        {
            logger.error("unable to start the camel context ",e);
        }

    }

    public void contextDestroyed(ServletContextEvent event) {
        logger.debug("context destroyed");
        try {
            camelContext.stop();
        } catch (Exception e) {
           logger.error("Problem stopping the camelContext ",e);
        }
    }
}