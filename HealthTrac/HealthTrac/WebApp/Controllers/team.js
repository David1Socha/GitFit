gitFit.controller('TeamsController', ['$scope', 'TeamApi', function ($scope, TeamApi) {

    $scope.teams = []

    $scope.searchTeams = function (name) {
        $scope.teams = TeamApi.SearchTeams({ name: name });
    }
}]);