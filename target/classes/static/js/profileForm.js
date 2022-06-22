$(document).ready(function () {
    var role = $("#product_block").val($("#role").attr("data-role"));

    if (role.val() === "3")
    {
        var num = $("#num_block").val($("#buddIdHo").attr("data-home"));
            $("#home[href*='buddy']").prop('href', '/business/' + num.val())
    }
});