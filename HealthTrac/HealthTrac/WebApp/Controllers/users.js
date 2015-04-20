gitFit.controller('UsersController', ['$scope', 'UserApi', function ($scope, UserApi) {
    $scope.searchUsers = function (name) {
        UserApi.SearchUsers({ name: name });
    };
}]);