$(document).ready(function () {
    var name2 = $("#product_block").val($("#role").attr("data-role"));
    if (name2.val() === "3")
    {
        var x = document.getElementsByClassName("friendAdd");
        for (var i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
    }
});
