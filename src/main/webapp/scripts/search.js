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
            
            loadCovers();
        },
        error: function(response) {
            console.log(response);
        }
    });
    resetAllSearchHTML();
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
            return;
    }

    populateDiv(resultList, targetId);
}

function populateDiv(resultList, targetDivId) {
    var targetDiv = $("#" + targetDivId + " > .comic-block");
    
    for (var i = 0; i < resultList.length; i++) {
        var imgSrc = getRandomCoverArt();
        ( 
            function(target, imageSource) {
                targetDiv.click(function() {
                    if (targetDivId.includes("users")) {
                        userToProfile(target.googleId);
                    }
                    else {
                        passBySession(target.id, imageSource);
                    }
                });
            }
        )(resultList[i], imgSrc[0]);

        var html = "<div class='comic-listing' data-cover='" + resultList[i].metadata.coverImage + "'><a role='button'><img src=" + imgSrc[1] + "></a></div>";
        targetDiv.append(html);
    }
}

function resetAllSearchHTML() {
    $(".comic-listing").remove();
}
