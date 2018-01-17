function initializeMap() {

    var lat = '13.968177'; //Set your latitude.
    var lon = '100.599283'; //Set your longitude.

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


$(document).ready(function () {

    $('#close_modal').click(function () {
        $('#inputname').val('');
        $('#inputemail').val('');
        $('#inputmessage').val('');
        // console.log("test")
    });

    $('#bnsend').click(function(){

        var dataname = $('#inputname').val();
        var dataemail = $('#inputemail').val();
        var datamessage = $('#inputmessage').val();
        // console.log(dataname);
        // console.log(dataemail);
        // console.log(datamessage);
        var data ={"name":dataname,"email":dataemail,"message":datamessage};
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
            $('#myModalEmail').modal('show');
            return false;
        }else if(checkname===true&&checkmail===true){

            $.ajax({

                type: "POST",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                headers: {
                    Accept: "application/json"
                },
                url: "/SPT/contact/saveContact",
                data: JSON.stringify(data),
                complete: function (xhr) {
                    if (xhr.readyState == 4) {

                    }
                    $('.dv-background').hide();
                    dataname = $('#inputname').val('');
                    dataemail = $('#inputemail').val('');
                    datamessage = $('#inputmessage').val('');
                },


                async: false
            });
        }
        //
        //     saveData();
        // console.log(data);

    });

});