function submitComicFunction() {
    var genre = document.getElementById("genreName").value;
    //var file = document.getElementById("fileSubmit").files[0];
    var files = document.getElementById("fileSubmit").files;
    var title = document.getElementById("titleid").value;
    var summary = document.getElementById("comicSummary").value;
    if(title == null || summary == null || title == "" || summary == ""){
        alert("Fields cannot be blank.");
        return false;
    }
    if(files.length == 0){
        alert("Missing an upload");
        return false;
    }

        $.ajax({
            type: "POST",
            url: "/ComicServlet",
            data: {"action" : "CREATE COMIC", "description" : summary, "genre" : genre, "name" : title },
            success: function(result){
                var comicId = result;
                var status = 0;
                for(var i = 0; i < files.length; i++){
                    var dataURL;
                    (function(i){
                        var file = files[i];
                        var reader = new FileReader();
                        reader.readAsDataURL(file);
                        reader.onload = function(e) {
                            dataURL = reader.result;
                            var page = i+1;
                            var end = files.length;
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
                                    console.log("fail");
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
