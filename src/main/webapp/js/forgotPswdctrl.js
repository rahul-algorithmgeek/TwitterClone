/**
 * Created by fareye on 18/11/15.
 */

app.controller('forgotPswdctrl',['$scope','$http','$window','loggedUserDetails','$state', function($scope, $http, $window,loggedUserDetails,$state){
    console.log("In forget controller");
    $scope.reqPwsd={};
    $scope.forgotPswd=function(){


        console.log($scope.reqPswd);
        $http({
            url:'/forgot',
            method: 'POST',
            data:   $scope.reqPswd

        }).then(function successCallBack(response){
            if(response.statusText="OK") {
                $window.alert("Password will be sent to your email");
                $state.go('Login');
            }
            else {
                $window.alert("Enter valid registered email and phone no.!");
                    $scope.reqPwsd.email='';
                    $scope.reqPwsd.tel='';

            }
        },function errorCallBack(response){
            $window.alert("Enter valid registered email and phone no.!");
            $scope.reqPwsd.email='';
            $scope.reqPwsd.tel='';
        });
    }
}]);