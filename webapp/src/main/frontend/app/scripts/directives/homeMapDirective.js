'use strict';
define(['ChoPidoTurnos'], function(ChoPidoTurnos) {

  ChoPidoTurnos.directive('homeMap', function () {
    // directive link function
    var link = function (scope, element, attrs) {
      var map, infoWindow;
      var markers = [];

      // map config
      var mapOptions = {
        center: new google.maps.LatLng(-34.6109874,-58.4641928),
        zoom: 12,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        scrollwheel: false
      };

      // init the map
      function initMap() {
        if (map === void 0) {
          map = new google.maps.Map(element[0], mapOptions);
        }
      }

      // place a marker
      function setMarker(map, position, title, content) {
        var marker;
        var markerOptions = {
          position: position,
          map: map,
          title: title,
          icon: 'https://maps.google.com/mapfiles/ms/icons/green-dot.png'
        };

        marker = new google.maps.Marker(markerOptions);
        markers.push(marker); // add marker to array

        google.maps.event.addListener(marker, 'click', function () {
          // close window if not undefined
          if (infoWindow !== void 0) {
            infoWindow.close();
          }
          // create new window
          var infoWindowOptions = {
            content: content
          };
          infoWindow = new google.maps.InfoWindow(infoWindowOptions);
          infoWindow.open(map, marker);
        });
      }

      function fillMarkers(institutions){
        for (var i = 0; i < institutions.length; i++) {
          setMarker(map, new google.maps.LatLng(institutions[i].lat, institutions[i].long), institutions[i].name, 'More content');
        }
      }

      // show the map and place some markers
      initMap();
      var institutions = {
        'institutions': [{
          'name': 'Clinica trinidad',
          'lat': '-34.5904283',
          'lon': '-58.4811873'
        }, {
          'name': 'Sanatorio sarasa',
          'lat': '-34.5904283',
          'lon': '-58.4811873'
        }, {
          'name': 'Sanatorio sarlanga',
          'lat': '34.5970747',
          'lon': '-58.4348102'
        }]
      };
      fillMarkers(institutions);
    };

    return {
      restrict: 'A',
      template: '<div id="gmaps"></div>',
      replace: true,
      link: link
    };
  });
});
