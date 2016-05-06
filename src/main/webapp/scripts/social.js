function subscribe(number){
    $.ajax({
        type: "POST",
        url: "/SocialServlet",
        data: {"action": "SUBSCRIBE", "comicId": "5910974510923776"},
        success: function (result) {
            console.log(result);
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function unsubscribe(number){
    $.ajax({
        type: "POST",
        url: "/SocialServlet",
        data: {"action": "UNSUBSCRIBE", "comicId": "5910974510923776"},
        success: function (result) {
            console.log(result);
        },
        error: function (err) {
            console.log(err);
        }
    });
}
function favorite(number){
    $.ajax({
        type: "POST",
        url: "/SocialServlet",
        data: {"action": "FAVORITE", "comicId": "5910974510923776"},
        success: function (result) {
            console.log(result);
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function unfavorite(number){
    $.ajax({
        type: "POST",
        url: "/SocialServlet",
        data: {"action": "UNFAVORITE", "comicId": "5910974510923776"},
        success: function (result) {
            console.log(result);
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function like(number){
    $.ajax({
        type: "POST",
        url: "/SocialServlet",
        data: {"action": "LIKE", "comicId": "5910974510923776"},
        success: function (result) {
            console.log(result);
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function unlike(number){
    $.ajax({
        type: "POST",
        url: "/SocialServlet",
        data: {"action": "UNLIKE", "comicId": "5910974510923776"},
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
        data: {"action": "RATE", "comicId": "5910974510923776", "rating": "3"},
        success: function (result) {
            console.log(result);
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function addComment(number) {
    $.ajax({
        url: "/SocialServlet",
        type: "post",
        data: {"action": "COMMENT", "comicId": "5910974510923776", "comment": "insert comment here"},
        success: function (result) {
            console.log(result);
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function addBookmark(number) {
    $.ajax({
        url: "/SocialServlet",
        type: "post",
        data: {"action": "BOOKMARK", "comicId": "5910974510923776"},
        success: function (result) {
            console.log(result);
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function removeBookmark(number) {
    $.ajax({
        url: "/SocialServlet",
        type: "post",
        data: {"action": "UNBOOKMARK", "comicId": "5910974510923776"},
        success: function (result) {
            console.log(result);
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function checkLike() {
    $.get("/SocialServlet", {"request": "isLiked", "comicId": "5910974510923776"})
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
    $.get("/SocialServlet", {"request": "isFavorited", "comicId": "5910974510923776"})
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
    $.get("/SocialServlet", {"request": "isSubscribed", "comicId": "5910974510923776"})
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
    $.get("/SocialServlet", {"request": "numLikes", "comicId": "5910974510923776"})
        .done(function (resp) { // on sucess
            console.log(resp);
        })
        .fail(function () { // on failure
            alert("Request failed.");
        });
}

function numFavorites(){
    $.get("/SocialServlet", {"request": "numFavorites", "comicId": "5910974510923776"})
        .done(function (resp) { // on sucess
            console.log(resp);
        })
        .fail(function () { // on failure
            alert("Request failed.");
        });
}

function getComments(){
    $.get("/SocialServlet", {"request": "getComments", "comicId": comicId})
        .done(function (resp) { // on success
            if(resp == "null"){
                console.log("no comments");
            } else {
                for(c in resp){
                    console.log(resp[c].description);
                    console.log(resp[c].userOrigin);
                }
            }

        })
        .fail(function () { // on failure
            alert("Request failed.");
        });
}

function getRating(){
    $.get("/SocialServlet", {"request": "getRating", "comicId": comicId})
        .done(function (resp) { // on sucess
            console.log(resp);
        })
        .fail(function () { // on failure
            alert("Request failed.");
        });
}