/**
 * Created by root on 20/11/15.
 */
app.controller('mytweetsctrl', ['$window','$interval','$scope','$http','loggedUserDetails','$state','$stateParams',function($window, $interval,$scope, $http, loggedUserDetails,$state,$location,$stateParams) {
    var username=loggedUserDetails.getUsername('username');
    $scope.image=loggedUserDetails.getUrl();
    $scope.name=loggedUserDetails.getName();
    console.log(username);
    //For Displaying My own tweets
    var req={
        url:'/mytweets',
        method:'POST',
        data:   {username:username}
    };
    $scope.mytweets={};
    $http(req).then(
        function successCallback(response){
            console.log(response);
            if(response.statusText=="OK"){
                $scope.mytweets=response.data;
            }
            else{
                // $scope.invalid_cred=true;
                $window.alert("Fail to To load mytweetlist list");
            }

        },
        function errorCallback(response){
            console.log("Exception");
            $window.alert("Exception");
        }
    );


}])