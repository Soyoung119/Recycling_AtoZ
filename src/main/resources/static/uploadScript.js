    var isUserSignedIn = /*[(${session.firstname != null}]]*/ false;

    function uploadImage() {
        if (!isUserSignedIn) {
            alert("Please sign in to upload an image.");
            return false;
        }
        return true;
        }