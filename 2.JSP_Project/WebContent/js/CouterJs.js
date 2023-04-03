/**
 * 
 */
 
 $(document).ready(function(){
		let $DataTd = document.querySelectorAll(".DataTd");
		let tableNumbers = new Array();
		for(let i =0 ; i <$DataTd.length ; i++){
			tableNumbers.push($DataTd[i].childNodes[1].textContent.replace('\n','').trim());	
		}
		console.log(tableNumbers);
		
		$('.DataTd').on('click',function(event){
			
			if(IngFunction) return;
			
			console.log(event.target);
			let checkTd = event.target;
			let tdIndex = checkTd.cellIndex;
			let trIndex = checkTd.parentElement.rowIndex; 
			while(![0,1,2,3].includes(trIndex)){
				checkTd = checkTd.parentElement;
				console.log(checkTd);
				tdIndex = checkTd.cellIndex;
				trIndex = checkTd.parentElement.rowIndex;
			}
			
			let tableNumberDiv = checkTd.childNodes[1].textContent
			let tableNumber = tableNumberDiv.substr(tableNumberDiv.lastIndexOf('\t')+1);
			console.log(tableNumber);
			window.open('ProjectServlet1?command=PayMentPage&tableNumber='+tableNumber,'popName',
            'width=800,height=370,top=236,left=295,toolbar=no,menubar=no,scrollbars=no,resizable=no,status=no');
			
			
		})
		let addClick = 0;
		let IngFunction = '';
		$('#addTable').click(function(){
			
			if(IngFunction !== 'addTable' && IngFunction) return;
			if(++addClick%2 == 0) {
				
				IngFunction = '';
				$('.NonDataTd').off('click');
				$('.DataTd').css('opacity',1);
				$('.NonDataTd').css('opacity',0);
				$('.NonDataTd').off('mouseenter');
				$('.NonDataTd').off('mouseleave');
				return;
			}
			
			
			IngFunction = 'addTable';
			$('.DataTd').css('opacity',0.5);
			$('.NonDataTd').css('opacity',1);
			
			$('.NonDataTd').mouseenter(function(target){
				
				$(this).css('background-image','url(img/check.png)').css('background-size','107px 140px');
			})
			$('.NonDataTd').mouseleave(function(target){
				
				$(this).css('background-image','');
			})
			
			$('.NonDataTd').on('click',function(event){
				
				let tableNumber = prompt("테이블 번호를 입력해 주세요");
				let checkTd = event.target;
				
				let tdIndex = checkTd.cellIndex;
				let trIndex = checkTd.parentElement.rowIndex;
				window.location.href='ProjectServlet1?command=TableAdd&tableNum='+tableNumber+'&tdIndex='+tdIndex+'&trIndex='+trIndex; 
			})

		})
		
		let deleteClick = 0;
		
		
		$('#deleteTable').click(function(){
			
			if(IngFunction !== 'deleteTable' && IngFunction) return;
			if(++deleteClick%2 == 0) {
				
				IngFunction = '';
				$('.DataTd').off('click');
				$('.DataTd').off('mouseenter');
				$('.DataTd').off('mouseleave');
				return;
			}
			
			IngFunction = 'deleteTable';
			$('.DataTd').mouseenter(function(target){
				
				$(this).css('background-image','url(img/xx.png)').css('background-size','107px 140px');
			})
			
			$('.DataTd').mouseleave(function(target){
				
				$(this).css('background-image','');
			})
			
			$('.DataTd').on('click',function(event){
				
			
			let checkTd = event.target;
			let tdIndex = checkTd.cellIndex;
			let trIndex = checkTd.parentElement.rowIndex; 
			while(!tdIndex){
				checkTd = checkTd.parentElement;
				tdIndex = checkTd.cellIndex;
				trIndex = checkTd.parentElement.rowIndex;
			}
			window.location.href='ProjectServlet1?command=TableDelete&tdIndex='+tdIndex+'&trIndex='+trIndex;
			})
			
			
		})
		
		let changeClick = 0;
		
		$('#moveTable').click(function(){
			
			if(IngFunction !== 'moveTable' && IngFunction) return;
			if(++changeClick%2 == 0) {
				
				IngFunction = '';
				$('.DataTd').off('click');
				$('.NonDataTd').off('click');
				$('.DataTd').off('mouseenter');
				$('.DataTd').off('mouseleave');
				return;
			}
			
			$('.DataTd').mouseenter(function(target){
				
				$(this).css('background-image','url(img/check.png)').css('background-size','107px 140px');
			})
			
			$('.DataTd').mouseleave(function(target){
				
				$(this).css('background-image','');
			})
			
			IngFunction = 'moveTable';
			let DataTdClick = 0;
			
			let bcheckTd;
			let btdIndex;
			let btrIndex;
			
			
			$('.DataTd').on('click',function(event){
				
				/* if(++DataTdClick %2 === 0){
					$(this).css('background-image','');
					$('.NonDataTd').off('click');
					return
				} */
				$('.DataTd').off('mouseenter');
				$('.DataTd').off('mouseleave');
				
				$('.DataTd').css('opacity',0.5);
				$('.NonDataTd').css('opacity',1);
				
				bcheckTd = event.target;
				btdIndex = bcheckTd.cellIndex;
				btrIndex = bcheckTd.parentElement.rowIndex;
				while(!btdIndex){	
					bcheckTd = bcheckTd.parentElement;
					btdIndex = bcheckTd.cellIndex;
					btrIndex = bcheckTd.parentElement.rowIndex;
				}
				
				$('.NonDataTd').mouseenter(function(target){
					
					$(this).css('background-image','url(img/check.png)').css('background-size','107px 140px');
				})
				$('.NonDataTd').mouseleave(function(target){
					
					$(this).css('background-image','');
				})
			
				$('.NonDataTd').on('click',function(event){
				
					let checkTd = event.target;		
					let tdIndex = checkTd.cellIndex;
					let trIndex = checkTd.parentElement.rowIndex;
					
					window.location.href=
						'ProjectServlet1?command=TableMove&tdIndex='+tdIndex+'&trIndex='+trIndex+
							'&btdIndex='+btdIndex+'&btrIndex='+btrIndex; 
				})
			
			})
	
		})
		
		let changeTable = 0;
		$('#changeTable').click(function(){
			
			if(IngFunction !== 'changeTable' && IngFunction) return;
			if(++deleteClick%2 == 0) {
				
				IngFunction = '';
				$('.DataTd').off('click');
				$('.DataTd').off('mouseenter');
				$('.DataTd').off('mouseleave');
				return;
			}
			
			IngFunction = 'changeTable';
			$('.DataTd').mouseenter(function(target){
				
				$(this).css('background-image','url(img/check.png)').css('background-size','107px 140px');
			})
			
			$('.DataTd').mouseleave(function(target){
				
				$(this).css('background-image','');
			})
			
			$('.DataTd').click(function(event){
					let tableNum	
				while(true){
					tableNum = Number(prompt('바꾸실 테이블 번호를 입력하세요'));
					if(!Number.isInteger(tableNum)){
						alert('정수가 아닙니다. 다시입력해주세요');
						continue;
					}
					if(tableNumbers.includes(tableNum)) {
						alert('이미 존재하는 테이블 번호입니다 다시입력해주세요');
						continue;	
					}
					if(tableNum>32){
						alert('테이블 번호는 32를 넘을 수 없습니다. 다시입력해주세요');
						continue;
					}
					break;
				}
	
				let checkTd = event.target;
				let tdIndex = checkTd.cellIndex;
				let trIndex = checkTd.parentElement.rowIndex;
				if(!tdIndex){
					checkTd = checkTd.parentElement;
					tdIndex = checkTd.cellIndex;
					trIndex = checkTd.parentElement.rowIndex;
				}
				window.location.href='ProjectServlet1?command=TableChange&tdIndex='+tdIndex+'&trIndex='+trIndex+'&tableNum='+tableNum;
			})
		})
		
		$('#addMenu').click(function(){
			
			window.open('ProjectServlet1?command=PopUp_AddMenu_Move','popName',
            'width=800,height=370,top=236,left=295,toolbar=no,menubar=no,scrollbars=no,resizable=no,status=no');
		})
		
		$('#deleteMenu').click(function(){
			
			window.open('ProjectServlet1?command=PopUp_DeleteMenu_Move','popName',
            'width=800,height=370,top=236,left=295,toolbar=no,menubar=no,scrollbars=no,resizable=no,status=no');
		})
		$('#updateMenu').click(function(){
			
			window.open('ProjectServlet1?command=PopUp_UpdateMenu_Move','popName',
            'width=800,height=370,top=236,left=295,toolbar=no,menubar=no,scrollbars=no,resizable=no,status=no');
		})
		$('#soldOutMenu').click(function(){
			
			window.open('ProjectServlet1?command=PopUp_SoldOutMenu_Move','popName',
            'width=800,height=370,top=236,left=295,toolbar=no,menubar=no,scrollbars=no,resizable=no,status=no');
		})
		$('#cashUp').click(function(){
			
			window.open('ProjectServlet1?command=PopUp_CashUp','popName',
            'width=800,height=370,top=236,left=295,toolbar=no,menubar=no,scrollbars=no,resizable=no,status=no');
		})
	})
	