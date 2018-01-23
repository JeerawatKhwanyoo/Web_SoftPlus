function initializeMap() {

    var lat = '13.9681754'; //Set your latitude.
    var lon = '100.5992342'; //Set your longitude.

    var centerLon = lon - 0.0105;

    var myOptions = {
        // scrollwheel: false,
        // draggable: false,
        //disableDefaultUI: true,
        center: new google.maps.LatLng(lat, centerLon),
        zoom: 15,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    //Bind map to elemet with id map-canvas
    var map = new google.maps.Map(document.getElementById('map-canvas'), myOptions);
    var marker = new google.maps.Marker({
        map: map,
        position: new google.maps.LatLng(lat, lon),

    });

    var infowindow = new google.maps.InfoWindow({
        content: "Your content goes here!"
    });

    google.maps.event.addListener(marker, 'click', function () {
        infowindow.open(map, marker);
    });

    infowindow.open(map, marker);
}


function validateForm() {
    var x = $('#inputemail').val();

    // var checkName = false;
    var atpos = x.indexOf("@");
    var dotpos = x.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {

        return false;

    }else{
        return true;

    }
}

function checkName() {
    var y = $('#inputname').val();
    var checkname = false;
    if(y===''){
        return false;
    }else{

        return true;
    }
}

function checkMail() {

    var check = $("#inputemail").val();
    var checkmail = validateForm();
    var mail;

    if(check==="") {
        // console.log("Null")

    }else if(checkmail===false){
        var a =  $("#inputemail").val();
        $('p').append('Invalid format. Ex. xxx@xxx.com');
        $('#inputemail').css("border-color", "red");

    }


}

function uploadFile() {

    //
    // $.ajax({
    //     url: "/SPT/contact/uploadFile",
    //     type: "POST",
    //     data: new FormData($("#upload-file-form")[0]),
    //     enctype: 'multipart/form-data',
    //     processData: false,
    //     contentType: false,
    //     cache: false,
    //     success: function () {
    //         // Handle upload success
    //         $("#upload-file-message").text("File succesfully uploaded");
    //     },
    //     error: function () {
    //         // Handle upload error
    //         $("#upload-file-message").text(
    //             "File not uploaded (perhaps it's too much big)");
    //     }
    // });
}




$(document).ready(function () {


    $("#inputemail").focus(function(){
        $('p').empty();
        $('#inputemail').css("border-color", "");
    });

        $("#inputemail").on('blur',function(){
            checkMail();
    });

    // $("#upload-file-form").on("change", uploadFile);

    $('#close_modal').click(function () {
        $('#inputname').val('');
        $('#inputemail').val('');
        $('#inputmessage').val('');
        // console.log("test")
    });

    $('#bnsend').click(function(){
        $('#loader').show();

        var dataname = $('#inputname').val();
        var dataemail = $('#inputemail').val();
        var datamessage = $('#inputmessage').val();


        var file = document.getElementById('upload-file-input').files;
        var formData = new FormData();
        formData.append("name",dataname);
        formData.append("email",dataemail);
        formData.append("message",datamessage);
        formData.append('file',file[0]);

        var checkname = checkName();
        var checkmail = validateForm();
        // console.log(check);
        console.log(dataname);
        console.log(dataemail);
        console.log(datamessage);

        if(checkname===false&&checkmail===false){
            $('#myModalAll').modal('show');
            return false;
        }else if(checkmail===true&&checkname===false){
            $('#myModalName').modal('show');
            return false;
        }else if(checkname===true&&checkmail===false){
            if(dataemail===""){
                $('#myModalEmail').modal('show');
            }else {

            }
            return false;
        }else if(checkname===true&&checkmail===true){

            // myFunction();
            $.ajax({



                // dataType: 'text',
                processData: false,
                contentType: false,
                async:false,
                type: "POST",
                // contentType: "application/json; charset=utf-8",
                dataType: "json",
                headers: {
                    Accept: "application/json"
                },
                url: "/SPT/contact/saveContact",
                data: formData,
                complete: function (xhr) {
                    if (xhr.readyState == 4) {

                    }

                    $('.dv-background').hide();
                    dataname = $('#inputname').val('');
                    dataemail = $('#inputemail').val('');
                    datamessage = $('#inputmessage').val('');
                    $('#upload-file-input').val(null);
                    $('#loader').hide();
                },


                async: false
            });
        }
        //
        //     saveData();
        // console.log(data);

    });

});