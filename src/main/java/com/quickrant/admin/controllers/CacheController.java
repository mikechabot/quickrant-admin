package com.quickrant.admin.controllers;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quickrant.admin.Controller;
import com.quickrant.admin.utils.Utils;
import com.quickrant.api.Params;
import com.quickrant.api.models.Rant;
import com.quickrant.api.services.EmotionService;
import com.quickrant.api.services.QuestionService;
import com.quickrant.api.services.RantService;
import com.quickrant.api.services.VisitorService;


public class CacheController extends Controller {

	private static final long serialVersionUID = 1L;
	
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
	 * Handle GET requests to /rant
	 */
	public class GetAction implements Action {
		public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			String action = request.getPathInfo();
			
			/* Match action to root (/rant/) or /rant/[number] */
			if (action == null || action.equals("") || action.equals("/")) {
				visitorSvc.fetch("is_active", value)
			}
			return basePath() + "/index.jsp";
		}	
	}
}