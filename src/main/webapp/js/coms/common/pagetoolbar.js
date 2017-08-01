//跳转的页面
function toPage(formId,pageIndex){
document.getElementById("pageIndex").value=pageIndex;
mySubmitForm(formId);
}


//首页
function startPage(formId){
document.getElementById("pageIndex").value="1";
mySubmitForm(formId);
}
//末页
function endPage(formId,pageIndex){
document.getElementById("pageIndex").value=pageIndex;
mySubmitForm(formId);
}


function searchPage(formId,pageIndex) {

    $("#pageFooterSearch").unbind('keyup').keyup(function (e) {
        // 兼容FF和IE和Opera
        var theEvent = e || window.event;
        var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
        if (code == 13) {
            toPage(formId,pageIndex) ;
        }
    }) ;

}

