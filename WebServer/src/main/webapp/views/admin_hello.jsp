<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/resources/design/layout/top.jspf" %>

hello from admin_hello.jsp yo dawg
<h1>${message}</h1>

<br/>

<sec:authorize access="hasRole('BASIC_USER')">

    Toto je viditelné pouze když je uživatel přihlášen a má BASIC_USER roli

</sec:authorize>

<br/>
<br/>

<sec:authorize access="hasRole('ROLE_ADMIN')">

    Toto je viditelné pouze když je uživatel přihlášen a má ROLE_ADMIN roli

</sec:authorize>



<%@include file="/resources/design/layout/bot.jspf" %>