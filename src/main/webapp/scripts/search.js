function searchFunc() {
    $.ajax({
        url: "/search",
        type: "POST",
        data: {"search" : $("#search-text").val(), "jsp" : true},
        success: function(response) {
            var comicString = "comics";
            var commentString = "comments";
            var userString = "users";
            addToHTML(response[0], comicString);
            addToHTML(response[1], commentString);
            addToHTML(response[2], userString);
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

function addToHTML(resultMap, typeString) {
    switch (typeString) {
        case "comics":
            var comicsMap = getComics(resultMap);
            populateComicsDiv(resultMap);
            break;
        case "comments":
            break;
        case "users":
            break;
        default:
            console.log("ERROR");
    }
}

function getComics(resultMap) {
    $.ajax({
        url: ""
    })
}

function populateComicsDiv(finalComicsMap) {
    var comicsDiv = $("#search-comics > ul");


}

function check_session() {
    var resultsList = sessionStorage.getItem("result-object");
    
    if (resultsList == null) {
        
    }
}
