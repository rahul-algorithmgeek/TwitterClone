    /**
     * Created by fareye on 19/11/15.
     */
    app.controller('profilectrl', ['$window','$interval','$scope','$http','loggedUserDetails','$state','$stateParams',function($window, $interval,$scope, $http, loggedUserDetails,$state,$location,$stateParams) {
    var username=loggedUserDetails.getUsername('username');
        $scope.image=loggedUserDetails.getUrl();
        $scope.name=loggedUserDetails.getName();
        console.log(username);

        //For getting tweet, follower and following  counts
        $http({
            url:'/counts',
            method:'POST',
            data:{username:username}
        }).then(
            function successCallback(response){
                console.log("Count data :"+ angular.fromJson(response.data.following));
                if(response.statusText=="OK"){
                    $scope.followingcounts=angular.fromJson(response.data.following);
                    $scope.followercounts=angular.fromJson(response.data.follower);
                    $scope.tweetcounts=angular.fromJson(response.data.tweetcount);
                    console.log("new count data"+$scope.tweetcounts);

                }
                else
                //$window.alert("Fail to load count !");
                console.log("Fail to load count !");
            },
            function errorCallback(response){
                console.log("Exception in count loading !");

            }
        )


        //For Displaying Follow List
        var req={
            url:'/follow',
            method:'POST',
            data:   {username:username}
        };

        $http(req).then(
            function successCallback(response){
                console.log(response.data);
                if(response.statusText=="OK"){
                    $scope.follows=response.data;
                    //console.log('log :'+loggedUserDetails.getUsername());
                }
                else{
                   // $scope.invalid_cred=true;
                   $window.alert("Fail to To load follow list");
                }

            },
            function errorCallback(response){
                console.log("Exception");
                $window.alert("Exception");
            }
        );
    //For Recommendation List

        var req1={
            url:'/nfollow',
            method:'POST',
            data:   {username:username}
        };

        $http(req1).then(
            function successCallback(response){
                console.log(response.data);
                if(response.statusText=="OK"){
                    $scope.nfollows=response.data;



                }
                else{

                    $window.alert("Fail to To load to be followed list");
                }

            },
            function errorCallback(response){
                console.log("Exception");
                $window.alert("Exception");
            }
        );

        //UnFollow function

        $scope.unfollow=function(x){

            console.log($scope.Dusername);

            var req2={
                url:'/Requnfollow',
                method:'POST',
                data:{follow:x}
            };

            $http(req2).then(
                function successCallback(response){
                    console.log(response.data);
                    if(response.statusText=="OK"){

                        $state.go($state.current, {}, {reload: true});
                        $state.transitionTo($state.current, $stateParams, {
                            reload: true,
                            inherit: false,
                            notify: true
                        });


                    }
                    else{

                        $window.alert("Fail to Unfolow");
                    }

                },
                function errorCallback(response){
                    console.log("Exception");
                    $window.alert("Exception");
                }
            );
        }

        $scope.follow=function(c){

            console.log($scope.Dusername);
            var req3={
                url:'/Reqfollow',
                method:'POST',
                data:{follow:c }
            };

            $http(req3).then(
                function successCallback(response){
                    console.log(response.data);
                    if(response.statusText=="OK"){


                        $state.transitionTo($state.current, $stateParams, {
                            reload: true,
                            inherit: true,
                            notify: true
                        });

                    }
                    else{

                        $window.alert("Fail to Unfolow");
                    }

                },
                function errorCallback(response){
                    console.log("Exception");
                    $window.alert("Exception");
                }
            );
        }

    }])