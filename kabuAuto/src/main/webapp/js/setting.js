// JavaScript Document

var countNo=1;
var maxItem=4;
var slWidth = 960;
var slSpeed = 600;
var slInterval = 5000;

//slider function
function scrollSlider(){
	countNo++;
	if(countNo > maxItem){ countNo=1;}
	var leftPixcels = -slWidth*(countNo-1);
	 jQuery("#sliderItem").animate(
	{"left":leftPixcels+"px"},slSpeed);
}

jQuery(document).ready(function(){

	jQuery("a[href^=#]").click(function(){
		var Hash = jQuery(this.hash);
		var HashOffset = jQuery(Hash).offset().top;
		jQuery("html,body").animate({
			scrollTop: HashOffset
		}, 1000);
		return false;
	});
	
	init = setInterval("scrollSlider()", slInterval);

	$("#infoAll .section .inner:first-child").css("border-top", "none");
	$("#infoAll .section .inner:first-child").css("margin", "0");

});

$('.navi ul li:first-child').corner("left 8px");
$('.navi ul li:first-child a').corner("left 8px");
$('.navi ul li:last-child').corner("right 8px");
$('.navi ul li:last-child a').corner("right 8px");
$('#banner1').corner("8px");
$('#banner1 a').corner("7px");
$('#banner2').corner("8px");
$('#banner2 a').corner("7px");
$('#banner3').corner("8px");
$('#banner3 a').corner("7px");
$('.subMidashi').corner("8px");
$('#feature').corner("8px");
$('#feature02').corner("7px");
$('#feature .linkMenu a').corner("16px");

