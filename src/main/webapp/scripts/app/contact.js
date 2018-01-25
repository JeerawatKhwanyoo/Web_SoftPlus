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
        return false;

    }else if(checkmail===false){
        var a =  $("#inputemail").val();
        $('#warning-mail').append('Invalid format. Ex. xxx@xxx.com');
        $('#inputemail').css("border-color", "red");

    }


}

function checkPdfFile() {
    var checkPDF = $('#upload-file-input').val();
    var arr = checkPDF.split(".");
    $('#warning_file').empty();
    $('#warning_size_files').empty();
    // $('#bnsend').prop('disabled', false)
    if(checkPDF){
        var fileSize = $('#upload-file-input')[0].files[0].size;
        // if(fileSize<=3145728){
            if(arr[1]  ==="pdf"&&fileSize<=3145728){
                console.log("PDF file")
                $('#warning_file').empty();
                // $('#bnsend').prop('disabled', false);
                return true;
            }
            // else if(arr[1]===undefined){
            //     console.log("null")
            //     $('#warning_file').empty();
            //
            //     return false;
            // }
            else if(arr[1]!==""){
                console.log("Not PDF File")
                // $('#warning_file').append('Invalid file');
                // $('#warning_size_files').append('Size 3 Mb');
                // $('#bnsend').prop('disabled', true);

                $('#myModalsizefile').modal('show');
                $('#upload-file-input').empty();

                return false;
            }
        // }else{
        //     $('#warning_size_files').append('File size is greater than 3MB');
        //     return false;
        // }


    }else{
        console.log("Not File")
        $('#warning_size_files').empty();
        return false;

    }


}




$(document).ready(function () {


    $('#loader').hide();


    $("#inputemail").focus(function(){
        $('#warning-mail').empty();
        $('#inputemail').css("border-color", "");
    });

        $("#inputemail").on('blur',function(){
            checkMail();
    });

    $("#upload-file-input").on("change", function () {
      checkPdfFile();

    });

    $('#close_modal').click(function () {
        $('#inputname').val('');
        $('#inputemail').val('');
        $('#inputmessage').val('');
        $('#upload-file-input').val('');
        // console.log("test")
        $('#loader').hide();
    });




    $('#closesize').click(function(){
        $('#upload-file-input').val('');

    });


    $('#bnsend').click(function(){


        var checkPDF = $('#upload-file-input').val();
        var arr = checkPDF.split(".");




        var dataname = $('#inputname').val();
        var dataemail = $('#inputemail').val();
        var datamessage = $('#inputmessage').val();


        var file = document.getElementById('upload-file-input').files;
        var formData = new FormData();
        formData.append("name",dataname);
        formData.append("email",dataemail);
        formData.append("msg",datamessage);
        formData.append('file',file[0]);

        var checkname = checkName();
        var checkmail = validateForm();
        var checkfile = checkPdfFile()
        // console.log(check);
        console.log(dataname);
        console.log(dataemail);
        console.log(datamessage);

        if(checkname===false&&checkmail===false&&checkfile===false){
            $('#myModalAll').modal('show');
            return false;
        }else if(checkmail===true&&checkname===false&&checkfile===true){
            $('#myModalName').modal('show');
            return false;
        }
        else if(checkname===true&&checkmail===false&&checkfile===false) {
            if (dataemail === "") {
                $('#myModalAll').modal('show');
            } else {

            }
            return false;
        }
        else if(checkname===true&&checkmail===false&&checkfile===true){
                $('#myModalEmail').modal('show');
                return false;
        }else if(checkfile==false&&checkname===true&&checkmail===true) {
            if (arr[1] === undefined) {
                $('#myModalfile').modal('show');
                return false;
            } else if (arr[1] !== "") {

                return false;
            }
        }else if(checkfile==true&&checkname===false&&checkmail===false){

            $('#myModalAll').modal('show');
            return false;

        }
        else if(checkfile==false&&checkname===false&&checkmail===true){

            $('#myModalName').modal('show');
            return false;

        }

        else if(checkname===true&&checkmail===true&&checkfile===true){
            $('#bnsend').prop('disabled', true);
            $('#loader').show();

            // myFunction();
            $.ajax({
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
                    $('#bnsend').prop('disabled', false);


                },


                async: false
            });
        }
        //
        //     saveData();
        // console.log(data);
        // $('#loader').hide();

    });

});