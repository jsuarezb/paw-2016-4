'use strict';
define(['ChoPidoTurnos'], function (ChoPidoTurnos) {
  function appointmentModalCtrl ($stateParams,sessionService, $scope, $uibModal, $log) {
    var $ctrl = this;

    this.$onInit = function() {
      this.institutionId = this.resolve.modalData.institutionId;
    };


    $scope.dayOfTheMonth = ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31'];

    $ctrl.handleSpecialitySelected = function(specialityId) {
      $ctrl.specialityId = specialityId;
      console.log('speciality' + specialityId);
    };
    $ctrl.handleDoctorSelected = function(doctorId) {
      $ctrl.doctorId = doctorId;
      console.log('doctor ' + doctorId);
    };
  }
  appointmentModalCtrl.$inject = ['$stateParams', 'sessionService', '$scope', '$uibModal', '$log'];

  ChoPidoTurnos.component('appointmentModal', {
    templateUrl: 'views/Modal/modalView.html',
    controller: appointmentModalCtrl,
    bindings: {
      modalInstance: '<',
      resolve: '<'
    }
  });
});
