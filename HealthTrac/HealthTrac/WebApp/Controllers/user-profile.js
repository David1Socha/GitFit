gitFit.controller('UserProfileController', ['$scope', '$routeParams', 'UserApi', 'TeamApi', '$location', function ($scope, $routeParams, UserApi, TeamApi, $location) {
    $scope.userId = $routeParams.userId;

    $scope.getUser = function () {
        var userResource = UserApi.GetUser({ userId: $scope.userId });
        userResource.$promise.then(function (user) {
            $scope.user = user;
            if (user.Sex == 0) {
                $scope.user.Sex = 'Male'
            } else {
                $scope.user.Sex = 'Female'
            }
            $scope.user.DateCreated = moment(user.DateCreated).format('MMMM DD, YYYY');
        })
    };
    $scope.noTeams = true;
    $scope.getTeamsFromUser = function () {
        var teamResource = TeamApi.GetTeamsFromUser({ userId: $scope.userId });
        teamResource.$promise.then(function (teams) {
            $scope.teams = teams;
            if (teams.length != 0) {
                $scope.noTeams = false;
            }
        });
    };

    $scope.viewProfile = function (teamId) {
        $location.path('/Team-Profile/' + teamId);
    }

    $scope.getUser();
    $scope.getTeamsFromUser();
}]);