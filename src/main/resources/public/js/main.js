$(document).ready(function () {

    $("#search-form").submit(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        fire_ajax_submit();

    });
    
    get_statistics();

});

function get_statistics() {
	$.ajax({
		  type: "GET",
		  url: "/api/v1/statistics",
		  cache: false,
		  success: function(data){
			  
			 var data = "Total Short Url: " + data + "";
	         $("#statistics").text(data);
		  }
	});
}

function fire_ajax_submit() {

    var search = {}
    search["url"] = $("#url").val();
  
    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/v1/urls",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>You Short Url:</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);
            
            var html = "<h4>You Short Url</h4><br /><a href='" + data.url + "' target='_blank'>" + data.urlShort + "</a><br />";
            $('#response').html(html);''

            console.log("SUCCESS : ", data);
            $("#btn-search").prop("disabled", false);
            
            get_statistics();

        },
        error: function (e) {

            var json = "<h4>Erro:</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }
    });

}