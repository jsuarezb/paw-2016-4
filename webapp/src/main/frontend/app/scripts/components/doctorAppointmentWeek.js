'use strict';

define(['ChoPidoTurnos', 'moment'], function (ChoPidoTurnos, moment) {
  function DoctorAppointmentWeekController($scope, appointmentsService, dateService) {
    return {
      '$onInit': function() {
        var now = new Date();
        var _this = this;

        this.weekNumber = dateService.weekOfYear(now);
        this.year = now.getFullYear();

        appointmentsService
          .getDoctorAvailableAppointments(this.doctorId, dateService.weekOfYear(now), now.getFullYear())
          .then(function (result) {
            var data = result.data;
            _this.daySlots = {};

            data
//              .filter(function(appointment) {
//                var now = new Date();
//
//                return now < new Date(appointment.date);
//              })
              .forEach(function(freeAppointment) {
                if (!_this.daySlots[freeAppointment.appointmentSlot.dayOfWeek]) {
                  _this.daySlots[freeAppointment.appointmentSlot.dayOfWeek] = [];
                }

                _this.daySlots[freeAppointment.appointmentSlot.dayOfWeek].push(freeAppointment);
            });
            console.log(data);
          });
      },
      days: [1, 2, 3, 4, 5, 6, 7],
      daysString: ['LUN', 'MAR', 'MIE', 'JUE', 'VIE', 'SAB', 'DOM']
    };
  }

  DoctorAppointmentWeekController.$inject = ['$scope', 'appointmentsService', 'dateService'];

  ChoPidoTurnos
    .component('doctorAppointmentWeek', {
      controller: DoctorAppointmentWeekController,
      bindings: {
        doctorId: '<'
      },
      templateUrl: 'views/doctorAppointmentWeek.html'
    });
});
