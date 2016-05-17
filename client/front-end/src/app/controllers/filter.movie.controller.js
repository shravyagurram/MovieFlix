
(function() {
    'use strict';

    angular.module('uimodule')
        .controller('filterMovieController', filterMovieController);

    filterMovieController.$inject = ['movieService','authService','$location'];

    function filterMovieController(movieService,authService,$location) {
        var filterVm = this;
        filterVm.changeSort=changeSort;
        filterVm.logout=logout;
        init();

        function init() {
            if (!authService.getToken()) { // Get token.
                $location.path('/homepage');
            }
            filterVm.sorter = {
                by: 'title',
                reverse: false
            };


             var movie="movie";
            movieService
                .topratedShow(movie)
                .then(function (movies) {
                    filterVm.movies = movies;
                }, function (error) {
                    console.log(error);
                });


        }
        function changeSort(prop) {
            filterVm.sorter.by = prop;
            filterVm.sorter.reverse = !filterVm.sorter.reverse;
        }
        function logout(){
            authService.logout();
        }
    }
})();

