'use strict';

angular
		.module('salasApp')
		.controller(
				'ReservationController',
				[
						'ReservationService',
						'RoomService',
						'UserService',
						'$scope',
						function(ReservationService, RoomService, UserService,
								$scope) {

							var self = this;

							self.reservation = {};

							self.submit = submit;
							self.createReservation = createReservation;
							self.reset = reset;
							self.startDateBeforeRender = startDateBeforeRender;

							self.rooms = [];
							self.users = [];

							init();
							function init() {
								console.log('init');
								loadRooms();
								loadUsers();
							}

							function submit() {
								console.log('Submitting');
								if (self.reservation.id === undefined
										|| self.reservation.id === null) {
									console.log('Creando la nueva reserva',
											self.reservation);
									createReservation(self.reservation);
								}
							}

							function createReservation(reservation) {
								console
										.log('createReservation: '
												+ reservation);
								ReservationService
										.createReservation(reservation)
										.then(
												function(response) {
													console
															.log('Reserva creada exitosamente');
													self.successMessage = 'Reserva creada exitosamente';
													self.errorMessage = '';
													self.done = true;
													$scope.myForm
															.$setPristine();
												},
												function(errResponse) {
													console
															.error('Error creando la reserva');
													self.errorMessage = 'Error durante la creación: '
															+ errResponse.data.errorMessage;
													self.successMessage = '';
												});
							}

							function loadRooms() {
								console.log('loadRooms');
								RoomService
										.loadAllRooms()
										.then(
												function(response) {
													console
															.log('carga de salas exitosa');
													self.rooms = response.data;
												},
												function(errResponse) {
													console
															.error('Error cargando salas');
													self.rooms = [];
												});
							}

							function loadUsers() {
								console.log('loadUsers');
								UserService
										.loadAllUsers()
										.then(
												function(response) {
													console
															.log('carga de usuarios exitosa');
													self.users = response.data;
												},
												function(errResponse) {
													console
															.error('Error cargando usuarios');
													self.users = [];
												});
							}

							function reset() {
								self.successMessage = '';
								self.errorMessage = '';
								self.reservation = {};
								self.done = false;
								$scope.myForm.$setPristine();
							}

							function startDateBeforeRender($dates) {
								const
								todaySinceMidnight = new Date();
								// todaySinceMidnight.setUTCHours(15);
								// todaySinceMidnight.setUTCHours(0, 0, 0, 0);
								$dates
										.filter(
												function(date) {
													return date.utcDateValue < todaySinceMidnight
															.getTime();
												}).forEach(function(date) {
											date.selectable = false;
										});
							}

						}

				]);