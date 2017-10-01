'use strict';

angular.module('salasApp').factory('RoomService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                getRoom: getRoom,
                createRoom: createRoom,
                loadAllRooms: loadAllRooms
            };

            return factory;
            
            function loadAllRooms() {
                console.log('Cargando todas las salas');
                var deferred = $q.defer();
                $http.get(urls.ROOM_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Carga exitosa de las salas');
                            $localStorage.rooms = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error cargando las salas');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getRoom(id) {
                console.log('consultando sala con id :'+id);
                var deferred = $q.defer();
                $http.get(urls.ROOM_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Sala encontrada id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error consultando la sala con id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createRoom(room) {
                console.log('Creando sala');
                var deferred = $q.defer();
                $http.post(urls.ROOM_SERVICE_API, room)
                    .then(
                        function (response) {
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error durante la creación de la sala : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);