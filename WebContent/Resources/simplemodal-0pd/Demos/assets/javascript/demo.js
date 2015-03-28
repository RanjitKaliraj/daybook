/*
* Mootools Simple Modal
* Version 1.0
* Copyright (c) 2011 Marco Dell'Anna - http://www.plasm.it
*/

//This function checks the description validity
    function InvalidMsgDes(description) {    
    	if (description.value == '') {
    		description.setCustomValidity('Please, Enter Description.');
    	}
    	else {
    		description.setCustomValidity('');
    	}
    	return true;
    }
    
    //This function checks the source validity
    function InvalidMsgSrc(source) {    
    	if (source.value == '') {
    		source.setCustomValidity('Please, Specify Source.');
    	}
    	else {
    		source.setCustomValidity('');
    	}
    	return true;
    }
    
    //This function checks the amount validity
    function InvalidMsgAmt(amount) {    
    	if (amount.value == '') {
    		amount.setCustomValidity('Please, Enter Amount.');
    	}
    	else {
    		amount.setCustomValidity('');
    	}
    	return true;
    }
	

window.addEvent("domready", function(e){
  /* Alert */
  $("alert").addEvent("click", function(e){
    e.stop();
    var SM = new SimpleModal({"btn_ok":"Alert button"});
        SM.show({
          "title":"Alert Modal Title",
          "contents":"Lorem ipsum dolor sit amet..."
        });
  })
  
  /* Confirm */
  $("confirm").addEvent("click", function(e){
    e.stop();
    var SM = new SimpleModal({"btn_ok":"Confirm button"});
        SM.show({
          "model":"confirm",
          "callback": function(){
            alert("Action confirm!");
          },
          "title":"Confirm Modal Title",
          "contents":"Lorem ipsum dolor sit amet..."
        });
  })
  
  /* Modal */
  //var size = document.getElementByName("apple");

  for (var i=1; i<=2;i++){
	var n = "a"+i;
	var type = document.getElementById(n);
	alert(type.length);
	 
  type.addEvent("click", function(e){
    e.stop();
    var SM = new SimpleModal({"btn_ok":"Confirm button"});
        // Aggiunge Bottone Conferma
        //SM.addButton("Confirm", "btn primary", function(){
        //    document.update.submit();
        //    this.hide();
        //});
        // Aggiunge Bottone annulla
        //SM.addButton("Cancel", "btn");
        SM.show({
          "model":"modal",
          "title":"Update Transaction",
          "contents":"<form style='font-size:12px; class='form-2' action='ajax-content.html' name='update' method='GET'>Description: <br><input style='color:black' size='58' type='text' name='description' placeholder='' oninvalid='InvalidMsgDes(this)' oninput='InvalidMsgDes(this)' required='required'/><br/><br/>Details (Optional):<br> <input style='color:black' size='58' type='text' name='detail' placeholder='' /><br /><br /> <input type='radio' name='type' value='income' required='required'/> Income      <input type='radio' name='type' value='expenses' required='required'/> Expenses<br><br>Source of Amount <select style='color:black' name='source' oninvalid='InvalidMsgSrc(this)' oninput='InvalidMsgSrc(this)' required='required'/>  <option value=''>--Select--</option> <option value='cash'>Cash</option><option value='cheque'>Cheque</option><option value='other'>Other</option></select> <br><br> Amount: <input style='color:black' size='19' type='text' name='amount' placeholder='' oninvalid='InvalidMsgAmt(this)' oninput='InvalidMsgAmt(this)' required='required'/> <input type='submit' value='Update'/></form> "
	        });
  })
  }
 
  
  
  /* Modal Ajax */
  $("modal-ajax").addEvent("click", function(e){
    e.stop();
    var SM = new SimpleModal({"btn_ok":"Confirm button", "width":600});
        // Aggiunge Bottone Conferma
        SM.addButton("Confirm", "btn primary", function(){
						// Check
						if( $("confirm-text").get("value") != "DELETE" ){
							$("confirm-delete-error").setStyle("display", "block");
						}else{
							// Your code ...
							this.hide();
						}
        });
        // Aggiunge Bottone annulla
        SM.addButton("Calcel", "btn");
        SM.show({
          "model":"modal-ajax",
          "title":"Are you sure you want to delete this?",
          "param":{
            "url":"ajax-content.html",
            "onRequestComplete": function(){ }
          }
        });
  })

  /* Modal Image */
  $("modal-image").addEvent("click", function(e){
    e.stop();
    var SM = new SimpleModal({
          "onAppend":function(){
            $("simple-modal").fade("hide").fade("in")
          }
        });
        SM.show({
          "model":"modal-ajax",
					"title":"Modal Lightbox",
          "param":{
            "url":"assets/images/lightbox.jpg",
            "onRequestComplete": function(){ }
          }
        });
  })

  /* NO Header */
  $("alert-noheader").addEvent("click", function(e){
    e.stop();
    var SM = new SimpleModal({"hideHeader":true, "closeButton":false, "btn_ok":"Close window", "width":600});
        SM.show({
          "model":"alert",
          "contents":"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
        });
  })
  
  /* NO Footer */
  $("modal-nofooter").addEvent("click", function(e){
    e.stop();
    var SM = new SimpleModal({"hideFooter":true, "width":710});
        SM.show({
          "title":"Vimeo video",
          "model":"modal",
          "contents":'<iframe src="http://player.vimeo.com/video/26198635?title=0&amp;byline=0&amp;portrait=0&amp;color=824571" width="680" height="382" frameborder="0" webkitAllowFullScreen allowFullScreen></iframe>'
        });
  })
  
  $("example-eheh").addEvent("click", function(e){
    e.stop();
    var SM = new SimpleModal(
            {
              "btn_ok":"Confirm button",
              "overlayClick":false,
              "width":300,
              "onAppend":function(){
                $("simple-modal").fade("hide");
                setTimeout((function(){ $("simple-modal").fade("show")}), 200 );
                var tw = new Fx.Tween($("simple-modal"),{
                  duration: 1600,
                  transition: 'bounce:out',
                  link: 'cancel',
                  property: 'top'
                }).start(-400, 150)

                var item = $("simple-modal").getElement(".simple-modal-footer a");
                    item.removeClass("primary").setStyles({"background":"#824571","color": "#FFF" });
                    item.getParent().addClass("align-left");
	                  item.addEvent("mouseenter", function(){
	                    var parent = this.getParent();
	                    if( parent.hasClass("align-left") ){
	                      parent.removeClass("align-left").addClass("align-right");
	                    }else{
	                      parent.removeClass("align-right").addClass("align-left");
	                    }
	                  })
              }
            });
        // Aggiunge Bottone Conferma
        SM.addButton("Click ME please!", "btn primary", function(){});
        SM.show({
          "model":"modal",
          "title":"Eh eh eh",
          "contents":"<p>Try clicking on the button \"Click ME please!\"</p>"
        });
  });
});