package com.quickrant.admin;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.quickrant.api.Configuration;
import com.quickrant.api.database.Database;

public class Bootstrap implements ServletContextListener {

	private static Logger log = Logger.getLogger(Bootstrap.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		String path = context.getRealPath("WEB-INF/etc");

		try {
			/* Load the version */
			Version.load();
			log.info("quickrant-admin, v" + Version.display());

			/* Load the configuration */
			Configuration conf = Configuration.getInstance();
			conf.initialize(path);
			log.info("Loaded rant.properties");

			/* Database stuff */
			Database database = new Database();
			database.startStatisticsJob();
			log.info("Database reached: " + database.getVersion());

			log.info("Bootstrapping complete...");

		} catch (Exception e) {
			log.fatal("Could not start application", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("Context destroyed");
	}

}