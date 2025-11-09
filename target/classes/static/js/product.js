$("#addproduct").click(function (e) {
  e.preventDefault();
  $("#productForm").show(500);
});
$("#closeform").click(function () {
  $("#productForm").hide(500);
});

// Addproduct form
$("#productAddForm").submit(function (e) {
       e.preventDefault();
       var formData = new FormData(this);
       $.ajax({
           url: "/api/products",
           type: "POST",
           data: formData,
           processData: false,
           contentType: false,
           success: function (response) {
               alert("Product added successfully!");
               $("#productForm").hide();
               $("#productAddForm")[0].reset();
           },
           error: function () {
               alert("Failed to add product.");
           }
       });
   });
   
   
// Get All Products
   $(document).ready(function () {
       function fetchAllProducts() {
           $.ajax({
               url: "/api/products",
               method: "GET",
               success: function (products) {
                   let rows = '';
                   products.forEach(function (product) {
                       rows += `
                           <tr>
                               <td class="text-center">
                                   <img src="${product.productImage}" width="100px" height="100px" alt="product">
                               </td>
                               <td class="text-center">
                                   <p class="text-dark font-weight-bold mb-0">${product.productName}</p>
                               </td>
                               <td class="text-center">
                                   <div class="d-flex justify-content-center gap-4 align-items-center">
                                       <button class="btn bg-gradient-success btn-sm editBtn" data-id="${product.id}">Edit</button>
								       <button class="btn bg-gradient-secondary btn-sm deleteBtn" data-id="${product.id}">Delete</button>
                                   </div>
                               </td>
                           </tr>`;
                   });
                   $("#adminAllProducts").html(rows);
               },
               error: function () {
                   alert("Failed to fetch products.");
               }
           });
       }
       fetchAllProducts();
   });  

// Get Product Id for edit
   $(document).on("click", ".editBtn", function () {
       let productId = $(this).data("id");
       console.log("Clicked product ID:", productId);
       
       $.ajax({
           url: `/api/products/${productId}`,
           method: "GET",
           success: function (product) {
               console.log("Fetched product:", product);

               // ✅ Correct IDs
               $("#productId").val(product.id);
               $("#editproductName").val(product.productName);
               $("#editImagePreview").attr("src", product.productImage);
               
               $("#editProductContainer").show(500);
           },
           error: function () {
               alert("Failed to fetch product details.");
           }
       });
   });
   
// Update product
   $("#productEditForm").on("submit", function (e) {
       e.preventDefault();
       
       let formData = new FormData(this);

       $.ajax({
           url: "/api/products/update",
           method: "POST",
           data: formData,
           processData: false,
           contentType: false,
           success: function (response) {
               alert(response);
               $("#editProductContainer").hide();
               fetchAllProducts();
           },
           error: function () {
               alert("Failed to update product.");
           }
       });
   });

   
   $("#closeEditform").click(function () {
     $("#editProductContainer").hide(500);
   });
   
// Delete Product
   $(document).on("click", ".deleteBtn", function () {
       const productId = $(this).data("id");

       if (confirm("Are you sure you want to delete this product?")) {
           $.ajax({
               url: `/api/products/${productId}`,
               method: "DELETE",
               success: function (response) {
                   alert(response);
                   fetchAllProducts();
               },
               error: function () {
                   alert("Failed to delete product.");
               }
           });
       }
   });

  