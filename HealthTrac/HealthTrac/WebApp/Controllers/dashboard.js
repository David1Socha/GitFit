gitFit.controller('DashboardController', ['$scope', 'UserApi', '$location', 'activities', 'meals', 'badges', 'userBadges', function ($scope, UserApi, $location, activities, meals, badges, userBadges) {

    activities.$promise.then(function (activities) {
        $scope.activities = activities;
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
                activity.FormattedDuration = Math.round(activity.Duration / 60) + ' minutes';
            }

        });
    });

    meals.$promise.then(function (meals) {
        $scope.meals = meals;
        $scope.DailyIntake = {};
        angular.forEach($scope.meals, function (meal) {
            meal.DateCreated = moment(meal.DateCreated);
            meal.FormattedDateCreated = meal.DateCreated.format('MMMM DD, YYYY');
            if ($scope.DailyIntake[meal.FormattedDateCreated] == 0 || $scope.DailyIntake[meal.FormattedDateCreated] == null) {
                $scope.DailyIntake[meal.FormattedDateCreated] = meal.Calories;
            } else {
                $scope.DailyIntake[meal.FormattedDateCreated] += meal.Calories;
            }
        });
        $scope.theKeys = Object.keys($scope.DailyIntake);
        $scope.theHighchartsData = [];
        var i = 0;
        angular.forEach($scope.DailyIntake, function (thing) {
            var dataObject = [Date.parse(moment($scope.theKeys[i], "MMMM DD, YYYY")), thing]
            $scope.theHighchartsData.push(dataObject);
            i++;
        })
        $scope.renderCalorieChart();
    });

    badges.$promise.then(function (badges) {
        $scope.getUserBadges(badges);
    });
    $scope.getUserBadges = function (badges) {
        userBadges.$promise.then(function (userBadges) {
            $scope.userBadges = userBadges;
            angular.forEach($scope.userBadges, function (userBadge) {
                userBadge.badgeName = badges[userBadge.BadgeID - 1].Name;
            })
        })
    }

    $scope.renderCalorieChart = function () {
        $(function () {
            $('#CalorieContainer').highcharts({
                chart: {
                    zoomType: 'x'
                },
                tooltip:{
                    xDateFormat: '%A, %b %e, %Y'
                },
                title: {
                    text: 'Calorie Intake'
                },
                xAxis: {
                    type: 'datetime',
                    minRange: 7 * 24 * 3600000
                },
                yAxis: {
                    title: {
                        text: 'Calorie Intake'
                    }
                },
                plotOptions: {
                    area: {
                        fillColor: {
                            linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
                            stops: [
                                [0, Highcharts.getOptions().colors[2]],
                                [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                            ]
                        },
                        marker: {
                            radius: 2
                        },
                        lineWidth: 1,
                        states: {
                            hover: {
                                lineWidth: 1
                            }
                        },
                        threshold: null
                    }
                },
                legend: {
                    enabled: false
                },
                series: [{
                    type: 'area',
                    name: 'Calorie Intake',
                    pointInterval: 24 * 3600 * 1000,
                    data: $scope.theHighchartsData
                }]
            });
        });
    };
    
    
}]);