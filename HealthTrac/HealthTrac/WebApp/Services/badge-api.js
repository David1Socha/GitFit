gitFit.factory('BadgeApi', ['$resource', function ($resource) {
    'use strict';

    return $resource('/api/Badges', {}, {
        GetBadges: { method: 'GET', url: '/api/Badges', isArray: true },
    });
}]);