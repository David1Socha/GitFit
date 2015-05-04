gitFit.controller('DashboardController', ['$scope', 'UserApi', '$location', 'activities', 'meals', 'badges', 'userBadges', function ($scope, UserApi, $location, activities, meals, badges, userBadges) {

    activities.$promise.then(function (activities) {
        $scope.numActivitiesDisplay = 10;
        $scope.displayAllActivities = false;
        $scope.DailyDistance = {};
        $scope.DailyDuration = {};
        $scope.DailySteps = {};
        $scope.earliestDate = moment(activities[0].StartDate)
        $scope.mostRecentDate = moment(activities[0].StartDate)
        angular.forEach(activities, function (activity) {
            activity.StartDate = moment(activity.StartDate);
            if ($scope.earliestDate.isAfter(activity.StartDate)) {
                $scope.earliestDate = activity.StartDate;
            }
            if ($scope.mostRecentDate.isBefore(activity.StartDate)) {
                $scope.mostRecentDate = activity.StartDate;
            }
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
            activity.FormattedStartDate = activity.StartDate.format('MMMM DD, YYYY');
            // Logic for distance graph                        
            if ($scope.DailyDistance[activity.FormattedStartDate] > 0 ) {
                $scope.DailyDistance[activity.FormattedStartDate] += activity.Distance;
            } else {                
                $scope.DailyDistance[activity.FormattedStartDate] = activity.Distance;
            }
            // Logic for steps graph
            if ($scope.DailySteps[activity.FormattedStartDate] > 0) {
                $scope.DailySteps[activity.FormattedStartDate] += activity.Steps;
            } else {
                $scope.DailySteps[activity.FormattedStartDate] = activity.Steps;
            }
            // Logic for duration graph
            if ($scope.DailyDuration[activity.FormattedStartDate] > 0) {
                $scope.DailyDuration[activity.FormattedStartDate] += activity.Duration;
            } else {
                $scope.DailyDuration[activity.FormattedStartDate] = activity.Duration;
            }
        });
        $scope.activities = activities;
        
        $scope.distanceKeys = Object.keys($scope.DailyDistance);
        $scope.distanceHighchartsData = [];
        $scope.stepsKeys = Object.keys($scope.DailySteps);
        $scope.stepsHighchartsData = [];
        $scope.durationKeys = Object.keys($scope.DailyDuration);
        $scope.durationHighchartsData = [];

        var i = 0;
        angular.forEach($scope.DailyDistance, function (distance) {
            var dataObject = [Date.parse(moment($scope.distanceKeys[i], "MMMM DD, YYYY")), distance];
            $scope.distanceHighchartsData.push(dataObject);
            i++;
        });

        var j = 0;
        angular.forEach($scope.DailySteps, function (steps) {
            var dataObject = [Date.parse(moment($scope.stepsKeys[j], "MMMM DD, YYYY")), steps];
            $scope.stepsHighchartsData.push(dataObject);
            j++;
        });

        var k = 0;
        angular.forEach($scope.DailyDuration, function (duration) {
            var dataObject = [Date.parse(moment($scope.durationKeys[k], "MMMM DD, YYYY")), duration];
            $scope.durationHighchartsData.push(dataObject);
            k++;
        });
        // Currently Unneccesary logic that adds null entries to days with no distance
        $scope.daysRange = $scope.mostRecentDate.diff($scope.earliestDate, 'days');
        //var tempDate = $scope.earliestDate;
        //for (var j = 0; j < $scope.daysRange; j++) {
        //    if ($.inArray(tempDate.format('MMMM DD, YYYY'), $scope.distanceKeys) == -1) {
        //        var tempObject = [Date.parse(tempDate), 0]
        //        $scope.distanceHighchartsData.push(tempObject);
        //    }
        //    tempDate = tempDate.add(1, 'days');
        //}
        $scope.renderActivityDistanceChart();
        $scope.currentUserActivitybreakdown = $scope.getActivityBreakDown(activities);
        $scope.renderActivityTypePieChart();
    });

    $scope.showAllActivities = function () {
        $scope.numActivitiesDisplay = 10000000;
        $scope.displayAllActivities = true;
    }

    $scope.getActivityBreakDown = function (activities) {
        var bikingTime = 0;
        var walkingTime = 0;
        var joggingTime = 0;
        var runningtime = 0;
        var otherTime = 0;
        var totalTime = 0;
        angular.forEach(activities, function (activity){
            //if activity is within the last 30 days then add time to corresponding type
            if (activity.StartDate.isAfter(moment().add(-30, 'days'))){
                switch (activity.ActivityType){
                    case 0:
                        walkingTime += activity.Duration;
                        totalTime += activity.Duration;
                        break;
                    case 1:
                        joggingTime += activity.Duration;
                        totalTime += activity.Duration;
                        break;
                    case 2:
                        runningtime += activity.Duration;
                        totalTime += activity.Duration;
                        break;
                    case 3:
                        bikingTime += activity.Duration;
                        totalTime += activity.Duration;
                        break;
                    default:
                        otherTime += activity.Duration;
                        totalTime += activity.Duration;
                }
            }
        });
        var sedentaryTime = (30 * 24) - (totalTime/3600);
        var timeBreakdown = [
            ['Walking', Math.round(walkingTime/3600)],
            ['Jogging', Math.round(joggingTime/3600)],
            ['Running', Math.round(runningtime/3600)],
            ['Biking', Math.round(bikingTime/3600)],
            ['Other Activity', Math.round(otherTime/3600)],
            ['Sedentary', Math.round(sedentaryTime)]
        ]
        return timeBreakdown;
    }

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
        $scope.calorieKeys = Object.keys($scope.DailyIntake);
        $scope.caloriesHighchartsData = [];
        var i = 0;
        angular.forEach($scope.DailyIntake, function (calories) {
            var dataObject = [Date.parse(moment($scope.calorieKeys[i], "MMMM DD, YYYY")), calories];
            $scope.caloriesHighchartsData.push(dataObject);
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
            });
        });
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
                    data: $scope.caloriesHighchartsData
                }],
                credits: {
                    enabled: false
                }
            });
        });
    };
    
    $scope.renderActivityDistanceChart = function () {
        $(function () {
            $('#ActivityDistanceContainer').highcharts({
                chart: {
                    zoomType: 'x'
                },
                tooltip: {
                    xDateFormat: '%A, %b %e, %Y'
                },
                title: {
                    text: 'Distance Travelled'
                },
                xAxis: {
                    type: 'datetime',
                    minRange: 7 * 24 * 3600000
                },
                yAxis: {
                    title: {
                        text: 'Daily Distance (feet)'
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
                    name: 'Daily Distance',
                    pointInterval: 24 * 3600 * 1000,
                    data: $scope.distanceHighchartsData
                }],
                credits: {
                    enabled: false
                }
            });
        });
    };

    $scope.renderActivityTypePieChart = function () {
        $(function () {
            $('#activityTypePieChartContainer').highcharts({
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false
                },
                title: {
                    text: 'Activity Type Breakdown'
                },
                subtitle: {
                    text: 'Over the last 30 days'
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            format: '<b>{point.name}</b>: {point.y} Hours',
                            style: {
                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                            }
                        }
                    }
                },
                series: [{
                    type: 'pie',
                    name: 'Percent activity time',
                    data: $scope.currentUserActivitybreakdown
                }],
                credits: {
                    enabled: false
                }
            });
        });

    }
    
    
}]);