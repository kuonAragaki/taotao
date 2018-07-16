<%--
  Created by IntelliJ IDEA.
  User: Kuon
  Date: 2018/7/11
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%
    //out.print("fun({\"abc\":123})");
    String callback=request.getParameter("callback");
    if(null!=callback ||! "".equals(callback))
    {
        out.print(callback + "({\"abc\":123})");
    }
    else
    {
        out.print("{\"abc\":123}");
    }
%>

