gitFit.controller('TeamProfileController', ['$scope', '$routeParams', 'TeamApi', 'UserApi', '$location', function ($scope, $routeParams, TeamApi, UserApi, $location) {
    $scope.teamId = $routeParams.teamId;
    $scope.noUsers = true;

    $scope.getUsersFromTeam = function () {
        $scope.users = [];
        angular.forEach($scope.team.Memberships, function (membership) {
            if (membership.UserID != null) {
                var userResource = UserApi.GetUser({ userId: membership.UserID });
                userResource.$promise.then(function (user) {
                    if (user.FirstName == null || user.LastName == null) {
                        user.DisplayName = user.UserName;
                    } else {
                        user.DisplayName = user.FirstName + ' ' + user.LastName;
                    }
                    $scope.users.push(user);
                    $scope.noUsers = false;
                });
            }
            
        });      
    };

    $scope.getTeam = function () {
        var teamResource = TeamApi.GetTeam({ teamId: $scope.teamId });
        teamResource.$promise.then(function (team) {
            $scope.team = team;
            $scope.team.DateCreated = moment(team.DateCreated).format('MMMM DD, YYYY');
            $scope.getUsersFromTeam();
        })
    };
    
    $scope.viewProfile = function (userId) {
        $location.path('/User-Profile/' + userId);
    }

    $scope.getTeam();

}]);