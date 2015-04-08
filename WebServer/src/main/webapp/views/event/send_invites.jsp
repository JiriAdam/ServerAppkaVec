<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@include file="/resources/design/layout/top.jspf" %>


<div id="featured-wrapper">

  <div id="featured">

    <div class="left" style="width: 330px">

      <h2>Invite <span>people</span></h2>
      <br/>

      <p class="large text-center">


        <form:form action="/user/invite_to_event" method="post" commandName="invitationsForm">

      <table border="0">
        <tr>
          <td>Write user's usernames to be invited</td>
          <td><form:input path="usernames" placeholder="malis,tomas,..." required="required"  /></td>
        </tr>


      <spring:bind path="eventId">
        <input name="eventId"  type="hidden" value="${event}" required>
      </spring:bind>

        <tr>
          <td></td>
          <td><input type="submit" value="Send invites!" /></td>
        </tr>



      </table>

      </form:form>


      </p>
    </div>


    <div class="clearer">&nbsp;</div>

  </div>
</div>


<%@include file="/resources/design/layout/bot.jspf" %>
