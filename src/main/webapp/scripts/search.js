function searchFunc() {
    $.ajax({
        url: "/search",
        type: "POST",
        data: {"search" : $("#search-text").val(), "jsp" : true},
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
    
    console.log("SUCCESS");
}

function check_session() {
    var resultsList = sessionStorage.getItem("result-object");
    
    if (resultsList == null) {
        
    }
}
