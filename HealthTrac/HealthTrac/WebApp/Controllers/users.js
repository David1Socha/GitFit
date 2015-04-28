gitFit.controller('UsersController', ['$scope', 'UserApi', '$location', function ($scope, UserApi, $location) {

    $scope.users = [];
    $scope.searchUsers = function (name) {
        $scope.users = UserApi.SearchUsers({ name: name });
    };
    $scope.viewProfile = function (userId) {
        $location.path('/User-Profile/' + userId);
    }

}]);