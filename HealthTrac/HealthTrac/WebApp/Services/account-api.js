gitFit.factory('AccountApi', ['$resource', function ($resource) {
    'use strict';

    return $resource('/api/Account', {}, {
        LogOff: { method: 'POST', url: '/api/Account/LogOff'}
    });
}]);