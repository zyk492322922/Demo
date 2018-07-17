<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html class="login-alone">
<head>
    <title>页面乱码 </title>

    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <style >
        #main{
            width: 100%;
            height: 100%;
            border: 1px solid red;
        }
        #container{
            border: 1px solid red;
            width: 65%;
            height: 45%;
            margin:0 auto;
            margin-top: 25%
        }
        #username , #password{
            margin-top: 15%;
            margin-left: 20px;
        }

    </style>
</head>
<body>
<div id="main">
    <div id="container">
        <div>
            <div id="username">username: <input type="text" name="fname" /></div>
            <div id="password">password: <input type="text" name="fname" /></div>
        </div>
    </div>
</div>
</body>
</html>