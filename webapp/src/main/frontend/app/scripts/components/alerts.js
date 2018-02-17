'use strict';

define(['ChoPidoTurnos'], function(ChoPidoTurnos) {
  ChoPidoTurnos.component('alerts', {
    template:
      '<div class="alert-wrapper">' +
        '<div uib-alert dismiss-on-timeout="10" ' +
             'class="alert alert-dismissible" ' +
             'ng-class="\'alert-\' + (alert.type || \'warning\')" ' +
             'ng-repeat="alert in $ctrl.alerts">' +
             '{{ alert.message }}' +
             '<button type="button" class="close" aria-label="Close" ng-click="$ctrl.onAlertClosed({index: $index})">' +
               '<span aria-hidden="true">&times;</span>' +
             '</button>' +
      '</div>' +
    '</div>',
    bindings: {
      alerts: '<',
      onAlertClosed: '&'
    }
  });
});
