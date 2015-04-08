
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/resources/design/layout/top.jspf" %>


<div id="featured-wrapper">

  <div id="featured">

    <div class="left" style="width: 360px">

      <h2>Welcome <span>${pageContext.request.userPrincipal.name}</span> </h2>
      <br/>

      <p class="large text-center">



        <sec:authorize access="isAuthenticated()">

          Toto je viditelné pouze když je uživatel authenticated ...
          <br/>

        </sec:authorize>

        <br/>

        <sec:authorize access="hasRole('BASIC_USER')">

          Toto je viditelné pouze když je uživatel přihlášen a má BASIC_USER roli

          <a href="/user/edit_account">Edit account</a>

        </sec:authorize>

        <br/>

        <br/>

        <sec:authorize access="hasRole('ROLE_ADMIN')">

          Toto je viditelné pouze když je uživatel přihlášen a má ROLE_ADMIN roli
          <br/>

          <a href="/admin">Go to administration</a>

        </sec:authorize>


      </p>
    </div>


    <div class="clearer">&nbsp;</div>

  </div>
</div>



<%@include file="/resources/design/layout/bot.jspf" %>