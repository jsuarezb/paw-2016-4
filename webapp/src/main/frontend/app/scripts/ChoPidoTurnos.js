'use strict';
define(['routes',
  'services/dependencyResolverFor',
  'i18n/i18nLoader!',
  'angularAMD',
  'angular',
  'angular-ui-router',
  'bootstrap',
  'angular-translate'],
  function(config, dependencyResolverFor, i18n, angularAMD) {
    var ChoPidoTurnos = angular.module('ChoPidoTurnos', [
      'pascalprecht.translate',
      'ui.router'
    ]);
    ChoPidoTurnos
      .config(
        ['$stateProvider',
        '$controllerProvider',
        '$compileProvider',
        '$filterProvider',
        '$provide',
        '$translateProvider',
        '$urlServiceProvider',
        '$httpProvider',
        function($stateProvider, $controllerProvider, $compileProvider, $filterProvider,
                 $provide, $translateProvider, $urlServiceProvider, $httpProvider) {

          ChoPidoTurnos.controller = $controllerProvider.register;
          ChoPidoTurnos.directive = $compileProvider.directive;
          ChoPidoTurnos.filter = $filterProvider.register;
          ChoPidoTurnos.factory = $provide.factory;
          ChoPidoTurnos.service = $provide.service;

          if (config.routes !== undefined) {
            angular.forEach(config.routes, function(route) {
              $stateProvider.state(route);
            });
          }
          if (config.defaultRoutePath !== undefined) {
            $urlServiceProvider.rules.otherwise('/');
          }

          $translateProvider.translations('preferredLanguage', i18n);
          $translateProvider.preferredLanguage('preferredLanguage');
        }]);
    return ChoPidoTurnos;
  }
);
