
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>客户管理</title>
</head>
<body>
    <h1>客户详情</h1>
    <p><a href="customer_list">返回</a></p>
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
        <tr>
            <td colspan="2">
                <a href="customer_edit?id=${customer.id}">编辑</a>
                <a href="customer_delete?id=${customer.id}">删除</a>
            </td>
        </tr>
    </table>
</body>
</html>
