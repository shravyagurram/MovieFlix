
(function() {
    'use strict';

    angular.module('uimodule')
        .service('movieService', movieService);

    movieService.$inject = ['$http', '$q', 'CONFIG'];

    function movieService($http, $q, CONFIG) {

        var self = this;

        self.getMovies = getMovies;
        self.getMovieById = getMovieById;
        self.deleteMovieById=deleteMovieById;
        self.createNewMovie=createNewMovie;
        self.updateMovie=updateMovie;
        self.logRating=logRating;
        self.viewRating=viewRating;
        self.viewComments=viewComments;
        self.userSignIn=userSignIn;
        self.getUserById=getUserById;
        self.userSignUp=userSignUp;
        self.logComment=logComment;
        self.topratedShow=topratedShow;

        function getMovies() {
            return $http.get(CONFIG.API_HOST + '/api/home')
                .then(successFn, errorFn);
        }

        function getMovieById(id) {
            return $http.get(CONFIG.API_HOST + '/api/home/' + id)
                .then(successFn, errorFn);
        }

        function createNewMovie(movie) {
            return $http.post(CONFIG.API_HOST + '/api/admin',movie)
                .then(successFn, errorFn);
        }
        function deleteMovieById(id) {
            return $http.delete(CONFIG.API_HOST + '/api/admin/' + id)
                .then(successFn, errorFn);
        }
        function updateMovie(id,movie) {
            return $http.put(CONFIG.API_HOST + '/api/admin/' + id,movie)
                .then(successFn, errorFn);
        }
        function logRating(usercomments) {
            console.log(usercomments);
            return $http.post(CONFIG.API_HOST + '/api/usercomments/lograting', usercomments)
                .then(successFn, errorFn);
        }
        function logComment(usercomments) {
            console.log(usercomments);
            return $http.post(CONFIG.API_HOST + '/api/usercomments/logcomment', usercomments)
                .then(successFn, errorFn);
        }
        function viewRating(id) {
            return $http.get(CONFIG.API_HOST + '/api/usercomments/' +id)
                .then(successFn, errorFn);
        }
        function viewComments(id) {
            return $http.get(CONFIG.API_HOST + '/api/usercomments/' +id +'/userComment')
                .then(successFn, errorFn);
        }
        function userSignIn(user) {
            return $http.post(CONFIG.API_HOST + '/auth/login',user)
                .then(successFn, errorFn);
        }
        function getUserById(emailid) {
            return $http.get(CONFIG.API_HOST + '/api/user/'+ emailid)
                .then(successFn, errorFn);
        }
        function userSignUp(user) {
            return $http.post(CONFIG.API_HOST + '/api/user',user)
                .then(successFn, errorFn);
        }
        function topratedShow(show) {
            return $http.get(CONFIG.API_HOST + '/api/home/toprated/'+show)
                .then(successFn, errorFn);
        }

        function successFn(response) {
            return response.data;
        }

        function errorFn(response) {
            return $q.reject('ERROR: ' + response.statusText);
        }
    }

})();
