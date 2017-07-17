
$(document).ready(function() { 

 
  $(window).scroll(function() {
	  var value = $(this).scrollTop();
      if (value > 0)
        $(".my-navbar").css("background-color", "#fff").css("transition-duration", ".4s");
      else {
        $(".my-navbar").css("background-color", "transparent");
      }
  });
  
  function centerModals() {
	  $('.modal').each(function(i){   //遍历每一个模态框
	       var $clone = $(this).clone().css('display', 'block').appendTo('body');    
	       var top = Math.round(($clone.height() - $clone.find('.modal-content').height()) / 2);
	       top = top > 0 ? top : 0;
	       $clone.remove();
	       $(this).find('.modal-content').css("margin-top", top-30);  //修正原先已经有的30个像素
	   });
	  var paddingRightLen = -(window.innerWidth - document.documentElement.clientWidth);
	  $("body").css("padding-right", paddingRightLen);		//解决
	  $("body").css("overflow-y", "hidden");
  }

  $(".modal").on("show.bs.modal", centerModals);      //当模态框出现的时候
  $(".modal").on("hide.bs.modal", function() {
  	$("body").css("padding-right", "0px");
  	$("body").css("overflow-y", "auto");
    $("#sign-result").html("");
  	$("#email").val("");
  	$("#Password").val("");
  });


});

