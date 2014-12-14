<%@page session="false"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/resources/design/layout/top.jspf" %>




<div id="featured-wrapper">

    <div id="featured">

        <div class="left" style="width: 360px">

            <h2>Web app <span>from HELL</span> </h2>
            <br/>

            <p class="large text-center">
                dost drsnej spring
            ${message}

                <sec:authorize access="hasRole('BASIC_USER')">

                    Toto je viditelné pouze když je uživatel přihlášen a má BASIC_USER roli

                </sec:authorize>


            </p>
        </div>


        <div class="clearer">&nbsp;</div>

    </div>
</div>



<%@include file="/resources/design/layout/bot.jspf" %>