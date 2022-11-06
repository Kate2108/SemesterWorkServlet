document.addEventListener('DOMContentLoaded', function() {

    button = document.getElementById('logOut');

    function confirmLogOut() {
        if (confirm('Are you sure you want to log out?')) {
            window.location.replace('http://localhost:8080/my-website/logout');
        } else {
            window.location.replace('http://localhost:8080/my-website/profile');
        }
    }
    if (button) {
        button.addEventListener('click', confirmLogOut);
    }
});
