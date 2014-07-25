/*jslint browser: true*/
/*global  $*/
$(document).ready(function () {
    "use strict";
    $('#timestamp').text(getTimestamp());
    $(".tablesorter").tablesorter();
});

function getTimestamp() {
    var months = new Array("January", "February", "March", "April", "May", "June", "June", "July", "August", "September", "October", "November", "December");
    var days = new Array("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
    var now = new Date();
   
    function getDay() {
        return days[now.getDay()];
    }
   
    function getDate() {
        return months[now.getMonth() + 1] + " " + now.getDate() + ", " + now.getFullYear();
    }
   
    function getTime() {
    	var hours = now.getHours();
    	var minutes = now.getMinutes();
    	var seconds = now.getSeconds();
    	var amOrPm = hours >= 12 ? 'pm' : 'am';
    	hours = hours % 12;
    	hours = hours ? hours : 12; // the hour '0' should be '12'
    	minutes = minutes < 10 ? '0' + minutes : minutes;
    	var time = hours + ':' + minutes + ':' + seconds + ' ' + amOrPm;
    	return time;
    }    
    return getDay() + " | " + getDate() + " | " + getTime();
}