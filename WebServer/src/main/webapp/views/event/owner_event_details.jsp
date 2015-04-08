<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@include file="/resources/design/layout/top.jspf" %>


<div id="featured-wrapper">

    <div id="featured">

        <div class="left" style="width: 330px">

            <h2>My <span>Event</span></h2>
            <br/>


            <c:if test="${event != null}">


                <table cellspacing="0">

                    <tr>
                        <td>What?</td>
                        <td>${event.name}</td>
                    </tr>
                    <tr>
                        <td>Where?</td>
                        <td>${event.address.streetName}, ${event.address.city}</td>
                    </tr>
                    <tr>
                        <td>For how many?</td>
                        <td>${event.capacity}</td>
                    </tr>
                    <tr>
                        <td>When?</td>
                        <td>${event.time}</td>
                    </tr>
                    <tr>
                        <td>What's this about?</td>
                        <td>${event.description}</td>
                    </tr>
                    <tr>
                        <td>Who is hosting?</td>
                        <td>me</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><a href="/user/invite_to_event/${event.id}">Send invites!</a></td>
                    </tr>

                </table>


                <h4>Confirmed <span>users</span></h4>
                <c:if test="${reservations != null}">
                    <table cellspacing="0">
                        <thead>
                        <tr>
                            <th>User</th>
                            <th>Time</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="reservation" items="${reservations}">
                            <tr>
                                <td><a href="/profile/${reservation.userId}">${reservation.username}</a></td>
                                <td>${reservation.time}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>


                <h4>User's waiting <span>for confirmation </span></h4>
                <c:if test="${confirmsPending != null}">
                    <table cellspacing="0">
                        <thead>
                        <tr>
                            <th>User</th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="reservation" items="${confirmsPending}">
                            <tr>
                                <td><a href="/profile/${reservation.userId}">${reservation.username}</a></td>
                                <td><a href="/user/my_event/confirm_request/${event.id}/${reservation.id}">Confirm</a>
                                </td>
                                <td><a href="/user/my_event/decline_request/${event.id}/${reservation.id}">Decline</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>

                <h4>Invited <span>users</span></h4>
                <c:if test="${invitationsPending != null}">
                    <table cellspacing="0">
                        <thead>
                        <tr>
                            <th>User</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="reservation" items="${invitationsPending}">
                            <tr>
                                <td><a href="/profile/${reservation.userId}">${reservation.username}</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>

            </c:if>


        </div>

        <div class="right" style="width: 330px">


            <iframe
                    width="400"
                    height="300"
                    frameborder="0" style="border:0"
                    src="https://www.google.com/maps/embed/v1/place?key=AIzaSyDz3QGCq7BTAmkJNKKEKmOrfw5NR1O4USo&q=${event.address.addressForGMapApi}">
            </iframe>


        </div>

        <div class="clearer">&nbsp;</div>

    </div>
</div>


<%@include file="/resources/design/layout/bot.jspf" %>