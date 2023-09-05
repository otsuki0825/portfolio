/* globals Chart:false */
(() => {

var label1 = [];
var label2 = [];
var label3 = [];

var date = "";
var event = "";
var kg = -1;
var reps = -1;

const canvas = document.getElementById('myChart');
const table = document.getElementById('targetTable');
for (let row of table.rows) {
	if(row.cells[0].innerText == date && row.cells[1].innerText == event){

		if(parseInt(row.cells[2].innerText) > kg){
			label2.pop();
			kg = row.cells[2].innerText;
			kg = parseInt(kg);
			label2.push(kg);

		}

		if(parseInt(row.cells[3].innerText) > reps){
			label3.pop();
			reps = row.cells[3].innerText;
			reps = parseInt(reps);
			label3.push(reps);

		}

	}else{
		kg = -1;
		reps = -1;

		event = row.cells[1].innerText;

		date = row.cells[0].innerText;
		label1.push(date + ":" + event);

		if(parseInt(row.cells[2].innerText) > kg){
			kg = row.cells[2].innerText;
			kg = parseInt(kg);
			label2.push(kg);

		}

		if(parseInt(row.cells[3].innerText) > reps){
			reps = row.cells[3].innerText;
			reps = parseInt(reps);
			label3.push(reps);
		}
	}
}
const myChart = new Chart(canvas, {
	type: 'bar',
    data: {
    	   labels: label1,
    	   datasets: [
    	     {
    	      label: 'kg',
    	      data:label2,
    	      backgroundColor:'#aaf'
    	      },
    	      {
    	       label: 'reps',
        	   data:label3,
        	     backgroundColor:'#faa'
        	   }
    	     ]
    },
    scales: {
          kg: {
        	  suggestedMax: 1000,
			  suggestedMin: 0,
           ticks: {
		          stepSize: 5,

                }
          },
	      reps: {
	  	      suggestedMax: 1000,
			  suggestedMin: 0,
	          ticks: {
		             stepSize: 5,
				}
           }
	   }

    }
  )
})()
