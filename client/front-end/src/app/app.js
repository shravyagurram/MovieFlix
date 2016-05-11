/**
 * Created by shrav_000 on 5/5/2016.
 */
(function() {
    'use strict';

    angular
        .module('uimodule', ['ngRoute', 'ui.bootstrap','ngAnimate','ngMessages','ui-notification'])
        .factory('authInterceptor', authInterceptor)
        .config(['$routeProvider','$httpProvider','NotificationProvider',function($routeProvider,$httpProvider,NotificationProvider){
            $routeProvider
                .when('/homepage', {
                    templateUrl: 'app/views/home-page.tmpl.html',
                    controller: 'homeController',
                    controllerAs: 'homeVm'
                })
                .when('/signin', {
                    templateUrl: 'app/views/sign-in.tmpl.html',
                    controller: 'homeController',
                    controllerAs: 'homeVm'
                })
                .when('/signup', {
                    templateUrl: 'app/views/sign-up.tmpl.html',
                    controller: 'homeController',
                    controllerAs: 'homeVm'
                })
                .when('/api/home', {
                    templateUrl: 'app/views/movies.tmpl.html',
                    controller: 'MoviesController',
                    controllerAs: 'moviesVm'
                })
                .when('/api/home/:id', {
                    templateUrl: 'app/views/movie.detail.tmpl.html',
                    controller: 'MovieDetailController',
                    controllerAs: 'movieDetailVm'
                })
                .when('/api/admin', {
                    templateUrl: 'app/views/add-movie.tmpl.html',
                    controller: 'MoviesController',
                    controllerAs: 'moviesVm'
                })
                .when('/api/admin/:id', {
                    templateUrl: 'app/views/movies.tmpl.html',
                    controller: 'MoviesController',
                    controllerAs: 'moviesVm'
                })
                .when('/api/admin/edit/:id', {
                    templateUrl: 'app/views/edit-movie.tmpl.html',
                    controller: 'MoviesController',
                    controllerAs: 'moviesVm'
                })
                .when('/api/user', {
                    templateUrl: 'app/views/browse-movie.tmpl.html',
                    controller: 'MoviesController',
                    controllerAs: 'moviesVm'
                })
                .when('/api/user/:id', {
                    templateUrl: 'app/views/user-moviedetail.tmpl.html',
                    controller: 'MovieDetailController',
                    controllerAs: 'movieDetailVm'
                })
                .when('/api/topratedmovies', {
                    templateUrl: 'app/views/toprated-movie.tmpl.html',
                    controller: 'filterMovieController',
                    controllerAs: 'filterVm'
                })
                .when('/api/topratedseries', {
                    templateUrl: 'app/views/toprated-series.tmpl.html',
                    controller: 'filterSeriesController',
                    controllerAs: 'seriesVm'
                })
                .when('/api/sortcalalogue', {
                    templateUrl: 'app/views/sort-catalogue.tmpl.html',
                    controller: 'MoviesController',
                    controllerAs: 'moviesVm'
                })
                .when('/resources', {
                    templateUrl: 'app/views/resources.tmpl.html',
                    controller: 'ResourcesController',
                    controllerAs: 'resourcesVm'
                })

                .otherwise({
                    redirectTo: '/signin'
                });

            $httpProvider
                .interceptors.push('authInterceptor');

            NotificationProvider.setOptions({
                delay: 10000,
                startTop: 20,
                startRight: 10,
                verticalSpacing: 20,
                horizontalSpacing: 20,
                positionX: 'center',
                positionY: 'top'
            });

        }]);



    function authInterceptor(authService) {
        return {
            // automatically attach Authorization header
            request: function(config) {
                var token = authService.getToken();
                console.log("app "+token);
                if(token) {
                    config.headers.Authorization = 'Bearer ' + token;
                }

                return config;
            },

            // If a token was sent back, save it
            response: function(res) {
                if(res.token) {
                    authService.saveToken(res.token);
                }

                return res;
            }
        }
    }
})();
