/**
 * Created by shrav_000 on 5/10/2016.
 */



(function() {
    'use strict';

    angular.module('uimodule')
        .controller('filterSeriesController', filterSeriesController);

    filterSeriesController.$inject = ['movieService','authService','$location'];

    function filterSeriesController(movieService,authService,$location) {
        var seriesVm = this;
        seriesVm.changeSort=changeSort;
        seriesVm.logout=logout;
        init();

        function init() {
            if (!authService.getToken()) { // Get token.
                $location.path('/homepage');
            }
            seriesVm.sorter = {
                by: 'title',
                reverse: false
            };


            var series="series";
            movieService
                .topratedShow(series)
                .then(function (series) {
                    seriesVm.movies = series;
                }, function (error) {
                    console.log(error);
                });


        }
        function changeSort(prop) {
            seriesVm.sorter.by = prop;
            seriesVm.sorter.reverse = !seriesVm.sorter.reverse;
        }
        function logout(){
            authService.logout();
        }
    }
})();
