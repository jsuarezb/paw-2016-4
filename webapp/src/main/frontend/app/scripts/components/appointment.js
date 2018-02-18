'use strict';

define(['ChoPidoTurnos'], function(ChoPidoTurnos) {
  function AppointmentCtrl() {
    return {
      isCanceling: false,
      onCancelClick: function() {
        var _this = this;
        this.isCanceling = true;

        this.onCancel({appointment: this.appointment})
          .catch(function(e) {
            _this.isCanceling = false;
          });
      }
    };
  }

  ChoPidoTurnos
    .component('appointment', {
      bindings: {
        appointment: '<',
        subject: '@',
        onCancel: '&'
      },
      controller: AppointmentCtrl,
      templateUrl: 'views/appointment.html'
    });
});
