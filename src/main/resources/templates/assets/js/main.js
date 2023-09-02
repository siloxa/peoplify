$(function() {
   fetchAvatar();
});

function fetchAvatar() {
    const req = new XMLHttpRequest();
    req.getResponseHeader('Content-Type', 'image/png');
    const url = new URL(window.location.origin + '/api/generate/avatar');
    url.searchParams.set('size', '224');
    req.open("GET", url, true);
    req.responseType = "arraybuffer";

    req.onload = (event) => {
      const data = req.response;
      handleFetchAvatarResponse(data);
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
    input.select();
    try {
        return document.execCommand("copy");  // Security exception may be thrown by some browsers.
    } catch (ex) {
        console.warn("Copy to clipboard failed.", ex);
        return prompt("Copy to clipboard: Ctrl+C, Enter", text);
    }
}

