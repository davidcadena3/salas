'use strict';

angular
		.module('salasApp')
		.controller(
				'ManageController',
				[
						'ReservationService',
						'$scope',
						function(ReservationService, $scope) {

							var self = this;

							self.reservations = [];

							self.confirm = confirm;
							self.remove = remove;

							init();
							function init() {
								console.log('init');
								loadPendingReservations();
							}

							function loadPendingReservations() {
								ReservationService
										.getAllPendingReservations()
										.then(
												function(response) {
													console
															.log('carga de reservaciones pendientes exitosa');
													self.reservations = response;
												},
												function(errResponse) {
													console
															.error('Error cargando reservaciones pendientes');
													self.reservations = [];
												});

							}

							function confirm(reservation) {
								console.log('confirm: ' + reservation.id);
								ReservationService
										.confirm(reservation, reservation.id)
										.then(
												function(response) {
													console
															.log('Reserva confirmada exitosamente');
													self.successMessage = 'Reserva confirmada exitosamente';
													self.errorMessage = '';
													loadPendingReservations();
												},
												function(errResponse) {
													console
															.error('Error confirmando la reserva');
													self.errorMessage = 'Error durante la confirmación: '
															+ errResponse.data.errorMessage;
													self.successMessage = '';
												});
							}

							function remove(reservation) {
								console.log('remove: ' + reservation.id);
								ReservationService
										.remove(reservation.id)
										.then(
												function(response) {
													console
															.log('Reserva eliminada exitosamente');
													self.successMessage = 'Reserva eliminada exitosamente';
													self.errorMessage = '';
													loadPendingReservations();
												},
												function(errResponse) {
													console
															.error('Error eliminando la reserva');
													self.errorMessage = 'Error durante la eliminación: '
															+ errResponse.data.errorMessage;
													self.successMessage = '';
												});
							}

						}

				]);