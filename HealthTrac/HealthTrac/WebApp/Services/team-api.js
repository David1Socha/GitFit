gitFit.factory('TeamApi', ['$resource', function ($resource) {
    'use strict';

    return $resource('/api/Teams', {}, {
        SearchTeams: { method: 'GET', url: '/api/Teams/Search/:name', isArray: true },
        GetTeam: { method: 'GET', url: '/api/Teams/withUsers/:teamId' },
        GetTeamsFromUser: { method: 'GET', url: '/api/Teams?userId=:userId', isArray: true}
    });
}]);