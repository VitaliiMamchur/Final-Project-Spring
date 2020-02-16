function confirmDelete(idToDelete) {
    var modal = document.getElementById('myModal');
    modal.style.display = "block";
    $("#deleteForm").val(idToDelete);
    $('#deleteForm').attr('action', '/decliningmanagerlist/'+idToDelete);
}

function cancel() {
    var modal = document.getElementById('myModal');
    modal.style.display = "none";
};