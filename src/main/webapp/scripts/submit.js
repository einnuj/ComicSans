function submitComicFunction() {
    var genre = document.getElementById("genreName").value;
    //var file = document.getElementById("fileSubmit").files[0];
    var files = document.getElementById("fileSubmit").files;
    var title = document.getElementById("titleid").value;
    var summary = document.getElementById("comicSummary").value;


        $.ajax({
            type: "POST",
            url: "/ComicServlet",
            data: {"action" : "CREATE COMIC", "description" : summary, "genre" : genre, "name" : title },
            success: function(result){
                console.log("result:" + result);
                var comicId = result;

                for(var i = 0; i < files.length; i++){
                    var dataURL;
                    (function(i){
                        var file = files[i];
                        var reader = new FileReader();
                        reader.readAsDataURL(file);
                        reader.onload = function(e) {
                            dataURL = reader.result;
                            var page = i+1;
                            console.log("page:" + page);
                            $.ajax({
                                type: "POST",
                                url: "/ComicServlet",
                                data: {"action" : "ADD PAGE", "comicId": comicId, "file" : dataURL, "page" : page},
                                success: function(result){
                                    console.log("success");
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

