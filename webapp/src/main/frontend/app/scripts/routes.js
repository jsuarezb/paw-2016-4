'use strict';

define(['angularAMD'], function(angularAMD) {
    return {
      defaultRoutePath: '/',
      routes: [
        angularAMD.route({
          url: '/',
          name: 'home',
          templateUrl: 'views/home.html',
          controller: 'HomeCtrl as $ctrl',
          controllerUrl: 'controllers/HomeCtrl'
        }),
        angularAMD.route({
          url: '/institutions',
          name: 'institutions',
          templateUrl: 'views/institutions.html',
          controller: 'InstitutionsCtrl as $ctrl',
          controllerUrl: 'controllers/InstitutionsCtrl'
        }),
        angularAMD.route({
          url: '/doctors?firstName&lastName',
          name: 'doctors',
          templateUrl: 'views/doctors.html',
          controller: 'DoctorsCtrl as $ctrl',
          controllerUrl: 'controllers/DoctorsCtrl'
        }),
        angularAMD.route({
          url: '/neighborhoods',
          name: 'neighborhoods',
          templateUrl: 'views/neighborhoods.html',
          controller: 'NeighborhoodsCtrl as $ctrl',
          controllerUrl: 'controllers/NeighborhoodsCtrl'
        }),
        angularAMD.route({
          url: '/appointments',
          name: 'appointments',
          templateUrl: 'views/appointments.html',
          controller: 'AppointmentsCtrl as $ctrl',
          controllerUrl: 'controllers/AppointmentsCtrl'
        }),
        angularAMD.route({
          url: '/appointments/search?speciality&neighborhood&institution&doctor',
          name: 'appointmentSearch',
          templateUrl: 'views/appointmentSearch.html',
          controller: 'AppointmentSearchCtrl as $ctrl',
          controllerUrl: 'controllers/AppointmentSearchCtrl'
        }),
        angularAMD.route({
          url: '/appointments/patient',
          name: 'patientAppointments',
          templateUrl: 'views/patientAppointments.html',
          controller: 'AppointmentsCtrl as $ctrl',
          controllerUrl: 'controllers/AppointmentsCtrl'
        }),
        angularAMD.route({
          url: '/appointments/doctor',
          name: 'doctorAppointments',
          templateUrl: 'views/doctorAppointments.html',
          controller: 'DoctorAppointmentsCtrl as $ctrl',
          controllerUrl: 'controllers/DoctorAppointmentsCtrl'
        }),
        angularAMD.route({
          url: '/patients',
          name: 'patients',
          templateUrl: 'views/patients.html',
          controller: 'PatientsCtrl as $ctrl',
          controllerUrl: 'controllers/PatientsCtrl'
        }),
        angularAMD.route({
          url: '/specialities',
          name: 'specialities',
          templateUrl: 'views/specialities.html',
          controller: 'SpecialitiesCtrl as $ctrl',
          controllerUrl: 'controllers/SpecialitiesCtrl'
        }),
        angularAMD.route({
          url: '/patients/:patientId',
          name: 'patient',
          templateUrl: 'views/patients.html',
          controller: 'PatientsCtrl as $ctrl',
          controllerUrl: 'controllers/PatientsCtrl'
        }),
        angularAMD.route({
          url: '/specialities/:specialityId',
          name: 'speciality',
          templateUrl: 'views/patients.html',
          controller: 'SpecialitiesCtrl as $ctrl',
          controllerUrl: 'controllers/SpecialitiesCtrl'
        }),
        angularAMD.route({
          url: '/institutions/:institutionId',
          name: 'institution',
          templateUrl: 'views/institutionDetail.html',
          controller: 'InstitutionDetailCtrl as $ctrl',
          controllerUrl: 'controllers/InstitutionDetailCtrl'
        }),
        angularAMD.route({
          url: '/doctors/:doctorId',
          name: 'doctor',
          templateUrl: 'views/doctorDetail.html',
          controller: 'DoctorDetailCtrl as $ctrl',
          controllerUrl: 'controllers/DoctorDetailCtrl'
        }),
        angularAMD.route({
          url: '/login',
          name: 'login',
          templateUrl: 'views/login.html',
          controller: 'LoginCtrl as $ctrl',
          controllerUrl: 'controllers/LoginCtrl'
        }),
        angularAMD.route({
          url: '/institutions/:institutionId/specialities',
          name: 'institutionSpecialities',
          templateUrl: 'views/institutionSpecialities.html',
          controller: 'InstitutionSpecialitiesCtrl as $ctrl',
          controllerUrl: 'controllers/InstitutionSpecialitiesCtrl'
        }),
        angularAMD.route({
          url: '/institutions/:institutionId/doctors',
          name: 'institutionDoctors',
          templateUrl: 'views/institutionDoctors.html',
          controller: 'InstitutionDoctorsCtrl as $ctrl',
          controllerUrl: 'controllers/InstitutionDoctorsCtrl'
        }),
        angularAMD.route({
          url: '/bookedAppointment',
          name: 'bookedAppointment',
          templateUrl: 'views/bookedAppointment.html',
          controller: 'BookedAppointmentCtrl as $ctrl',
          controllerUrl: 'controllers/BookedAppointmentCtrl'
        }),
        angularAMD.route({
          url: '/register',
          name: 'register',
          templateUrl: 'views/register.html',
          controller: 'RegisterCtrl as $ctrl',
          controllerUrl: 'controllers/RegisterCtrl'
        }),
        angularAMD.route({
          url: '/recover?token',
          name: 'recover',
          templateUrl: 'views/recover.html',
          controller: 'RecoverPasswordCtrl',
          controllerUrl: 'controllers/RecoverPasswordCtrl'
        }),
        angularAMD.route({
          url: '/requestPasswordRecovery',
          name: 'requestPasswordRecovery',
          templateUrl: 'views/requestPasswordRecovery.html',
          controller: 'RequestPasswordRecoveryCtrl',
          controllerUrl: 'controllers/RequestPasswordRecoveryCtrl'
        })
          /* ===== yeoman hook ===== */
          /* Do not remove these commented lines! Needed for auto-generation */
      ]
    };
});
