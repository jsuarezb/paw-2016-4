<%@ taglib prefix="z" tagdir="/WEB-INF/tags" %>
<z:base title="Index">
  <h1>${ institution.name }</h1>
  <div class="institution-detail">
    <p class="street-address">${ institution.address.streetName } ${ institution.address.streetNumber }</p>
    <p class="locality">${ institution.address.city } - ${ institution.address.state }, ${ institution.address.country }</p>
  </div>
</z:base>