'use strict';
define(['ChoPidoTurnos', 'components/appointmentModal'], function (ChoPidoTurnos) {
  ChoPidoTurnos.component('appointmentsModalPresenter', {
    template: '<button type="button" class="btn btn-default" ng-click="$ctrl.open()">Reservar turno</button>',
    bindings:{
      institutionId :"<",
    },
    controller: function ($uibModal) {
      var $ctrl = this;

      $ctrl.$onInit = function () {
        $ctrl.dataForModal= {
          institutionId: this.institutionId
        };
        console.log(this);
      }
      $ctrl.open = function () {
        $uibModal.open({
          component: 'appointmentModal',
          resolve: {
            modalData: function () {
              return $ctrl.dataForModal;
            }
          }
        }).result.then(function (result) {
          console.info("I was closed, so do what I need to do myContent's controller now. Result was->");
          console.info(result);
        }, function (reason) {
          console.info("I was dimissed, so do what I need to do myContent's controller now. Reason was->" + reason);
        });
      };

    }
  });
});
