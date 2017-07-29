package com.cisco.cmad.blog.test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebAppListener implements ServletContextListener {

	private  DatabaseSetup dbs = null;

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println(" **********************WEB APP START**********************************");
		if (dbs == null) {
			dbs = DatabaseSetup.getInstance();
		}
		dbs.initDatabaseSample();
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println(" **********************WEB APP SHUTDOWN**********************************");
		dbs.cleanConnections();
	}

}