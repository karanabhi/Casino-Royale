<%-- 
    Document   : Slots
    Created on : Nov 18, 2016, 2:15:50 AM
    Author     : Abhishek Karan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <style type="text/css">
            canvas{
                /*position: absolute;*/
                background: #111;
                /*height: 100%;*/
                width: 100%;
                left: 0;
                top: 0;
            }
            body{
                background: #111;
            }
            input[type=button]{
                -webkit-border-radius: 16;
                -moz-border-radius: 16;
                border-radius: 16px;
                -webkit-box-shadow: 0px 1px 3px #666666;
                -moz-box-shadow: 0px 1px 3px #666666;
                box-shadow: 0px 1px 3px #666666;
                font-family: Courier New;
                color: #ffffff;
                font-size: 24px;
                background: #662222;
                padding: 10px 20px 10px 20px;
                text-decoration: none;
                outline: none;
                margin-top: 5%;
            }

            input[type=button]:hover {
                background: #874747;
                text-decoration: none;
            }
            .main-box{
                margin:8% 30%;
                border : solid white 2px;
                padding: 2%;
            }
        </style>
        <div class="main-box">
            <center><h1 style="color:white;font-family:futura;"> SLOT MACHINE </h1><br><hr></center>
            <canvas></canvas>
            <center>
                <hr>
                <input type="button" value="PULL" id="pull"/>
            </center>
        </div>
        <script type="text/javascript">

            text = '<%=session.getAttribute("strs")%>';  // The message displayed
            chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';  // All possible Charactrers
            scale = 50;  // Font size and overall scale
            breaks = 0.003;  // Speed loss per frame
            endSpeed = 0.05;  // Speed at which the letter stops
            firstLetter = 220;  // Number of frames untill the first letter stopps (60 frames per second)
            delay = 40;  // Number of frames between letters stopping



            canvas = document.querySelector('canvas');
            ctx = canvas.getContext('2d');

            text = text.split('');
            chars = chars.split('');
            charMap = [];
            offset = [];
            offsetV = [];

            for (var i = 0; i < chars.length; i++) {
                charMap[chars[i]] = i;
            }

            for (var i = 0; i < text.length; i++) {
                var f = firstLetter + delay * i;
                offsetV[i] = endSpeed + breaks * f;
                offset[i] = -(1 + f) * (breaks * f + 2 * endSpeed) / 2;
            }

            (onresize = function () {
                canvas.width = canvas.clientWidth;
                canvas.height = canvas.clientHeight;
            })();

            //------------ print once
            ctx.setTransform(1, 0, 0, 1, 0, 0);
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ctx.globalAlpha = 1;
            ctx.fillStyle = '#622';
            ctx.fillRect(0, (canvas.height - scale) / 2, canvas.width, scale);


            for (var i = 0; i < text.length; i++) {
                ctx.fillStyle = '#ccc';
                ctx.textBaseline = 'middle';
                ctx.textAlign = 'center';
                ctx.setTransform(1, 0, 0, 1, Math.floor((canvas.width - scale * (text.length - 1)) / 2), Math.floor(canvas.height / 2));
                /*var o = offset[i];
                 while(o<0)o++;
                 o %= 1;*/
                var o = 1;
                var h = Math.ceil(canvas.height / 2 / scale)
                for (var j = -h; j < h; j++) {
                    var c = charMap[text[i]] + j - Math.floor(offset[i]);
                    while (c < 0)
                        c += chars.length;
                    c %= chars.length;
                    var s = 1 - Math.abs(j + o) / (canvas.height / 2 / scale + 1)
                    ctx.globalAlpha = s
                    ctx.font = scale * s + 'px Helvetica'
                    ctx.fillText(chars[c], scale * i, (j + o) * scale);
                }
                offset[i] += offsetV[i];
                offsetV[i] -= breaks;
                if (offsetV[i] < endSpeed) {
                    offset[i] = 0;
                    offsetV[i] = 0;
                }
            }


            //-------------end : print once
            function run() {
                requestAnimationFrame(loop = function () {
                    ctx.setTransform(1, 0, 0, 1, 0, 0);
                    ctx.clearRect(0, 0, canvas.width, canvas.height);
                    ctx.globalAlpha = 1;
                    ctx.fillStyle = '#622';
                    ctx.fillRect(0, (canvas.height - scale) / 2, canvas.width, scale);
                    for (var i = 0; i < text.length; i++) {
                        ctx.fillStyle = '#ccc';
                        ctx.textBaseline = 'middle';
                        ctx.textAlign = 'center';
                        ctx.setTransform(1, 0, 0, 1, Math.floor((canvas.width - scale * (text.length - 1)) / 2), Math.floor(canvas.height / 2));
                        var o = offset[i];
                        while (o < 0)
                            o++;
                        o %= 1;
                        var h = Math.ceil(canvas.height / 2 / scale)
                        for (var j = -h; j < h; j++) {
                            var c = charMap[text[i]] + j - Math.floor(offset[i]);
                            while (c < 0)
                                c += chars.length;
                            c %= chars.length;
                            var s = 1 - Math.abs(j + o) / (canvas.height / 2 / scale + 1)
                            ctx.globalAlpha = s
                            ctx.font = scale * s + 'px Helvetica'
                            ctx.fillText(chars[c], scale * i, (j + o) * scale);
                        }
                        offset[i] += offsetV[i];
                        offsetV[i] -= breaks;
                        if (offsetV[i] < endSpeed) {
                            offset[i] = 0;
                            offsetV[i] = 0;
                        }
                    }
                    requestAnimationFrame(loop);
                });
                window.setTimeout(function () {
                    window.location.href = "Selector.jsp"
                }, 9000);
            }
            document.getElementById("pull").addEventListener("click", run);
            document.getElementById("pull").focus();
        </script>
    </body>
</html>
