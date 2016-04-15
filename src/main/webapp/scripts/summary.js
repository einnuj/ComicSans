/**
 * Created by Guacamole on 4/14/16.
 */

var editTitle = false;

function changeCoverImage() {

}
function editComicSummary() {

}
function editComicTitle() {
    if (editTitle == true) {
        editTitle = false;
        $("#title-header")
        if ($("#title-text").val() != "") {
            // if it has, reflect the change in the title header
            $("#title-header").html($("#title-text").val());
        }
    } // else just change boolean flag
    else editTitle = true;

    $("#edit-title").toggle(); // toggle visibility of text box either way
}