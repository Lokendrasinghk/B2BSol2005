// Load Datepicker on this class
  $('#datetimepicker1').datetimepicker({
	  
  });
  
  // Cart Dropdown Hover    
	   $("#nav .dropdown").hover(
		function() {
			$('#products-menu.dropdown-menu', this).stop( true, true ).fadeIn("fast");
			$(this).toggleClass('open');
		},
		function() {
			$('#products-menu.dropdown-menu', this).stop( true, true ).fadeOut("fast");
			$(this).toggleClass('open');
		});

 // Pagerefresh on Cartpopup close		
		function pageRefresh(){
			$('#cboxClose').on("click", function(){
				location.reload();
			});
		};
		setInterval(pageRefresh, 1000);
		
 // Overlay on page load in add ticket page	
		 $('#addTicket').click(function(){
			if ( $('#createTicket-subject').val() != '' &&  $('#createTicket-message').val() != '') {
			$('body').append("<div id='overlayDiv'><div class='spinner'></div></div>");
			}
		}); 
		
		$('#updateTicket').on('click', function(){
			$('body').append("<div id='overlayDiv'><div class='spinner'></div></div>");
		});

		
		$(function() {
		$("#picture-frame").zoomToo({
		  magnify: 1
		   });
        });

