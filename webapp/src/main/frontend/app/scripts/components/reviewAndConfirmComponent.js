'use strict';
define(['ChoPidoTurnos'], function(ChoPidoTurnos) {
  function ReviewAndConfirmCtrl() {
    var _this = this;
    $scope.attributes = ['Doctor: Juan Perez', 'Especialidad: Traumatología', 'Institución: Sanatorio de la trinidad', 'Fecha: 12 de marzo de 2018 a las 15:00 hs'];
    /* institutionService.getInstitutionSpecialities(1).then(
      function(result) {
        console.log(result);
        _this.specialities = result.data;
      }
    ); */
  }

  ChoPidoTurnos.component('reviewAndConfirmComponent', {
    templateUrl: 'views/Modal/reviewAndConfirmInnerView.html',
    controller: ReviewAndConfirmCtrl,
    bindings: {
      modalInstance: '<',
      resolve: '<'
    }
  });


});
