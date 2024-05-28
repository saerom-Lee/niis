OpenLayers1.Control.CustomNavToolbar = OpenLayers1.Class(OpenLayers1.Control.Panel, {
			
    /**
     * Constructor: OpenLayers.Control.NavToolbar 
     * Add our two mousedefaults controls.
     *
     * Parameters:
     * options - {Object} An optional object whose properties will be used
     *     to extend the control.
     */
	
	
    initialize: function(options) {
        OpenLayers1.Control.Panel.prototype.initialize.apply(this, [options]);
        this.addControls([
          new OpenLayers1.Control.Navigation(),
		  //Here it come
          new OpenLayers1.Control.ZoomBox({alwaysZoom:false, out:false}), // 드래그 줌인 컨트롤
          new OpenLayers1.Control.ZoomBox({alwaysZoom:false, out:true}) // 드래그 줌아웃 컨트롤
        ]);
		// To make the custom navtoolbar use the regular navtoolbar style
		this.displayClass = 'olControlNavToolbar';
    },
	
	

    /**
     * Method: draw 
     * calls the default draw, and then activates mouse defaults.
     */
    draw: function() {
        var div = OpenLayers1.Control.Panel.prototype.draw.apply(this, arguments);
        this.defaultControl = this.controls[0];
        return div;
    }
});