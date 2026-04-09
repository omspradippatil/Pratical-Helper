var app = angular.module("studentApp", []);

app.controller("StudentController", function($scope) {
  $scope.students = [
    { roll: 1, name: "Amit", marks: 80 },
    { roll: 2, name: "Pooja", marks: 82 },
    { roll: 3, name: "Rahul", marks: 77 },
    { roll: 4, name: "Sneha", marks: 91 },
    { roll: 5, name: "Kiran", marks: 73 }
  ];
});
