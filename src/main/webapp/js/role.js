$(document).ready(function(){
    $(".btn-delete-role").click(function(){
        var id = $(this).attr("roleid")
        var This = $(this)
        $.ajax({
          method: "GET",
          url: "http://localhost:8083/project_crm/role/delete?id=" + id,
        }).done(function(result) {
            This.closest("tr").remove();
            console.log("Ket qua ", result);
          })
    })
})