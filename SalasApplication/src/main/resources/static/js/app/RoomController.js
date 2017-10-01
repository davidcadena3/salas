'use strict';

angular
		.module('salasApp')
		.controller(
				'RoomController',
				[
						'RoomService',
						'$scope',
						function(RoomService, $scope) {

							var self = this;

							self.room = {};

							self.submit = submit;
							self.createRoom = createRoom;
							self.reset = reset;

							self.successMessage = '';
							self.errorMessage = '';
							self.done = false;

							self.onlyIntegers = /^\d+$/;

							function submit() {
								console.log('Submitting');
								if (self.room.id === undefined
										|| self.room.id === null) {
									console.log('Creando la nueva sala',
											self.room);
									createRoom(self.room);
								}
							}

							function createRoom(room) {
								console.log('createRoom: ' + room);
								RoomService
										.createRoom(room)
										.then(
												function(response) {
													console
															.log('Sala creada exitosamente');
													self.successMessage = 'Sala creada exitosamente';
													self.errorMessage = '';
													self.done = true;
													$scope.myForm
															.$setPristine();
												},
												function(errResponse) {
													console
															.error('Error creando la sala');
													self.errorMessage = 'Error durante la creación: '
															+ errResponse.data.errorMessage;
													self.successMessage = '';
												});
							}

							function reset() {
								self.successMessage = '';
								self.errorMessage = '';
								self.room = {};
								self.done = false;
								$scope.myForm.$setPristine();
							}

						}

				]);