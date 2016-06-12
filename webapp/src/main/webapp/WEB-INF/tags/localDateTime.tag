<%@ tag description="Java 8 LocalDateTime parser" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ attribute name="date" required="true" type="java.time.LocalDateTime" %>
<%@ attribute name="pattern" required="true" %>
<%@ attribute name="var" required="true" type="java.lang.String" rtexprvalue="false" %>

<%@ variable alias="formattedDate" name-from-attribute="var" scope="AT_BEGIN" variable-class="java.lang.String" %>

<c:set var="formattedDate">
    <fmt:parseDate value="${ date }" pattern="yyyy-MM-dd'T'HH:mm:ss.SSS" type="date" var="parsedDate" />
    <fmt:formatDate value="${ parsedDate }" pattern="${ pattern }" type="date" />
</c:set>