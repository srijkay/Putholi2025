$(document).ready(function() {
	$('.datatables-1').DataTable().destroy();
	$('.datatables-nosort').DataTable().destroy();
	$('.datatables-nosort-scroll').DataTable().destroy();
	$('.datatables-nosort-scroll-paginate').DataTable().destroy();
	$('.datatables-1').DataTable({ "bPaginate": false, "bFilter": false});
	$('.datatables-nosort').DataTable({ "bPaginate": false, sorting: false, "aoColumnDefs": [{ "bSortable": false, "aTargets": [ "_all" ] }], "bFilter" : false });
	$('.datatables-nosort-scroll').DataTable({ "bPaginate": false, sorting: false, "scrollX": true, "aoColumnDefs": [{ "bSortable": false, "aTargets": [ "_all" ] }], "bFilter" : false });
	$('.datatables-nosort-scroll-paginate').DataTable({ sorting: false, "aoColumnDefs": [{ "bSortable": false, "aTargets": [ "_all" ] }], "bFilter" : false });
});
