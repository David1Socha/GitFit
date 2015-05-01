gitFit.factory('ActivityApi', ['$resource', function ($resource) {
    'use strict';

    return $resource('/api/Activities', {}, {
        GetActivities: { method: 'GET', url: '/api/Activities?userId=current', isArray: true },
    });
}]);