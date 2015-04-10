﻿'use strict';

var gitFit = angular.module('gitFit', ['ngRoute', 'ngResource', 'ui.bootstrap', 'ngCookies']);

gitFit.config(['$httpProvider', '$routeProvider', '$locationProvider', function ($httpProvider, $routeProvider, $locationProvider) {

    $routeProvider
        .when('/Account/Login', {
            templateUrl: '/Account/Login'
        })
        .when('/Users', {
            templateUrl: '/WebApp/Views/users.html',
            controller: 'UsersController'
        })
        .otherwise({ redirectTo: '/' });

    $locationProvider.html5Mode(true);
    $locationProvider.hashPrefix('!');
}]);