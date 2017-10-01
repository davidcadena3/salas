'use strict';

angular
		.module('salasApp')
		.controller(
				'UserController',
				[
						'UserService',
						'$scope',
						function(UserService, $scope) {

							var self = this;

							self.user = {};

							self.submit = submit;
							self.createUser = createUser;
							self.reset = reset;

							self.successMessage = '';
							self.errorMessage = '';
							self.roles = [ 'ADMINISTRADOR', 'USUARIO' ];
							self.done = false;

							function submit() {
								console.log('Submitting');
								if (self.user.id === undefined
										|| self.user.id === null) {
									console.log('Creando el nuevo usuario',
											self.user);
									createUser(self.user);
								}
							}

							function createUser(user) {
								console.log('createUser: ' + user);
								UserService
										.createUser(user)
										.then(
												function(response) {
													console
															.log('Usuario creado exitosamente');
													self.successMessage = 'Usuario creado exitosamente';
													self.errorMessage = '';
													self.done = true;
													$scope.myForm
															.$setPristine();
												},
												function(errResponse) {
													console
															.error('Error creando el usuario');
													self.errorMessage = 'Error durante la creación: '
															+ errResponse.data.errorMessage;
													self.successMessage = '';
												});
							}

							function reset() {
								self.successMessage = '';
								self.errorMessage = '';
								self.user = {};
								self.done = false;
								$scope.myForm.$setPristine();
							}

						}

				]);