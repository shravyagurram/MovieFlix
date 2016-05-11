(function() {
    'use strict';

    angular.module('uimodule')
        .controller('homeController', homeController);

    homeController.$inject = ['movieService','authService','$location','$http','Notification'];

    function homeController(movieService,authService,$location,$http,Notification) {
        var homeVm = this;
        homeVm.signIn=signIn;
        homeVm.signUp=signUp;
        
        function signIn(){
            console.log(homeVm.user.username);
            var id=homeVm.user.username;
            movieService
                .userSignIn(homeVm.user)
                .then(function(res) {
                    console.log(res);
                    authService.saveToken(res.token);
                    console.log(id);
                    authService.saveUser(id);
                   console.log( "token "+authService.getToken());
                   // $http.defaults.headers.common.Authorization = 'Bearer ' + res.token;
                    $http.defaults.headers.common['Authorization'] = 'Bearer ' + res.token;
                    $location.path('/api/user');
                }, function (error) {

                    console.log(error);
                    Notification.error('Invalid Credentials');
                });
        }
        
        function signUp(){
            homeVm.user.createdDate=new Date();
            homeVm.user.userType="user";
            movieService
                .userSignUp(homeVm.user)
                .then(function(res) {
                    console.log(res);
                    Notification.success('Succesfully Registered');
                    $location.path('/signin');
                }, function (error) {
                    console.log(error);

                });
        }
    }

    })();
