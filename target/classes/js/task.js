$(document).ready(function(){
    $(".btn-delete-task").click(function(){
        var id = $(this).attr("taskid")
        var This = $(this)
        $.ajax({
          method: "GET",
          url: "http://localhost:8083/project_crm/task/delete?id=" + id,
        }).done(function(result) {
            This.closest("tr").remove();
            console.log("Ket qua ", result);
          })
    })
})