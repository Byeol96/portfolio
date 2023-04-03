/**
 * 
 */
document.addEventListener("DOMContentLoaded", () => { 
	

	const fileDOM = document.querySelector('#file');
	const preview = document.querySelector('#preview');
	fileDOM.onchange = () =>{
		
		 const reader = new FileReader();
		  reader.onload = ({ target }) => {
		    preview.src = target.result;
		  };
		  reader.readAsDataURL(fileDOM.files[0]);
	}

	
})


