$(document).ready( function () {
    var table = $('#productTypesTable').DataTable({
        "sAjaxSource": "/productTypes",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData": "id"},
            { "mData": "name" }
        ]
    })
});