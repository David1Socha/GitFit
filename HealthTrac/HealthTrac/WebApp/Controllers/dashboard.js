gitFit.controller('DashboardController', ['$scope', 'UserApi', '$location', 'activities', 'meals', 'TeamApi', 'ActivityApi', function ($scope, UserApi, $location, activities, meals, TeamApi, ActivityApi) {

    $scope.post_on_wall = function () {
        FB.login(function (response) {
            if (response.authResponse) {
                // Post message to your wall

                FB.ui({
                    method: 'feed',
                    name: 'Facebook Dialogs',
                }, function (response) {
                    if (!response || response.error) {
                        alert('Posting error occured');
                    }
                    else {
                        alert('Successfuly Posted');
                    }
                });
            }
            else {
                alert('Not logged in');
            }
        }, { scope: 'publish_stream' });
    }

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
        //variables for the SMA data
        $scope.steps7DaySMA = 0;
        $scope.distance7DaySMA = 0;
        $scope.duration7DaySMA = 0;

        var i = 0;
        var dateThreshold = $scope.mostRecentDate.add(-7, 'days');
        angular.forEach($scope.DailyDistance, function (distance) {
            var dataObject = [Date.parse(moment($scope.distanceKeys[i], "MMMM DD, YYYY")), distance];
            $scope.distanceHighchartsData.push(dataObject);
            
            if (moment($scope.distanceKeys[i], "MMMM DD, YYYY").isAfter(dateThreshold)) {
                $scope.distance7DaySMA += $scope.DailyDistance[$scope.distanceKeys[i]];
                $scope.steps7DaySMA += $scope.DailySteps[$scope.stepsKeys[i]];
                $scope.duration7DaySMA += $scope.DailyDuration[$scope.durationKeys[i]];
            }
            i++;
        });

        $scope.steps7DaySMA = Math.round($scope.steps7DaySMA / 7);
        $scope.distance7DaySMA = Math.round($scope.distance7DaySMA / 7);
        $scope.duration7DaySMA = Math.round($scope.duration7DaySMA / (60 * 7));


        var j = 0;
        angular.forEach($scope.DailySteps, function (steps) {
            var dataObject = [Date.parse(moment($scope.stepsKeys[j], "MMMM DD, YYYY")), steps];
            $scope.stepsHighchartsData.push(dataObject);
            j++;
        });

        var k = 0;
        angular.forEach($scope.DailyDuration, function (duration) {
            var dataObject = [Date.parse(moment($scope.durationKeys[k], "MMMM DD, YYYY")), Math.round(duration/60)];
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

        $scope.getTeams();
        $scope.renderActivityDistanceChart();
        $scope.currentUserActivitybreakdown = $scope.getActivityBreakDown(activities);
        $scope.renderActivityTypePieChart();
        $scope.renderActivityStepsChart();
        $scope.renderActivityDurationChart();
        
        

    });

    $scope.getTeams = function () {
        $scope.noTeams = true;
        $scope.showTeamOptions = true;
        var teamResource = TeamApi.GetTeamsFromUser({ userId: 'current' });
        teamResource.$promise.then(function (teams) {
            $scope.teams = teams;
            if (teams.length != 0) {
                $scope.noTeams = false;
            }
        });
    }

    $scope.processTeamsGraph = function (teamId) {
                $scope.showTeamOptions = false;
                $scope.UserValuePairs = [];
                $scope.areWeDoneCounter = 0;
                var teamWithUsersResource = UserApi.GetUsersFromTeam({ teamId: teamID })
                teamWithUsersResource.$promise.then(function (users) {
                    angular.forEach(users, function (user) {                        
                        if (user.Id != null) {
                            var tempUserValuePair = [];
                            var stepsAdder = 0;
                            var distanceAdder = 0;
                            var durationAdder = 0;
                            var dateComparer = moment().add(-30, 'days');
                            var userActivityResource = ActivityApi.GetActivities({ userId: user.Id });
                            userActivityResource.$promise.then(function (userActivities) {
                                if (userActivities.length > 0) {
                                    angular.forEach(userActivities, function (userActivity) {
                                        if (moment(userActivity.StartDate).isAfter(dateComparer)) {
                                            stepsAdder += userActivity.Steps;
                                            distanceAdder += userActivity.Distance;
                                            durationAdder += userActivity.Duration;
                                        }
                                    });
                                    tempUserValuePair = [user.UserName, stepsAdder, distanceAdder, durationAdder]
                                    $scope.UserValuePairs.push(tempUserValuePair);
                                }                               
                            });
                        }
                    });                                           
                })
                setTimeout(function () {
                    $scope.processTeamData($scope.UserValuePairs);                    
                }, 2000)
    }

    $scope.processTeamData = function (teamData){
        //process CurrentUserData
        var dateComparer = moment().add(-30, 'days');
        var currentStepsAdder = 0;
        var currentDistanceAdder = 0;
        var currentDurationAdder = 0;
        angular.forEach($scope.activities, function (currentUserActivity) {
            if (moment(currentUserActivity.StartDate).isAfter(dateComparer)) {
                currentStepsAdder += currentUserActivity.Steps;
                currentDistanceAdder += currentUserActivity.Distance;
                currentDurationAdder += currentUserActivity.Duration;
            }
        });

        var low = [teamData[0][1], teamData[0][2], teamData[0][3]];
        var average = [0, 0, 0];
        var top = [teamData[0][1], teamData[0][2], teamData[0][3]];
        var current = [currentStepsAdder, currentDistanceAdder, currentDurationAdder];

        angular.forEach(teamData, function (userObj) {
            if (userObj[1] < low[0]) {
                low[0] = userObj[1];
            }
            if (userObj[1] > top[0]) {
                top[0] = userObj[1];
            }
            if (userObj[2] < low[1]) {
                low[1] = userObj[2];
            }
            if (userObj[2] > top[1]) {
                top[1] = userObj[2];
            }
            if (userObj[3] < low[2]) {
                low[2] = userObj[3];
            }
            if (userObj[3] > top[2]) {
                top[2] = userObj[3];
            }
            average[0] += userObj[1];
            average[1] += userObj[2];
            average[2] += userObj[3];
        });
        average[0] = average[0]/teamData.length;
        average[1] = average[1]/teamData.length;
        average[2] = average[2] / teamData.length;

        //converting to thousands of steps, miles and hours
        low = $scope.convertData(low);
        average = $scope.convertData(average);
        top = $scope.convertData(top);
        current = $scope.convertData(current);
        
        $scope.teamHighChartsData = [{
            type: 'column',
            name: 'Low',
            data: low
        }, {
            type: 'column',
            name: 'Average',
            data: average
        }, {
            type:'column',
            name: 'Top',
            data: top
        }, {
            type: 'spline',
            name: 'My Stats',
            data: current,
            marker: {
                lineWidth: 2,
                lineColor: Highcharts.getOptions().colors[3],
                fillColor: 'white'
            }
        }];
        
        $scope.renderTeamChart();
    }

    $scope.convertData = function (data){
        data[0] = Math.round(data[0]/10)/100
        data[1] = Math.round((data[1]/5280) * 100)/100
        data[2] = Math.round((data[2] / 3600) * 100) / 100
        return data;
    }

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
        $scope.hideMeals = false;
        if (meals.length > 0) {
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
        } else {
            $scope.hideMeals = true;
        }
        
    });

    $scope.renderTeamChart = function () {
        $(function () {
            $('#teamChartContainer').highcharts({
                title: {
                    text: 'Team Comparison'
                },
                xAxis: {
                    categories: ['Steps (in thousands)', 'Distance (miles)', 'Duration (hours)']
                },
                series: $scope.teamHighChartsData
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
            });
        });
    };

    $scope.renderActivityStepsChart = function () {
        $(function () {
            $('#ActivityStepsContainer').highcharts({
                chart: {
                    zoomType: 'x'
                },
                tooltip: {
                    xDateFormat: '%A, %b %e, %Y'
                },
                title: {
                    text: 'Number of steps'
                },
                xAxis: {
                    type: 'datetime',
                    minRange: 7 * 24 * 3600000
                },
                yAxis: {
                    title: {
                        text: 'Daily Steps'
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
                    name: 'Daily Steps',
                    pointInterval: 24 * 3600 * 1000,
                    data: $scope.stepsHighchartsData
                }]
            });
        });
    };

    $scope.renderActivityDurationChart = function () {
        $(function () {
            $('#ActivityDurationContainer').highcharts({
                chart: {
                    zoomType: 'x'
                },
                tooltip: {
                    xDateFormat: '%A, %b %e, %Y'
                },
                title: {
                    text: 'Time Active'
                },
                xAxis: {
                    type: 'datetime',
                    minRange: 7 * 24 * 3600000
                },
                yAxis: {
                    title: {
                        text: 'Daily Activity Time (minutes)'
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
                    name: 'Daily ActivityTime',
                    pointInterval: 24 * 3600 * 1000,
                    data: $scope.durationHighchartsData
                }]
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
                }]
            });
        });

    }
    
    
}]);