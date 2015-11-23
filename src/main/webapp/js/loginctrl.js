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
                else{
                    $scope.invalid_cred=true;
                    $window.alert("Fail to authenticate ! check your email ID ");
                }

             },
            function errorCallback(response){
                console.log("Exception");
                $window.alert("Exception check your email ID to authenticate .");
            }
        );
    }


// new user
    $scope.addUser=function(){

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

                     console.log(' added log :'+loggedUserDetails.getUsername());
                    $window.alert("ADDED Check your email for Activation !");
                    $scope.new_user='';

                    $state.go('Login');

                }
                else if(response.statusText="FOUND"){
                    $window.alert("Email ID or Phone already registered .");
                }

            },
            function errorCallback(response){
                console.log("Exception");
                $window.alert("Enter valid Email ID or Phone  .");
            }
        );
    }
}])