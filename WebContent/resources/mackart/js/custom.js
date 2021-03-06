/* JS */



/* Recent posts carousel */


$(window).load(function(){
  $('.rps-carousel').carouFredSel({
  	responsive: true,
  	width: '100%',
  	pauseOnHover : true,
  	auto : false,
  	circular	: true,
  	infinite	: false,
  	prev : {
  		button	: "#car_prev",
  		key		: "left"
  	},
  	next : {
  		button	: "#car_next",
  		key		: "right"
  	},
  	swipe: {
  		onMouse: true,
  		onTouch: true
  	},
  	items: {
  		visible: {
  			min: 1,
  			max: 4
  		}
  	}
  }); 
});


/* Flex image slider */


   $('.flex-image').flexslider({
      direction: "vertical",
      controlNav: false,
      directionNav: true,
      pauseOnHover: true,
      slideshowSpeed: 10000      
   });


/* Flexslider for product images */


   $('.product-image-slider').flexslider({
      direction: "vertical",
      controlNav: false,
      directionNav: true,
      pauseOnHover: true,
      slideshowSpeed: 10000      
   });

/* Twitter */

jQuery(function($){
   $(".tweet").tweet({
      username: "ashokramesh90",
      join_text: "auto",
      avatar_size: 0,
      count: 4,
      auto_join_text_default: "we said,",
      auto_join_text_ed: "we",
      auto_join_text_ing: "we were",
      auto_join_text_reply: "we replied to",
      auto_join_text_url: "we were checking out",
      loading_text: "loading tweets...",
      template: "{text}"
   });
}); 


/* Support */

$("#slist a").click(function(e){
   e.preventDefault();
   $(this).next('p').toggle(200);
});



/* Pretty Photo for Gallery*/

jQuery("a[class^='prettyPhoto']").prettyPhoto({
overlay_gallery: false, social_tools: false
});


/* Scroll to Top */

$(document).ready(function(){
  $(".totop").hide();

  $(function(){
    $(window).scroll(function(){
      if ($(this).scrollTop()>600)
      {
        $('.totop').slideDown();
      } 
      else
      {
        $('.totop').slideUp();
      }
    });

    $('.totop a').click(function (e) {
      e.preventDefault();
      $('body,html').animate({scrollTop: 0}, 500);
    });

  });
});