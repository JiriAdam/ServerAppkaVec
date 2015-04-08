<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@include file="/resources/design/layout/top.jspf" %>


<div id="featured-wrapper">


    <div id="featured">

        <div class="left" style="width: 90%">

            <h2>Public <span>events</span></h2>
            <br/>


        </div>
        <div class="right" style="width: 10%">


            <a href="javascript:GoListView()"><img src='/resources/images/list_button.png'
                                                   onmouseover="this.src='/resources/images/list_button_hover.png';"
                                                   onmouseout="this.src='/resources/images/list_button.png';"/></a>

            <a href="javascript:GoGridView()"><img src='/resources/images/grid_button.png'
                                                   onmouseover="this.src='/resources/images/grid_button_hover.png';"
                                                   onmouseout="this.src='/resources/images/grid_button.png';"/></a>


        </div>

        <div id="image_view" style="display:block;">

            <section>
                <ul id="da-thumbs" class="da-thumbs">

                    <c:forEach var="publicEvent" items="${publicEvents}">

                        <li>
                            <a href="/public/event/${publicEvent.id}">
                                <img src="https://maps.googleapis.com/maps/api/staticmap?center=${publicEvent.address.addressForGMapApi}&markers=${publicEvent.address.addressForGMapApi}&zoom=12&size=220x165&key=AIzaSyDz3QGCq7BTAmkJNKKEKmOrfw5NR1O4USo"/>

                                <div>
              <span>
               Host  <strong> ${publicEvent.name} </strong>

               <strong style="color: darkorange;font-weight: normal;">${publicEvent.shortDescription} </strong>

                  <c:if test="${publicEvent.shortDescription.length() < 60 }"><br/></c:if>
                at ${publicEvent.address.city}
            </span>
                                </div>
                            </a>
                        </li>

                    </c:forEach>

                </ul>
            </section>
        </div>

        <div id="list_view" style="display:none;">


            <table cellspacing="0">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>City Test</th>
                    <th>Time</th>
                    <th>Capacity</th>
                    <th>Description</th>
                    <th>Requires confirm</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>


                <c:forEach var="publicEvent" items="${publicEvents}">
                    <tr>
                        <td>${publicEvent.name}</td>
                        <td>${publicEvent.address.city}</td>
                        <td>${publicEvent.time}</td>
                        <td>${publicEvent.capacity}</td>
                        <td>${publicEvent.description}</td>
                        <td>${publicEvent.requireConfirm}</td>
                        <td><a href="/public/event/${publicEvent.id}">Details</a></td>
                        <td><a href="/user/attend_public_event/${publicEvent.id}">Attend</a></td>
                    </tr>
                </c:forEach>


                </tbody>
            </table>


        </div>

        <div class="clearer">&nbsp;</div>




    </div>


    <!-- TODO strankovac -->

    <div style="text-align: center;">


        <ul class="pagination modal-2">
            <li><a href="/public/events/page/1" class="firstPage">&laquo;&laquo;</a></li>

            <li><a href="/public/events/page/${previousPage}" class="prev">&laquo;</a></li>



            <c:forEach var="page" items="${listedPages}">
                <c:if test="${page == currentPage}">
                    <li><a href="/public/events/page/${page}" class="active">${currentPage}</a></li>
                </c:if>

                <c:if test="${page != currentPage}">
                    <li><a href="/public/events/page/${page}">${page}</a></li>
                </c:if>


            </c:forEach>




            <li><a href="/public/events/page/${nextPage}" class="next">&raquo;</a></li>
            <li><a href="/public/events/page/${totalPages}" class="lastPage">&raquo;&raquo;</a></li>
        </ul>

    </div>

</div>


<%@include file="/resources/design/layout/bot.jspf" %>