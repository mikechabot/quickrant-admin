package com.quickrant.admin.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.quickrant.admin.Controller;
import com.quickrant.admin.utils.Utils;
import com.quickrant.api.database.Database;
import com.quickrant.api.models.CacheStats;
import com.quickrant.api.models.DatabaseStats;
import com.quickrant.api.services.CacheStatsService;
import com.quickrant.api.services.DatabaseStatsService;
import com.quickrant.api.services.VisitorService;
import com.quickrant.api.utils.TimeUtils;

public class StatsController extends Controller {

	private static Logger log = Logger.getLogger(StatsController.class);
	
	private static final long serialVersionUID = 1L;
	
	public static final String LAST_24_HOURS = "select id, created_at, cookie, ip_address, user_agent from visitors where created_at > (now() - interval '1 day') and is_complete = true";
	public static final String LAST_3_DAYS = "select id, created_at, cookie, ip_address, user_agent from visitors where created_at > (now() - interval '3 days') and is_complete = true";
	public static final String LAST_5_DAYS = "select id, created_at, cookie, ip_address, user_agent from visitors where created_at > (now() - interval '5 days') and is_complete = true";
	public static final String LAST_7_DAYS = "select id, created_at, cookie, ip_address, user_agent from visitors where created_at > (now() - interval '7 days') and is_complete = true";
	public static final String LAST_15_DAYS = "select id, created_at, cookie, ip_address, user_agent from visitors where created_at > (now() - interval '15 days') and is_complete = true";
	public static final String LAST_30_DAYS = "select id, created_at, cookie, ip_address, user_agent from visitors where created_at > (now() - interval '30 days') and is_complete = true";
	public static final String LAST_3_MONTHS = "select id, created_at, cookie, ip_address, user_agent from visitors where created_at > (now() - interval '3 months') and is_complete = true";
	public static final String LAST_6_MONTHS = "select id, created_at, cookie, ip_address, user_agent from visitors where created_at > (now() - interval '6 months') and is_complete = true";
	public static final String LAST_1_YEAR = "select id, created_at, cookie, ip_address, user_agent from visitors where created_at > (now() - interval '1 year') and is_complete = true";
	public static final String GET_CACHE_STATS = "select * from cache_stats where id in (select max(id) from cache_stats)";
	public static final String GET_DATABASE_STATS = "select * from database_stats where id in (select max(id) from database_stats)";
	
	public static List<String> timeframes = new ArrayList<String>(0);
	
	private VisitorService visitorSvc;	
	private CacheStatsService cacheStatsSvc;
	private DatabaseStatsService databaseStatsSvc;

	@Override
	protected String basePath() { return "/stats"; }

	@Override
	protected void initActions(ServletConfig config) {
		/* Add dependencies */
		setVisitorService(config.getInitParameter("visitor-service"));
		setCacheStatsService(config.getInitParameter("cache-stats-service"));
		setDatabaseStatsService(config.getInitParameter("database-stats-service"));
		
		/* Populate the timeframes list */
		timeframes.add(LAST_24_HOURS);
		timeframes.add(LAST_3_DAYS);
		timeframes.add(LAST_5_DAYS);
		timeframes.add(LAST_7_DAYS);
		timeframes.add(LAST_15_DAYS);
		timeframes.add(LAST_30_DAYS);
		timeframes.add(LAST_3_MONTHS);
		timeframes.add(LAST_6_MONTHS);
		timeframes.add(LAST_1_YEAR);
		
		/* Add servlet actions */
		addAction(null, new GetAction());
		addAction("/cache", new CacheAction());
		addAction("/database", new DatabaseAction());
	}

	private void setDatabaseStatsService(String databaseStatsSvcClass) {
		databaseStatsSvc = (DatabaseStatsService) Utils.newInstance(databaseStatsSvcClass);
	}

	private void setCacheStatsService(String cacheStatsSvcClass) {
		cacheStatsSvc = (CacheStatsService) Utils.newInstance(cacheStatsSvcClass);
	}

	private void setVisitorService(String visitorSvcClass) {
		visitorSvc = (VisitorService) Utils.newInstance(visitorSvcClass);
	}

	@Override
	protected Action defaultAction() {
		return new GetAction();
	}

	/**
	 * Handle GET requests to /stats
	 */
	public class GetAction implements Action {
		public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			return "/index.jsp";
		}	
	}

	/**
	 * Handle GET requests to /cache
	 */
	public class CacheAction implements Action {
		public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
			/* Get URL parameters and convert to SQL */
			String timeframe = request.getParameter("timeframe");
			String sql = getCacheStatsSql(timeframe);
			if (sql != null && !sql.isEmpty()) {
				CacheStats cacheStats = (CacheStats) cacheStatsSvc.fetchBySql(GET_CACHE_STATS).get(0);
				request.setAttribute("cacheStats", cacheStats);
				request.setAttribute("maxAge", TimeUtils.getPastTimestamp(cacheStats.getExpiry()));
				request.setAttribute("visitors", visitorSvc.fetchBySql(sql));
				request.setAttribute("timeframe", timeframe);
			} else {
				response.sendError(400);
				return null;
			}			
			return basePath() + "/cache/index.jsp";
		}

		private String getCacheStatsSql(String timeframe) {
			if (timeframe != null && !timeframe.isEmpty()) {
				try {
					int index = Integer.parseInt(timeframe);
					if (index >= 0 && index < timeframes.size()) {
						return timeframes.get(index);
					} else {
						return null;
					}
				} catch (NumberFormatException e) {
					return null;
				}
			} else {
				return LAST_24_HOURS;
			}
		}
	}
	
	/**
	 * Handle GET requests to /database
	 */
	public class DatabaseAction implements Action {
		public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			request.setAttribute("databaseVersion", Database.version);
			DatabaseStats stats = (DatabaseStats) databaseStatsSvc.fetchBySql(GET_CACHE_STATS).get(0);
			log.info(stats.toString());
			request.setAttribute("databaseStats", (DatabaseStats) databaseStatsSvc.fetchBySql(GET_DATABASE_STATS).get(0));
			return basePath() + "/database/index.jsp";
		}	
	}

}
