gitFit.controller('UsersController', ['$scope', 'UserApi', function ($scope, UserApi) {

    $scope.users = [];
    $scope.searchUsers = function (name) {
        $scope.users = UserApi.SearchUsers({ name: name });
    };

}]);