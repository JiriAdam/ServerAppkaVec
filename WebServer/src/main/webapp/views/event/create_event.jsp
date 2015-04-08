<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@include file="/resources/design/layout/top_with_gmaps.jspf" %>


<div id="featured-wrapper">

    <div id="featured">

        <h2>New <span>Event</span></h2>
        <br/>

        <form:form action="/user/create_event" method="post" commandName="newEventForm">


            <div class="left" style="width: 70%">


                <p class="large text-center">


                <table border="0">
                    <tr>
                        <td style="width: 200px;">Event name:</td>
                        <td><form:input path="name" placeholder="Party at Mike's" required="required"/></td>
                    </tr>

                    <tr>
                        <td>Capacity:</td>
                        <td><form:input path="capacity" placeholder="0 for no capacity" required="required"/></td>
                    </tr>


                    <tr>
                        <td>Public event:</td>
                        <td>
                            <form:radiobutton path="isPublic" id="isPublic 1" value="true" checked="checked"/> <label
                                for="isPublic 1">Yes</label>
                            <form:radiobutton path="isPublic" id="isPublic 2" value="false"/><label
                                for="isPublic 2">No</label>

                        </td>
                    </tr>

                    <tr>
                        <td>Require your confirm for people signing up?</td>
                        <td>
                            <form:radiobutton path="requireConfirm" id="requireConfirm 1" value="true"/> <label
                                for="requireConfirm 1">Yes</label>
                            <form:radiobutton path="requireConfirm" id="requireConfirm 2" value="false"
                                              checked="checked"/><label for="requireConfirm 2">No</label>

                        </td>
                    </tr>


                    <tr>
                        <td>Time:</td>
                        <td>
                            <form:input type="text" path="time" cssClass="jquery_datetimepicker"/>
                        </td>
                    </tr>


                    <tr>
                        <td>Event description:</td>
                        <td><form:input path="description" placeholder="Mike's celebration of the world for no reason"
                                        required="required"/></td>
                    </tr>


                </table>

                <br/>

                <table id="address">


                    <tr>


                        <td style="width: 200px;  text-align: right"></td>
                        <td>

                            <div id="locationField">
                                <input id="autocomplete" placeholder="Enter your address"
                                       onFocus="geolocate()" type="text"/>
                            </div>

                        </td>
                    </tr>

                    <tr>

                        <td style="width: 200px">Street</td>
                        <td><form:input class="field" path="streetName" id="route" disabled="true"/></td>

                        <td><form:input class="field" path="route" id="street_number" disabled="true"
                                        style="width: 35px;"/></td>
                    </tr>

                    <tr>
                        <td style="width: 200px">City</td>
                        <td><form:input class="field" path="city" id="locality" disabled="true"/></td>
                    </tr>

                    <tr>
                        <td style="width: 200px">Region</td>
                        <td><form:input class="field" path="region" id="administrative_area_level_1"
                                        disabled="true"/></td>
                    </tr>
                    <tr>
                        <td style="width: 200px">Postal code</td>
                        <td>
                            <form:input class="field" path="postalCode" id="postal_code" disabled="true"/></td>
                    </tr>
                    <tr>
                        <td style="width: 200px">Country</td>
                        <td><form:input class="field" id="country" path="country" disabled="true"/></td>
                    </tr>


                    <form:hidden path="latitude" class="field"  id="lat" name="lat" />

                    <form:hidden path="longitude" class="field"  id="lng" name="lng" />

                    <tr>
                        <td></td>
                        <td><input type="submit" value="Create"/></td>
                    </tr>


                </table>


                </p>

            </div>


            <div class="right" style="width: 30%">


                <table border="0">
                    <tr>
                        <td>Describe your event:</td>
                    </tr>



                    <tr>
                        <td>
                            <spring:bind path="eventTypes">
                                <input id="ev1" name="eventTypes" type="checkbox" value="casual" checked="checked"/>
                                <label for="ev1">Casual</label>
                            </spring:bind>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <spring:bind path="eventTypes">
                                <input id="ev2" name="eventTypes" type="checkbox" value="cultural"  />
                                <label for="ev2">Cultural</label>
                            </spring:bind>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <spring:bind path="eventTypes">
                                <input id="ev3" name="eventTypes" type="checkbox" value="birthday"  />
                                <label for="ev3">Birthday</label>
                            </spring:bind>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <spring:bind path="eventTypes">
                                <input id="ev4" name="eventTypes" type="checkbox" value="social"  />
                                <label for="ev4">Social</label>
                            </spring:bind>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <spring:bind path="eventTypes">
                                <input id="ev5" name="eventTypes" type="checkbox" value="festiveHoliday"  />
                                <label for="ev5">Festive</label>
                            </spring:bind>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <spring:bind path="eventTypes">
                                <input id="ev6" name="eventTypes" type="checkbox" value="liveMusic"  />
                                <label for="ev6">Live Music</label>
                            </spring:bind>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <spring:bind path="eventTypes">
                                <input id="ev7" name="eventTypes" type="checkbox" value="forWomen"  />
                                <label for="ev7">For women</label>
                            </spring:bind>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <spring:bind path="eventTypes">
                                <input id="ev8" name="eventTypes" type="checkbox" value="forMen"  />
                                <label for="ev8">For men</label>
                            </spring:bind>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <spring:bind path="eventTypes">
                                <input id="ev9" name="eventTypes" type="checkbox" value="forChildren" />
                                <label for="ev9">For kids</label>
                            </spring:bind>

                        </td>
                    </tr>

                </table>


            </div>


        </form:form>

        <div class="clearer">&nbsp;</div>

    </div>
</div>


<%@include file="/resources/design/layout/bot.jspf" %>
