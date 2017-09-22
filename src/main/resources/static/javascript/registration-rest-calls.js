var app = angular.module('registerApp', []);
app.controller('registerCtrl', function ($scope, $http) {

    document.getElementById("registration-success-div").style.display="none";

        $scope.postUserRegistrationData = function () {

            var user_email = $scope.user_email;
            var user_username = $scope.user_username;
            var user_password = $scope.user_password;

            if (!user_email || !user_username || !user_password) {
                console.error("Empty required field detected")
                return;
            }

            var registration_data = ({
                email: user_email,
                username: user_username,
                password: user_password
            });

            console.log(registration_data);

            $http.post("http://localhost:8080/registerUser", registration_data).then(
                function successCallback(response) {
                    console.log(response);
                    $scope.message_alert = "Welcome to the game " + response.data.username
                    document.getElementById("registration-form-div").style.display="none";
                    document.getElementById("registration-success-div").style.display="block";
                }, function errorCallback(response) {
                    console.error("Unable to perform a POST request on \"/registerUser\"");
                    console.error(response);
                });

        }


    $scope.postUserCredentials = function () {
        var user_username = $scope.user_username;
        var user_password = $scope.user_password;

        if (!user_email || !user_username || !user_password) {
            console.error("Empty required field detected")
            return;
        }

        var authentication_data = ({
            username: user_username,
            password: user_password
        });

        $http.post("http://localhost:8080/authenticateUser", authentication_data).then(
            function successCallback(response) {
                console.log(response)
                $scope.message_alert = "You are now connected"

            }, function errorCallback(response) {
                console.error("Unable to perform a POST request on \"/authenticateUser\"");
                console.error(response);
                $scope.message_alert = "Unknown user"
            });
    }

    }
)