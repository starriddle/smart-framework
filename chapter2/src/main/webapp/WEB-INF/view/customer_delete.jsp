
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>客户管理</title>
</head>
<body>
    <h1>删除客户</h1>
    <p><a href="customer_list">返回</a></p>
    <form action="customer_delete" method="post">
        <input type="text" name="id" value="${customer.id}" hidden="hidden">
        <table>
            <tr>
                <th>客户 ID</th>
                <td>${customer.id}</td>
            </tr>
            <tr>
                <th>客户名称</th>
                <td>${customer.name}</td>
            </tr>
            <tr>
                <th>联系人</th>
                <td>${customer.contact}</td>
            </tr>
            <tr>
                <th>电话号码</th>
                <td>${customer.telephone}</td>
            </tr>
            <tr>
                <th>邮箱地址</th>
                <td>${customer.email}</td>
            </tr>
            <tr>
                <th>备注</th>
                <td>${customer.remark}</td>
            </tr>
            <c:if test="${delete_result == null}">
                <tr>
                    <td colspan="2">
                        <input type="submit" value="确认删除">
                    </td>
                </tr>
            </c:if>
            <c:if test="${delete_result == true}">
                <tr>
                    <td colspan="2">删除成功</td>
                </tr>
            </c:if>
            <c:if test="${delete_result  == false}">
                <tr>
                    <td colspan="2">删除失败</td>
                </tr>
            </c:if>
        </table>
    </form>
</body>
</html>
