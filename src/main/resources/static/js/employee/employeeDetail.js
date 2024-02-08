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

document.querySelector("#saveProfile").addEventListener('click', (e) => {
    const input = document.querySelector("#upFile");
    const frm = document.updateProfile;
    console.log(frm);
    const frmData = new FormData(frm);

    if(input.value === '') return;


   $.ajax({
       url: `${contextPath}employee/updateProfile.do`,
       headers: {
         [csrfHeaderName]: csrfToken
       },
       data: frmData,
       processData: false,
       contentType: false,
       method: 'post',
       success(response){
           alert(response);
           input.value = '';
       }
   })
});