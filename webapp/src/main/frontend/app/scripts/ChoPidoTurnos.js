'use strict';
define(['routes',
	'services/dependencyResolverFor',
	'i18n/i18nLoader!',
	'angular',
	'angular-route',
	'bootstrap',
	'angular-translate'],
	function(config, dependencyResolverFor, i18n) {
		var ChoPidoTurnos = angular.module('ChoPidoTurnos', [
			'ngRoute',
			'pascalprecht.translate'
		]);
		ChoPidoTurnos
			.config(
				['$routeProvider',
				'$controllerProvider',
				'$compileProvider',
				'$filterProvider',
				'$provide',
				'$translateProvider',
				function($routeProvider, $controllerProvider, $compileProvider, $filterProvider, $provide, $translateProvider) {

					ChoPidoTurnos.controller = $controllerProvider.register;
					ChoPidoTurnos.directive = $compileProvider.directive;
					ChoPidoTurnos.filter = $filterProvider.register;
					ChoPidoTurnos.factory = $provide.factory;
					ChoPidoTurnos.service = $provide.service;

					if (config.routes !== undefined) {
						angular.forEach(config.routes, function(route, path) {
							$routeProvider.when(path, {templateUrl: route.templateUrl, resolve: dependencyResolverFor(['controllers/' + route.controller]), controller: route.controller, gaPageTitle: route.gaPageTitle});
						});
					}
					if (config.defaultRoutePath !== undefined) {
						$routeProvider.otherwise({redirectTo: config.defaultRoutePath});
					}

					$translateProvider.translations('preferredLanguage', i18n);
					$translateProvider.preferredLanguage('preferredLanguage');
				}]);
		return ChoPidoTurnos;
	}
);
