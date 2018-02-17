'use strict';
define(['ChoPidoTurnos', 'services/appointmentsService'], function(ChoPidoTurnos) {

  ChoPidoTurnos.controller('DoctorAppointmentsCtrl', ['appointmentsService', function(appointmentsService) {
      var _this = this;

      this.alerts = [];

      this.pastAppointmentsFetched = false;
      this.futureAppointmentsFetched = false;

      this.cancelAppointment = function (appointment) {
        appointmentsService
          .deleteAppointment(appointment.id)
          .then(function (response) {
            if (response.status >= 400) {
              _this.alerts.push({message: 'Ha ocurrido un error cancelando su turno', type: 'danger'});
            }
            _this.alerts.push({message: 'Su turno ha sido cancelado', type: 'success'});
            _this.futureAppointments.splice(_this.futureAppointments.indexOf(appointment), 1);
          });
      };

      appointmentsService
        .getDoctorFutureAppointments()
        .then(function (response) {
          _this.futureAppointmentsFetched = true;
          _this.futureAppointments = response.data.map(function (app) {
            app.isPast = false;
            return app;
          });
        });

      appointmentsService
        .getDoctorPastAppointments()
        .then(function (response) {
          console.log(response.data);
          _this.pastAppointmentsFetched = true;
          _this.pastAppointments = response.data.map(function (app) {
            app.isPast = true;
            return app;
          });
        });
    }
  ]);
});
