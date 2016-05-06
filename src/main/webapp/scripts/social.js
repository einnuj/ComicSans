
/**
 * Allows current user to subscribe to the comic with id = id_num
 * @param id_num
 */
function subscribe(id_num){
    $.ajax({
        type: "POST",
        url: "/SocialServlet",
        data: {"action": "SUBSCRIBE", "comicId": id_num},
        success: function (result) {
            console.log(result);
        },
        error: function (err) {
            console.log(err);
        }
    });
}

/**
 * Allows the current user to unsubscribe from the comic with id = id_num
 * @param id_num
 */
function unsubscribe(id_num){
    $.ajax({
        type: "POST",
        url: "/SocialServlet",
        data: {"action": "UNSUBSCRIBE", "comicId": id_num},
        success: function (result) {
            console.log(result);
        },
        error: function (err) {
            console.log(err);
        }
    });
}

/**
 * Allows the current user to favorite the comic with id = id_num
 * @param id_num
 */
function favorite(id_num){
    $.ajax({
        type: "POST",
        url: "/SocialServlet",
        data: {"action": "FAVORITE", "comicId": id_num},
        success: function (result) {
            console.log(result);
        },
        error: function (err) {
            console.log(err);
        }
    });
}

/**
 * Allows the current user to unfavorite the comic with id = id_num
 * @param id_num
 */
function unfavorite(id_num){
    $.ajax({
        type: "POST",
        url: "/SocialServlet",
        data: {"action": "UNFAVORITE", "comicId": id_num},
        success: function (result) {
            console.log(result);
        },
        error: function (err) {
            console.log(err);
        }
    });
}

/**
 * Allows the user to like the comic with id = id_num
 * @param id_num
 */
function like(id_num){
    $.ajax({
        type: "POST",
        url: "/SocialServlet",
        data: {"action": "LIKE", "comicId": id_num},
        success: function (result) {
            console.log(result);
        },
        error: function (err) {
            console.log(err);
        }
    });
}

/**
 * Allows the user to unlike the comic with id = id_num
 * @param id_num
 */
function unlike(id_num){
    $.ajax({
        type: "POST",
        url: "/SocialServlet",
        data: {"action": "UNLIKE", "comicId": id_num},
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

function addComment(id_num, text) {
    $.ajax({
        url: "/SocialServlet",
        type: "post",
        data: {"action": "COMMENT", "comicId": id_num, "comment": text},
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
/**
 * Checks to see if the current user likes the comic with the given comicId
 */
function checkLike(id_num) {
    var result;
    jQuery.ajax({
        url: "/SocialServlet",
        type: "get",
        async: false,
        data: {"request": "isLiked", "comicId": id_num},
        success: function (resp) {
            console.log(resp);
            result = resp;
        }
    });
    console.log("result is " + result);
    return result;
}
/**
 * Checks to see if the comic with the given comicId is in the current user's favorites
 */
function checkFavorite(id_num) {
    var result;
    jQuery.ajax({
        url: "/SocialServlet",
        type: "get",
        async: false,
        data: {"request": "isFavorited", "comicId": id_num},
        success: function (resp) {
            console.log(resp);
            result = resp;
        }
    });
    console.log("result is " + result);
    return result;
}
/**
 * Checks to see if the current user is subscribed to the comic with id = id_num
 */
function isSubscribed(id_num) {
    var result;
    jQuery.ajax({
        url: "/SocialServlet",
        type: "get",
        async: false,
        data: {"request": "isSubscribed", "comicId": id_num},
        success: function (resp) {
            console.log(resp);
            result = resp;
        }
    });
    console.log("result is " + result);
    return result;
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

function getCommentsBad(comicId){
    var list = [];
    $.get("/SocialServlet", {"request": "getComments", "comicId": comicId})
        .done(function (resp) { // on success
            if(resp == "null"){
                console.log("no comments");
            } else {
                for(c in resp){
                    list.push(c);
                }
            }

        })
        .fail(function () { // on failure
            alert("Request failed.");
        });
    return list;
}

function getComments(id_num, userIds, timestamp, text) {
    jQuery.ajax({
        url: "/SocialServlet",
        type: "get",
        async: false,
        data: {"request": "getComments", "comicId": id_num},
        success: function (resp) {
            for (c in resp) {
                userIds.push(resp[c].userOrigin);
                timestamp.push(resp[c].timeCreatedMillis);
                text.push(resp[c].description);
            }
        }
    });
    console.log("success???");
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