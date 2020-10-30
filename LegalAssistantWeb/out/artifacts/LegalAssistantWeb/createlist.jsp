<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание списка</title>
</head>
<body>
    <FORM  action="${pageContext.request.contextPath}/createlist" method="post">
        <span>Заголовок</span><br>
        <input name="title"><br>
        <span>Подробный заголовок</span><br>
        <input name="subtitle"><br>
        <span>Текст описание</span><br>
        <textarea style="width: 400px; height: 500px" name="text"></textarea><br>
        <span>Ссылки на PDF</span><br>
        <input name="pdf"><br>
        <span>Источник</span><br>
        <input name="link"><br>
        <button>Создать</button><br>
        <br>
        <br>
        <br>
        ${err}
    </FORM>
</body>
</html>
