let dragTemp;

$('.drag').on('dragstart',function(e){
   dragTemp = e.target;
   console.log('dragStart',dragTemp);
})

$('.drop').on('dragover', function(e){
   e.preventDefault();
})


$('#dp1').on('drop',function(){
   this.appendChild( dragTemp )
})

$('#dp2').on('drop',function(){
   this.appendChild( dragTemp )
   $('#dp2').children('.drag').each(function() {
      console.log(this.innerText);
   })
})

$('#dp3').on('drop',function(){
   this.appendChild( dragTemp )
   $('#dp3').children('.drag').each(function() {
      console.log(this.innerText);
   })
})



// const dragables = document.querySelectorAll(".task");
// const dropables = document.querySelectorAll(".swim-lane");
//
// dragables.forEach((task) => {
//    task.addEventListener('dragstart', () => {
//       task.classList.add('dragging');
//    });
//    task.addEventListener('dragend', () => {
//       task.classList.remove('dragging');
//    });
// });
//
// dropables.forEach((zone) => {
//    zone.addEventListener('dragover', (e) => {
//       e.preventDefault();
//
//       const bottomTask = insertAboveTask(zone, e.clientY);
//       const curTask = document.querySelector(".is-dragging");
//
//       if(!bottomTask) {
//          zone.appendChild(curTask);
//       } else {
//          zone.insertBefore(curTask, bottomTask);
//       }
//
//    });
// });
//
// const insertAboveTask = (zone, mouseY) => {
//    const els = zone.querySelectorAll(".task:not(.is-dragging)")
//
//    let closestTask = null;
//    let closestOffset = Number.NEGATIVE_INFINITY;
//
//    els.forEach((task) => {
//       const { top } = task.getBoundingClientRect();
//       const offset = mouseY - top;
//
//       if(offset < 0 && offset < closestOffset){
//          closestOffset = offset;
//          closestTask = task;
//       }
//
//    });
//
//    return closestTask;
// }