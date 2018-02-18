'use strict';
define(['ChoPidoTurnos','services/httpRequestBuilderService'], function(ChoPidoTurnos) {
  ChoPidoTurnos.service('doctorsService', [
    'httpRequestBuilderService',
    function (httpRequestBuilderService) {
      return {
        getDoctors: function(firstName, lastName) {
         return httpRequestBuilderService
            .buildHttpRequest('GET', 'doctors?first_name=' + firstName + '&last_name=' + lastName, null);
        },
        getInstitutionSpecialityDoctors: function(institutionId,specialityId) {
          return httpRequestBuilderService
            .buildHttpRequest('GET', 'doctors?speciality_id=' + specialityId+ '&institution_id='+institutionId, null);
        },
        getDoctor: function(doctorId) {
          return httpRequestBuilderService.buildHttpRequest('GET', 'doctors/' + doctorId, null);
        },
        findAvailable: function(speciality, neighborhood, institution, weekOfYear, year, page) {
          return httpRequestBuilderService
            .buildHttpRequest('GET', 'doctors/available?speciality=' + (speciality || '') +
              '&neighborhood=' + (neighborhood || '') +
              '&institution=' + (institution || '') +
              '&weekOfYear=' + (weekOfYear || '') +
              '&year=' + (year || '') +
              '&page=' + (page || ''), null);
        },
        getUserRating: function (doctorId) {
          return httpRequestBuilderService.buildHttpRequest('GET', 'doctors/' + doctorId + '/ratings', null);
        },
        getRatingSummary: function (doctorId) {
          return httpRequestBuilderService.buildHttpRequest('GET', 'doctors/' + doctorId + '/rating_summary', null);
        },
        rate: function (doctorId, rating) {
          var params = {value: rating};
          return httpRequestBuilderService.buildHttpRequest('PUT', 'doctors/' + doctorId + '/ratings', params);
        }
    };
  }]);
});
