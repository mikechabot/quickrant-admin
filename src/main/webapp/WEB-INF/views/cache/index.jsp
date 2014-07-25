<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tags/helper.tld" prefix="helper" %> 
<c:import url="/header.jsp"/>
  <div class="container">
	<div class="page-header">
	  <h1>Cache Info <small>quickrant</small></h1>
	</div>
  </div>
  <div class="container">
	<div class="panel panel-default">
	  <div class="panel-heading">
	  <div class="row">
	    <div class="col-md-9">
	      <h3 class="panel-title">Cache Entries</h3>
	    </div>
	    <div class="col-md-3 align-right">
	  	  <span id="timestamp" class="mild-bold"></span>
	    </div>
	  </div>
	  </div>
	  <div class="panel-body">
	    <div class="container half-width">
        <form method="get" role="form" action="/cache">
          <select name="timeframe" class="form-control input-sm" onchange="this.form.submit()">
            <option value="1">Last 24 hours</option>
            <option value="2">Last 3 days</option>
            <option value="3">Last 5 days</option>
            <option value="4">Last 7 days</option>
            <option value="5">Last 15 days</option>
            <option value="6">Last 30 days</option>
            <option value="7">Last 3 months</option>
            <option value="8">Last 6 months</option>
            <option value="9">Last 1 year</option>
          </select>
        </form>
        </div>
	  	 <table class="table table-condensed small tablesorter">
	  	 <thead>
	  	 <tr>
	  	   <td colspan="5" class="align-right" valign="bottom"><em>Found <strong>${fn:length(visitors)}</strong> unique cookies (past 1 year)</em></td>
	  	 </tr>
	  	 <tr>
	  	   <th>Id</th>
	  	   <th>Created At</th>
	  	   <th>Cookie Value</th>
	  	   <th>Ip Address</th>
	  	   <th>User-Agent</th>
	  	   </tr>
	  	 </thead>
	  	 <tbody>
		 <c:choose>
		   <c:when test="${fn:length(visitors) > 0}">
		     <c:forEach items="${visitors}" var="visitor">
			   <tr>
			     <td>${visitor.id}</td>
			  	 <td>${helper:getFormattedDate(visitor.createdAt)}</td>
			  	 <td>${visitor.cookie}</td>
			  	 <td>${visitor.ipAddress}</td>
			  	 <td>${visitor.userAgent}</td>
			  	</tr>
		  	  </c:forEach>
			</c:when>
			<c:otherwise>
			  <td colspan="2"><em>No cookies found</em></td>
            </c:otherwise>
	      </c:choose>
		  </tbody>
	  	 </table>
	  </div>
	</div>
  </div>
<c:import url="/footer.jsp"/>
