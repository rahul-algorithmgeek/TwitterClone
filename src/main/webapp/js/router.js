/**
 * Created by rahul on 11/11/15.
 */
app.config(function($stateProvider){

    $stateProvider

        .state('Login',{
            url:'/login',
            templateUrl:'templates/login.html',
            controller:'loginctrl'
        })
        .state('Error',{
            url:'/error',
            templateUrl:'templates/error.html'
        })
        .state('ForgotPswd',{
            url:'/forgotPswd',
            templateUrl:'templates/forgotPswd.html',
            controller:'forgotPswdctrl'
        })
        .state('Dashboard',{
            url:'/dashboard',
            templateUrl: 'templates/dashboard.html',
            controller: 'dashboardctrl'
        })
        .state('Dashboard.Home',{
            url:'/home',
            templateUrl: 'templates/home.html',
            controller: 'homectrl'
        })
        .state('Dashboard.ChangePassword',{
            url:'/change_user_password',
            templateUrl:'templates/changePassword.html',
            controller: 'changePswdctrl'
        })
        .state('Dashboard.Profile',{
            url:'/profile',
            templateUrl:'templates/profile.html',
            controller: 'profilectrl'
        })
        .state('Dashboard.MyTweets',{
            url:'/mytweets',
            templateUrl:'templates/mytweets.html',
            controller:'mytweetsctrl'

        })

        .state('Dashboard.Logout',{

            controller:'logoutctrl'
        })
        .state('Dashboard.Tweet',{
            url:'/uploadNewTweet',
            templateUrl:'templates/posttweet.html',
            controller:'tweetctrl'
        })
});

