package com.quickrant.admin.controllers;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javalite.activejdbc.Model;

import com.quickrant.admin.Controller;
import com.quickrant.admin.utils.Utils;
import com.quickrant.api.services.VisitorService;

public class AdminCacheController extends Controller {

	private static final long serialVersionUID = 1L;
	
	public static final String POPULATE_COOKIE_CACHE = "select id, created_at, cookie, ip_address, user_agent from visitors where created_at > (now() - interval '365 days') and is_complete = true";
	
	private VisitorService visitorSvc;

	@Override
	protected String basePath() { return ""; }
	
	@Override
	protected void initActions(ServletConfig config) {
		/* Add dependencies */
		setVisitorService(config.getInitParameter("visitor-service"));
		
		/* Add servlet actions */
		addAction(null, new GetAction());
	}
	
	private void setVisitorService(String visitorSvcClass) {
		visitorSvc = (VisitorService) Utils.newInstance(visitorSvcClass);
	}

	@Override
	protected Action defaultAction() {
		return new GetAction();
	}

	/**
	 * Handle GET requests to /cache
	 */
	public class GetAction implements Action {
		public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			List<Model> visitors = visitorSvc.fetchBySql(POPULATE_COOKIE_CACHE);
			request.setAttribute("visitors", visitors);
			request.setAttribute("cache-size", visitors.size());
			return basePath() + "/cache/index.jsp";
		}	
	}
	
}