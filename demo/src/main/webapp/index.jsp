<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>


<form action="/calculator-servlet" method="get">
    <label>Số thứ nhất: <input type="number" name="num1"></label>
    <label>Số thứ hai: <input type="hidden" name="num2" value="123"></label>
    <input type="submit" value="Tính tổng">

</form>
Kết quả: ${result}
<%--<%--%>
<%--    if("POST".equalsIgnoreCase(request.getMethod())) {--%>
<%--        double num1 = Double.parseDouble(request.getParameter("num1"));--%>
<%--        double num2 = Double.parseDouble(request.getParameter("num2"));--%>
<%--        double result = num1 + num2;--%>

<%--%>--%>
<%--Kết quả: <%=result%>--%>
<%--<%--%>
<%--    }--%>
<%--%>--%>

</body>
</html>