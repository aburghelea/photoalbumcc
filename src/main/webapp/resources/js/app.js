/**
 * Created by aburghelea on 1/8/14.
 */

(function (d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s);
    js.id = id;
    js.src = "//connect.facebook.net/en_US/all.js#xfbml=1&appId=676922152320013";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

function getImages() {
    $.ajax({
        url: "images/small",
        type: "GET",
        success: function (data) {
            var $display = $("#display");
            $display.empty();
            for (var i = 0; i < data.length; i++) {
                console.log(data[i]);
                var image = $("<img/>").attr({
                    src: "data:image/gif;base64," + data[i].value,
                    "data-id": data[i].key,
                    "class": "pull-left",
                    "style": "max-width: 150px; max-height:150px; padding: 5px"
                });

                $(image).on("click", function () {
                    $.ajax({
                        url: "images/original/" + $(this).attr("data-id"),
                        type: 'GET',
                        async: false,
                        success: function (data) {
                            console.log(data);
                            $("#imageOriginal").attr("src", "data:image/jpg;base64,"+data);
                        }

                    });
                    console.log("setting-comments " + $(this).attr("data-id"));
                    var $fbIframe = $("iframe[title='Facebook Social Plugin']");
                    var all = $fbIframe.attr("src");
                    var start = all.substr(0, all.lastIndexOf("idStart"));
                    var end = all.substr(all.lastIndexOf("idEnd") + 6);
                    var user = $("#user").attr("data-user");
                    var photoId = $(this).attr("data-id");

                    var redone = start + "idStart/"+ user+ "/" + photoId+"/idEnd/"+end;
                    $fbIframe.attr("src",redone);
                    $("#display").slideUp("fast");
                    $("#single").slideDown("fast");
                });

                $display.append(image);

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
        $("#single:visible").slideUp("fast");
        $("#upload").slideDown("slow");
        $(this).slideDown("slow");
    });

    $("#showDisplay").on("click", function () {
        console.log("display");
        $("#upload:visible").slideUp("fast");
        $("#slideshow:visible").slideUp("fast");
        $("#single:visible").slideUp("fast");
        $("#display").slideDown("slow", function () {
            getImages();
        });

    });

    $("#showSlideShow").on("click", function () {
        console.log("slide");
        $("#upload:visible").slideUp("fast");
        $("#display:visible").slideUp("fast");
        $("#single:visible").slideUp("fast");
        $("#slideshow").slideDown("slow");
    });

    $("#showSingle").on("click", function () {
        console.log("slide");
        $("#upload:visible").slideUp("fast");
        $("#display:visible").slideUp("fast");
        $("#slideshow:visible").slideUp("fast");
        $("#single").slideDown("slow");
    });

    $("#fileUploader").change(function () {
        var path = $(this).val();
        var name = path.substring(path.lastIndexOf("\\") + 1);
        $("#photoCover").val(name);
    });

    $("#display").slideDown("slow", getImages());

});
