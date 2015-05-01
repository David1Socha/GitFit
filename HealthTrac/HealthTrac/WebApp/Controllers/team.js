gitFit.controller('TeamsController', ['$scope', 'TeamApi', '$location', function ($scope, TeamApi, $location) {

    $scope.searchCompleted = 'not completed';
    $scope.teams = [];
    $scope.searchTeams = function (name) {
        TeamsResource = TeamApi.SearchTeams({ name: name });
        TeamsResource.$promise.then(function (teams) {
            $scope.teams = teams;
            if (teams.length == 0) {
                $scope.searchCompleted = 'no results';
            } else {
                $scope.searchCompleted = 'complete';
            }
            angular.forEach($scope.teams, function (team) {
                team.DateCreated = moment(team.DateCreated).format('MMMM DD, YYYY');
            });
        });
    };

    $scope.resetSearch = function () {
        $scope.searchCompleted = 'not completed';
    }
    $scope.viewProfile = function (teamId) {
        $location.path('/Team-Profile/' + teamId);
    }

}]);