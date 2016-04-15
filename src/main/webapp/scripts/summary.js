/**
 * Created by Guacamole on 4/14/16.
 */

var editTitle = false;
var editSummary = false;

function changeCoverImage() {
    console.log("Tried to change cover image.");
}
function editComicSummary() {
    $("#edit-summary").toggle();
    if (editSummary == false) {
        editSummary = true;
    }
    else {
        $("#summary-paragraph").html($("#summary-text-area").val());
        editSummary = false;
    }

}
function editComicTitle() {
    if (editTitle == true) {
        editTitle = false;
        //$("#title-header")
        if ($("#title-text").val() != "") {
            // if it has, reflect the change in the title header
            $("#title-header").html($("#title-text").val());
        }
    } // else just change boolean flag
    else editTitle = true;

    $("#edit-title").toggle(); // toggle visibility of text box either way
}