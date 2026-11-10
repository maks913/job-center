const educationDialog = document.getElementById("addEducationModal");
educationDialog.addEventListener('show.bs.modal', function (event) {
    const educationType = document.getElementById("educationType");
    const specialty = document.getElementById("specialty-div");

    educationType.addEventListener("change", function (event) {

        if (educationType.value.toLowerCase() === "secondary") {

            specialty.setAttribute('hidden', '')

        } else {

            specialty.removeAttribute('hidden');

        }

    })

})