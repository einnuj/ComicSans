function subscribe(){
    $.ajax({
        type: "POST",
        url: "/SocialServlet",
        data: {"action" : "SUBSCRIBE", "comicId" : "INSERT ID HERE"},
        success: function(result){
            console.log(result);
        },
        error: function(err){
            console.log(err);
        }
    });
}

function unsubscribe(){
    $.ajax({
        type: "POST",
        url: "/SocialServlet",
        data: {"action" : "UNSUBSCRIBE", "comicId" : "INSERT ID HERE"},
        success: function(result){
            console.log(result);
        },
        error: function(err){
            console.log(err);
        }
    });
}


function favorite(){
    $.ajax({
        type: "POST",
        url: "/SocialServlet",
        data: {"action" : "FAVORITE", "comicId" : "INSERT ID HERE"},
        success: function(result){
            console.log(result);
        },
        error: function(err){
            console.log(err);
        }
    });
}

function unfavorite(){
    $.ajax({
        type: "POST",
        url: "/SocialServlet",
        data: {"action" : "UNFAVORITE", "comicId" : "INSERT ID HERE"},
        success: function(result){
            console.log(result);
        },
        error: function(err){
            console.log(err);
        }
    });
}

function like(){
    $.ajax({
        type: "POST",
        url: "/SocialServlet",
        data: {"action" : "LIKE", "comicId" : "INSERT ID HERE"},
        success: function(result){
            console.log(result);
        },
        error: function(err){
            console.log(err);
        }
    });
}

function unlike(){
    $.ajax({
        type: "POST",
        url: "/SocialServlet",
        data: {"action" : "UNLIKE", "comicId" : "INSERT ID HERE"},
        success: function(result){
            console.log(result);
        },
        error: function(err){
            console.log(err);
        }
    });
}

function addRating() {
    $.ajax({
        url: "/SocialServlet",
        type: "post",
        data: {"action" : "RATE", "comicId" : "INSERT ID HERE", "rating" : "INSERT RATING"},
        success: function(result){
            console.log(result);
        },
        error: function(err){
            console.log(err);
        }
    });
}

function addComment() {
    $.ajax({
        url: "/SocialServlet",
        type: "post",
        data: {"action" : "COMMENT", "comicId" : "INSERT ID", "comment" : "insert comment here"},
        success: function(result){
            console.log(result);
        },
        error: function(err){
            console.log(err);
        }
    });
}

function addBookmark() {
    $.ajax({
        url: "/SocialServlet",
        type: "post",
        data: {"action" : "BOOKMARK", "comicId" : "INSERT ID"},
        success: function(result){
            console.log(result);
        },
        error: function(err){
            console.log(err);
        }
    });
}

function removeBookmark() {
    $.ajax({
        url: "/SocialServlet",
        type: "post",
        data: {"action" : "UNBOOKMARK", "comicId" : "INSERT ID"},
        success: function(result){
            console.log(result);
        },
        error: function(err){
            console.log(err);
        }
    });
}