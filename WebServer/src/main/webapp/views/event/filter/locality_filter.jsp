<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@include file="/resources/design/layout/top.jspf" %>


<div id="featured-wrapper">

    <div id="featured">

        <div class="left" style="width: 100%;">

            <h2>Locality <span>Filter</span></h2>
            <br/>

            Here you can specify localities of events you are interested in


            <p class="large text-center">


                <form:form action="/user/locality_update" method="post" commandName="localityUpdate">

                 <form:hidden path="id" value="${country_id}" />

            <table border="0">

                <c:forEach var="regionFO" items="${regionCheckboxes}">

                    <tr>
                        <td>

                            <c:if test="${regionFO.checked}">
                                <spring:bind path="regions">
                                    <input id="reg ${regionFO.id}" name="regions" type="checkbox" value="${regionFO.id}" checked="true"/>
                                    <label for="reg ${regionFO.id}">${regionFO.name}</label>
                                </spring:bind>
                            </c:if>

                            <c:if test="${not regionFO.checked}">
                                <spring:bind path="regions">
                                    <input id="reg ${regionFO.id}" name="regions" type="checkbox" value="${regionFO.id}" />
                                    <label for="reg ${regionFO.id}">${regionFO.name}</label>
                                </spring:bind>
                            </c:if>


                        </td>
                    </tr>

                </c:forEach>


                <tr>
                    <td><input type="submit" value="Update Filter"/></td>
                </tr>


            </table>

            </form:form>


            </p>
        </div>


        <div class="clearer">&nbsp;</div>

    </div>
</div>


<%@include file="/resources/design/layout/bot.jspf" %>