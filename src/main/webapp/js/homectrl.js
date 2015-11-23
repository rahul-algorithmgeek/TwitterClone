/**
 * Created by fareye on 20/11/15.
 */
/**
 * Created by root on 20/11/15.
 */
app.controller('homectrl', ['$window','$interval','$scope','$http','loggedUserDetails','$state','$stateParams',function($window, $interval,$scope, $http, loggedUserDetails,$state,$location,$stateParams) {
    var username=loggedUserDetails.getUsername('username');
    $scope.image=loggedUserDetails.getUrl();
    $scope.name=loggedUserDetails.getName();
    console.log(username);
    //For Displaying Follow
    var req={
        url:'/tweets',
        method:'POST',
        data:   {username:username}
    };
    $scope.tweets={};
    $http(req).then(
        function successCallback(response){
            console.log(response);
            if(response.statusText=="OK"){
                $scope.tweets=response.data;


                //loggedUserDetails.setName(response.data.name);
                //loggedUserDetails.setUsername(response.data.username);
                //loggedUserDetails.setUrl(response.data.imageUrl);
                //console.log('log :'+loggedUserDetails.getUsername());
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

    $interval(function(){$http(req)
        .then(
        function successCallback(response){
            console.log(response);
            $scope.tweets=response.data;
        },function errorCallback(response){}
    );},30000);
}])