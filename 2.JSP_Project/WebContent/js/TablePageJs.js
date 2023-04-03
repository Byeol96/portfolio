/**
 * 
 */
 
 	
	document.addEventListener("DOMContentLoaded", () => { 
		
		const costCalaulation = ()=>{
			
			const $costDiv = document.querySelectorAll('.totalOrderDate');
			let menuDivName;
			let price;
			let totalPrice =0;
			let count;
			for(var l = 0; l<$costDiv.length ; l++){
				
				menuDivName =$costDiv[l].childNodes[1].textContent+'info';
				price = document.querySelector('#'+menuDivName).childNodes[document.querySelector('#'+menuDivName).childNodes.length-1].textContent.replace('원','');
				count = $costDiv[l].childNodes[3].childNodes[3].textContent;
				totalPrice = totalPrice + price*count;
			}
			
			const $totalCostContent = document.querySelector('#totalCostContent');
			$totalCostContent.textContent = totalPrice;
		};
		
		var $menuDiv = document.querySelectorAll('.menuDiv');
		
		const $orderContent = document.querySelector('#orderContent');
		
		var orders = [];
		const menuClick = (event)=>{
	
		
		
			if(event.target.children[3]){
				
				if(orders.includes(event.target.children[3].textContent)) {
					const target1 = document.querySelector('#'+event.target.children[3].textContent)
					target1.parentElement.childNodes[3].childNodes[3].textContent = parseInt(target1.parentElement.childNodes[3].childNodes[3].textContent)+1;
					costCalaulation();
					return;
				}
				orders.push(event.target.children[3].textContent);
				
			}else{
				if(orders.includes(event.target.parentElement.children[3].textContent)) {
					const target2 = document.querySelector('#'+event.target.parentElement.children[3].textContent)
					target2.parentElement.childNodes[3].childNodes[3].textContent = parseInt(target2.parentElement.childNodes[3].childNodes[3].textContent)+1;
					costCalaulation();
					return;
				}
				orders.push(event.target.parentElement.children[3].textContent);
			}
			
			let inHtml;
			
			const i = orders.length -1 ;
			
			let text = '<div class = "totalOrderDate"><div class = "cancel">x</div><div class = "menuName" id="'+orders[i]+'">'+orders[i]+'</div> <div class = "menuQuantityOuter"> <input type="button" value="-" class="minus"/>  <div class = "menuQuantity"> 1 </div> <input type="button" value="+" class="plus"/></div></div>';
			
			if(!$orderContent.innerHTML) {
				$orderContent.innerHTML = text
				costCalaulation();
			}else{
				$orderContent.innerHTML = $orderContent.innerHTML + text;
				costCalaulation();
			}
			
			
			const $plus = document.querySelectorAll('.plus')
			
			const QuantityPlus = (event)=>{
				
				event.target.parentElement.childNodes[3].textContent = parseInt(event.target.parentElement.childNodes[3].textContent)+1;
				costCalaulation();
				console.log('click');
			}
			
			
			for(var j = 0 ; j < $plus.length ; j++ ) $plus[j].addEventListener('click',QuantityPlus);
			
			
			const $minus = document.querySelectorAll('.minus')
			
			const QuantityMinus = (event)=>{
				
				const a = parseInt(event.target.parentElement.childNodes[3].textContent);
				if(a===1) return;
				event.target.parentElement.childNodes[3].textContent = a-1;
				costCalaulation();
				console.log('click');
			}
			for(var k = 0 ; k < $minus.length ; k++ ) $minus[k].addEventListener('click',QuantityMinus);
			console.log(orders);
			
			const $deleteDiv = (event) => {
				
				event.target.parentElement.remove();
				orders = orders.filter(function(data) {
					  return data !== event.target.parentElement.childNodes[1].textContent;
					});
				costCalaulation();
				console.log(orders);
				
				
			}
			
			const $cancel = document.querySelectorAll('.cancel');
			
			for(var n = 0 ; n < $cancel.length ; n++) $cancel[n].addEventListener('click',$deleteDiv);
		}
		
		for (var i = 0; i < $menuDiv.length; i++) {
			
			$menuDiv[i].addEventListener('click',menuClick);
		}
		
		
		$orderButton = document.querySelector('#OrderButton');
		
		
		const orderButtonClick = () =>{
			
			if (document.querySelectorAll('.totalOrderDate').length == 0) return;
			$totalOrderDateDiv = document.querySelectorAll('.totalOrderDate');
			
			let menuNames ='';
			let menuQuantity ='';
			
			for(let i = 0 ; i < $totalOrderDateDiv.length ; i ++){
				
				if(menuNames){
					
					menuNames = menuNames +',';
					menuQuantity = menuQuantity +',';
				}
				menuNames = menuNames + $totalOrderDateDiv[i].childNodes[1].textContent;
				menuQuantity = menuQuantity +$totalOrderDateDiv[i].childNodes[3].childNodes[3].textContent 
				
				
			}
			
			const tableNumber = parseInt(document.querySelector('#tableNumberDiv').textContent);
			
			console.log(menuNames +' , ' +menuQuantity);
			
			window.location.href='ProjectServlet1?command=Order&tableNum='+tableNumber+'&orderMenuNames='
			+menuNames+'&orderMenuQuantity='+menuQuantity;
			
		}
		
		$orderButton.addEventListener('click',orderButtonClick)
		
		const orderListButtonClick = () =>{
			
			const tableNumber = parseInt(document.querySelector('#tableNumberDiv').textContent);
			
			window.open('ProjectServlet1?command=OrderList&tableNum='+tableNumber,'popName',
            'width=800,height=370,top=236,left=295,toolbar=no,menubar=no,scrollbars=no,resizable=no,status=no');			
		}
		const $orderListButton = document.querySelectorAll("button")[0];
		$orderListButton.addEventListener('click',orderListButtonClick)
		
		
		
	}); 
	