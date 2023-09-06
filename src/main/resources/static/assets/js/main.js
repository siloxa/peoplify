$(function () {
    localStorage.setItem('isAvatarLoading', true);
    localStorage.setItem('isNameLoading', true);
    const gender = randomGender();
    const language = randomLanguage();
    avatarLoader();
    nameLoader();
    fetchAvatar(false, gender);
    fetchName(false, gender, language);
});


// This function has been written with the help of Ali https://github.com/ralia79
async function fetchAvatar(alarm, gender) {
    const params = new URLSearchParams({gender});
    await fetch(window.location.origin + '/api/generate/avatar?' + params)
        .then(async res => {
            if (res.status === 200) {
                if (alarm) {
                    swal("Avatar Generated!", 'success');
                }
                await timer(500);
                localStorage.setItem('isAvatarLoading', false);
                await handleFetchAvatarResponse(res);
            } else {
                swal("Something's wrong with server!", 'error');
            }
        })
        .catch(err => {
            swal("Couldn't reach the server!", 'error');
        });
}

async function fetchName(alarm, gender, language) {
    const params = new URLSearchParams({gender, language});
    await fetch(window.location.origin + '/api/generate/name?' + params)
        .then(async res => {
            console.log(res);
            if (res.status === 200) {
                if (alarm) {
                    swal("Name Generated!", 'success');
                }
                await timer(500);
                localStorage.setItem('isNameLoading', false);
                await handleFetchNameResponse(res);
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

async function handleFetchNameResponse(data) {
    const json = await data.json();
    document.getElementById("firstname").value = json.first_name.name;
    document.getElementById("lastname").value = json.last_name.name;
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

function randomGender() {
    let genders = document.getElementById("genders").options;
    genders = [...genders].map(element => element.text);
    genders.shift();
    return genders[Math.floor(Math.random() * genders.length)];
}

function randomLanguage() {
    let genders = document.getElementById("languages").options;
    genders = [...genders].map(element => element.text);
    genders.shift();
    return genders[Math.floor(Math.random() * genders.length)];
}
