$(document).ready(function () {
    var name = $(".Buddy_block").val($("#userName").attr("data-userName"));

    var name2 = $("#product_block").val($("#nameUs").attr("data-nameUs"));
    if (name.val() !== name2.val())
    {
        console.log(name2.val())
        console.log(name.val())
        var x = document.getElementById("editBtn")
        x.style.display = "none";
    }
});