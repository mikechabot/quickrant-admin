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
	  	 <table class="table table-condensed small">
	  	 	<tr>
	  	 	<tr><td colspan="5" align="right"><em>Found <strong>${fn:length(visitors)}</strong> unique cookies (past 1 year)</em></td></tr>
	  	 	<tr>
	  	 	  <th>Id</th>
	  	 	  <th>Created At</th>
	  	 	  <th>Cookie Value</th>
	  	 	  <th>Ip Address</th>
	  	 	  <th>User-Agent</th>
	  	 	</tr>
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
	  	 </table>
	  </div>
	</div>
  </div>
<c:import url="/footer.jsp"/>
