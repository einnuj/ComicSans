function submitComicFunction() {
    var chapter = document.getElementById("chapterNumber").value;
    var page = document.getElementById("pageNumber").value;
    var genre = document.getElementById("genreName").value;
    var file = document.getElementById("fileSubmit").files[0];
    var title = document.getElementById("titleid").value;
    console.log(chapter + page + genre);

    var reader = new FileReader();
    var dataURL;


    reader.readAsDataURL(file);
    reader.onload = function(e) {
        dataURL = reader.result;

        $.ajax({
            type: "POST",
            url: "/ComicServlet.create",
            data: {"chapter" : chapter, "page" : page, "genre" : genre, "imgFile" : dataURL, "title" : title },
            success: function(result){
                console.log(result);
            },
            error: function(err){
                console.log(err);
            }
        });
    }


}

