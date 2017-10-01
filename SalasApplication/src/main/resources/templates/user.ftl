<div class="container">

<div class="row titulo">
<h1>Nuevo usuario</h1>
</div>

	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                
	                <input type="hidden" ng-model="ctrl.user.id" />
	                
	                    <div class="form-group row">
	                        <label class="control-label" for="uname">Nombre</label>
	                        <input type="text" ng-model="ctrl.user.name" ng-readonly="ctrl.done" id="uname" class="form-control" placeholder="Ingrese el nombre" required ng-minlength="3"/>
	                    </div>

	                    <div class="form-group row">
	                        <label class="control-label" for="rol">Rol</label>
	                        <select ng-options="rol for rol in ctrl.roles" ng-model="ctrl.user.rol" ng-disabled="ctrl.done" class="form-control" required>
	                        	<option value="">Seleccione el rol del usuario</option>
	                        </select>
	                    </div>

	                    <div class="form-group row">
	                        <input type="submit"  value="Guardar" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm">Limpiar</button>
	                    </div>
	                    
	            </form>
	            
    	    </div>
</div>