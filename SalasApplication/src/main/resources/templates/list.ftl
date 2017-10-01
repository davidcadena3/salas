<div class="container">

	<div class="row titulo">
		<h1>Salas</h1>
	</div>

	<div class="formcontainer">
		<div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
		<div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	                
				<div class="form-group row">
					<label class="control-label" for="sala">Sala</label>
					<select id="sala" ng-options="sala as sala.name for sala in ctrl.rooms" ng-model="ctrl.roomSelected" ng-change="ctrl.getRoomReservations()" class="form-control" >
						<option value="">Seleccione una sala</option>
					</select>
				</div>
			
			<div class="row" >

				<div class="table-responsive" ng-show="ctrl.reservations.length>0">
		        	<table class="table table-hover">
		            	<thead>
		            		<tr>
		                		<th>Usuario</th>
		                		<th>Capacidad</th>
		                		<th>Capacidad reservada</th>
		                		<th>Fecha reserva</th>
		                		<th>Estado</th>
		            		</tr>
		            	</thead>
		            	<tbody>
		            		<tr ng-repeat="reservation in ctrl.reservations | orderBy:'date':true"">
		                		<td>{{reservation.user.name}}</td>
		                		<td>{{reservation.room.capacity}}</td>
		                		<td>{{reservation.reservedCapacity}}</td>
		                		<td>{{reservation.date | date:'yyyy-MM-dd HH:mm'}}</td>
		                		<td>{{reservation.status}}</td>
		            		</tr>
		            	</tbody>
		        	</table>		
				</div>
			
			</div>
 
	</div>
    	    
</div>