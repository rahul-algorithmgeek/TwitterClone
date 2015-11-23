/**
 * Created by root on 18/11/15.
 */
app.controller('logoutctrl', ['$window','$interval','$scope','$http','$state','$localStorage','loggedUserDetails', function($window, $interval,$scope, $http, $state,$localStorage,loggedUserDetails) {
    loggedUserDetails.setName(null);
    loggedUserDetails.setUsername(null);
    loggedUserDetails.setUrl(null);

    $http({
        url:'/logout',
        method:'POST',
        data:{}

    }).then(function successCallBack(response){
        $scope.enableLogin=function(){
            $state.go('Login');
        }();
    },function errorCallBack(response){
        $window.alert("Some Exception happened !")
    });


}])