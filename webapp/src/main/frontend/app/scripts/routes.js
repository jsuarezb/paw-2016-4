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
          '/patients': {
            templateUrl: '/views/patients.html',
            controller: 'PatientsCtrl'
          }
            /* ===== yeoman hook ===== */
            /* Do not remove these commented lines! Needed for auto-generation */
        }
    };
});
