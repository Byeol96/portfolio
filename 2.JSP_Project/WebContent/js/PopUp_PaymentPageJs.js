/**
 * 
 */
document.addEventListener("DOMContentLoaded", () => {

	const $button = document.querySelector('#nonData');
	if (!$button) {
		const $costDiv = document.querySelectorAll('#orderMenuTotalCost');
		let totalCost = 0;
		for (let i = 0; i < $costDiv.length; i++) {

			totalCost = totalCost + Number($costDiv[i].textContent);
		}

		const $totalCost = document.querySelector('#totalCost');
		$totalCost.textContent = totalCost + ' 원';

	} else {

		$button.addEventListener('click', () => {
			window.close();
		})
	}
})