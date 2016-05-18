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
            console.log(response);
        },
        error: function(response) {
            console.log(response);
        }
    });
    resetAllSearchHTML();
    
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
    // var targetDiv = $("#" + targetDivId + "> ul > .comicBlock");
    var targetDiv = $("#" + targetDivId + " > .comicBlock");
    
    for (var i = 0; i < resultList.length; i++) {
        var imgSrc = getRandomCoverArt();
        var html = "<div class='comic-listing' data-cover='" + resultList[i].metadata.coverImage + "'><a onclick='passBySession(" + resultList[i].id + "," + imgSrc[0] +")' role='button'><img src=" + imgSrc[1] + "></a></div>";
        targetDiv.append(html);
    }
}

function resetAllSearchHTML() {
    $(".comic-listing").remove();
}

// ADDITIONAL STUFF

function retrieveImage(img_key) {
    var path = "";
    $.ajax({
        url: "/upload",
        type: "GET",
        async: false,
        data: {"action": "GET COVER", "blob_key": img_key},
        success: function (responseText) {
            path = responseText;
        }
    });
    return path;
}

function loadCovers() {
    $(".comic-listing").each(function() {

        // get the image key
        var cover_key = $(this).data("cover");

        // get the actual image tag from the page
        var the_image = $(this).find("img");

        // get the image url via ajax
        if (cover_key == ""  || cover_key == null) {
            // no cover for the comic, default to Doenut
            the_image.attr("src", "images/covers/DoenutCover.png");
        } else {
            var img_path = retrieveImage(cover_key);

            if (img_path == "")
            // blob wasn't retrieved
                the_image.attr("src", "images/covers/Doofus.png");
            else
                the_image.attr("src", img_path); // blob was retrieved =)
        }

    });
    //jQuery(this).find("img");
}
