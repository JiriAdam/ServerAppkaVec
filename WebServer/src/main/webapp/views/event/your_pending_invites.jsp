
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/resources/design/layout/top.jspf" %>


<div id="featured-wrapper">

  <div id="featured">

    <div class="left" style="width: 650px">

      <h2>Your pending <span>invitations</span> </h2>
      <br/>

      <p class="large text-center">



      </p>
    </div>

    <table cellspacing="0" >
      <thead>
      <tr>
        <th>Time</th>
        <th>Who</th>
        <th>What</th>
        <th></th>
        <th></th>
      </tr>
      </thead>
      <tbody>


      <c:forEach var="pendingInvite" items="${pendingInvites}">
        <tr>
          <td>${pendingInvite.time}</td>
          <td>${pendingInvite.idUser}</td>
          <td>${pendingInvite.idEvent}</td>
          <td><a href="/user/confirm_invite/${pendingInvite.id}">Confirm</a></td>
          <td><a href="/user/decline_invite/${pendingInvite.id}">Decline</a></td>
        </tr>
      </c:forEach>

      </tbody>
    </table>


    <div class="clearer">&nbsp;</div>

  </div>
</div>



<%@include file="/resources/design/layout/bot.jspf" %>