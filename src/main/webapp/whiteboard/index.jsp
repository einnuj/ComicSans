<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8" />
    <title>Whiteboard</title>
    <meta name="Description" content="" />
    <meta name="Keywords"  content="" />

    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <%-- Stylesheets --%>
    <link href="../css/main.css" rel="stylesheet">
    <link href="../css/sideNav.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="screen,print" href="style.css" />

    <script src="http://jsrun.it/assets/l/z/O/z/lzOzs" type="text/javascript"></script>
    <script src="http://jsrun.it/assets/b/M/t/M/bMtM0" type="text/javascript"></script>
    <script type="text/javascript" src="http://jsrun.it/assets/m/Y/a/i/mYaiC"></script>
    <script type="text/javascript" src="http://jsrun.it/assets/7/4/h/K/74hKn"></script>
    <script type="text/javascript" src="http://jsrun.it/assets/2/s/7/2/2s72w"></script>
    <script type="text/javascript" src="http://jsrun.it/assets/7/L/2/c/7L2cy"></script>
    <script type="text/javascript" src="http://jsrun.it/assets/k/R/i/M/kRiMK"></script>
    <script type="text/javascript" src="http://jsrun.it/assets/v/0/Z/q/v0Zqt"></script>
    <script type="text/javascript" src="http://jsrun.it/assets/9/P/c/m/9Pcm9"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
    <script type="text/javascript" src="index.js"></script>

</head>
<body>

    <nav class="navbar navbar-default" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="../index.jsp"><span class="glyphicon glyphicon-home" aria-hidden="true"></span></a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav">
                <li><a href=""><span class="glyphicon glyphicon-search" aria-hidden="true"></span></a></li>
                <li><a href="directory.jsp"><span class="glyphicon glyphicon-sort-by-alphabet" aria-hidden="true"></span></a></li>
                <li><a href=""><span class="glyphicon glyphicon-star" aria-hidden="true"></span></a></li>
                <li><a href=""><span class="glyphicon glyphicon-tags" aria-hidden="true"></span></a></li>
                <li><a href=""><span class="glyphicon glyphicon-user" aria-hidden="true"></span></a></li>
                <li><a href="../publish.jsp"><span class="glyphicon glyphicon glyphicon-pencil" aria-hidden="true"></span></a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </nav>

    <div class="drawing-board">
        <div id="drawing-mode-options">
            <button class=btn-primary id="clear" type="button">Clear Canvas <span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button>
            <button class=btn-primary id="draw" type="button">Draw <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button>
            <button class=btn-primary id="stopDraw" type="button">Select <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span></button>
            <button class=btn-primary id="remove" type="button">Remove object <span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span></button>
            <br><br>
            <label for="amount" id="labelForAmount">Set Width:</label>
            <input type="text" id="amount" readonly>
            <div id="slider"></div>
            <input type="color" id="colorpicker"/>
            <br>
            <button class=btn-primary id="bringFront" type="button">Bring to Front</button>
            <button class=btn-primary id="sendBack" type="button">Send to Back</button>
            <button class=btn-primary id="layerUp" type="button">Layer up <span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span></button>
            <button class=btn-primary id="layerDown" type="button">Layer down <span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span></button>
            <br>
            <button class=btn-primary id="rect" type="button">Add rectangle</button>
            <button class=btn-primary id="circle" type="button">Add circle</button>
            <button class=btn-primary id="ellipse" type="button">Add ellipse</button>
            <button class=btn-primary id="line" type="button">Draw line</button>
            <br>
            <button class=btn-primary id="text_input" type="button">Input Text <span class="glyphicon glyphicon-font" aria-hidden="true"></span></button>
            <input id="text" name="text" type="text" size="40" value="Input here!">
            <font size="2">Size:</font> <input id="size" name="size" type="text" size="3" value="18">

            <select id="textdropdown">
                <option value="Comic Sans MS">Comic Sans</option>
                <option value="Times New Roman">Times New Roman</option>
            </select>
            <br>
            <button class=btn-primary id="save" type="button">Save <span class="glyphicon glyphicon-save-file" aria-hidden="true"></span></button>
            <button class=btn-primary id="load" type="button">Load <span class="glyphicon glyphicon-open-file" aria-hidden="true"></span></button>
            <button class=btn-primary id="delete" type="button">Delete save <span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
            <br>
            <button class=btn-primary id="image_save" type="button">Save png to localstorage <span class="glyphicon glyphicon-save-file" aria-hidden="true"></span></button>
            <button class=btn-primary id="image_load" type="button">Load png from localstorage <span class="glyphicon glyphicon-open-file" aria-hidden="true"></span></button>
            <button class=btn-primary id="image_delete" type="button">Remove png file <span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
            <button class=btn-primary id="image_out" type="button">Image(png) Output <span class="glyphicon glyphicon-picture" aria-hidden="true"></span></button>
            <button class=btn-primary id="pdf_out" type="button">PDF Output <span class="glyphicon glyphicon-share" aria-hidden="true"></span></button>
        </div>
        <canvas id="canvas" width="900" height="700"></canvas>
    </div>
</body>
</html>

<%-- SCRIPTS --%>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>