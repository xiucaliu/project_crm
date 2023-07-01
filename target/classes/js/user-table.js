$(document).ready(function () {
    $(".btn-delete-user").click(function () {
        var id = $(this).attr("userid")
        var This = $(this)
        //var Path = window.location.pathname;

        $.ajax({
            method: "GET",
            url: "http://localhost:8083/project_crm/user/delete?id=" + id,
        }).done(function (result) {
            This.closest("tr").remove();
            console.log("Ket qua ", result);
        })
    })
})