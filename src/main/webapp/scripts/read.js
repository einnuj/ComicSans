// get the comic to read
var actualComic = getComic(sessionStorage.getItem("id_to_load"));
console.log(actualComic);

// get the list of issues
// issues[i].childMediaList[0].imgURL gets the page for issue i+1
var issues = actualComic.childMediaList;
console.log(issues);

// initialize current issue
var currentIssue = 0;

// get the img blob_key for the particular issue
var imgToLoad = issues[currentIssue].childMediaList[0].imgURL;
console.log(imgToLoad);

// retrieve the image for the issue and set it as the img src
var image = retrieveImage(imgToLoad);
$("#page-image").attr("src", image);


function loadNextPage() {
    if (currentIssue + 1 < issues.length) {
        currentIssue++;
        imageToLoad = issues[currentIssue].childMediaList[0].imgURL;
        image = retrieveImage(imageToLoad);
        console.log("NEXT REACHED: " + image);
        $("#page-image").attr("src", image);
    }
}

function loadPreviousPage() {
    if (currentIssue != 0) { // if this is not the first issue
        currentIssue--;
        imageToLoad = issues[currentIssue].childMediaList[0].imgURL;
        image = retrieveImage(imageToLoad);
        console.log("PREV REACHED: " + image);
        $("#page-image").attr("src", image);
    }
}

function getComic(comicId) {
    var theComic = "";
    $.ajax({
        url: "/ComicServlet",
        type: "get",
        data: {"id": comicId},
        async: false,
        success: function (responseText) {
            $("#comicJson > a").text(responseText);
            theComic = responseText;
        }
    })
    return theComic;
}

function retrieveImage(img_key) {
    var path = "";
    $.ajax({
        url: "/upload",
        type: "GET",
        async: false,
        data: {"action": "GET IMAGE", "blob_key": img_key},
        success: function(responseText) {
            console.log(responseText);
            path = responseText;
        },
    });
    return path;
}