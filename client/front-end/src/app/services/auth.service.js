/**
 * Created by shrav_000 on 5/9/2016.
 */

(function() {
    'use strict';

    angular.module('uimodule')
        .service('authService', authService);

    authService.$inject = ['$window'];

    function authService($window) {
        var self = this;
        self.parseJwt=parseJwt;
        self.saveToken=saveToken;
        self.getToken=getToken;
        self.saveUser=saveUser;
        self.getUser=getUser;
        self.isAuthed=isAuthed;
        self.logout=logout;

        function parseJwt(token) {
            var base64Url = token.split('.')[1];
            var base64 = base64Url.replace('-', '+').replace('_', '/');
            return JSON.parse($window.atob(base64));
        }

        function saveToken(token) {
            $window.localStorage['jwtToken'] = token;
           //
        }
        function saveUser(username) {
            $window.localStorage['username']= username;
        }

         function getToken() {
            return $window.localStorage['jwtToken'];
        }

        function getUser(){
            return $window.localStorage['username'];
        }

        function isAuthed() {
            var token = self.getToken();
            if(token) {
                var params = self.parseJwt(token);
                console.log(params);
                return Math.round(new Date().getTime() / 1000) <= params.exp;
            } else {
                return false;
            }
        }

        function logout(){
            $window.localStorage.removeItem('jwtToken');
            $window.localStorage.removeItem('username');
        }

    }

})();

