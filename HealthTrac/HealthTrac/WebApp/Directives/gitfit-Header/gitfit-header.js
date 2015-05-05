'use strict';

gitFit.directive('gitfitHeader', function () {
    return {
        restrict: 'E',
        templateUrl: '../WebApp/Directives/gitfit-Header/gitfit-Header.html',
        controller: ['$scope', '$rootScope', '$location', '$route', function ($scope, $rootScope, $location, $route) {
            $scope.headerTabs = [
                { route: '/', label: 'Dashboard' },
                { route: '/Users', label: 'Users' },
                { route: '/Teams', label: 'Teams' },
            ];
        }]
    }
})