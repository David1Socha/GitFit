gitFit.controller('DashboardController', ['$scope', 'UserApi', '$location', 'activities', function ($scope, UserApi, $location, activities) {

    activities.$promise.then(function (activities) {
        $scope.activities = activities;
        $scope.activities[0].Distance += 10;
        angular.forEach(activities, function (activity) {
            activity.StartDate = moment(activity.StartDate);
            if (activity.ActivityType == 0) {
                activity.FormattedType = 'Walked';
            } else if (activity.ActivityType == 1) {
                activity.FormattedType = 'Jogged';
            } else if (activity.ActivityType == 2) {
                activity.FormattedType = 'Ran';
            } else if (activity.ActivityType == 3) {
                activity.FormattedType = 'Biked';
            } else {
                activity.FormattedType = 'Traveled'
            }
            if (activity.Duration <= 95) {
                activity.FormattedDuration = activity.Duration + ' seconds';
            } else {
                activity.FormattedDuration = Math.round(activity.Duration/60) + ' minutes';
            }
        });
    })
    
}]);