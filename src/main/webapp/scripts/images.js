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