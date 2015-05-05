gitFit.controller('TeamProfileController', ['$scope', '$routeParams', 'TeamApi', 'UserApi', '$location', function ($scope, $routeParams, TeamApi, UserApi, $location) {
    $scope.teamId = $routeParams.teamId;
    $scope.noUsers = true;

    $scope.getUsersFromTeam = function () {
        var userResource = UserApi.GetUsersFromTeam({ teamId: $scope.teamId});
        userResource.$promise.then(function (users){
            $scope.users = users;
            if (users.length > 0){
                angular.forEach($scope.users, function (user){
                    if (user.FirstName == null || user.LastName == null) {
                        user.DisplayName = user.UserName;
                    } else {
                        user.DisplayName = user.FirstName + ' ' + user.LastName;
                    }
                });
                $scope.noUsers = false;
            }
            
        });   
    };

    $scope.getTeam = function () {
        var teamResource = TeamApi.GetTeam({ teamId: $scope.teamId });
        teamResource.$promise.then(function (team) {
            $scope.team = team;
            $scope.team.DateCreated = moment(team.DateCreated).format('MMMM DD, YYYY');
        })
    };
    
    $scope.viewProfile = function (userId) {
        $location.path('/User-Profile/' + userId);
    }

    $scope.getTeam();
    $scope.getUsersFromTeam();

}]);