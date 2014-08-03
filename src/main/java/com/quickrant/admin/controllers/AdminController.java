package com.quickrant.admin.controllers;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quickrant.admin.Controller;

@SuppressWarnings("serial")
public class AdminController extends Controller {

	@Override
	protected String basePath() { return ""; }
	
	@Override
	protected void initActions(ServletConfig config) {
		addAction(null, new GetAction());
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
			request.setAttribute("pageTitle", "Admin Tools");
			return basePath() + "/index.jsp";
		}	
	}

}
