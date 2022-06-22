$(function () {
    $('#submitButton').click(function (e) {


        e.preventDefault();


        $('input').next('span').remove();

        $.post({
            url: '/addReview',
            data: $('#productReviewForm').serialize(),
            success: function (res) {
                if (res.validated) {
                    //В алерт надо вставить переход обратно на страницу продукта по id
                    alert("Review added");
                } else {
                    $.each(res.errorMessages, function (key, value) {
                        $('input[name=' + key + ']').after('<span class="error">' + value + '</span>');
                    });
                }
            }
        })
    });

});