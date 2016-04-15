var pageArray = new Array();
var img = document.getElementById("comic-page");

pageArray[0] = new Image();
pageArray[0].src = "images/covers/CoConutCover.png";

pageArray[1] = new Image();
pageArray[1].src = "../images/mockpages/1.png";

pageArray[2] = new Image();
pageArray[2].src = "../images/mockpages/2.png";

pageArray[3] = new Image();
pageArray[3].src = "../images/mockpages/3.png";

function nextPage(){
    for(var i = 0; i < pageArray.length; i++)
    {
        if(pageArray[i].src == img.src){
            if(i === pageArray.length - 1){
                img.src = pageArray[0].src;
                break;
            }
            else{
                img.src = pageArray[i+1].src;
                break;
            }
        }
    }
}
function prevPage(){
    var img = document.getElementById("comic-page");

    for(var i = 0; i < pageArray.length; i++)
    {
        if(pageArray[i].src == img.src){
            if(i === 0){
                img.src = pageArray[pageArray.length - 1].src;
                break;
            }
            else{
                img.src = pageArray[i-1].src;
                break;
            }
        }
    }
}
$("body").keydown(function(e) {
    if(e.keyCode == 37) { // left
        prevPage();
    }
    else if(e.keyCode == 39) { // right
        nextPage();
    }
});

function jumpToPage(param){
    img.src = pageArray[param - 1].src;
}