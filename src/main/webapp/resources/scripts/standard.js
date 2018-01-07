



// -- Compare Number
function checkBetweenFromAndTo(tagStart,tagEnd,meassage,attribute,valueStart,valueEnd,btnClose) {
    
    if(valueEnd != "" ){
        if(valueStart > valueEnd){
            bootbox.dialog({
                message: meassage,
                title: '<span class="fa fa-info-circle text-warning">&#160;&#160;</span>'+MESSAGE_GLOBAL.message_global_warning,
                buttons: {
                    main: {
                        label: btnClose,
                        className: "btn-danger",
                        callback: function(){
                            tagStart.val("");
                            $.each(attribute,function (index,item) {
                                tagStart.attr(item.id,"");
                            });
                        }
                    }
                }
            });
        }else{
    
            $.each(attribute,function (index,item) {
                tagStart.attr(item.id,item.value);
            });
    
        }
    
    }else{
        $.each(attribute,function (index,item) {
            tagStart.attr(item.id,item.value);
        });
    }

}

// -- Compare String
function checkBetweenFromAndToString(tagStart,tagEnd,meassage,attribute,valueStart,valueEnd,btnClose) {
    
    if(valueStart != "" && valueEnd != "" ){

        var valueForCheck = valueStart.localeCompare(valueEnd);

        if(valueForCheck == -1){
            bootbox.dialog({
                message: meassage,
                title: '<span class="fa fa-info-circle text-warning">&#160;&#160;</span>'+MESSAGE_GLOBAL.message_global_warning,
                buttons: {
                    main: {
                        label: btnClose,
                        className: "btn-danger",
                        callback: function(){
                            tagStart.val("");
                            $.each(attribute,function (index,item) {
                                tagStart.attr(item.id,"");
                            });
                        }
                    }
                }
            });
        }else{

            $.each(attribute,function (index,item) {
                tagStart.attr(item.id,item.value);
            });

        }

    }else{
        $.each(attribute,function (index,item) {
            tagStart.attr(item.id,item.value);
        });
    }

}

// -- Compare Date
function checkBetweenFromAndToDate(tagStart,tagEnd,meassage,valueStart,valueEnd,btnClose,dateForSet) {

    if(valueEnd != "" && valueStart != "" ){
        if(valueStart > valueEnd){
            bootbox.dialog({
                message: meassage,
                title: '<span class="fa fa-info-circle text-warning">&#160;&#160;</span>'+MESSAGE_GLOBAL.message_global_warning,
                buttons: {
                    main: {
                        label: btnClose,
                        className: "btn-danger",
                        callback: function(){
                            tagStart.val("");
                        }
                    }
                }
            });
        }else{
            tagStart.val(moment(dateForSet).format('DD/MM/YYYY'));
        }

    }else{
        tagStart.val(moment(dateForSet).format('DD/MM/YYYY'));
    }

}