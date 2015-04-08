<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/resources/design/layout/top.jspf" %>


<div id="featured-wrapper">

    <div id="featured">

        <div class="left" style="width: 330px">

            <h2>Log<span> in</span></h2>
            <br/>

            <p class="large text-center">


                <c:if test="${not empty error}">
            <div class="error">${error}</div>
            </c:if>

            <c:if test="${not empty msg}">
                <div class="msg">${msg}</div>
            </c:if>

            <form name='loginForm' action="<c:url value='j_spring_security_check' />" method='POST'>

                <table>
                    <tr>
                        <td>User:</td>
                        <td><input type='text' name='username' value='' required autofocus=""></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type='password' name='password'required /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input name="submit" type="submit" value="Proceed" /></td>
                    </tr>
                </table>

                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}" />

            </form>


            </p>
        </div>


        <div class="clearer">&nbsp;</div>

    </div>
</div>


<%@include file="/resources/design/layout/bot.jspf" %>
