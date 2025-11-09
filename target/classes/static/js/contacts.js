
//	Save User Query
$(document).ready(function() {
  // Only allow digits while typing
  $("#phone").on("input", function () {
    this.value = this.value.replace(/\D/g, ""); // Remove non-digits
  });

  $("#contactForm").submit(function(e) {
    e.preventDefault();

    const phone = $("#phone").val();

    if (!/^\d{10}$/.test(phone)) {
      $("#phoneError").show();
      return;
    } else {
      $("#phoneError").hide();
    }

    const contactData = {
      fullName: $("#fullName").val(),
      email: $("#email").val(),
      mobileNo: phone,
      message: $("#message").val()
    };

    $.ajax({
      url: "/api/contact",
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify(contactData),
      success: function(response) {
        alert("Message sent successfully!");
        $("#contactForm")[0].reset();
      },
      error: function() {
        alert("Something went wrong!");
      }
    });
  });
});

//	Get ALl User Query
$(document).ready(function () {
    fetchAllContacts();

    // Fetch all contact data
    function fetchAllContacts() {
        $.ajax({
            url: "/api/allUserQuery",
            type: "GET",
            success: function (contacts) {
                let html = "";

                contacts.forEach(function (contact) {
                    html += `
                        <tr>
                            <td class="text-center">${contact.fullName}</td>
                            <td class="text-center">${contact.message}</td>
                            <td class="text-center">${contact.email}</td>
                            <td class="text-center">${contact.mobileNo}</td>
                            <td class="text-center">
                                <button class="btn bg-gradient-success btn-sm replyBtn"
                                    data-id="${contact.id}"
                                    data-name="${contact.fullName}"
                                    data-email="${contact.email}"
                                >Reply</button>
                                <button class="btn bg-gradient-secondary btn-sm" onclick="deleteContact(${contact.id})">Delete</button>
                            </td>
                        </tr>
                    `;
                });

                $("#allUserQuery").html(html);

                // Attach click handler to reply buttons (after DOM update)
                $(".replyBtn").click(function () {
                    const id = $(this).data("id");
                    const name = $(this).data("name");
                    const email = $(this).data("email");

                    openReplyForm(id, name, email);
                });
            },
            error: function () {
                alert("Failed to fetch contacts.");
            }
        });
    }

    // Close reply form
    $("#closeReplayform").click(function () {
        $("#replayContainer").hide();
    });

    // Submit reply form
    $("#userReplayForm").submit(function (e) {
        e.preventDefault();

        const formData = {
            id: $("#id").val(),
            fullName: $("#fullName").val(),
            toEmail: $("#toEmail").val(),
            message: $("#message").val()
        };

        $.ajax({
            url: "/api/replyUser",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(formData),
            success: function () {
                alert("Reply sent successfully!");
                $("#userReplayForm")[0].reset();
                $("#replayContainer").hide();
            },
            error: function () {
                alert("Error sending reply.");
            }
        });
    });
});

// Function to populate and show reply form
function openReplyForm(id, fullName, email) {
    $("#id").val(id);
    $("#fullName").val(fullName);
    $("#toEmail").val(email);
    $("#replayContainer").show();
}

//	Delete User Query
function deleteContact(id) {
    if (confirm("Are you sure you want to delete this contact?")) {
        $.ajax({
            url: "/api/contact/" + id,
            type: "DELETE",
            success: function() {
                alert("Contact deleted successfully!");
                location.reload(); // or re-fetch data using fetchAllContacts()
            },
            error: function() {
                alert("Delete failed.");
            }
        });
    }
}


















