gitFit.controller('UserProfileController', ['$scope', '$routeParams', 'UserApi', 'TeamApi', function ($scope, $routeParams, UserApi, TeamApi) {
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
    $scope.getUser();
    $scope.getTeamsFromUser();
}]);