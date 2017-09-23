var app = angular.module('authenticateApp', []);
app.controller('authenticateCtrl', function ($scope, $http) {


    $scope.postUserCredentials = function () {
        var user_username = $scope.user_username;
        var user_password = $scope.user_password;

        if (!user_username || !user_password) {
            console.error("Empty required field detected")
            return;
        }

        var config = {
            headers : {
                'Token': 'application/x-www-form-urlencoded;charset=utf-8;'
            }
        }

        var authentication_data = ({
            username: user_username,
            password: user_password
        });

        $http.post("http://localhost:8080/authenticateUser", authentication_data).then(
            function successCallback(response) {
                console.log(response)
                $scope.error_message_alert = ""
                $scope.success_message_alert = "You are now connected"
            }, function errorCallback(response) {
                console.error(response);
                $scope.success_message_alert = ""
                $scope.error_message_alert = "Unknown user"
            });
    }

    }
)