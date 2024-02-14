const dragables = document.querySelectorAll(".task");
const dropables = document.querySelectorAll(".swim-lane");

dragables.forEach((task) => {
   task.addEventListener('dragstart', () => {
      task.classList.add('is-dragging');
   });
   task.addEventListener('dragend', () => {
      task.classList.remove('is-dragging');
   });
});

dropables.forEach((zone) => {
   zone.addEventListener('dragover', () => {

   });
});
