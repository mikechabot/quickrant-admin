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
	      <h3 class="panel-title">Overview</h3>
	    </div>
	    <div class="col-md-3 align-right">
	  	  <span id="timestamp" class="mild-bold"></span>
	    </div>
	  </div>
	  </div>
	  <div class="panel-body">
	    <div class="container half-width">
	    <table class="table table-bordered">
	      <tr>
	      	<th class="align-right">Cache name: </th>
	      	<td>${cacheStats.cacheName}</td>
	      </tr>
	      <tr>
	      	<th class="align-right">Current size: </th>
	      	<td>${cacheStats.entries}</td>
	      </tr>
	      <tr>
	      	<th class="align-right">Entry expiry (in min): </th>
	      	<td>${cacheStats.expiry}</td>
	      </tr>
	      <tr>
	      	<th class="align-right">Next cleaning: </th>
	      	<td>${cacheStats.nextRunTime}</td>
	      </tr>
	      <tr>
	      	<th class="align-right">Purging entries created before: </th>
	      	<td>${helper:getFormattedDateWithSec(maxAge)}</td>
	      </tr>
	    </table>
	    </div>
	  </div>
	</div>
	<div class="panel panel-default">
	  <div class="panel-heading">
	  <div class="row">
	    <div class="col-md-12">
	      <h3 class="panel-title">Cache Entries</h3>
	    </div>
	  </div>
	  </div>
	  <div class="panel-body">
	    <div class="container half-width">
        <form method="get" role="form" action="/stats/cache">
          <select name="timeframe" class="form-control input-sm" onchange="this.form.submit()">
            <option value="0" ${timeframe == '0' ? 'selected' : ''}>Last 24 hours</option>
            <option value="1" ${timeframe == '1' ? 'selected' : ''}>Last 3 days</option>
            <option value="2" ${timeframe == '2' ? 'selected' : ''}>Last 5 days</option>
            <option value="3" ${timeframe == '3' ? 'selected' : ''}>Last 7 days</option>
            <option value="4" ${timeframe == '4' ? 'selected' : ''}>Last 15 days</option>
            <option value="5" ${timeframe == '5' ? 'selected' : ''}>Last 30 days</option>
            <option value="6" ${timeframe == '6' ? 'selected' : ''}>Last 3 months</option>
            <option value="7" ${timeframe == '7' ? 'selected' : ''}>Last 6 months</option>
            <option value="8" ${timeframe == '8' ? 'selected' : ''}>Last 1 year</option>
          </select>
        </form>
        </div>
	  	 <table class="table table-condensed small tablesorter">
	  	 <thead>
	  	 <tr>
	  	   <td colspan="5" class="align-right" valign="bottom"><em>Found <strong>${fn:length(visitors)}</strong> unique cookies</em></td>
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
