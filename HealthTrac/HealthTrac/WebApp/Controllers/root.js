gitFit.controller('RootController', ['$scope', '$window', '$location', 'UserApi', 'AccountApi', function ($scope, $window, $location, UserApi, AccountApi) {
    var currentUserResource = UserApi.GetUser({ userId: "current" });

    currentUserResource.$promise.then(function (currentUser) {
        $scope.currentUser = currentUser;
        if ($scope.currentUser.FirstName == null || $scope.currentUser.LastName == null) {
            $scope.currentUserName = $scope.currentUser.UserName;
        } else {
            $scope.currentUserName = $scope.currentUser.FirstName + ' ' + $scope.currentUser.LastName;
        }
        
    });

    $scope.LogOff = function () {
        logoffResource = AccountApi.LogOff();
        logoffResource.$promise.then(function () {
            $window.location.reload();
        });
    };

    $scope.viewProfile = function (userId) {
        $location.path('/User-Profile/' + userId);
    }
    
}]);