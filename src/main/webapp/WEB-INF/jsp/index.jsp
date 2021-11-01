<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Welcome!</title>
</head>
<body>
<h1>Choose what to manage:</h1>
<button onclick="window.location.href='<%=request.getContextPath()%>/car/';">
    Cars
</button>
<button onclick="window.location.href='<%=request.getContextPath()%>/engine/';">
    Engines
</button>
<button onclick="window.location.href='<%=request.getContextPath()%>/manufacturer/';">
    Manufacturers
</button>
</body>
</html>