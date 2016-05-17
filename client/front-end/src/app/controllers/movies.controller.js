/**
 * Created by shrav_000 on 5/5/2016.
 */
(function() {
    'use strict';

    angular.module('uimodule')
        .controller('MoviesController', MoviesController);
      //  .directive('input',inputDirective);
    MoviesController.$inject = ['movieService','$routeParams','authService','$location','Notification'];

    function MoviesController(movieService,$routeParams,authService,$location,Notification) {
        var moviesVm = this;

        moviesVm.changeSort = changeSort;
        moviesVm.deleteMovie = deleteMovie;
        moviesVm.addMovie = addMovie;
        moviesVm.EditMovie= EditMovie;
        moviesVm.updateMovie=updateMovie;
        moviesVm.logout=logout;
        init();


        function init() {
            console.log('MoviesController');

            moviesVm.sorter = {
                by: 'title',
                reverse: false
            };
            if (!authService.getToken()) { 
                $location.path('/homepage');
            }

            movieService
                .getMovies()
                .then(function (movies) {
                    moviesVm.movies = movies;
                }, function (error) {
                    console.log(error);
                });

            movieService.getUserById(authService.getUser())
                .then(function (user) {
                    console.log(user);
                    moviesVm.user = user;
                }, function (error) {
                    console.log(error);
                });

           movieService
                .getMovieById($routeParams.id)
              .then(function (movie){
                   console.log(movie);
                  moviesVm.editmovie = movie;

                }, function (error) {
                 console.log(error);
                });

        }

        function changeSort(prop) {
            moviesVm.sorter.by = prop;
            moviesVm.sorter.reverse = !moviesVm.sorter.reverse;
        }
        
        function deleteMovie(movie){
            console.log(movie);
            movieService
                .deleteMovieById(movie.movieId)
                .then( moviesVm.movies.splice(moviesVm.movies.indexOf(movie), 1),
             function (error) {
                    console.log(error);
                });

        }
        function addMovie(){
            console.log(moviesVm.newMovie);
            movieService
                .createNewMovie(moviesVm.newMovie)
                .then( function(res){
                        console.log(res);
                        Notification.success('Movie Successfully added');
                        $location.path('/api/home');
                },
                    function (error) {
                        console.log(error);
                        Notification.error('Invalid data please submit again');
                    });
        }
        
        function EditMovie(movie){
            movieService
                .getMovieById(movie.movieId)
                .then(function (movie){
                    console.log(movie);
                    moviesVm.editmovie = movie;

                }, function (error) {
                    console.log(error);
                });
        }

        function updateMovie(){
            console.log($routeParams.id);
            console.log(moviesVm.editmovie);
            moviesVm.editmovie.movieId=$routeParams.id;
            console.log(moviesVm.editmovie.movieId);
            movieService.updateMovie($routeParams.id,moviesVm.editmovie)
                .then(function(res){
                        console.log(res);
                        Notification.success('Movie Successfully Updated');
                        $location.path('/api/home');
                    }
                    , function (error) {
                        console.log(error);
                        Notification.error('Invalid data please submit again');
                    });
        }
        
        function logout(){
            authService.logout();

        }
        
       
        

    }
    function inputDirective(){
        var directiveDefinitionObject = {
            restrict: 'E',
            require: '?ngModel',
            link: function postLink(scope, iElement, iAttrs, ngModelController) {
                if (iAttrs.value && ngModelController) {
                    ngModelController.$setViewValue(iAttrs.value);
                }
            }
        };

        return directiveDefinitionObject;
    }
})();
