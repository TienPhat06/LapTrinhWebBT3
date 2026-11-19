<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/includes/header.html" %>

<h1>Thanks for taking our survey!</h1>
<p>Here is the information that you entered:</p>

<label>Email:</label>
<span>${survey.user.email}</span><br>

<label>First Name:</label>
<span>${survey.user.firstName}</span><br>

<label>Last Name:</label>
<span>${survey.user.lastName}</span><br>

<label>Heard From:</label>
<span>${survey.heardFrom}</span><br>

<label>Updates:</label>
<span>${survey.wantUpdates}</span><br>

<c:if test="${survey.wantUpdates == 'Yes'}">
    <label>Contact Via:</label>
    <span>${survey.contactVia}</span><br>
</c:if>

<%@ include file="/includes/footer.jsp" %>