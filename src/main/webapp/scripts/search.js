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
            console.log(response);
        },
        error: function(response) {
            console.log(response);
        }
    });
    
    console.log("SUCCESS");
}

function addToHTML(resultList, typeString) {
    var comicsDivId = "search-comics";
    var commentDivId = "search-comments";
    var userDivId = "search-users";
    var targetId;

    switch (typeString) {
        case "comics":
            targetId = comicsDivId;
            break;
        case "comments":
            targetId = commentDivId;
            break;
        case "users":
            targetId = userDivId;
            break;
        default:
            console.log("ERROR");
    }

    populateDiv(resultList, targetId);
}

function populateDiv(resultList, targetDivId) {
    var targetDiv = $("#" + targetDivId + "> ul");

    for (var i = 0; i < resultList.length; i++) {
        var imgSrc = getRandomCoverArt();
        var html = "<li><a onclick='passBySession(" + resultList[i].id + "," + imgSrc[0] +")' role='button'><img src=" + imgSrc[1] + "/></a></li>";
        targetDiv.append(html);
    }
}

function check_session() {
    var resultsList = sessionStorage.getItem("result-object");
    
    if (resultsList == null) {
        
    }
}
