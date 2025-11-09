// Admin js File
  
// Admin Save & Update
  $(document).ready(function () {
    $(".input-group input").each(function () {
      if ($(this).val().trim() !== "") {
        $(this).parent(".input-group").addClass("is-filled");
      }
    });

    $("#submitBtn").click(function () {
      var adminData = {
        id: $("#adminId").val(),
        adminName: $("#adminName").val(),
        adminEmail: $("#adminEmail").val(),
        adminPassword: $("#adminPassword").val()
      };

      let url = adminData.id ? "/api/update" : "/api/register";

      $.ajax({
        url: url,
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(adminData),
        success: function (response) {
          alert(response);
          location.reload();
        },
        error: function (xhr) {
          alert("Error: " + xhr.responseText);
        }
      });
    });
  });
  
  
