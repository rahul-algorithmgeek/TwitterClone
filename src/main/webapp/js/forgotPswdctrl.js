/**
 * Created by fareye on 18/11/15.
 */

app.controller('forgotPswdctrl',['$scope','$http','$window','loggedUserDetails','$state', function($scope, $http, $window,loggedUserDetails,$state){
    console.log("In forget controller");
    $scope.reqPwsd={};
    $scope.forgotPswd=function(){
        $scope.floader=true;

        console.log($scope.reqPswd);
        $http({
            url:'/forgot',
            method: 'POST',
            data:   $scope.reqPswd

        }).then(function successCallBack(response){
            if(response.statusText="OK") {
                $scope.floader=false;
                $state.go('Login');
                $window.alert("Password will be sent to your email");
//
            }

        },function errorCallBack(response){
            $scope.floader=false;
            $window.alert("Enter valid registered email and phone no.!");
            $scope.reqPwsd.email='';
            $scope.reqPwsd.tel='';
        });
    }
}]);