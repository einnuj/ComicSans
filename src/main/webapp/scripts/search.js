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
    var resultsList = sessionStorage.getItem("result-object");
    
    if (resultsList == null) {
        
    }
}
