$(document).ready(function () {
    var name = $(".Business_block").val($("#userName").attr("data-userName"));

    var name2 = $("#product_block").val($("#nameUs").attr("data-nameUs"));
    if (name.val() !== name2.val())
    {
        var x = document.getElementById("editBtn")
        x.style.display = "none";

        var y = document.getElementById("addNewProduct")
        y.style.display = "none";
    }
});