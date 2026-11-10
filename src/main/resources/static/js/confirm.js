let myConfirmDialog = document.getElementById("confirmDelete");
myConfirmDialog.addEventListener('show.bs.modal', function (event) {
    let button = event.relatedTarget;
    let link = button.getAttribute('data-bs-link');
    let id = button.getAttribute('data-bs-id');
    let text = button.getAttribute('data-bs-text');
    let modalTitle = document.getElementById('staticWarningLabel');
    modalTitle.textContent  = 'Delete: ' + text + ' (' + id + ')';
    let modalText = myConfirmDialog.querySelector('.modal-text');
    modalText.textContent  = text + ' (' + id + ')';
    let deleteButton = myConfirmDialog.querySelector('.delete-button');
    deleteButton.setAttribute('href', link)
});