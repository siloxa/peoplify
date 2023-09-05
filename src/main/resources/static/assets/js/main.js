$(function() {
   fetchAvatar(false);
});

function fetchAvatar(alarm) {
    const req = new XMLHttpRequest();
    req.getResponseHeader('Content-Type', 'image/png');
    const url = new URL(window.location.origin + '/api/generate/avatar');
    req.open("GET", url, true);
    req.responseType = "arraybuffer";

    req.onload = (event) => {
      const data = req.response;
      if(req.status == 200) {
        if(alarm) {
            swal("Avatar Generated!", 'success');
        }
        handleFetchAvatarResponse(data);
      } else {
        swal("Something's wrong with server!", 'error');
      }
    };

    req.onerror = (event) => {
        swal("Couldn't reach the server!", 'error');
    };

    req.send();
}

function handleFetchAvatarResponse(data) {
    const unit8Array = new Uint8Array(data);
    const prepareToEncode = String.fromCharCode.apply(null, unit8Array);
    const dataInBase64 = btoa(prepareToEncode);
    document.getElementById("avatar").src = "data:image/png;base64," + dataInBase64;
    var downloadAvatarButton = document.getElementById("download-avatar");
    downloadAvatarButton.href = "data:image/png;base64," + dataInBase64;
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
