
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/resources/design/layout/top.jspf" %>


<div id="featured-wrapper">

  <div id="featured">

    <div class="left" style="width: 360px">

      <h2>Your <span>events</span> </h2>
      <br/>

      <p class="large text-center">

        <br/>

        <a href="/user/create_event">Create new event</a>
        <br/>
        <a href="/user/my_pending_invitations">Show my received pending invitations</a>

        <br/>



      </p>
    </div>

    <table cellspacing="0">
      <thead>
      <tr>
        <th>Name</th>
        <th>Time</th>
        <th>Capacity</th>
        <th>Description</th>
        <th>Public</th>
        <th>Require confirm</th>
        <th></th>
      </tr>
      </thead>
      <tbody>


      <c:forEach var="ownerEvent" items="${ownersEvents}">
        <tr>
          <td>${ownerEvent.name}</td>
          <td>${ownerEvent.time}</td>
          <td>${ownerEvent.capacity}</td>
          <td>${ownerEvent.description}</td>
          <td>${ownerEvent.isPublic}</td>
          <td>${ownerEvent.requireConfirm}</td>
          <td><a href="/user/my_event/${ownerEvent.id}">Details</a></td>
        </tr>
      </c:forEach>





      </tbody>
    </table>


    <div class="clearer">&nbsp;</div>

  </div>
</div>



<%@include file="/resources/design/layout/bot.jspf" %>