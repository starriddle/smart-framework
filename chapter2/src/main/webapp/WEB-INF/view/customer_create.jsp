<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>客户管理</title>
</head>
<body>
    <h1>创建客户</h1>
    <p><a href="customer_list">返回</a></p>
    <form action="customer_create" method="post">
        <table>
            <tr>
                <th>客户名称</th>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <th>联系人</th>
                <td><input type="text" name="contact"></td>
            </tr>
            <tr>
                <th>电话号码</th>
                <td><input type="tel" name="telephone"></td>
            </tr>
            <tr>
                <th>邮箱地址</th>
                <td><input type="email" name="email"></td>
            </tr>
            <tr>
                <th>备注</th>
                <td><textarea name="remark" id="" cols="30" rows="10"></textarea></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="确定">
                    <input type="reset" value="重填">
                </td>
            </tr>
            <c:if test="${create_result == true}">
                <tr>
                    <td colspan="2">创建成功</td>
                </tr>
            </c:if>
            <c:if test="${create_result  == false}">
                <tr>
                    <td colspan="2">创建失败</td>
                </tr>
            </c:if>
        </table>
    </form>
</body>
</html>
