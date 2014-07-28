<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tags/helper.tld" prefix="helper" %> 
<c:import url="/header.jsp"/>
  <div class="container">
	<div class="page-header">
	  <h1>Admin Tools <small>quickrant</small></h1>
	</div>
  </div>
  <div class="container">
	<div class="panel panel-default">
	  <div class="panel-heading">
	  <div class="row">
	    <div class="col-md-9">
	      <h3 class="panel-title">Click for more information:</h3>
	    </div>
	    <div class="col-md-3 align-right">
	  	  <span id="timestamp" class="mild-bold"></span>
	    </div>
	  </div>
	  </div>
	  <div class="panel-body">
        <ul>
          <li><a href="/stats/cache">Cache Stats</a></li>
          <li><a href="/stats/database">Database Stats</a></li>
	    </ul>
	  </div>
	</div>
  </div>
<c:import url="/footer.jsp"/>


