// forked from yamineko's "fabric.jsを利用したシンプルホワイトボード" http://jsdo.it/yamineko/g6IA
window.onload = function() {

    var canvas = new fabric.Canvas('canvas');

    jQuery(document).ready( function() {

        canvas.freeDrawingLineWidth = 5;

        $("#bringFront").click(function(){
            var currentObj = canvas.getActiveObject();
            canvas.bringToFront(currentObj);
        });

        $("#sendBack").click(function(){
            var currentObj = canvas.getActiveObject();
            canvas.sendToBack(currentObj);
        });

        $("#layerUp").click(function(){
            var currentObj = canvas.getActiveObject();
            canvas.bringForward(currentObj, true);
        });

        $("#layerDown").click(function(){
            var currentObj = canvas.getActiveObject();
            canvas.sendBackwards(currentObj, true);
        });

        $("#slider").slider({
            min:1,
            max:50,
            value:5,
            slide: function(event, ui) {
                $("#amount").val(ui.value);
                canvas.freeDrawingLineWidth = ui.value;
            }
        });
        $( "#amount" ).val( $( "#slider" ).slider( "value" ) );

        $("#setWidth").click(function(){
            console.log("got here");

            canvas.isDrawingMode = true;
            if (canvas.getContext) {
                var context = canvas.getContext('2d');
            }
            var drawWidth;
            var mouse_pos = { x:0 , y:0 };

            drawWidth = $('#drawWidth').val();
            //canvas.freeDrawingLineWidth = drawWidth;
            canvas.renderAll();
            canvas.calcOffset();
        });

        document.getElementById('colorpicker').addEventListener('change', function (e) {
            console.log(e.target.value);
            canvas.freeDrawingColor = e.target.value;
        });

        $("#text_input").click(function(){

            canvas.isDrawingMode = false;

            if (canvas.getContext) {
                var context = canvas.getContext('2d');
            }

            var text, size, color;

            var mouse_pos = { x:0 , y:0 };

            text = $('#text').val();
            size = $('#size').val();
            color = $('#color').val();


            canvas.observe('mouse:down', function(e) {

                mouse_pos = canvas.getPointer(e.e);
                size = parseInt(size, 10);

                canvas.add(new fabric.Text(text, {
                    fontFamily: 'Arial',
                    fontSize: size,
                    left: mouse_pos.x,
                    top: mouse_pos.y,
                    textAlign: "left",
                    fontWeight: 'bold'
                }));
                canvas.off('mouse:down');
                canvas.renderAll();
                canvas.calcOffset();
            });

        });
        $("#draw").click(function(){
            canvas.isDrawingMode = true;
            canvas.renderAll();
            canvas.calcOffset();
        });
        $("#stopDraw").click(function(){
            canvas.isDrawingMode = false;
            canvas.renderAll();
            canvas.calcOffset();
        });
        $("#rect").click(function(){

            var mouse_pos = { x:0 , y:0 };

            canvas.isDrawingMode = false;

            canvas.observe('mouse:down', function(e) {

                mouse_pos = canvas.getPointer(e.e);

                canvas.add(new fabric.Rect({
                    left: mouse_pos.x,
                    top: mouse_pos.y,
                    width: 75,
                    height: 50,
                    fill: 'white',
                    stroke: 'black',
                    strokeWidth: 3,
                    padding: 10
                }));

                canvas.off('mouse:down');

            });

        });
        $("#circle").click(function(){

            var mouse_pos = { x:0 , y:0 };

            canvas.isDrawingMode = false;

            canvas.observe('mouse:down', function(e) {

                mouse_pos = canvas.getPointer(e.e);

                canvas.add(new fabric.Circle({
                    left: mouse_pos.x,
                    top: mouse_pos.y,
                    radius: 30,
                    fill: 'white',
                    stroke: 'black',
                    strokeWidth: 3
                }));

                canvas.off('mouse:down');

            });

        });
        $("#ellipse").click(function(){

            var mouse_pos = { x:0 , y:0 };

            canvas.isDrawingMode = false;

            canvas.observe('mouse:down', function(e) {

                mouse_pos = canvas.getPointer(e.e);

                canvas.add(new fabric.Ellipse({
                    rx: 45,
                    ry: 25,
                    fill: 'white',
                    stroke: 'black',
                    strokeWidth: 8,
                    left: mouse_pos.x,
                    top: mouse_pos.y
                }));

                canvas.off('mouse:down');

            });

        });
        $("#line").click(function(){

            canvas.isDrawingMode = false;

            if (canvas.getContext) {
                var context = canvas.getContext('2d');
            }

            canvas.observe('mouse:down', function(e) { mousedown(e); });
            canvas.observe('mouse:move', function(e) { mousemove(e); });
            canvas.observe('mouse:up', function(e) { mouseup(e); });

            var started = false;
            var startX = 0;
            var startY = 0;

            /* Mousedown */
            function mousedown(e) {
                var mouse = canvas.getPointer(e.e);
                started = true;
                startX = mouse.x;
                startY = mouse.y;
                canvas.off('mouse:down');
            }

            /* Mousemove */
            function mousemove(e) {

                if(!started) {

                    return false;

                }
                canvas.off('mouse:move');

            }

            /* Mouseup */
            function mouseup(e) {

                if(started) {

                    var mouse = canvas.getPointer(e.e);

                    canvas.add(new fabric.Line([startX, startY, mouse.x, mouse.y],{ stroke: "#000000", strokeWidth: 2 }));
                    canvas.renderAll();
                    canvas.calcOffset();

                    started = false;
                    canvas.off('mouse:up');

                }

            }

        });
        $("#save").click(function(){
            canvas.isDrawingMode = false;
            if(!window.localStorage){alert("This function is not supported by your browser."); return;}
            // save to localStorage
            var json = JSON.stringify(canvas);
            var url = 'data:text/json;charset=utf8,' + encodeURIComponent(json);
            window.open(url, '_blank');
            window.focus();
            //window.localStorage.setItem("hoge", json);

            // $http.post('ENTER SERVER URL HERE', json);
        });
        $("#load").click(function(){
            canvas.isDrawingMode = false;
            if(!window.localStorage){alert("This function is not supported by your browser."); return;}
            //clear canvas
            canvas.clear();
            //load from localStorage
            canvas.loadFromJSON(window.localStorage.getItem("hoge"));
            // re-render the canvas
            canvas.renderAll();
            // optional
            canvas.calcOffset();
        });
        $("#delete").click(function(){
            canvas.isDrawingMode = false;
            if(!window.localStorage){alert("This function is not supported by your browser."); return;}
            if (confirm('Are you sure?')) {
                window.localStorage.removeItem("hoge");
            }
        });
        $("#clear").click(function(){
            canvas.isDrawingMode = false;
            if (confirm('Are you sure?')) {
                canvas.clear();
            }
        });
        $("#remove").click(function(){
            canvas.isDrawingMode = false;

            var activeObject = canvas.getActiveObject(),
                activeGroup = canvas.getActiveGroup();
            if (activeObject) {
                if (confirm('Are you sure?')) {
                    canvas.remove(activeObject);
                }
            }
            else if (activeGroup) {
                if (confirm('Are you sure?')) {
                    var objectsInGroup = activeGroup.getObjects();
                    canvas.discardActiveGroup();
                    objectsInGroup.forEach(function(object) {
                        canvas.remove(object);
                    });
                }
            }

        });
        $("#image_save").click(function(){
            canvas.isDrawingMode = false;
            if(!window.localStorage){alert("This function is not supported by your browser."); return;}
            // save to localStorage
            var base64 = $('canvas').get(0).toDataURL('png');
            window.localStorage.setItem("foo", base64);
        });
        $("#image_load").click(function(){
            canvas.isDrawingMode = false;
            if(!window.localStorage){alert("This function is not supported by your browser."); return;}
            //load from localStorage
            var base64 = window.localStorage.getItem("foo");
            if (base64) {
                if (canvas.getContext) {
                    var context = canvas.getContext('2d');
                }
                canvas.clear();
                var image = new Image();
                image.onload = function() {
                    fabric.Image.fromURL(image.src, function(img) {
                        canvas.add(img);
                        img.set('originX', 350);
                        img.set('originY', 300);
                        img.set('left', 350);
                        img.set('top', 300);
                        img.set('zindex', 0);
                        img.set('selectable', false);
                        canvas.bringToFront(img);
                    });
                    canvas.renderAll();
                    canvas.calcOffset();
                };
                image.src = base64;
            }
        });
        $("#image_delete").click(function(){
            canvas.isDrawingMode = false;
            if(!window.localStorage){alert("This function is not supported by your browser."); return;}
            if (confirm('Are you sure?')) {
                window.localStorage.removeItem("foo");
            }
        });
        $("#image_out").click(function(){
            canvas.isDrawingMode = false;

            var json = JSON.stringify(canvas);

            // add the temporary canvas
            tempCanvas = document.createElement('canvas');
            tempCanvas.id = 'tmp_canvas';
            var temp_canvas = new fabric.Canvas('tmp_canvas',{backgroundColor : "#fff"});
            temp_canvas.setWidth(700);
            temp_canvas.setHeight(600);
            wrapperEl = document.createElement('div');
            wrapperEl.className = 'CONTAINER_CLASS';
            fabric.util.makeElementUnselectable(wrapperEl);
            $('body').append(tempCanvas.wrapperEl);

            temp_canvas.loadFromJSON(json);
            temp_canvas.renderAll();
            temp_canvas.calcOffset();

            var base64 = temp_canvas.toDataURL("png");

            var image = new Image();
            image.onload = function() {
                window.open(image.src);
            }
            image.src = base64;

            // remove the temporary canvas
            $("#tmp_canvas").remove();

        });
        $("#pdf_out").click(function(){
            canvas.isDrawingMode = false;

            canvas.backgroundColor = "white";
            canvas.renderAll();

            var base64 = $('canvas').get(0).toDataURL('image/jpeg');

            var doc = new jsPDF('landscape');
            doc.addImage(base64, 'JPEG', 0, 0, 250, 214);

            var data = doc.output();
            var buffer = new ArrayBuffer(data.length);
            var array = new Uint8Array(buffer);

            for (var i = 0; i < data.length; i++) {
                array[i] = data.charCodeAt(i);
            }

            var blob = new Blob(
                [array],
                {type: 'application/pdf', encoding: 'raw'}
            );

            saveAs(blob, 'draft.pdf');
            canvas.backgroundColor = "rgba(0, 0, 0, 0)";
            canvas.renderAll();

        });


    });

    canvas.calcOffset();

    document.onkeyup = function(e) {
        canvas.renderAll();
    };

    setTimeout(function() {
        canvas.calcOffset();
    }, 100);

};