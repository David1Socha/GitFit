gitFit.controller('RootController', ['$scope', '$window', '$location', 'UserApi', 'AccountApi', function ($scope, $window, $location, UserApi, AccountApi) {
    var currentUserResource = UserApi.GetUser({ userId: "current" });

    $scope.isFacebookUser = false;

    currentUserResource.$promise.then(function (currentUser) {
        $scope.currentUser = currentUser;
        if ($scope.currentUser.FirstName == null || $scope.currentUser.LastName == null) {
            $scope.currentUserName = $scope.currentUser.UserName;
        } else {
            $scope.currentUserName = $scope.currentUser.FirstName + ' ' + $scope.currentUser.LastName;
        }
        if (currentUser.ProfilePicture.indexOf("facebook") != -1) {
            $scope.isFacebookUser = true;
        }
    });

    $scope.LogOff = function () {
        var logoffResource = AccountApi.LogOff();
        logoffResource.$promise.then(function () {
            $window.location.reload();
        });
    };

    $scope.viewCurrentProfile = function (userId) {
        $location.path('/User-Profile/' + userId);
    }
    
}]);