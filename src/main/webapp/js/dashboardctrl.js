/**
 * Created by rahul on 12/11/15.
 */
app.controller('dashboardctrl', ['$scope','$window','$http','$state','loggedUserDetails', function($scope,$window,$http,$state,loggedUserDetails){
if(loggedUserDetails.getUsername()!=null) {
    $scope.links = ['Profile', 'ChangePassword', 'MyTweets', 'Tweet'];

        $state.go('Dashboard.Profile');
    }
    else{
    console.log("No session exists...");
    $state.go('Login');
}


}]);

