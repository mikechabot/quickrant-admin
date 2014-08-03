<%@ page language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tags/helper.tld" prefix="helper"%>
<c:import url="/header.jsp" />
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<ol class="breadcrumb">
  				<li><a href="/">Home</a></li>
  				<li class="active">Database Statistics</li>
  			</ol>
		</div>
	</div>
</div>
<div class="container">
	<div class="panel panel-custom">
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
			<div class="container three-quarter-width">
				<div class="panel panel-custom">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3 align-right">
								<strong>Database version</strong>:
							</div>
							<div class="col-md-9">${databaseVersion}</div>
						</div>
					</div>
				</div>
				<div class="panel panel-custom">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3 align-right">
								<strong>Stats last updated</strong>:
							</div>
							<div class="col-md-9">${helper:getFormattedDateWithSec(databaseStats.createdAt)}</div>
						</div>
					</div>
				</div>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-md-4 col-md-offset-1">
						<div class="panel panel-custom">
							<div class="panel-heading">
								<h3 class="panel-title">Connection Information</h3>
							</div>
							<table class="table table-bordered">
								<tr>
									<th class="align-right">Free connections:</th>
									<td>${databaseStats.freeConnections}</td>
								</tr>
								<tr>
									<th class="align-right">Leased connections:</th>
									<td>${databaseStats.leasedConnections}</td>
								</tr>
								<tr>
									<th class="align-right">Total connections created:</th>
									<td>${databaseStats.totalCreatedConnections}</td>
								</tr>
							</table>
						</div>
					</div>
					<div class="col-md-4 col-md-offset-1">
						<div class="panel panel-custom">
							<div class="panel-heading">
								<h3 class="panel-title">BoneCP Connection Pool</h3>
							</div>
							<table class="table table-bordered">
								<tr>
									<th class="align-right">Total Partitions:</th>
									<td>${databaseStats.bonePartitions}</td>
								</tr>
								<tr>
									<th class="align-right">Max connections per partition:</th>
									<td>${databaseStats.boneMaxConnections}</td>
								</tr>
								<tr>
									<th class="align-right">Min connections per partition:</th>
									<td>${databaseStats.boneMinConnections}</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4 col-md-offset-1">
						<div class="panel panel-custom">
							<div class="panel-heading">
								<h3 class="panel-title">Cache Stats</h3>
							</div>
							<table class="table table-bordered">
								<tr>
									<th class="align-right">Total hits:</th>
									<td>${databaseStats.cacheHits}</td>
								</tr>
								<tr>
									<th class="align-right">Total misses:</th>
									<td>${databaseStats.cacheMisses}</td>
								</tr>
								<tr>
									<th class="align-right">Hit ratio:</th>
									<td>${databaseStats.cacheHitRatio}</td>
								</tr>
							</table>
						</div>
					</div>
					<div class="col-md-4 col-md-offset-1">
						<div class="panel panel-custom">
							<div class="panel-heading">
								<h3 class="panel-title">Connection Performance</h3>
							</div>
							<table class="table table-bordered">
								<tr>
									<th class="align-right">Average connection wait:</th>
									<td>${databaseStats.avgConnectionWait}</td>
								</tr>
								<tr>
									<th class="align-right">Average execution:</th>
									<td>${databaseStats.avgExecution}</td>
								</tr>
								<tr>
									<th class="align-right">Average preparation:</th>
									<td>${databaseStats.avgPrepare}</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<c:import url="/footer.jsp" />
