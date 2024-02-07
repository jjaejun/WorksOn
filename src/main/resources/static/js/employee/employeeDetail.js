document.querySelector("#upFile").addEventListener('change', (e) => {
    const profile = document.querySelector("#profile-preview");
    const input = e.target;

    if (input.files && input.files[0]) {
        let reader = new FileReader();
        reader.onload = function(e) {
            profile.src = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
    }
});

document.updateProfile.addEventListener('submit', (e) => {
    e.preventDefault();
    const frm = e.target;

    console.log(`${contextPath}employee/updateProfile.do`);
    console.log(frm.upFile);

   $.ajax({
       url: `${contextPath}employee/updateProfile.do`,
       headers: {
         [csrfHeaderName]: csrfToken
       },
       data: {
           upFile : frm.upFile[0],
       },
       method: 'post',
       success(response){
           console.log(response);

       }
   })
});