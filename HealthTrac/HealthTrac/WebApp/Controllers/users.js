gitFit.controller('UsersController', ['$scope', 'UserApi', '$location', function ($scope, UserApi, $location) {

    $scope.searchCompleted = 'not completed';
    $scope.users = [];
    $scope.searchUsers = function (name) {
        var UsersResource = UserApi.SearchUsers({ name: name });
        UsersResource.$promise.then(function (users) {
            $scope.users = users;
            if (users.length == 0) {
                $scope.searchCompleted = 'no results';
            } else {
                $scope.searchCompleted = 'complete';
            }
            angular.forEach($scope.users, function (user) {
                user.DateCreated = moment(user.DateCreated).format('MMMM DD, YYYY');
            });
        });
    };

    $scope.resetSearch = function () {
        $scope.searchCompleted = 'not completed';
    }
    $scope.viewProfile = function (userId) {
        $location.path('/User-Profile/' + userId);
    }

}]);