gitFit.controller('TeamProfileController', ['$scope', '$routeParams', 'TeamApi', function ($scope, $routeParams, TeamApi) {
    $scope.teamId = $routeParams.teamId;

    $scope.getTeam = function (teamId) {
        var teamResource = UTeamApi.GetTeam({ teamId: $scope.teamId });
        teamResource.$promise.then(function (team) {
            $scope.team = team;
        })
    };
    $scope.getTeam();
}]);