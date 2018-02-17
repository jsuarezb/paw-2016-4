'use strict';
define(['ChoPidoTurnos', 'services/institutionsService'], function(ChoPidoTurnos,institutionsService) {
  function DoctorListCtrl ($scope,institutionsService) {
    var _this = this;
    $scope.doctors = ['SNI','SNU','SNA','Sne'];
    this.getDoctors = function() {
      institutionsService
        .getInstitutionDoctors(this.institutionId)
        .then(function(result) {
          _this.doctors = result.data;
          console.log(result.data);
        });
    };
    console.log(_this.doctors);
    this.handleDoctorSelected = function (doctorId) {
      this.onDoctorSelected({doctorId: doctorId});
    };

    this.$onInit = function() {
      this.getDoctors();
    };

  }
  DoctorListCtrl.$inject = ['$scope', 'institutionsService'];

  ChoPidoTurnos.component('doctorComponent', {
    templateUrl: 'views/Modal/doctorsListInnerView.html',
    controller: DoctorListCtrl,
    bindings: {
      onDoctorSelected: '&',
      institutionId: '<'
    }
  });


});
