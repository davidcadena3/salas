<div class="container">

	<div class="row titulo">
		<h1>Gestionar reservas</h1>
	</div>

	<div class="formcontainer">
		<div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
		<div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	                
			<div class="row" >
			
				<div ng-show="ctrl.reservations.length<1">
					<span>Actualmente no existen reservas pendientes por gestionar.</span>
				</div>

				<div class="table-responsive" ng-show="ctrl.reservations.length>0">
		        	<table class="table table-hover">
		            	<thead>
		            		<tr>
		                		<th>Sala</th>
		                		<th>Usuario</th>
		                		<th>Capacidad</th>
		                		<th>Capacidad reservada</th>
		                		<th>Fecha reserva</th>
		                		<th>Acciones</th>
		            		</tr>
		            	</thead>
		            	<tbody>
		            		<tr ng-repeat="reservation in ctrl.reservations | orderBy:'date':true"">
		                		<td>{{reservation.room.name}}</td>
		                		<td>{{reservation.user.name}}</td>
		                		<td>{{reservation.room.capacity}}</td>
		                		<td>{{reservation.reservedCapacity}}</td>
		                		<td>{{reservation.date | date:'yyyy-MM-dd HH:mm'}}</td>
		                		<td>
		                			<button type="button" ng-click="ctrl.confirm(reservation)" class="btn btn-primary btn-sm">Confirmar</button>
		                			<button type="button" ng-click="ctrl.remove(reservation)" class="btn btn-danger btn-sm">Eliminar</button>
		                		</td>
		            		</tr>
		            	</tbody>
		        	</table>		
				</div>
			
			</div>
 
	</div>
    	    
</div>