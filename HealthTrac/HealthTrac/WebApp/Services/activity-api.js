gitFit.factory('ActivityApi', ['$resource', function ($resource) {
    'use strict';

    return $resource('/api/Activities', {}, {
        GetCurrentActivities: { method: 'GET', url: '/api/Activities?userId=current', isArray: true },
        GetActivities: { method: 'GET', url: '/api/Activities?userId=:userId', isArray: true },
    });
}]);