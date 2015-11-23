/**
 * Created by rahul on 8/11/15.
 */

var app = angular.module("twitter_app",['ui.router','ui.bootstrap','ngStorage']);

app.controller("mainctrl", ['$window','$scope','$state','$location','loggedUserDetails', function($window, $scope, $state,$location, loggedUserDetails){
    $scope.footerLinks=["About","Help","Terms","Privacy","Developer","Directory"];

    $scope.enableLogin=function(){

        if(loggedUserDetails.getUsername()!=null)
            $state.go('Dashboard');
        else
            $state.go('Login');
    }();

}]);

