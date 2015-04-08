<%--
  Created by IntelliJ IDEA.
  User: Irrielde
  Date: 1.4.2015
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>


  <style>
    .MsgAnchor { }
    .MsgAnchor A:visited
    {
      DISPLAY:none;
    }
    .MsgAnchor A:link
    {
      BACKGROUND-COLOR: LightGreen
    TEXT-DECORATION:none;
    }
  </style>

    <title></title>
</head>
<body>

<table ID="Table1">
  <tr>
    <td><b>Topic</b></td>
    <td><b>Date Submitted</b></td>
    <td><b>Author</b></td>
  </tr>
  <tr>
    <td>
      <a href="CSSSampleDetails.html?ID=1&NumReplies=4">
        Building Robust ASP Pages</a>
            <span class="MsgAnchor">
               <a href="CSSSampleDetails.html?ID=1&NumReplies=4">New!</a>
            </span>
    </td>
    <td>7/19 09:56AM</td>
    <td>John Doe</td>
  </tr>
  <tr>
    <td>
      <a href="CSSSampleDetails.html?ID=2&NumReplies=2">
        Anyone seen this before?</a>
            <span class="MsgAnchor">
               <a href="CSSSampleDetails.html?ID=2&NumReplies=2">New!</a>
            </span>
    </td>
    <td>7/19 08:50AM</td>
    <td>Jimmy D.</td>
  </tr>
  <tr>
    <td>
      <a href="CSSSampleDetails.html?ID=3&NumReplies=15">
        Programming question...</a>
            <span class="MsgAnchor">
               <a href="CSSSampleDetails.html?ID=3&NumReplies=15">New!</a>
            </span>
    </td>
    <td>7/18 08:50AM</td>
    <td>Newbie L.</td>
  </tr>
</table>

</body>
</html>


