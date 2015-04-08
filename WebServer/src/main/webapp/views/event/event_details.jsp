<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@include file="/resources/design/layout/top.jspf" %>


<div id="featured-wrapper">

    <div id="featured">

        <div class="left" style="width: 330px">

            <h2>Event <span>details</span></h2>
            <br/>


            <p class="large text-center">


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
                    <td>${numberOfReservations} / ${event.capacity}</td>
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
                <td>Event types</td>
                <td>
                    <c:forEach var="type" items="${event.eventTypes}">
                        ${type.typeName}&nbsp;
                    </c:forEach>
                </td>
            </tr>
                <tr>
                    <td>Who is hosting?</td>
                    <td><a href="/profile/${event.owner.id}">${event.owner.username}</a></td>
                </tr>
                <tr>
                    <td></td>
                    <td><a href="/user/attend_public_event/${event.id}">Attend</a></td>
                </tr>
            </table>


            </p>
        </div>

        <div class="right" style="width: 330px">

            <table cellspacing="0">
                <tr>
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <iframe
                                width="400"
                                height="300"
                                frameborder="0" style="border:0"
                                src="https://www.google.com/maps/embed/v1/place?key=AIzaSyDz3QGCq7BTAmkJNKKEKmOrfw5NR1O4USo&q=${event.address.addressForGMapApi}">
                        </iframe>
                    </td>
                </tr>
            </table>

        </div>


        <div class="clearer">&nbsp;</div>

        <h3>Comments</h3>

        <div class="comment-list">

            <%! int pom = 1; %>

            <c:forEach var="comment" items="${event.comments}">


                <div class="comment">


                    <c:if test="${not empty comment.user.id}">
                        <div class="comment-gravatar left">

                            <c:if test="${empty comment.user.avatar}">
                                <img src="/resources/images/icon_anonymous.png" height="32" width="32"/>
                            </c:if>

                            <c:if test="${not empty comment.user.avatar}">
                                <img src="data:image/jpeg;base64,${comment.user.avatarBase64}" height="32" width="32"/>
                            </c:if>

                        </div>
                        By <a href="/profile/${comment.user.id}">${comment.user.username}</a>
                    </c:if>

                    <c:if test="${empty comment.user.id}">
                        <div class="comment-gravatar left">
                            <img src="/resources/images/icon_anonymous.png" height="32" width="32"/>
                        </div>

                        By anonymous
                    </c:if>

                    <div class="comment-date"><a href="#"> ${comment.time.toLocaleString()}</a></div>

                    <div class="clearer">&nbsp;</div>

                    <div class="comment-body">
                        <p>${comment.text} </p>
                    </div>

                </div>

            </c:forEach>

        </div>
        <br/>

        <form:form action="/public/event/add_comment/${event.id}" method="post" commandName="newCommentForm">

            <div class="legend"><h3>Leave a Reply</h3></div>


            <div class="form-row">

                <div class="form-property form-required">Your message:</div>
                <div class="form-value">
                    <form:textarea path="text" rows="10" cols="46"/>
                </div>

                <div class="clearer">&nbsp;</div>

            </div>

            <div class="form-row form-row-submit">

                <sec:authorize access="isAnonymous()">

                    Your comment will be added as anonymous. &nbsp;

                </sec:authorize>

                <div class="form-value"><input type="submit" value="Submit Comment &#187;"/>

                </div>

                <div class="clearer">&nbsp;</div>

            </div>


        </form:form>


    </div>

    </c:if>

</div>
</div>


<%@include file="/resources/design/layout/bot.jspf" %>