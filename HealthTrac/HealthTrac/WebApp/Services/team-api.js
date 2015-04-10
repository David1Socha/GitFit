gitFit.factory('TeamApi', ['$resource', function ($resource) {
    'use strict';

    return $resource('/api/Teams', {}, {
        SearchTeams: { method: 'GET', url: '/api/Teams/Search/:name', isArray: true }
    });
}]);