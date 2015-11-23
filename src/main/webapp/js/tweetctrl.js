/**
 * Created by fareye on 18/11/15.
 */

app.controller('tweetctrl',['$scope','$http','$window', 'loggedUserDetails',function($scope, $http, $window,growl,loggedUserDetails){
    $scope.tweetdata={};
    //to post data in database


        $scope.postNewTweet = function () {


            console.log("Tweet object request:");
            console.log($scope.tweetdata);

            $http({
                url: '/uploadNewTweet',
                method: 'POST',
                data: $scope.tweetdata
            }).then(
                function successCallBack(response) {
                    console.log("Successfully uploaded");
                    $window.alert("Posted");
                    $scope.tweetdata.message = '';
                },
                function errorCallBack(response) {
                    console.log("Uploading failed");
                }
            )


        }

}]);