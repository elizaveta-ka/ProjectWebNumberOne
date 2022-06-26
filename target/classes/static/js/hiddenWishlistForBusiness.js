$(document).ready(function () {
    var name2 = $("#product_block").val($("#role").attr("data-role"));
    if (name2.val() === "3")
    {
        var x = document.getElementById("btnAdd")
        x.style.display = "none";
    }
});