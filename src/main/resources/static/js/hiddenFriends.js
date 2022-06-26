$(document).ready(function () {
    var name2 = $("#product_block").val($("#role").attr("data-role"));
    if (name2.val() === "3")
    {
        console.log(name2.val())
        var x = document.getElementById("friendAdd")
        x.style.display = "none";
    }
});

//не работает