
(function() {
    'use strict';

    angular.module('uimodule')
        .controller('MovieDetailController', MovieDetailController)
         .directive('starRating',starRating);
    MovieDetailController.$inject = ['movieService', '$routeParams','authService','$location'];

    function MovieDetailController(movieService, $routeParams,authService,$location) {
        var movieDetailVm = this;
        movieDetailVm.rateFunction=rateFunction;
        movieDetailVm.logcomment=logcomment;
        movieDetailVm.logout=logout;
        init();

        function init() {

            console.log('MovieDetailController');

            if (!authService.getToken()) { // Get token.
                $location.path('/homepage');
            }

            movieService
                .getMovieById($routeParams.id)
                .then(function (movie){
                    movieDetailVm.movie = movie;
                }, function (error) {
                    console.log(error);
                });

            movieService
                .viewRating($routeParams.id)
                .then(function (rating){
                    movieDetailVm.userRating=rating;
                }, function (error) {
                    console.log(error);
                });

            movieService
                .viewComments($routeParams.id)
                .then(function (comments){
                    movieDetailVm.userComment=comments;
                }, function (error) {
                    console.log(error);
                });
        }

        function rateFunction(rating){
         console.log(rating);
            movieDetailVm.usercomments={};
            movieDetailVm.usercomments.userdetails={};
            movieDetailVm.usercomments.showdetails={};
            movieDetailVm.usercomments.userRating=rating;
            console.log(movieDetailVm.usercomments.userComment);
            movieService.getUserById(authService.getUser())
                .then(function(user){
                    movieDetailVm.usercomments.userdetails.emailId=user.emailId;
                    console.log(user.emailId);
                    movieDetailVm.usercomments.showdetails.movieId=$routeParams.id;
                    movieService
                        .logRating(movieDetailVm.usercomments)
                        .then( init()
                            , function (error) {
                                console.log(error);
                            });
                }, function (error) {
                    console.log(error);
                });
            //alert('Rating selected - ' + rating);
        }

        function logcomment(comment){
            movieDetailVm.usercomments={};
            movieDetailVm.usercomments.userdetails={};
            movieDetailVm.usercomments.showdetails={};
            movieDetailVm.usercomments.userComment=comment;
           console.log(movieDetailVm.usercomments.userComment);
            movieService.getUserById(authService.getUser())
                .then(function(user){
                    movieDetailVm.usercomments.userdetails.emailId=user.emailId;
                    console.log(user.emailId);
                    movieDetailVm.usercomments.showdetails.movieId=$routeParams.id;
                    movieService
                        .logComment(movieDetailVm.usercomments)
                        .then(
                            function(){
                                movieDetailVm.userComment.push(comment);
                            }
                           // init()
                            , function (error) {
                                console.log(error);
                            });
                }, function (error) {
                    console.log(error);
                });

        }
        function logout(){
            authService.logout();
        }


    }

    function starRating() {
        return {
            restrict : 'A',
            template : '<ul class="rating">'
            + ' <li ng-repeat="star in stars" ng-class="star" ng-click="toggle($index)">' + ' <i class="fa fa-star-o"></i>'
                + ' </li>'
                + '</ul>',
            scope : {
                ratingValue : '=',
                max : '=',
                onRatingSelected : '&'
            },
            link : function(scope, elem, attrs) {
                var updateStars = function() {
                    scope.stars = [];
                    for ( var i = 0; i < scope.max; i++) {
                        scope.stars.push({
                            filled : i < scope.ratingValue
                        });
                    }
                };

                scope.toggle = function(index) {
                    scope.ratingValue = index + 1;
                    scope.onRatingSelected({
                        rating : index + 1
                    });
                };

                scope.$watch('ratingValue',
                    function(oldVal, newVal) {
                        if (newVal) {
                            updateStars();
                        }
                    }
                );
            }
        };

    }
})();
