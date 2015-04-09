'use strict';

var gitFit = angular.module('gitFit', ['ngRoute', 'ngResource', 'ui.bootstrap', 'ngCookies']);

gitFit.config(['$httpProvider', '$routeProvider', '$locationProvider', function ($httpProvider, $routeProvider, $locationProvider) {

    $locationProvider.html5Mode(true);
    $locationProvider.hashPrefix('!');
}]);