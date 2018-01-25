
$(document).ready(function () {

    $('#bnen').click(function(){

            URLUtil.changeLanguage($(this).attr('alt'));

    });
    $('#bnth').click(function(){
        URLUtil.changeLanguage($(this).attr('alt'));
    });

});


