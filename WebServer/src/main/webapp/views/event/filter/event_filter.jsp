<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@include file="/resources/design/layout/top.jspf" %>


<div id="featured-wrapper">

    <div id="featured">
        <h2>Basic <span>Filter</span></h2>
        <br/>

        <div class="left" style="width: 65%;">


            <p class="large">
                Here you can specify what types of events you wish to be alerted to.
            </p>

            </br>


            <form:form action="/user/filter" method="post" commandName="eventFilterForm">

                <table border="0">


                    <tr>
                        <td>Periodic events</td>
                        <td>


                            <c:choose>
                                <c:when test="${eventFilterForm.isPeriodic == true}">
                                    <form:radiobutton path="isPeriodic" id="isPeriodic 1" value="true"
                                                      checked="checked"/> <label for="isPeriodic 1">Yes</label>
                                    <form:radiobutton path="isPeriodic" id="isPeriodic 2" value="false"/><label
                                        for="isPeriodic 2">No</label>
                                </c:when>
                                <c:otherwise>
                                    <form:radiobutton path="isPeriodic" id="isPeriodic 1" value="true"/> <label
                                        for="isPeriodic 1">Yes</label>
                                    <form:radiobutton path="isPeriodic" id="isPeriodic 2" value="false"
                                                      checked="checked"/><label for="isPeriodic 2">No</label>
                                </c:otherwise>
                            </c:choose>


                        </td>
                    </tr>

                    <tr>
                        <td>Casual events</td>
                        <td>

                            <c:choose>
                                <c:when test="${eventFilterForm.casual == true}">
                                    <form:radiobutton path="casual" id="casual 1" value="true" checked="checked"/>
                                    <label for="casual 1">Yes</label>
                                    <form:radiobutton path="casual" id="casual 2" value="false"/><label for="casual 2">No</label>
                                </c:when>
                                <c:otherwise>
                                    <form:radiobutton path="casual" id="casual 1" value="true"/> <label for="casual 1">Yes</label>
                                    <form:radiobutton path="casual" id="casual 2" value="false"
                                                      checked="checked"/><label for="casual 2">No</label>
                                </c:otherwise>
                            </c:choose>

                        </td>
                    </tr>

                    <tr>
                        <td>Birthdays</td>
                        <td>

                            <c:choose>
                                <c:when test="${eventFilterForm.birthday == true}">
                                    <form:radiobutton path="birthday" id="birthday 1" value="true" checked="checked"/>
                                    <label for="birthday 1">Yes</label>
                                    <form:radiobutton path="birthday" id="birthday 2" value="false"/><label
                                        for="birthday 2">No</label>
                                </c:when>
                                <c:otherwise>
                                    <form:radiobutton path="birthday" id="birthday 1" value="true"/> <label
                                        for="birthday 1">Yes</label>
                                    <form:radiobutton path="birthday" id="birthday 2" value="false"
                                                      checked="checked"/><label for="birthday 2">No</label>
                                </c:otherwise>
                            </c:choose>


                        </td>
                    </tr>

                    <tr>
                        <td>Cultural</td>
                        <td>
                            <c:choose>
                                <c:when test="${eventFilterForm.cultural == true}">
                                    <form:radiobutton path="cultural" id="cultural 1" value="true" checked="checked"/>
                                    <label for="cultural 1">Yes</label>
                                    <form:radiobutton path="cultural" id="cultural 2" value="false"/><label
                                        for="cultural 2">No</label>
                                </c:when>
                                <c:otherwise>
                                    <form:radiobutton path="cultural" id="cultural 1" value="true"/> <label
                                        for="cultural 1">Yes</label>
                                    <form:radiobutton path="cultural" id="cultural 2" value="false"
                                                      checked="checked"/><label for="cultural 2">No</label>
                                </c:otherwise>
                            </c:choose>


                        </td>
                    </tr>

                    <tr>
                        <td>Social event</td>
                        <td>
                            <c:choose>
                                <c:when test="${eventFilterForm.social == true}">
                                    <form:radiobutton path="social" id="social 1" value="true" checked="checked"/>
                                    <label for="social 1">Yes</label>
                                    <form:radiobutton path="social" id="social 2" value="false"/><label for="social 2">No</label>
                                </c:when>
                                <c:otherwise>
                                    <form:radiobutton path="social" id="social 1" value="true"/> <label for="social 1">Yes</label>
                                    <form:radiobutton path="social" id="social 2" value="false"
                                                      checked="checked"/><label for="social 2">No</label>
                                </c:otherwise>
                            </c:choose>


                        </td>
                    </tr>

                    <tr>
                        <td>Festive holiday</td>
                        <td>
                            <c:choose>
                                <c:when test="${eventFilterForm.festiveHoliday == true}">
                                    <form:radiobutton path="festiveHoliday" id="festiveHoliday 1" value="true"
                                                      checked="checked"/> <label for="festiveHoliday 1">Yes</label>
                                    <form:radiobutton path="festiveHoliday" id="festiveHoliday 2" value="false"/><label
                                        for="festiveHoliday 2">No</label>
                                </c:when>
                                <c:otherwise>
                                    <form:radiobutton path="festiveHoliday" id="festiveHoliday 1" value="true"/> <label
                                        for="festiveHoliday 1">Yes</label>
                                    <form:radiobutton path="festiveHoliday" id="festiveHoliday 2" value="false"
                                                      checked="checked"/><label for="festiveHoliday 2">No</label>
                                </c:otherwise>
                            </c:choose>


                        </td>
                    </tr>

                    <tr>
                        <td>Live music</td>
                        <td>
                            <c:choose>
                                <c:when test="${eventFilterForm.liveMusic == true}">
                                    <form:radiobutton path="liveMusic" id="liveMusic 1" value="true" checked="checked"/>
                                    <label for="liveMusic 1">Yes</label>
                                    <form:radiobutton path="liveMusic" id="liveMusic 2" value="false"/><label
                                        for="liveMusic 2">No</label>
                                </c:when>
                                <c:otherwise>
                                    <form:radiobutton path="liveMusic" id="liveMusic 1" value="true"/> <label
                                        for="liveMusic 1">Yes</label>
                                    <form:radiobutton path="liveMusic" id="liveMusic 2" value="false"
                                                      checked="checked"/><label for="liveMusic 2">No</label>
                                </c:otherwise>
                            </c:choose>


                        </td>
                    </tr>

                    <tr>
                        <td>For women</td>
                        <td>
                            <c:choose>
                                <c:when test="${eventFilterForm.forWomen == true}">
                                    <form:radiobutton path="forWomen" id="forWomen 1" value="true" checked="checked"/>
                                    <label for="forWomen 1">Yes</label>
                                    <form:radiobutton path="forWomen" id="forWomen 2" value="false"/><label
                                        for="forWomen 2">No</label>
                                </c:when>
                                <c:otherwise>
                                    <form:radiobutton path="forWomen" id="forWomen 1" value="true"/> <label
                                        for="forWomen 1">Yes</label>
                                    <form:radiobutton path="forWomen" id="forWomen 2" value="false"
                                                      checked="checked"/><label for="forWomen 2">No</label>
                                </c:otherwise>
                            </c:choose>


                        </td>
                    </tr>

                    <tr>
                        <td>For men</td>
                        <td>
                            <c:choose>
                                <c:when test="${eventFilterForm.forMen == true}">
                                    <form:radiobutton path="forMen" id="forMen 1" value="true" checked="checked"/>
                                    <label for="forMen 1">Yes</label>
                                    <form:radiobutton path="forMen" id="forMen 2" value="false"/><label for="forMen 2">No</label>
                                </c:when>
                                <c:otherwise>
                                    <form:radiobutton path="forMen" id="forMen 1" value="true"/> <label for="forMen 1">Yes</label>
                                    <form:radiobutton path="forMen" id="forMen 2" value="false"
                                                      checked="checked"/><label for="forMen 2">No</label>
                                </c:otherwise>
                            </c:choose>


                        </td>
                    </tr>

                    <tr>
                        <td>For children</td>
                        <td>
                            <c:choose>
                                <c:when test="${eventFilterForm.birthday == true}">
                                    <form:radiobutton path="forChildren" id="forChildren 1" value="true"
                                                      checked="checked"/> <label for="forChildren 1">Yes</label>
                                    <form:radiobutton path="forChildren" id="forChildren 2" value="false"/><label
                                        for="forChildren 2">No</label>
                                </c:when>
                                <c:otherwise>
                                    <form:radiobutton path="forChildren" id="forChildren 1" value="true"/> <label
                                        for="forChildren 1">Yes</label>
                                    <form:radiobutton path="forChildren" id="forChildren 2" value="false"
                                                      checked="checked"/><label for="forChildren 2">No</label>
                                </c:otherwise>
                            </c:choose>


                        </td>
                    </tr>


                    <tr>
                        <td></td>
                        <td><input type="submit" value="Update Filter"/></td>
                    </tr>


                </table>

            </form:form>


        </div>


        <div class="right" style="width: 35%;">


            <p class="large">
                Select a country to filter its regions.
            </p>

            </br>

            <form:form action="/user/locality_filter" method="post" commandName="localityForm">

                <table border="0">

                    <tr>
                        <td></td>
                        <td>
                    </tr>

                    <tr>
                        <td></td>
                        <td>


                            <form:select path="id">
                                <form:options items="${countryList}" itemValue="id" itemLabel="name"/>
                            </form:select>


                        </td>
                    </tr>


                    <tr>
                        <td></td>
                        <td><input type="submit" value="Continue"/></td>
                    </tr>


                </table>

            </form:form>

            Subscribed regions:

            <table  id="thetable"  border="0">
                <thead>
                <tr>
                    <th>Country</th>
                    <th>Region</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="countryWithRegions" items="${actualLocalityFilter}">


                    <c:forEach var="region" items="${countryWithRegions.regions}">
                        <tr>
                            <td>${countryWithRegions.country.name}</td>
                            <td>${region.name}</td>
                        </tr>

                    </c:forEach>


                </c:forEach>

                </tbody>
            </table>
        </div>


        <div class="clearer">&nbsp;</div>

    </div>
</div>




<%@include file="/resources/design/layout/bot.jspf" %>