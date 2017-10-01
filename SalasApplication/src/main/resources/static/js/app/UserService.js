'use strict';

angular.module('salasApp').factory('UserService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                getUser: getUser,
                createUser: createUser,
                loadAllUsers: loadAllUsers,
            };

            return factory;
            
            function loadAllUsers() {
                console.log('Cargando todos los usuarios');
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Carga exitosa de los usuarios');
                            $localStorage.rooms = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error cargando los usuarios');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getUser(id) {
                console.log('consultando usuario con id :'+id);
                var deferred = $q.defer();
                $http.get(urls.USER_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Usuario encontrado id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error consultando el usuario con id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createUser(user) {
                console.log('Creando usuario');
                var deferred = $q.defer();
                $http.post(urls.USER_SERVICE_API, user)
                    .then(
                        function (response) {
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error durante la creación del usuario : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);