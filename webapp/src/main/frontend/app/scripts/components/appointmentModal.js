'use strict';
define(['ChoPidoTurnos'], function (ChoPidoTurnos) {
  function appointmentModalCtrl ($stateParams,sessionService, $scope, $uibModal, $log) {
    var $ctrl = this;

    this.$onInit = function() {
      this.institutionId = this.resolve.modalData.institutionId;
      console.log(this.institutionId);
    };
    $ctrl.setActiveTab = function(index) {
      $ctrl.activeTab = index;
    };
    $ctrl.handleSpecialitySelected = function(specialityId) {
      $ctrl.specialityId = specialityId;
      $ctrl.setActiveTab(1);
      console.log('speciality' + specialityId);
    };
    $ctrl.handleDoctorSelected = function(doctorId) {
      $ctrl.doctorId = doctorId;
      $ctrl.setActiveTab(2);
      console.log('doctor ' + doctorId);
    };
    $ctrl.handleAppointmentSelected = function(appointment) {
      $ctrl.appointment = appointment;
      $ctrl.setActiveTab(3);
      console.log('appointment ' + appointment);
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
