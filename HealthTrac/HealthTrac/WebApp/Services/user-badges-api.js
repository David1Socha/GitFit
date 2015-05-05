gitFit.factory('UserBadgeApi', ['$resource', function ($resource) {
    'use strict';

    return $resource('/api/UserBadges', {}, {
        GetUserBadges: { method: 'GET', url: '/api/UserBadges?userId=current', isArray: true },
    });
}]);