<div class="container">

<div class="row titulo">
<h1>Nueva reservación</h1>
</div>

	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                
	                <input type="hidden" ng-model="ctrl.reservation.id" />
	                
	                <div class="form-group row">
	                	<label class="control-label" for="user">Usuario</label>
	                    <select id="user" ng-options="user as user.name for user in ctrl.users" ng-model="ctrl.reservation.user" ng-disabled="ctrl.done" class="form-control" required>
	                    	<option value="">Seleccione usuario de la reservación</option>
	                    </select>
	                </div>
	                
	                <div class="form-group row">
	                	<label class="control-label" for="sala">Sala</label>
	                    <select id="sala" ng-options="sala as sala.name+' - capacidad: '+sala.capacity+' personas' for sala in ctrl.rooms" ng-model="ctrl.reservation.room" ng-disabled="ctrl.done" class="form-control" required>
	                    	<option value="">Seleccione la sala de la reservación</option>
	                    </select>
	                </div>
	                
	                <div class="form-group row">
	                	<label class="control-label" for="reservedCapacity">Capacidad a reservar</label>
	                    <input type="number" ng-model="ctrl.reservation.reservedCapacity" ng-readonly="ctrl.done" id="reservedCapacity" class="form-control" placeholder="Ingrese la capacidad a reservar." required ng-pattern="ctrl.onlyIntegers" ng-max="ctrl.reservation.room.capacity"/>
	                    <small class="form-text text-muted" ng-if="ctrl.reservation.room">capacidad máxima a reservar {{ctrl.reservation.room.capacity}} personas</small>
	               	</div>
	                
	                <div class="form-group row">
	                	<label class="control-label" for="fecha">Fecha <small class="form-text text-muted">{{ctrl.reservation.date | date:'yyyy-MM-dd HH:mm'}} </small></label>
	                    <datetimepicker ng-show="!ctrl.done" data-ng-model="ctrl.reservation.date" data-datetimepicker-config="{ startView:'day', minView:'hour' }" data-before-render="ctrl.startDateBeforeRender($dates)" required></datetimepicker>
	                </div>
	

	                <div class="form-group row">
	                	<input type="submit"  value="Guardar" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
	                    <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm">Limpiar</button>
	                </div>
	                    
	            </form>

			</div>
</div>