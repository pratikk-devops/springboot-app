









// Save Gallery Image
/*$(document).ready(function () {
    $("#galleryAddForm").submit(function (e) {
      e.preventDefault();

      var formData = new FormData(this);

      $.ajax({
        type: "POST",
        url: "/api/gallery",
        data: formData,
        processData: false,
        contentType: false,
        success: function (response) {
          alert(response);
          $("#galleryForm").hide();
          $("#galleryAddForm")[0].reset();
        },
        error: function (xhr, status, error) {
          alert("Image upload failed: " + xhr.responseText);
        }
      });
    });

    // Optional toggle form
    $("#addgallery").click(function (e) {
      e.preventDefault();
      $("#galleryForm").show(500);
    });

    $("#closeGalleryForm").click(function () {
      $("#galleryForm").hide(500);
    });
  });
  
  
  Get All Gallery Images
  function fetchGalleryImages() {
      $.ajax({
        url: "/api/allGallery",
        type: "GET",
        success: function (data) {
          let tableBody = $("#adminAllGallery");
          tableBody.empty(); // Clear existing rows

          data.forEach(function (item) {
            const row = `
              <tr>
                <td class="text-center">
                  <img src="/images/GalleryImages/${item.image}" width="110" height="110" class="img-thumbnail" />
                </td>
                <td class="text-center">
                  <button class="btn bg-gradient-secondary btn-sm" onclick="deleteGalleryImage(${item.id})">Delete</button>
                </td>
              </tr>
            `;
            tableBody.append(row);
          });
        },
        error: function () {
          alert("Failed to load gallery images.");
        }
      });
    }

    // Call fetch function on page load
    $(document).ready(function () {
      fetchGalleryImages();
    });
  
  Delete Gallery Image
  function deleteGalleryImage(id) {
      if (confirm("Are you sure you want to delete this image?")) {
        $.ajax({
          url: "/api/gallery/" + id,
          type: "DELETE",
          success: function () {
            fetchGalleryImages(); // refresh table
          },
          error: function () {
            alert("Failed to delete image.");
          }
        });
      }
    }*/
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  