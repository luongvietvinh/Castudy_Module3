<%--
  Created by IntelliJ IDEA.
  User: Admind
  Date: 31/12/2021
  Time: 10:13 SA
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <title>Title</title>
</head>
<body>
<form method="post">

    <div class="form-group">
        <label>user_name</label>
        <input type=text class="form-control" name="user_name" aria-describedby="emailHelp"
              >
    </div>
    <div class="form-group">
        <label>nhap passwords</label>
        <input type="password" class="form-control" name="passwords" >
    </div>
        <div class="form-group">
            <label>Full_Name</label>
            <input type=text class="form-control" name="full_name" aria-describedby="emailHelp"
                  >
        </div>
    <div class="form-group">
        <label>nhap email</label>
        <input type="email" class="form-control" name="email" aria-describedby="emailHelp"
               >
    </div>
    <div class="form-group">
        <label>nhap phone</label>
        <input type="number" class="form-control" name="phone" aria-describedby="emailHelp" >
    </div>
    <div class="form-group">
        <label>nhap address</label>
        <input type="text" class="form-control" name="address" aria-describedby="emailHelp">
    </div>
    <div class="form-group">
        <label>nhap img</label>
        <input type="text" class="form-control" name="img" aria-describedby="emailHelp" >
    </div>
    <div class="form-group">
        <label>nhap salary</label>
        <input type="text" class="form-control" name="salary" aria-describedby="emailHelp" >
    </div>
    <div class="form-group">
        <label>nhap coefficients_salary</label>
        <input type="text" class="form-control" name="coefficients_salary" aria-describedby="emailHelp" >
    </div>
    <div class="form-group">
        <label>chon role</label>
        <select name="id_role">
            <c:forEach items="${roles}" var="r">
                <option value="${r.id}">
                    <p>${r.name}</p>
                </option>
            </c:forEach>
        </select>
    </div>

    <%--    <div class="form-check">--%>
    <%--        <input type="checkbox" class="form-check-input" id="exampleCheck1">--%>
    <%--        <label class="form-check-label" for="exampleCheck1">Check me out</label>--%>
    <%--    </div>--%>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
</body>
</html>

