/**
 * 
 */
 


document.addEventListener("DOMContentLoaded", () => { 
	const $select = document.querySelector('select');
	const $menus = document.querySelector('#menus');
	const $aa = document.querySelectorAll('table');
	const selectChange = () => {
			
		for(let i = 0; i < $aa.length ; i++){ 
			$aa[i].style.display = 'none';
		}
		$aa[$select.value-1].style.display = 'block';
	}
	
	$select.onchange=selectChange;
	
	function getCheckboxValue(event)  {
		  let result = '';
		  if(event.target.checked)  {
		    result = event.target.value;
		  }else {
		    result = '';
		  }
		  
		 
		  }
	function reConfirm(){
		
		let confirmMessage='';		
		let result;
		
		const $input = document.querySelectorAll('input');
		for(let i = 0; i < $input.length ; i++ ){
			
			if($input[i].checked){
				
				if (!confirmMessage){
					confirmMessage = confirmMessage +  $input[i].value;
				}else{
					confirmMessage = confirmMessage +  ', '+$input[i].value;
				}
			}
		}
		if(confirmMessage) confirmMessage  = confirmMessage +'을(를) 메뉴에서 삭제하시겠습니까?';
		result = confirm(confirmMessage);
		if(result){
			
			document.querySelector('form').submit();
		}else{
			
			return false;
		}
		
		
	}
	
	document.querySelector('#submitA').onclick = reConfirm;
	
	
})
