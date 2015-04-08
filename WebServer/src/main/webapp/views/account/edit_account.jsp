<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@include file="/resources/design/layout/top.jspf" %>


<div id="featured-wrapper">

    <div id="featured">

        <div class="left" style="width: 60%">

            <h2>Edit <span>account</span></h2>

            <br/>
            <table border="0">

                <form:form method="post" action="/user/edit_account" commandName="accountForm">


                    <tr>
                        <td>Birthday:</td>
                        <td><form:input path="birthDate" placeholder="26/05/1988" required="required" value="${accountForm.birthDate}" /></td>
                    </tr>


                    <tr>
                        <td>E-mail:</td>
                        <td><form:input path="email" placeholder="someone@gmail.com" type="email" required="required"/></td>
                    </tr>

                    <tr>
                        <td>Notify via email:</td>
                        <td>


                                <form:radiobutton path="notifyViaEmail" id="notifyViaEmail 1" value="true"/>
                                <label for="notifyViaEmail 1">Yes</label>
                                <form:radiobutton path="notifyViaEmail" id="notifyViaEmail 2" value="false"/>
                                <label for="notifyViaEmail 2">No</label>



                        </td>
                    </tr>

                    <tr>
                        <td>Notify via push:</td>
                        <td>


                                <form:radiobutton path="notifyViaPush" id="notifyViaPush 1" value="true"/>
                                <label for="notifyViaPush 1">Yes</label>
                                <form:radiobutton path="notifyViaPush" id="notifyViaPush 2" value="false"/>
                                <label for="notifyViaPush 2">No</label>


                        </td>
                    </tr>

                    <tr>
                        <td></td>
                        <td><input type="submit" value="Update account"></td>
                    </tr>

                </form:form>


            </table>

        </div>


        <div class="right" style="width: 40%">

            Upload Avatar:
            <br/>
            <form:form method="post" action="/upload_avatar" modelattribute="avatarFile" enctype="multipart/form-data">

                <p>Select an image to upload. Must be of 32 x 32 (pixels) </p>


                <table id="fileTable">
                    <tbody>
                    <tr>
                        <td><input name="avatar[0]" type="file"></td>
                    </tr>

                    </tbody>
                </table>

                <br><input type="submit" value="Upload Avatar">
            </form:form>


            </p>
        </div>


        <div class="clearer">&nbsp;</div>

    </div>
</div>


<%@include file="/resources/design/layout/bot.jspf" %>