/**
 * Created by root on 18/11/15.
 */
app.service('loggedUserDetails',['$localStorage',function($localStorage){

    this.setName=function(uname){
        $localStorage.name=uname;
        //console.log("User name "+uname+" or "+this.name);
    }
    this.getName=function(){
        return $localStorage.name;
    }

    this.setUrl=function(iUrl){
        $localStorage.imgUrl=iUrl;
    }

    this.getUrl=function(){
        return $localStorage.imgUrl;
    }

    this.setUsername=function(userName){
        $localStorage.username=userName;
    }

    this.getUsername=function(){
        return $localStorage.username;
    }
}]);