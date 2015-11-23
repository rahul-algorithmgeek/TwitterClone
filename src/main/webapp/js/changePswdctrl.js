/**
 * Created by fareye on 15/11/15.
 */
app.controller('changePswdctrl',['$scope','$http','$window','$state', function($scope, $http, $window,$state){
    $scope.reset={};

        $scope.changePswd = function () {
            console.log($scope.reset);
            $http({
                url: '/changePswd',
                method: 'POST',
                data: $scope.reset
            }).then(function successCallBack(response) {
                console.log(response);
                $window.alert("Done");
                $scope.reset.email="";
                $scope.reset.oldPassword="";
                $scope.reset.newPassword="";
                $scope.cpass="";
                $state.go('Dashboard.Profile');
            }, function errorCallBack(response) {
                $window.alert("Check your old password amd Email ID !");
            });
        }


       // $window.alert("Password not matched !");

}]);