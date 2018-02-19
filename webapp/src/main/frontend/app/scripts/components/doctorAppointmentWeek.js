'use strict';

define(['ChoPidoTurnos',
        'moment'],
        function (ChoPidoTurnos, moment) {
  function DoctorAppointmentWeekController($scope, $state, appointmentsService, sessionService) {
    return {
      '$onInit': function() {
        var _this = this;

        this.isBooking = false;

        this.currentStartOfWeek = moment().day('Sunday')
          .week(this.weekNumber)
          .year(this.year);

        $scope.$on('appointments.loaded', function (event, appointments) {
          _this.onAppointmentsLoaded(appointments);
        });

        $scope.$on('date.change', function (event, date) {
          _this.weekNumber = date.weekNumber;
          _this.year = date.year;
          _this.loadAppointments();
        });
      },
      loadAppointments: function() {
        var _this = this;

        appointmentsService
          .getDoctorAvailableAppointments(this.doctorId, this.weekNumber, this.year)
          .then(function (result) {
            var data = result.data;
            _this.onAppointmentsLoaded(data);
          });
      },
      onAppointmentsLoaded: function (appointments) {
        var _this = this;

        _this.daySlots = {};

        appointments
          .filter(function (appointment) {
            var now = new Date();

            return now < new Date(appointment.date);
          })
          .forEach(function(freeAppointment) {
            if (!_this.daySlots[freeAppointment.appointmentSlot.dayOfWeek]) {
              _this.daySlots[freeAppointment.appointmentSlot.dayOfWeek] = [];
            }

            _this.daySlots[freeAppointment.appointmentSlot.dayOfWeek].push(freeAppointment);
        });
      },
      onAppointmentSelected: function(appointment) {
        this.selectedAppointment = appointment;
      },
      bookSelectedAppointment: function() {
        var appointment = this.selectedAppointment;
        var _this = this;

        this.isBooking = true;

        appointmentsService
          .postAppointment({
            slotId: appointment.appointmentSlot.id,
            weekNumber: moment(appointment.date).week(),
            year: moment(appointment.date).year(),
            comments: appointment.comments
          })
          .then(function(response) {
            _this.isBooking = false;

            if (response.status !== 200) {
              // TODO
              return;
            }

            $state.go('patientAppointments');
          });
      },
      isUserLogged: function() {
        return !!sessionService.getLoggedUser();
      },
      login: function() {
        $state.go('login', {redirectTo: $state.current.name, redirectParams: JSON.stringify($state.params)});
      },
      days: [1, 2, 3, 4, 5, 6, 7],
      daysString: ['DOM', 'LUN', 'MAR', 'MIE', 'JUE', 'VIE', 'SAB'],
      dayNumber: function (dayIndex) {
        return this.currentStartOfWeek.clone().add(dayIndex, 'days').date();
      }
    };
  }

  DoctorAppointmentWeekController.$inject = ['$scope', '$state', 'appointmentsService', 'sessionService'];

  ChoPidoTurnos
    .component('doctorAppointmentWeek', {
      controller: DoctorAppointmentWeekController,
      bindings: {
        doctorId: '<',
        weekNumber: '<',
        year: '<'
      },
      templateUrl: 'views/doctorAppointmentWeek.html'
    });
});
