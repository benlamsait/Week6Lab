<%-- 
    Document   : shoppingList
    Created on : Feb 19, 2023, 10:31:01 PM
    Author     : Ben Lam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello ${username}</p> <%-- change this --%>
        <p><a href="ShoppingList?action=logout">Logout</a></p>
        
        <%-- start of the form --%>
        <form action="" method="POST">
            <h2>Add Item</h2>
            <label for="item">Add item</label>
            <input type="text" name="item">
            <input type="submit" name="Add Item">
            <input type="hidden" name="action" value="add">
        </form>
        
        <%-- list of user items --%>
        <form action="" method="POST">
            <ul>
                <c:forEach var="item" items="${shoppingList}">
                    <input type="radio" id="${item}" name="theList" value="${item}"
                    <li>${item}</li>
                    <br>
                </c:forEach>
            </ul>
            <input type="submit" value="delete">
            <input type="hidden" name="action" value="delete">
        </form>
        
    </body>
</html>