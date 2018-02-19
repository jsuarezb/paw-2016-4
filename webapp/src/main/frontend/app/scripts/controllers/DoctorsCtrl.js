'use strict';
define(['ChoPidoTurnos', 'moment', 'services/doctorsService', 'services/appointmentsService'],
    function(ChoPidoTurnos, moment) {

  ChoPidoTurnos.controller('DoctorsCtrl',
    ['doctorsService', '$stateParams', '$scope', function(doctorsService, $stateParams, $scope) {
    var _this = this;

    this.doctorsFetched = false;
    this.weekNumber = moment().weeks();
    this.year = moment().year();

    this.hasPrevPage = function () {
      return this.currentPage && this.currentPage.page > 0;
    };

    this.hasNextPage = function () {
      return this.currentPage && this.currentPage.page < Math.floor(this.currentPage.total / this.currentPage.pageSize);
    };

    this.nextPage = function() {
      _this.loadPage(_this.currentPage.page + 1);
    };

    this.prevPage = function() {
      _this.loadPage(_this.currentPage.page - 1);
    };

    $scope.firstName = $stateParams.firstName;
    $scope.lastName = $stateParams.lastName;

    this.isLoading = false;

    this.loadPage = function(page) {
      _this.isLoading = true;

      doctorsService
        .getDoctors($stateParams.firstName || '', $stateParams.lastName || '', page)
        .then(function(result) {
          _this.doctorsFetched = true;
          _this.currentPage = result.data;
          _this.doctors = result.data.results;

          _this.isLoading = false;
        });
    };

    this.loadPage(0);
  }]);
});
