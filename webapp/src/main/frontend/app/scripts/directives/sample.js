'use strict';
define(['ChoPidoTurnos'], function(ChoPidoTurnos) {

	ChoPidoTurnos.directive('sample', function() {
		return {
			restrict: 'E',
			template: '<span>Sample</span>'
		};
	});
});
