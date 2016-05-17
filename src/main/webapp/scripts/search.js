function search(keyword) {
    $.ajax({
        url: "/search",
        type: "POST",
        data: {"search" : keyword},
        success: function(response) {
            var comicResults = response[0];
            var commentResults = response[1];
            var userResults = response[2];
            console.log(response);
        },
        error: function(response) {
            console.log(response);
        }
    });
}

function check_session() {
    var comicResults = sessionStorage.getItem("comic-results");
    var commentResults = sessionStorage.getItem("comment-results");
    var userResults = sessionStorage.getItem("user-results");
    
    if (comicResults == null || commentResults == null || userResults == null) {
        
    }
}
