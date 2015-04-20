gitFit.controller('TeamsController', ['$scope', 'TeamApi', function ($scope, TeamApi) {
    $scope.searchTeams = function (name) {
        TeamApi.SearchTeams({ name: name });
    }
}]);