$(function () {
  localStorage.setItem('isLoading', true);
  avatarLoader();
  fetchAvatar(false);
});


// This function has been written with the help of Ali https://github.com/ralia79
async function fetchAvatar(alarm) {

  await fetch(window.location.origin + '/api/generate/avatar')
    .then(async res => {
      if (res.status === 200) {
        if (alarm) {
          swal("Avatar Generated!", 'success');
        }
        await timer(1000);
        localStorage.setItem('isLoading', false);
        await handleFetchAvatarResponse(res);
      } else {
        swal("Something's wrong with server!", 'error');
      }
    })
    .catch(err => {
      swal("Couldn't reach the server!", 'error');
    });
}

async function handleFetchAvatarResponse(data) {
  const imageBlob = await data.blob();
  const finalImage = URL.createObjectURL(imageBlob);
  document.getElementById("avatar").src = finalImage;
  var downloadAvatarButton = document.getElementById("download-avatar");
  downloadAvatarButton.href = finalImage;
  downloadAvatarButton.download = "avatar.png";
}

function copy(id) {
  var input = document.getElementById(id);
  try {
    document.execCommand("copy");  // Security exception may be thrown by some browsers.
    swal("Name Copied!", 'success');
  } catch (ex) {
    console.warn("Copy to clipboard failed.", ex);
    swal("Copy to clipboard failed!", 'error');
  }
}

function swal(text, icon) {
  const Toast = Swal.mixin({
    toast: true,
    position: 'top-right',
    iconColor: icon === 'success' ? '#a5dc86' : '#f27474',
    customClass: {
      popup: 'colored-toast'
    },
    showConfirmButton: false,
    timer: 3000,
    timerProgressBar: true
  });
  Toast.fire({
    icon: icon,
    title: text
  });
}
