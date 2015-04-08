
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/resources/design/layout/top.jspf" %>


<div id="featured-wrapper">

  <div id="featured">

    <div class="left" style="width: 660px">

      <h2>I am <span>going</span> here</h2>
      <br/>

      <p class="large text-center">

        <br/>


      </p>
    </div>

    <table cellspacing="0">
      <thead>
      <tr>
        <th>id</th>
        <th>When</th>
        <th>What</th>
        <th></th>
      </tr>
      </thead>
      <tbody>


      <c:forEach var="attendingEvent" items="${attendingEvents}">
        <tr>
          <td>${attendingEvent.id}</td>
          <td>${attendingEvent.time}</td>
          <td>${attendingEvent.idEvent}</td>

          <td><a href="/invited/event/${attendingEvent.idEvent}">Details</a></td>
        </tr>
      </c:forEach>





      </tbody>
    </table>


    <div class="clearer">&nbsp;</div>

  </div>
</div>



<%@include file="/resources/design/layout/bot.jspf" %>