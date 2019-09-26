function enviar(id) {
    
      document.getElementById(id).submit();
   }
function enviar(id) {

document.getElementById(id).submit();
 }
  

  $(function () {
    $('.datepicker').datepicker({
        startDate: new Date(),
        autoclose: true
    });
});


$.fn.datepicker.dates['es'] = {
    days: ["Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "VIernes", "Sábado"],
    daysShort: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"],
    daysMin: ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"],
    months: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
    monthsShort: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
    today: "Hoy",
    clear: "Deseleccionar",
    format: "dd/mm/yyyy",
    titleFormat: "MM yyyy", /* Leverages same syntax as 'format' */
    weekStart: 1,
    labelMonthNext: 'Siguiente mes',
	labelMonthPrev: 'Mes anterior',
	labelMonthSelect: 'Selecciona mes',
	labelYearSelect: 'Selecciona año',
	todayBtn: true,
    todayHighlight: true,
    
};

$.fn.datetimepicker.dates['en'] = {
    days: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
    daysShort: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
    daysMin: ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"],
    months: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
    monthsShort: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
    today: "Today"
};

function avisarFestivo() {
	alert("Este día ya es festivo");
}

