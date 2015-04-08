<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/resources/design/layout/top.jspf" %>

<h2>Administration <span>page</span> </h2>

<br/>

<sec:authorize access="hasRole('BASIC_USER')">

    Toto je viditelné pouze když je uživatel přihlášen a má BASIC_USER roli

</sec:authorize>

<br/>
<br/>

<sec:authorize access="hasRole('ROLE_ADMIN')">

    Toto je viditelné pouze když je uživatel přihlášen a má ROLE_ADMIN roli

</sec:authorize>

<br/>
<br/>

<a href="/admin/basic_users">See all basic users</a>



<%@include file="/resources/design/layout/bot.jspf" %>