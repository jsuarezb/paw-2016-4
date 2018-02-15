'use strict';

define(['angularAMD'], function(angularAMD) {
    return {
      defaultRoutePath: '/',
      routes: [
        angularAMD.route({
          url: '/',
          name: 'home',
          templateUrl: 'views/home.html',
          controller: 'HomeCtrl',
          controllerUrl: 'controllers/HomeCtrl'
        }),
        angularAMD.route({
          url: '/institutions',
          name: 'institutions',
          templateUrl: 'views/institutions.html',
          controller: 'InstitutionsCtrl',
          controllerUrl: 'controllers/InstitutionsCtrl'
        }),
        angularAMD.route({
          url: '/doctors',
          name: 'doctors',
          templateUrl: 'views/doctors.html',
          controller: 'DoctorsCtrl',
          controllerUrl: 'controllers/DoctorsCtrl'
        }),
        angularAMD.route({
          url: '/neighborhoods',
          name: 'neighborhoods',
          templateUrl: 'views/neighborhoods.html',
          controller: 'NeighborhoodsCtrl',
          controllerUrl: 'controllers/NeighborhoodsCtrl'
        }),
        angularAMD.route({
          url: '/appointments',
          name: 'appointments',
          templateUrl: 'views/appointments.html',
          controller: 'AppointmentsCtrl',
          controllerUrl: 'controllers/AppointmentsCtrl'
        }),
        angularAMD.route({
          url: '/appointments/search?speciality&neighborhood&institution',
          name: 'appointmentSearch',
          templateUrl: 'views/appointmentSearch.html',
          controller: 'AppointmentSearchCtrl',
          controllerUrl: 'controllers/AppointmentSearchCtrl'
        }),
        angularAMD.route({
          url: '/appointments/patient/:patientId',
          name: 'patientAppointments',
          templateUrl: 'views/patientAppointments.html',
          controller: 'AppointmentsCtrl',
          controllerUrl: 'controllers/AppointmentsCtrl'
        }),
        angularAMD.route({
          url: '/patients',
          name: 'patients',
          templateUrl: 'views/patients.html',
          controller: 'PatientsCtrl',
          controllerUrl: 'controllers/PatientsCtrl'
        }),
        angularAMD.route({
          url: '/specialities',
          name: 'specialities',
          templateUrl: 'views/specialities.html',
          controller: 'SpecialitiesCtrl',
          controllerUrl: 'controllers/SpecialitiesCtrl'
        }),
        angularAMD.route({
          url: '/patients/:patientId',
          name: 'patient',
          templateUrl: 'views/patients.html',
          controller: 'PatientsCtrl',
          controllerUrl: 'controllers/PatientsCtrl'
        }),
        angularAMD.route({
          url: '/specialities/:specialityId',
          name: 'speciality',
          templateUrl: 'views/patients.html',
          controller: 'SpecialitiesCtrl',
          controllerUrl: 'controllers/SpecialitiesCtrl'
        }),
        angularAMD.route({
          url: '/institutions/:institutionId',
          name: 'institution',
          templateUrl: 'views/institutionDetail.html',
          controller: 'InstitutionDetailCtrl',
          controllerUrl: 'controllers/InstitutionDetailCtrl'
        }),
        angularAMD.route({
          url: '/doctors/:doctorId',
          name: 'doctor',
          templateUrl: 'views/doctorDetail.html',
          controller: 'DoctorDetailCtrl',
          controllerUrl: 'controllers/DoctorDetailCtrl'
        }),
        angularAMD.route({
          url: '/login',
          name: 'login',
          templateUrl: 'views/login.html',
          controller: 'LoginCtrl',
          controllerUrl: 'controllers/LoginCtrl'
        }),
        angularAMD.route({
          url: '/institutions/:institutionId/specialities',
          name: 'institutionSpecialities',
          templateUrl: 'views/institutionSpecialities.html',
          controller: 'InstitutionSpecialitiesCtrl',
          controllerUrl: 'controllers/InstitutionSpecialitiesCtrl'
        }),
        angularAMD.route({
          url: '/institutions/:institutionId/doctors',
          name: 'institutionDoctors',
          templateUrl: 'views/institutionDoctors.html',
          controller: 'InstitutionDoctorsCtrl',
          controllerUrl: 'controllers/InstitutionDoctorsCtrl'
        }),
        angularAMD.route({
          url: '/register',
          name: 'register',
          templateUrl: 'views/register.html',
          controller: 'RegisterCtrl',
          controllerUrl: 'controllers/RegisterCtrl'
        })
          /* ===== yeoman hook ===== */
          /* Do not remove these commented lines! Needed for auto-generation */
      ]
    };
});
