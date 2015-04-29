'use strict';

var gitFit = angular.module('gitFit', ['ngRoute', 'ngResource', 'ui.bootstrap', 'ngCookies']);

gitFit.config(['$httpProvider', '$routeProvider', '$locationProvider', function ($httpProvider, $routeProvider, $locationProvider) {
    $locationProvider.html5Mode(true);
    $locationProvider.hashPrefix('!');

    $routeProvider
        .when('/Account/Login', {
            templateUrl: '/Account/Login'
        })
        .when('/Users', {
            templateUrl: '/WebApp/Views/users.html',
            controller: 'UsersController'
        })
        .when('/User-Profile/:userId', {
            templateUrl: '/WebApp/Views/user-profile.html',
            controller: 'UserProfileController'
        })
        .when('/Teams', {
            templateUrl: '/WebApp/Views/teams.html',
            controller: 'TeamsController'
        })
        .when('/Team-Profile/:teamId', {
            templateUrl: '/WebApp/Views/team-profile.html',
            controller: 'TeamProfileController'
        })
        .when('/', {
            templateUrl: '/WebApp/Views/dashboard.html',
            controller: 'DashboardController'
        })
        .otherwise({ redirectTo: '/' });
    
}]);