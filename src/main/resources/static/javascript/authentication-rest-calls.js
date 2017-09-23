var app = angular.module('authenticateApp', []);
app.controller('authenticateCtrl', function ($scope, $http) {

        console.log("I am trying to get a token")
        if ("token" in sessionStorage) {
            console.log("Token is: " + sessionStorage.getItem("token"))
            var config = {
                headers: {
                    'Token': sessionStorage.getItem("token")
                }
            }

            $http.get("http://localhost:8080/authenticateUserWithToken", config).then(
                function successCallback(response) {
                    console.log(response)
                    $scope.error_message_alert = ""
                    $scope.success_message_alert = "You are now connected thanks to your token"
                }, function errorCallback(response) {
                    console.error(response);
                    $scope.success_message_alert = ""
                    $scope.error_message_alert = ""
                });
        }

        $scope.postUserCredentials = function () {
            var user_username = $scope.user_username;
            var user_password = $scope.user_password;

            if (!user_username || !user_password) {
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
                    sessionStorage.setItem("token", response.data.token);
                    $scope.error_message_alert = ""
                    $scope.success_message_alert = "You are now connected"
                    console.log("Token after authentication is: " + sessionStorage.getItem("token"))
                }, function errorCallback(response) {
                    console.error(response);
                    $scope.success_message_alert = ""
                    $scope.error_message_alert = "Unknown user"
                });
        }

    }
)