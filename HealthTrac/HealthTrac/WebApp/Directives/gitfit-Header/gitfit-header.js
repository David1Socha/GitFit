'use strict';

gitFit.directive('gitfitHeader', function () {
    return {
        restrict: 'E',
        templateUrl: '../WebApp/Directives/gitfit-Header/gitfit-Header.html',
        controller: ['$scope', '$rootScope', '$location', function ($scope, $rootScope, $location) {
            $scope.headerTabs = [
                { route: '/', label: 'Dashboard' },
                { route: '/User', label: 'Users' },
                { route: 'Teams', label: 'Teams' },
            ];
        }]
    }
})