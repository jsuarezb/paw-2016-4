'use strict';
define(['ChoPidoTurnos', 'services/doctorsService'], function(ChoPidoTurnos,institutionsService) {
  function DoctorListCtrl ($scope,doctorsService) {
    var _this = this;

    this.getDoctors = function() {
      doctorsService
        .getInstitutionSpecialityDoctors(this.institutionId,this.specialityId)
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
      this.specialityId = 1;
      this.getDoctors();
    };

  }
  DoctorListCtrl.$inject = ['$scope', 'doctorsService'];

  ChoPidoTurnos.component('doctorComponent', {
    templateUrl: 'views/Modal/doctorsListInnerView.html',
    controller: DoctorListCtrl,
    bindings: {
      onDoctorSelected: '&',
      institutionId: '<'
    }
  });


});
