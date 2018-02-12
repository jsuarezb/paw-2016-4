'use strict';

define([], function() {
    return {
        defaultRoutePath: '/',
        routes: {
            '/': {
                templateUrl: '/views/home.html',
                controller: 'HomeCtrl'
            },
          '/institutions': {
            templateUrl: '/views/institutions.html',
            controller: 'InstitutionsCtrl'
            },
          '/doctors': {
            templateUrl: '/views/doctors.html',
            controller: 'DoctorsCtrl'
          },
          '/neighborhoods': {
            templateUrl: '/views/neighborhoods.html',
            controller: 'NeighborhoodsCtrl'
          },
          '/appointments': {
            templateUrl: '/views/appointments.html',
            controller: 'AppointmentsCtrl'
          },
          '/appointments/patient/:patientId': {
            templateUrl: '/views/patientAppointments.html',
            controller: 'AppointmentsCtrl'
          },
          '/patients': {
            templateUrl: '/views/patients.html',
            controller: 'PatientsCtrl'
          },
          '/specialities': {
            templateUrl: '/views/specialities.html',
            controller: 'SpecialitiesCtrl'
          },
          '/patients/:patientId': {
            templateUrl: '/views/patients.html',
            controller: 'PatientsCtrl'
          },
          '/specialities/:specialityId': {
            templateUrl: '/views/patients.html',
            controller: 'SpecialitiesCtrl'
          },
          '/institutions/:institutionId': {
            templateUrl: '/views/institutionDetail.html',
            controller: 'InstitutionsCtrl'
          },
          '/doctors/:doctorId': {
            templateUrl: '/views/doctorDetail.html',
            controller: 'DoctorsCtrl'
          },
          '/login': {
            templateUrl: '/views/login.html',
            controller: 'LoginCtrl'
          },
          '/institutions/:institutionId/specialities' : {
            templateUrl: '/views/institutionSpecialities.html',
            controller: 'InstitutionsCtrl'
          },
          '/institutions/:institutionId/doctors' : {
            templateUrl: '/views/institutionDoctors.html',
            controller: 'InstitutionsCtrl'
          }
            /* ===== yeoman hook ===== */
            /* Do not remove these commented lines! Needed for auto-generation */
        }
    };
});
