<%@page session="false"%>

<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/resources/design/layout/top.jspf" %>


<div id="featured-wrapper">

  <div id="featured">

    <div class="left" style="width: 360px">

      <h2>Error code:  <span>403</span> </h2>
      <br/>

      <p class="large text-center">
      You do not have permission to access this page

      </p>
    </div>


    <div class="clearer">&nbsp;</div>

  </div>
</div>


<%@include file="/resources/design/layout/bot.jspf" %>