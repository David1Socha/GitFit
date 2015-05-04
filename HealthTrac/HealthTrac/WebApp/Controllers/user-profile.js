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
            $scope.user.LifetimeDuration = Math.round($scope.user.LifetimeDuration / 3600);
            $scope.user.LifetimeDistance = Math.round($scope.user.LifetimeDistance / 5280);
            if ($scope.user.BirthDate == null){
                $scope.user.BirthDate = 'n/a';
            } else {
                $scope.user.BirthDate = moment($scope.user.BirthDate).format('MMMM DD, YYYY');
            }
            if ($scope.user.ProfilePicture.indexOf("square") > -1) {
                $scope.user.ProfilePicture = $scope.user.ProfilePicture.replace("square", "large");
            }
        });
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