'use strict';

define(['ChoPidoTurnos'], function (ChoPidoTurnos) {

  function CalendarAppointmentController() {
    return {
      book: function() {
        console.log(this.appointment);
      },
      appointmentSelected: function(appointment) {
        this.onAppointmentSelected(appointment);
      }
    };
  }

  ChoPidoTurnos
    .component('calendarAppointment', {
      bindings: {
        appointment: '<',
        onAppointmentSelected: '&'
      },
      controller: CalendarAppointmentController,
      templateUrl: 'views/calendarAppointment.html'
    });
});
