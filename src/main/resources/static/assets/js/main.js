function refreshAvatar() {
    const req = new XMLHttpRequest();
    req.getResponseHeader('Content-Type', 'image/png');
    const url = new URL(window.location.origin + '/api/generate/avatar');
    url.searchParams.set('size', '224');
    req.open("GET", url, true);
    req.responseType = "arraybuffer";

    req.onload = (event) => {
      const data = req.response;
      handleRefreshAvatarResponse(data);
    };

    req.send();
}

function handleRefreshAvatarResponse(data) {
    const unit8Array = new Uint8Array(data);
    const prepareToEncode = String.fromCharCode.apply(null, unit8Array);
    const dataInBase64 = btoa(prepareToEncode);
    document.getElementById("avatar").src = "data:image/png;base64," + dataInBase64;
}

