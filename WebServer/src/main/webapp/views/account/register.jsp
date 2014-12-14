<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@include file="/resources/design/layout/top.jspf" %>


<div id="featured-wrapper">

  <div id="featured">

    <div class="left" style="width: 330px">

      <h2>Registration<span></span></h2>
      <br/>

      <p class="large text-center">


        <c:if test="${not empty error}">
      <div class="error">${error}</div>
      </c:if>

      <c:if test="${not empty msg}">
        <div class="msg">${msg}</div>
      </c:if>

      <form:form action="register" method="post" commandName="userRegForm">

        <table border="0">
          <tr>
            <td>User Name:</td>
            <td><form:input path="username" placeholder="your login username" required="required"  /></td>
          </tr>


          <tr>
            <td>Password:</td>
            <td>
              <spring:bind path="password1"><input id="password" name="password1" type="password" pattern="^\S{5,}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Minimal length is 5 characters' : '');
                            if (this.checkValidity())
                                form.password2.pattern = this.value;" placeholder="your password" required>
              </spring:bind>
            </td>
          </tr>


            <tr>
              <td>Retype password:</td>
              <td>
                <spring:bind path="password2">
                  <input id="password_two" name="password2"  type="password" pattern="^\S{5,}$"
                         onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must match with field above' : '');"
                         placeholder="retype your password" required>
                </spring:bind>
              </td>
            </tr>



          <tr>
            <td>E-mail:</td>
            <td><form:input path="email" placeholder="someone@gmail.com" type="email" required="required" /></td>
          </tr>
          <tr>
            <td>Birthday (dd/mm/yyyy):</td>
            <td><form:input path="birthDate" placeholder="26/01/1989"  required="required"  /></td>
          </tr>

          <tr>
            <td></td>
            <td><input type="submit" value="Register" /></td>
          </tr>

        </table>

      </form:form>


      </p>
    </div>


    <div class="clearer">&nbsp;</div>

  </div>
</div>


<%@include file="/resources/design/layout/bot.jspf" %>
