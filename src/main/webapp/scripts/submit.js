function submitComicFunction() {
    var genre = document.getElementById("genreName").value;
    var file = document.getElementById("fileSubmit").files[0];
    var title = document.getElementById("titleid").value;
    var summary = document.getElementById("comicSummary").value;
    if(title == null || summary == null || title == "" || summary == ""){
        alert("Fields cannot be blank.");
        return false;
    }
    if(file.length == 0){
        alert("Missing file");
        return false;
    }
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function(e) {
        var dataURL = reader.result;
        $.ajax({
            type: "POST",
            url: "/ComicServlet",
            data: {"action" : "CREATE COMIC", "description" : summary, "genre" : genre, "name" : title },
            success: function(result){
                console.log("result:" + result);
                var comicId = result;
                console.log("comicId: " + comicId);
                $.ajax({
                    type: "POST",
                    url: "/ComicServlet",
                    data: {"action" : "ADD COVER", "file" : dataURL, "comicId" : comicId },
                    success: function(result){
                        sessionStorage.setItem('id_to_load', comicId);
                        window.location.href = "../summary.jsp";

                    },
                    error: function(err){
                        console.log(err);
                    }
                });
            },
            error: function(err){
                console.log(err);
            }
        });
    }

}

function submitChapter(){
    var title = document.getElementById("chapterNumber").value;
    var files = document.getElementById("fileSubmit").files;

    if(title == null){
        alert("Field cannot be blank.");
        return false;
    }
    if(files.length == 0){
        alert("Missing files");
        return false;
    }
    var comicId = sessionStorage.getItem('id_to_load');
    console.log("comicId: " + comicId);
    $.ajax({
        type: "POST",
        url: "/ComicServlet",
        data: {"action" : "CREATE CHAPTER", "title" : title, "comicId" : comicId },
        success: function(result){
            console.log("result:" + result);
            comicId = result;
            var status = 0;
            var end = files.length;
            for(var i = 0; i < files.length; i++){
                var dataURL;
                (function(i){
                    var file = files[i];
                    var reader = new FileReader();
                    reader.readAsDataURL(file);
                    reader.onload = function(e) {
                        dataURL = reader.result;
                        var page = i+1;
                        $.ajax({
                            type: "POST",
                            url: "/ComicServlet",
                            data: {"action" : "ADD PAGE", "comicId": comicId, "file" : dataURL, "page" : page},
                            success: function(result){
                                status++;
                                console.log("status:" + status);
                                if(status == end){
                                    sessionStorage.setItem('id_to_load', comicId);
                                    window.location.href = "../summary.jsp";
                                }
                            },
                            error: function(err){
                                console.log(err);
                            }
                        });
                    }
                })(i);

            }
        },
        error: function(err){
            console.log(err);
        }
    });
    
}

function submitComicFunctionorIG() {

    //var files = document.getElementById("fileSubmit").files;
    var title = document.getElementById("titleid").value;
    var summary = document.getElementById("comicSummary").value;
    if(title == null || summary == null || title == "" || summary == ""){
        alert("Fields cannot be blank.");
        return false;
    }
    if(file.length == 0){
        alert("Missing file");
    }

    $.ajax({
        type: "POST",
        url: "/ComicServlet",
        data: {"action" : "CREATE COMIC", "description" : summary, "genre" : genre, "name" : title },
        success: function(result){
            console.log("result:" + result);
            var comicId = result.id;
            var status = 0;
            var end = files.length;
            for(var i = 0; i < files.length; i++){
                var dataURL;
                (function(i){
                    var file = files[i];
                    var reader = new FileReader();
                    reader.readAsDataURL(file);
                    reader.onload = function(e) {
                        dataURL = reader.result;
                        var page = i+1;
                        $.ajax({
                            type: "POST",
                            url: "/ComicServlet",
                            data: {"action" : "ADD PAGE", "comicId": comicId, "file" : dataURL, "page" : page},
                            success: function(result){
                                status++;
                                console.log("status:" + status);
                                if(status == end){
                                    sessionStorage.setItem('id_to_load', comicId);
                                    window.location.href = "../summary.jsp";
                                }
                            },
                            error: function(err){
                                console.log(err);
                            }
                        });
                    }
                })(i);

            }
        },
        error: function(err){
            console.log(err);
        }
    });
}