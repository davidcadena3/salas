var app = angular.module('salasApp', [ 'ui.router', 'ngStorage',
		'ui.bootstrap.datetimepicker' ]);

app.constant('urls', {
	BASE : 'http://localhost:8080/salas',
	USER_SERVICE_API : 'http://localhost:8080/salas/user/',
	ROOM_SERVICE_API : 'http://localhost:8080/salas/room/',
	RESERVATION_SERVICE_API : 'http://localhost:8080/salas/reservation/'
});

app.config([ '$stateProvider', '$urlRouterProvider',
		function($stateProvider, $urlRouterProvider) {

			$stateProvider.state('home', {
				url : '/',
				templateUrl : 'partials/main'
			}).state('usuario', {
				url : '/usuario',
				templateUrl : 'partials/user',
				controller : 'UserController',
				controllerAs : 'ctrl'
			}).state('sala', {
				url : '/sala',
				templateUrl : 'partials/room',
				controller : 'RoomController',
				controllerAs : 'ctrl'
			}).state('reserva', {
				url : '/reserva',
				templateUrl : 'partials/reservation',
				controller : 'ReservationController',
				controllerAs : 'ctrl'
			}).state('listaSalas', {
				url : '/listar',
				templateUrl : 'partials/list',
				controller : 'ListController',
				controllerAs : 'ctrl'
			}).state('gestionar', {
				url : '/gestionar',
				templateUrl : 'partials/manage',
				controller : 'ManageController',
				controllerAs : 'ctrl'
			});
			$urlRouterProvider.otherwise('/');
		} ]);
