$(document).ready(function(){
    $(".btn-delete-job").click(function(){
        var id = $(this).attr("jobid")
        var This = $(this)
        $.ajax({
          method: "GET",
          url: "http://localhost:8083/project_crm/job/delete?id=" + id,
        }).done(function(result) {
            This.closest("tr").remove();
            console.log("Ket qua ", result);
          })

    })
})