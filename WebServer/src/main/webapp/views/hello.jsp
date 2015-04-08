<%@page session="false"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/resources/design/layout/top.jspf" %>


<div id="featured-wrapper">

    <div id="featured">


        <p class="large text-center">
            dost drsnej spring
            <sec:authorize access="hasRole('BASIC_USER')">
                Toto je viditelné pouze když je uživatel přihlášen a má BASIC_USER roli
            </sec:authorize>
        </p>


        <div class="clearer">&nbsp;</div>

        Testy:

        <br/>
        <a href="/observer">Test observers</a>
        <br/>
        <a href="/email">Test email</a>
        <br/>
        <a href="/test2">Test /test2</a>
        <br/>
        <a href="/public/map">Test map</a>
        <br/>
        <a href="/public/map2">Test map 2</a>



    </div>
</div>


<%@include file="/resources/design/layout/bot.jspf" %>