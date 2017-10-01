<!DOCTYPE html>

<html lang="en" ng-app="salasApp">
    <head>
        <title>${title}</title>
        <link href="css/bootstrap.css" rel="stylesheet"/>
        <link href="css/datetimepicker.css" rel="stylesheet"/>
        <link href="css/app.css" rel="stylesheet"/>
    </head>
    <body>
    
    	<a href="#" class="btn btn-primary">Inicio</a>

        <div ui-view></div>
        
        <script src="js/lib/angular.min.js" ></script>
        <script src="js/lib/angular-ui-router.min.js" ></script>
        <script src="js/lib/localforage.min.js" ></script>
        <script src="js/lib/ngStorage.min.js"></script>
        <script src="js/lib/moment.min.js"></script>
        <script src="js/lib/datetimepicker.js"></script>
        <script src="js/lib/datetimepicker.templates.js"></script>
        <script src="js/app/app.js"></script>
        <script src="js/app/UserController.js"></script>
        <script src="js/app/UserService.js"></script>
        <script src="js/app/RoomController.js"></script>
        <script src="js/app/RoomService.js"></script>
        <script src="js/app/ReservationService.js"></script>
        <script src="js/app/ReservationController.js"></script>
        <script src="js/app/ListController.js"></script>
        <script src="js/app/ManageController.js"></script>
        
    </body>
</html>