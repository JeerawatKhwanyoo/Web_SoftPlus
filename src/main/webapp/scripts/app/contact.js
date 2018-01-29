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


// if((int)input==32&&input.length<3){
//     return;
// }

function checkName() {
    var y = $('#inputname').val();
    var checkname = false;
    if(y===''||y.trim()==""){
        return false;
    }else{

        return true;
    }
}

function checkTel() {
    var y = $('#inputtel').val();
    var checktel = false;

    if(y===''||y.trim()==""){
        return false;
    }else {

        return true;
    }
}

function checkMail() {

    var check = $("#inputemail").val();
    var checkmail = validateForm();
    var mail;

    if(check===""||check.trim()=="") {
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
            if(arr[1]  ==="pdf"&&(fileSize<=3145728&&fileSize>0)){
                console.log("PDF file")
                $('#warning_file').empty();
                // $('#bnsend').prop('disabled', false);
                return true;
            }

            else if(arr[1]!==""){
                console.log("Not PDF File")

                $('#myModalsizefile').modal('show');
                $('#upload-file-input').empty();

                return false;
            }


    }else{
        console.log("Not File")
        $('#warning_size_files').empty();
        return false;

    }


}




$(document).ready(function () {

    // $("#inputtel").keydown(function(event) {
    //     // Allow: backspace, delete, tab, escape, enter and .
    //     if ( $.inArray(event.keyCode,[46,8,9,27,13,190]) !== -1 ||
    //         // Allow: Ctrl+A
    //         (event.keyCode == 65 && event.ctrlKey === true) ||
    //         // Allow: home, end, left, right
    //         (event.keyCode >= 35 && event.keyCode <= 39)) {
    //         // let it happen, don't do anything
    //         return;
    //     }
    //     else {
    //         // Ensure that it is a number and stop the keypress
    //         if (event.shiftKey || (event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105 )) {
    //             event.preventDefault();
    //         }
    //     }
    // });


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
        $('#inputtel').val('');
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
        var datatel = $('#inputtel').val();
        var datamessage = $('#inputmessage').val();


        var file = document.getElementById('upload-file-input').files;
        var formData = new FormData();
        formData.append("name",dataname);
        formData.append("email",dataemail);
        formData.append("tel",datatel);
        formData.append("msg",datamessage);
        formData.append('file',file[0]);

        var checkname = checkName();
        var checkmail = validateForm();
        var checktel= checkTel();
        var checkfile = checkPdfFile();

        // console.log(check);
        console.log(dataname);
        console.log(dataemail);
        console.log(datamessage);

        // if(checkname===false&&checkmail===false&&checkfile===false&&checktel===false){
        //     $('#myModalAll').modal('show');
        //     return false;
        // }else if(checkname===false&&checkmail===true&&checkfile===true&&checktel===true){
        //     $('#myModalName').modal('show');
        //     return false;
        // }
        // else if(checkname===true&&checkmail===false&&checkfile===false&&checktel===true) {
        //     if (dataemail === "") {
        //         $('#myModalAll').modal('show');
        //     } else {
        //
        //     }
        //     return false;
        // }
        // else if(checkname===true&&checkmail===false&&checkfile===true&&checktel===true){
        //         $('#myModalEmail').modal('show');
        //         return false;
        // }else if(checkfile==false&&checkname===true&&checkmail===true&&checktel===true) {
        //     if (arr[1] === undefined) {
        //         $('#myModalfile').modal('show');
        //         return false;
        //     } else if (arr[1] !== "") {
        //
        //         return false;
        //     }
        // }else if(checkfile==true&&checkname===false&&checkmail===false&&checktel===false){
        //
        //     $('#myModalAll').modal('show');
        //     return false;
        //
        // }
        // else if(checkfile==false&&checkname===false&&checkmail===true&&checktel===false){
        //
        //     $('#myModalAll').modal('show');
        //     return false;
        //
        // }



     if(checkname===true&&checkmail===true&&checktel===true&&checkfile===true){
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
                    datatel = $('#inputtel').val('');
                    datamessage = $('#inputmessage').val('');
                    $('#upload-file-input').val(null);
                    $('#loader').hide();
                    $('#bnsend').prop('disabled', false);


                },


                async: false
            });
        }else if(checkname===true&&checkmail===true&&checktel===true&&checkfile===false){
            $('#myModalfile').modal('show');
            return false;
        }else if(checkname===true&&checkmail===true&&checktel===false&&checkfile===true){
         $('#myModaltel').modal('show');
         return false;
         }else if(checkname===true&&checkmail===false&&checktel===true&&checkfile===true){
             // $('#myModalEmail').modal('show');
             // return false;

         if (dataemail===""||dataemail.trim()=="") {
             $('#myModalEmail').modal('show');
         } else {

         }
         return false;
     
         }else if(checkname===false&&checkmail===true&&checktel===true&&checkfile===true){
         $('#myModalName').modal('show');
         return false;
         }
         else{
         $('#myModalAll').modal('show');
         return false;
         }





    });

});