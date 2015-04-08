<%@ page import="org.joda.time.Years" %>
<%@ page import="org.joda.time.LocalDate" %>
<%@ page import="mybatis.model.complex.AppUser" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@include file="/resources/design/layout/top.jspf" %>


<div id="featured-wrapper">

  <div id="featured">

    <div class="left" style="width: 330px">

      <h2>User <span>Profile</span></h2>
      <br/>

      <p class="large text-center">


        <c:if test="${profile != null}">




      <table cellspacing="0">

        <tr>
          <td>
            <div class="comment">



              <div class="comment-gravatar left">

                <c:if test="${empty profile.avatar}">
                  <img src="/resources/images/icon_anonymous.png" height="32" width="32"/>
                </c:if>

                <c:if test="${not empty profile.avatar}">
                  <img src="data:image/jpeg;base64,${profile.avatarBase64}" height="32" width="32"/>
                </c:if>

              </div>


            </div>


          </td>
          <td>
            <h5> ${profile.username}  </h5></td>
        </tr>
        <tr>
          <td>Age:</td>
          <td><%

            AppUser user = (AppUser) request.getAttribute("profile");

            Years years = Years.yearsBetween(new LocalDate(user.getBirthDate()), new LocalDate());

            out.println(years.getYears());

          %></td>
        </tr>
        <tr>
          <td>Email:</td>
          <td>${profile.email}</td>
        </tr>


      </table>




      </table>


      </c:if>


      </p>
    </div>


    <div class="clearer">&nbsp;</div>

  </div>
</div>

<%@include file="/resources/design/layout/bot.jspf" %>
