gitFit.factory('MealApi', ['$resource', function ($resource) {
    'use strict';

    return $resource('/api/Meal', {}, {
        GetMeals: { method: 'GET', url: '/api/Meals?userId=current', isArray: true },
    });
}]);