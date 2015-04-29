gitFit.controller('RootController', ['$scope', 'UserApi', 'AccountApi', function ($scope, UserApi, AccountApi) {
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
        AccountApi.LogOff();
    }
    
}]);