/**
 * Created by aburghelea on 1/8/14.
 */

function getImages() {
    $.ajax({
        url: "images/small",
        type: "GET",
        success: function (data) {
            $("#display").empty();
            for (var i = 0; i < data.length; i++) {
                console.log(data[i]);
                var image = $("<img/>").attr({
                    src : "data:image/gif;base64," + data[i].value,
                    "data-id" : data[i].key,
                    "class": "pull-left",
                    "style": "max-width: 150px; max-height:150pox; padding: 5px"
                });

                $("#display").append(image);

            }
        },
        fail: function () {
            console.log("no images");
        }
    })

}
function openUploader() {
    $('input[id=fileUploader]').click();
}
$(document).ready(function () {

    $("#showUploader").on("click", function () {
        console.log("uploader");

        $("#display:visible").slideUp("fast");
        $("#slideshow:visible").slideUp("fast");
        $("#upload").slideDown("slow");
        $(this).slideDown("slow");
    });

    $("#showDisplay").on("click", function () {
        console.log("display");
        $("#upload:visible").slideUp("fast");
        $("#slideshow:visible").slideUp("fast");
        $("#display").slideDown("slow", function () {
            getImages();
        });

    });

    $("#showSlideShow").on("click", function () {
        console.log("slide");
        $("#upload:visible").slideUp("fast");
        $("#display:visible").slideUp("fast");
        $("#slideshow").slideDown("slow");
    });

    $("#fileUploader").change(function () {
        var path = $(this).val();
        var name = path.substring(path.lastIndexOf("\\") + 1);
        $("#photoCover").val(name);
    });

    $("#display").slideDown("slow", getImages());

});
