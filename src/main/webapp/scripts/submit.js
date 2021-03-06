function submitComicFunction() {
    var genre = document.getElementById("genreName").value;
    var file = document.getElementById("fileSubmit").files;
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
                var comicId = result;
                
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

function submitComic(){
    var fd = new FormData();
    var genre = document.getElementById("genreName").value;
    var title = document.getElementById("titleid").value;
    var summary = document.getElementById("comicSummary").value;
    var file = document.getElementById("fileSubmit").files[0];
    var hasFile = document.getElementById("fileSubmit").value;

    if(title == null || summary == null || title == "" || summary == ""){
        alert("Fields cannot be blank.");
        return false;
    }
    if(hasFile == ""){
        alert("Please choose a file.");
        return false;
    }

    fd.append("file", file);
    fd.append("title", title);
    fd.append("genre", genre);
    fd.append("summary", summary);
    fd.append("action", "CREATE COMIC");
    var xhr = new XMLHttpRequest();
    xhr.open("POST", document.getElementById('submitComicForm').action);

    xhr.onreadystatechange = function() {
        if (xhr.readyState == XMLHttpRequest.DONE) {

            sessionStorage.setItem('id_to_load', xhr.responseText);
            window.location.href = "../summary.jsp";
        }
    }
    xhr.send(fd);


}

function submitIssue() {
    var fd = new FormData();
    var files = document.getElementById("fileSubmit").files;
    var title = document.getElementById("issueTitle").value;
    var comicId = sessionStorage.getItem('id_to_load');
    var hasFile = document.getElementById("fileSubmit").value;


    if(title == null || title == ""){
        alert("Fields cannot be blank.");
        return false;
    } else {
    }
    if(hasFile == ""){
        alert("Please choose a file.");
        return false;
    }

    for ( var i = 0; i < files.length; i++) {
        fd.append("file" + i, files[i]);
    }
    fd.append("issueTitle", title);
    fd.append("comicId", comicId);
    fd.append("action", "ADD ISSUE");
    var xhr = new XMLHttpRequest();
    xhr.open("POST", document.getElementById('submitChapterForm').action);

    xhr.onreadystatechange = function() {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {

                sessionStorage.setItem('id_to_load', xhr.responseText);
                window.location.href = "../summary.jsp";
            }
            else if (xhr.status == 400) {
                var errorMsg = xhr.responseText.match(/{(.*?)}/)[1];
                alert("ERROR 400: " + errorMsg);
                window.location.reload();
            }
        }
    };
        xhr.send(fd);

}

