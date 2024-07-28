var app = angular.module("app", []);
app.controller("ctrl", function ($scope, $http, $window) {
  $scope.form = {};
  $scope.items = [];

  function loadItems() {
    $http
      .get("http://localhost:8080/view/list")
      .then(function (response) {
        $scope.items = response.data;
      })
      .catch(function (error) {
        console.error("Error loading items:", error);
      });
  }

  $scope.create = function () {
    $http
      .post("http://localhost:8080/view/create", $scope.form)
      .then(function (response) {
        $scope.items.push(response.data);
        $scope.form = {};
        $window.location.reload(); // Use $window to reload the page
      })
      .catch(function (error) {
        console.error("Error creating item:", error);
      });
  };

  $scope.update = function () {
    if ($scope.form.id) {
      $http
        .put("http://localhost:8080/view/update/" + $scope.form.id, $scope.form)
        .then(function () {
          loadItems();
          $scope.form = {};
          $window.location.reload(); // Use $window to reload the page
        })
        .catch(function (error) {
          console.error("Error updating item:", error);
        });
    }
  };

  $scope.delete = function (item) {
    $http
      .delete("http://localhost:8080/view/delete/" + item.id)
      .then(function () {
        loadItems();
        $window.location.reload(); // Use $window to reload the page
      })
      .catch(function (error) {
        console.error("Error deleting item:", error);
      });
  };

  $scope.edit = function (item) {
    $scope.form = angular.copy(item);
  };

  loadItems();
});
