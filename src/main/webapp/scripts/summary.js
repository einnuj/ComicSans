/**
 * Created by Guacamole on 4/14/16.
 */

var editTitle = false;
//var editSummary = false;

function editComicTitle() {
    $("#TitleText").html("buttholes");
    if (editTitle == false) {
        // show textbox TitleText
        $("#TitleText").show();
    }
    else { // textbox is visible
        //
        $("#TitleText").hide();
    }
}

function changeCoverImage() {

}

function editComicSummary() {

}

function myFunction() {
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