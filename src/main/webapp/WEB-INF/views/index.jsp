<%@ page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tags/helper.tld" prefix="helper" %> 
<c:import url="/header.jsp"/>
  <div class="container">
	<div class="panel panel-default">
	  <div class="panel-heading">
	  <div class="row">
	    <div class="col-md-10">
	      <h3 class="panel-title">Click for more information:</h3>
	    </div>
	    <div class="col-md-2">
	  	  <em>
	  	    <script>
	  	      var date = new Date($.now()).toLocaleString();
	  	      document.write(date);
	  	    </script>
          </em>
	    </div>
	  </div>
	  </div>
	  <div class="panel-body">
        <ul>
          <li><a href="/cache">Cache Stats</a></li>
	    </ul>
	  </div>
	</div>
  </div>
<c:import url="/footer.jsp"/>


