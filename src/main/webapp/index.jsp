<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyLocalChess</title>
    

    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    
    <style>
        /* Estilos adicionales en línea */
        body {
            background-image: url('<%= request.getContextPath() %>/img/1.png');
            background-size: cover;
        }
    </style>
</head>

<body>
    <div class="container">
        <button id="btnIniciar" class="btn btn-light">Iniciar Juego</button>
    </div>
</body>

</html>
