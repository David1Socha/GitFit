gitFit.controller('UserProfileController', ['$scope', '$routeParams', 'UserApi', function ($scope, $routeParams, UserApi) {
    $scope.userId = $routeParams.userId;

    $scope.getUser = function (userId) {
        var userResource = UserApi.GetUser({ userId: $scope.userId });
        userResource.$promise.then(function (user) {
            $scope.user = user;
        })
    };
    $scope.getUser();
}]);