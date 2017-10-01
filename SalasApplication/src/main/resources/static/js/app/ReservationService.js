'use strict';

angular.module('salasApp').factory('ReservationService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                createReservation: createReservation,
                getAllRoomReservations: getAllRoomReservations,
                getAllPendingReservations: getAllPendingReservations,
                remove: remove,
                confirm: confirm
            };

            return factory;
            
            function createReservation(reservation) {
                console.log('Creando reserva: '+reservation);
                var deferred = $q.defer();
                $http.post(urls.RESERVATION_SERVICE_API, reservation)
                    .then(
                        function (response) {
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error durante la creación de la sala: '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function getAllRoomReservations(roomId){
            	console.log('Consultando las reservas de la sala: '+roomId);
            	var deferred = $q.defer();
                $http.get(urls.RESERVATION_SERVICE_API + 'room/'+roomId)
                    .then(
                        function (response) {
                            console.log('Consulta exitosa de las reservas para la sala: '+roomId);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error consultando las reservas para la sala: '+roomId);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function getAllPendingReservations(){
            	console.log('Consultando las reservas pendientes');
            	var deferred = $q.defer();
                $http.get(urls.RESERVATION_SERVICE_API + 'pending')
                    .then(
                        function (response) {
                            console.log('Consulta exitosa de las reservas para la sala');
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error consultando las reservas para la sala');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function remove(id) {
                console.log('Eliminando reserva con id: '+id);
                var deferred = $q.defer();
                $http.delete(urls.RESERVATION_SERVICE_API + id)
                    .then(
                        function (response) {
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error eliminando la reserva id: '+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function confirm(user, id) {
                console.log('Actualizando la reserva con id: '+id);
                var deferred = $q.defer();
                $http.put(urls.RESERVATION_SERVICE_API + id, user)
                    .then(
                        function (response) {
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error actualizando la reserva id: '+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }


        }
    ]);