angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8080/gameplay';

    $scope.loadPage = function () {
        $http({
            url: contextPath + '/new?player1=Maxxx&player2=tset',
            method: 'GET',
            params: {
            }
        }).then(function (response) {
            $scope.productsPage = response.data;
            console.log(response.data);
        });
    };

    $scope.loadCart = function () {
        $http({
            url: contextPath + '/new?player1=Maxxx&player2=tset',
            method: 'GET',
            params: {
            }
        }).then(function (response) {
            $scope.cart = response.data;
            console.log(response.data);
        });
    };

    $scope.addProductToCart = function (productId) {
        $http({
            url: contextPath + '/cart/add/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.loadCart();
        });
    };

    $scope.loadOrders = function () {
        if (!$scope.isUserLoggedIn()) {
            return;
        }
        $http({
            url: contextPath + '/orders/user',
            method: 'GET'
        }).then(function (response) {
            $scope.orders = response.data;
        });
    }
    $scope.clearCart = function () {
        $http.get(contextPath + '/new?player1=Maxxx&player2=tset')
            .then(function (response) {
                $scope.MyOrders = response.data;
            });
    };

    $scope.createOrder = function () {
        $http({
            url: contextPath + '/orders',
            method: 'POST'
        }).then(function (response) {
            alert('Order created');
            $scope.loadCart();
            $scope.loadOrders();
        });
    }

    $scope.save = function () {
        $http({
            url: contextPath + '/cart/save_order/',
            method: 'GET'
        }).then(function (response) {
            $scope.loadCart();
        });
    };

    $scope.removeProductToCart = function (id) {
        $http({
            url: contextPath + '/cart/delete/' + id,
            method: 'GET'
        }).then(function (response) {
            $scope.loadCart();
        });
    };

    $scope.clearProductToCart = function (id) {
        $http({
            url: contextPath + '/cart/clear/' + id,
            method: 'GET'
        }).then(function (response) {
            $scope.loadCart();
            $scope.loadCart();
        });
    };


    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.logUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                    $scope.loadOrders();
                }
            }, function errorCallback(response) {
            });
    };

    $scope.clearUser = function () {
        delete $localStorage.logUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.logUser) {
            return true;
        } else {
            return false;
        }
    };

    if ($localStorage.logUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.logUser.token;
        $scope.loadOrders();
    }

    $scope.loadPage();
    $scope.loadCart();
    $scope.loadOrders();
});