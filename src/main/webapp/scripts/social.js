function subscribe() {
    $.ajax({
        type: "POST",
        url: "/SocialServlet",
        data: {"action": "SUBSCRIBE", "comicId": comicId},
        success: function (result) {
            console.log(result);
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function unsubscribe() {
    $.ajax({
        type: "POST",
        url: "/SocialServlet",
        data: {"action": "UNSUBSCRIBE", "comicId": comicId},
        success: function (result) {
            console.log(result);
        },
        error: function (err) {
            console.log(err);
        }
    });
}


function favorite() {
    $.ajax({
        type: "POST",
        url: "/SocialServlet",
        data: {"action": "FAVORITE", "comicId": comicId},
        success: function (result) {
            console.log(result);
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function unfavorite() {
    $.ajax({
        type: "POST",
        url: "/SocialServlet",
        data: {"action": "UNFAVORITE", "comicId": comicId},
        success: function (result) {
            console.log(result);
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function like() {
    $.ajax({
        type: "POST",
        url: "/SocialServlet",
        data: {"action": "LIKE", "comicId": comicId},
        success: function (result) {
            console.log(result);
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function unlike() {
    $.ajax({
        type: "POST",
        url: "/SocialServlet",
        data: {"action": "UNLIKE", "comicId": comicId},
        success: function (result) {
            console.log(result);
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function addRating() {
    $.ajax({
        url: "/SocialServlet",
        type: "post",
        data: {"action": "RATE", "comicId": comicId, "rating": "3"},
        success: function (result) {
            console.log(result);
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function addComment() {
    $.ajax({
        url: "/SocialServlet",
        type: "post",
        data: {"action": "COMMENT", "comicId": comicId, "comment": "insert comment here"},
        success: function (result) {
            console.log(result);
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function addBookmark() {
    $.ajax({
        url: "/SocialServlet",
        type: "post",
        data: {"action": "BOOKMARK", "comicId": comicId},
        success: function (result) {
            console.log(result);
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function removeBookmark() {
    $.ajax({
        url: "/SocialServlet",
        type: "post",
        data: {"action": "UNBOOKMARK", "comicId": comicId},
        success: function (result) {
            console.log(result);
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function checkLike() {
    $.get("/SocialServlet", {"request": "isLiked", "comicId": comicId})
        .done(function (resp) { // on sucess
            console.log(resp);
            if(resp == "true"){
                // this means the comic has been liked
                console.log("has been liked!");
            } else {
                //comic is not liked
            }             
        })
        .fail(function () { // on failure
            alert("Request failed.");
        });
}

function checkFavorite() {
    $.get("/SocialServlet", {"request": "isFavorited", "comicId": comicId})
        .done(function (resp) { // on sucess
            console.log(resp);
            if(resp == "true"){
                // do something if favorited
            } else {
                //comic is not faved
            }
        })
        .fail(function () { // on failure
            alert("Request failed.");
        });
}

function isSubscribed() {
    $.get("/SocialServlet", {"request": "isSubscribed", "comicId": comicId})
        .done(function (resp) { // on sucess
            if(resp == "true"){
                // do something if subscribed
            } else {
                //comic is not subscribed
            }
        })
        .fail(function () { // on failure
            alert("Request failed.");
        });
}

function numLikes(){
    $.get("/SocialServlet", {"request": "numLikes", "comicId": comicId})
        .done(function (resp) { // on sucess
            console.log(resp);
        })
        .fail(function () { // on failure
            alert("Request failed.");
        });
}

function numFavorites(){
    $.get("/SocialServlet", {"request": "numFavorites", "comicId": comicId})
        .done(function (resp) { // on sucess
            console.log(resp);
        })
        .fail(function () { // on failure
            alert("Request failed.");
        });
}

function getComments(){
    $.get("/SocialServlet", {"request": "getComments", "comicId": "4785074604081152"})
        .done(function (resp) { // on sucess
            for(c in resp){
                console.log(resp[c].description);
                console.log(resp[c].userOrigin);
            }
        })
        .fail(function () { // on failure
            alert("Request failed.");
        });
}

function getRating(){
    $.get("/SocialServlet", {"request": "getRating", "comicId": "4785074604081152"})
        .done(function (resp) { // on sucess
            console.log(resp);
        })
        .fail(function () { // on failure
            alert("Request failed.");
        });
}