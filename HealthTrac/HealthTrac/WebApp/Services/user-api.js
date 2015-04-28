gitFit.factory('UserApi', ['$resource', function ($resource) {
    'use strict';

    return $resource('/api/Users', {}, {
        SearchUsers: { method: 'GET', url: '/api/Users/Search/:name', isArray: true },
        GetUser: { method: 'GET', url: '/api/Users/:userId' }
    });
}]);