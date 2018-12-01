<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>客户管理</title>
</head>
<body>
    <h1>创建客户</h1>
    <p><a href="customer_list">返回</a></p>
    <form action="customer_edit" method="post">
        <input type="text" name="id" value="${customer.id}" hidden="hidden">
        <table>
            <tr>
                <th>客户名称</th>
                <td><input type="text" name="name" value="${customer.name}"></td>
            </tr>
            <tr>
                <th>联系人</th>
                <td><input type="text" name="contact" value="${customer.contact}"></td>
            </tr>
            <tr>
                <th>电话号码</th>
                <td><input type="tel" name="telephone" value="${customer.telephone}"></td>
            </tr>
            <tr>
                <th>邮箱地址</th>
                <td><input type="email" name="email" value="${customer.email}"></td>
            </tr>
            <tr>
                <th>备注</th>
                <td><textarea name="remark" id="" cols="30" rows="10" >${customer.remark}</textarea></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="更新">
                    <input type="reset" value="取消">
                </td>
            </tr>
            <c:if test="${update_result == 1}">
                <tr>
                    <td colspan="2">更新成功</td>
                </tr>
            </c:if>
            <c:if test="${update_result  == 0}">
                <tr>
                    <td colspan="2">更新失败</td>
                </tr>
            </c:if>
            <c:if test="${update_result  == 2}">
                <tr>
                    <td colspan="2">未作修改</td>
                </tr>
            </c:if>
        </table>
    </form>
</body>
</html>
