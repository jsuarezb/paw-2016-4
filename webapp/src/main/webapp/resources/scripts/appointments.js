successAlert = $('<div class="alert alert-success" role="alert">' +
            'Turno eliminado ' +
            '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
              '<span aria-hidden="true">&times;</span>' +
            '</button>' +
          '</div>');

errorAlert = $('<div class="alert alert-danger" role="alert">' +
             'Error al eliminar el turno ' +
             '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
               '<span aria-hidden="true">&times;</span>' +
             '</button>' +
         '</div>');

$(document).ready(function () {
  $(".appointment-delete").click(function () {
      $.ajax({url: $(this).data("url"),
              method: "DELETE"})
       .done(function () {
         $("body > .container-main").prepend(successAlert);
       })
       .fail(function () {
         $("body > .container-main").prepend(errorAlert);
       });
  })
});