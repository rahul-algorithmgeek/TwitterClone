/**
 * Created by rahul on 11/11/15.
 */
app.controller('loginctrl', ['$window','$interval','$scope','$http','loggedUserDetails','$state', function($window, $interval,$scope, $http,loggedUserDetails, $state){

    console.log("Login controller ON.");

    $scope.login_cred={};
    $scope.new_user={};
//Authentication
   $scope.authUser=function(){

        console.log($scope.login_cred);
        var req={
          url:'/auth',
          method:'POST',
          data:$scope.login_cred
        };

        $http(req).then(
            function successCallback(response){
                console.log(response.data);
                if(response.statusText=="OK"){
                    $state.go('Dashboard');
                    $scope.show_header=false;
                    loggedUserDetails.setName(response.data.name);
                    loggedUserDetails.setUsername(response.data.username);
                    loggedUserDetails.setUrl(response.data.imageUrl);
                    console.log('log :'+loggedUserDetails.getUsername());
                }


             },
            function errorCallback(response){
                console.log("Exception");
                $scope.invalid_cred=true;
            }
        );
    }


// new user
    $scope.addUser=function(){
           $scope.loader=true;
        console.log($scope.new_user);
        var req={
            url:'/addUser',
            method:'POST',
            data:$scope.new_user
        }
        $http(req).then(
            function successCallback(response){
                console.log(response.data);
                if(response.statusText=="OK")
                {
                    $scope.loader=false;
                    console.log(response);

                     console.log(' added log :'+loggedUserDetails.getUsername());
                    $window.alert("ADDED Check your email for Activation !");
                    $scope.new_user='';

                    $state.go('Login');

                }


            },
            function errorCallback(response){
                $scope.loader=false;
                if(response.status==302){
                    $window.alert("Email or Phone Already Registered !");

                }
                else {
                    console.log("Exception");
                    console.log(response);
                    $window.alert("Some Exception happened");
                }
            }
        );
    }
}])