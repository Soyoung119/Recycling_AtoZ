
    function CheckForm(){
    var email = document.getElementById("validationEmail").value;
    var password = document.getElementById("validationPassword").value;
    var confirmPassword = document.getElementById("validationConfirmPassword").value;

    if(!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)){
    window.alert("Invalid email");
    showError(document.getElementById("validationEmail"), "Invalid email format.");
    return false;
} else {
    showSuccess(document.getElementById("validationEmail"));
}

    if (password.length < 8 || !/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$/.test(password)) {
    window.alert("Invalid password")
    showError(document.getElementById("validationPassword"), "Invalid password format.");
    return false;
} else {
    showSuccess(document.getElementById("validationPassword"));
}

    if (password !== confirmPassword) {
    window.alert("Invalid Confirm password")
    showError(document.getElementById("validationConfirmPassword"), "Invalid confirmPassword format.");
    return false;
} else {
    showSuccess(document.getElementById("validationConfirmPassword"));
}
return true;
}
    function showError(inputElement, errorMessage) {
        inputElement.classList.add("is-invalid");
        inputElement.classList.remove("is-valid");
    }

    function showSuccess(inputElement) {
        inputElement.classList.remove("is-invalid");
        inputElement.classList.add("is-valid");
    }
