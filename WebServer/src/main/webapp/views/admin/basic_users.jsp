
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/resources/design/layout/top.jspf" %>


<div id="featured-wrapper">

  <div id="featured">

    <div class="left" style="width: 360px">

      <h2>Basic <span>users</span> </h2>
      <br/>

      <p class="large text-center">



      </p>
    </div>







    <div class="clearer">&nbsp;</div>

  </div>

  <table cellspacing="0">
    <thead>
    <tr>
      <th>ID</th>
      <th>Username</th>
      <th>Email</th>
      <th>Enabled</th>
      <th>Birth date</th>
    </tr>
    </thead>
    <tbody>


    <c:forEach var="user" items="${basicUsers}">
      <tr>
        <td>${user.id}</td>
        <td>${user.username}</td>
        <td>${user.email}</td>
        <td>${user.enabled}</td>
        <td>${user.birthDate}</td>
      </tr>
    </c:forEach>

    </tbody>
  </table>

</div>



<%@include file="/resources/design/layout/bot.jspf" %>