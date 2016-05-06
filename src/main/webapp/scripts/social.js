function subscribe(comicId){
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

function unsubscribe(comicId){
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
function favorite(comicId){
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

function unfavorite(comicId){
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

function like(number){
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

function unlike(number){
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
        
        data: {"action": "RATE", "comicId": comicId, "rating": rating},

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
        
        data: {"action": "COMMENT", "comicId": comicId, "comment": comment},

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

        data: {"action": "BOOKMARK", "comicId": comicId},

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
        
        data: {"action": "UNBOOKMARK", "comicId": comicId},

        success: function (result) {
            console.log(result);
        },
        error: function (err) {
            console.log(err);
        }
    });
}


    
function checkLike(comicId) {
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


function checkFavorite(comicId) {
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


function isSubscribed(comicId) {
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


function numLikes(comicId){
    $.get("/SocialServlet", {"request": "numLikes", "comicId": comicId})

        .done(function (resp) { // on sucess
            console.log(resp);
        })
        .fail(function () { // on failure
            alert("Request failed.");
        });
}


function numFavorites(comicId){
    $.get("/SocialServlet", {"request": "numFavorites", "comicId": comicId})

        .done(function (resp) { // on sucess
            console.log(resp);
        })
        .fail(function () { // on failure
            alert("Request failed.");
        });
}

function getComments(comicId){
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

function deleteComment(comicId, num){
    $.ajax({
        url: "/SocialServlet",
        type: "post",
        data: {"action": "DELETECOMMENT", "comicId": comicId, "numComment" : num},
        success: function (result) {
            console.log(result);
        },
        error: function (err) {
            console.log(err);
        }
    });
}


function getRating(comicId){
    $.get("/SocialServlet", {"request": "getRating", "comicId": comicId})
        .done(function (resp) { // on sucess
            console.log(resp);
        })
        .fail(function () { // on failure
            alert("Request failed.");
        });
}