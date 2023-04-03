/**
 * 
 */
 
 	$(document).ready(function(){
		
		$('.clickDiv').click(function(event){
			$('#content1').hide();
			$('#content2').hide();
			$('#content3').hide();
		    $('#content4').hide();
			$('#content5').hide();
			
			var targetContent;
			var caseObject = event.target.textContent;
			switch(caseObject){
			
			case '단품': 
				$('#content1').show();	
				window.location.href='#content1';
				break;
			case '반반':
				$('#content2').show();
				window.location.href='#content2';
				break;
			case '사시미':
				$('#content3').show();
				window.location.href='#content3';
				break;
			case '면류':
				$('#content5').show();
				window.location.href='#content4';
				break;
			case '튀김':
				$('#content4').show();
				window.location.href='#content5';
				break;
				
			}
			
			
		})
		
	
		
	})