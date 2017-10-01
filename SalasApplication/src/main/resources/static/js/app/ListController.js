'use strict';

angular.module('salasApp').controller(
		'ListController',
		[
				'ReservationService',
				'RoomService',
				'$scope',
				function(ReservationService, RoomService, $scope) {

					var self = this;

					self.rooms = [];
					self.reservations = [];

					self.getRoomReservations = getRoomReservations;

					self.roomSelected = null;

					init();
					function init() {
						console.log('init');
						loadRooms();
					}

					function getRoomReservations() {
						ReservationService.getAllRoomReservations(
								self.roomSelected.id).then(function(response) {
							console.log('carga de reservaciones exitosa');
							self.reservations = response;
						}, function(errResponse) {
							console.error('Error cargando reservaciones');
							self.reservations = [];
						});

					}

					function loadRooms() {
						console.log('loadRooms');
						RoomService.loadAllRooms().then(function(response) {
							console.log('carga de salas exitosa');
							self.rooms = response.data;
						}, function(errResponse) {
							console.error('Error cargando salas');
							self.rooms = [];
						});
					}

				}

		]);