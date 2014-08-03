package com.quickrant.admin.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.quickrant.admin.Controller;
import com.quickrant.api.database.Database;
import com.quickrant.api.models.CacheStats;
import com.quickrant.api.models.DatabaseStats;
import com.quickrant.api.models.Visitor;
import com.quickrant.api.utils.TimeUtils;

@SuppressWarnings("serial")
public class StatsController extends Controller {

	private static Logger log = Logger.getLogger(StatsController.class);
	
	public static List<String> timeframes = new ArrayList<String>(0);
	
	static {
		/* Last 24 hours */
		timeframes.add("select id, created_at, cookie, ip_address, user_agent from visitors where created_at > (now() - interval '1 day') and is_complete = true");
		timeframes.add("select id, created_at, cookie, ip_address, user_agent from visitors where created_at > (now() - interval '3 days') and is_complete = true");
		timeframes.add("select id, created_at, cookie, ip_address, user_agent from visitors where created_at > (now() - interval '5 days') and is_complete = true");
		timeframes.add("select id, created_at, cookie, ip_address, user_agent from visitors where created_at > (now() - interval '7 days') and is_complete = true");
		timeframes.add("select id, created_at, cookie, ip_address, user_agent from visitors where created_at > (now() - interval '15 days') and is_complete = true");
		timeframes.add("select id, created_at, cookie, ip_address, user_agent from visitors where created_at > (now() - interval '30 days') and is_complete = true");
		timeframes.add("select id, created_at, cookie, ip_address, user_agent from visitors where created_at > (now() - interval '3 months') and is_complete = true");
		timeframes.add("select id, created_at, cookie, ip_address, user_agent from visitors where created_at > (now() - interval '6 months') and is_complete = true");
		timeframes.add("select id, created_at, cookie, ip_address, user_agent from visitors where created_at > (now() - interval '1 year') and is_complete = true");
	}

	public static final String GET_CACHE_STATS = "select * from cache_stats where id in (select max(id) from cache_stats)";
	public static final String GET_DATABASE_STATS = "select * from database_stats where id in (select max(id) from database_stats)";
	
	@Override
	protected String basePath() { return "/stats"; }

	@Override
	protected void initActions(ServletConfig config) {		
		/* Add servlet actions */
		addAction(null, new GetAction());
		addAction("/cache", new CacheAction());
		addAction("/database", new DatabaseAction());
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
				CacheStats cacheStats = (CacheStats) CacheStats.findBySQL(GET_CACHE_STATS).get(0);
				request.setAttribute("cacheStats", cacheStats);
				request.setAttribute("maxAge", TimeUtils.getPastTimestamp(cacheStats.getExpiry()));
				request.setAttribute("visitors", Visitor.findBySQL(sql));
				request.setAttribute("timeframe", timeframe);
				request.setAttribute("pageTitle", "Cache Info");
			} else {
				response.sendError(400);
				return null;
			}			
			return basePath() + "/cache/index.jsp";
		}

		/**
		 * Pull a SQL entry out of the timeframes list
		 * @param timeframe
		 * @return SQL string
		 */
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
				return timeframes.get(0);
			}
		}
	}
		
	/**
	 * Handle GET requests to /database
	 */
	public class DatabaseAction implements Action {
		public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			request.setAttribute("databaseVersion", Database.version);
			request.setAttribute("databaseStats", (DatabaseStats) DatabaseStats.findBySQL(GET_DATABASE_STATS).get(0));
			request.setAttribute("pageTitle", "Database Statistics");
			return basePath() + "/database/index.jsp";
		}
	}
}
