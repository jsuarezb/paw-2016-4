'use strict';

define(['ChoPidoTurnos'], function (ChoPidoTurnos) {

  function CalendarAppointmentController() {
    return {
      book: function() {
        console.log(this.appointment);
      }
    };
  }

  ChoPidoTurnos
    .component('calendarAppointment', {
      bindings: {
        appointment: '<'
      },
      controller: CalendarAppointmentController,
      templateUrl: 'views/calendarAppointment.html'
    });
});
