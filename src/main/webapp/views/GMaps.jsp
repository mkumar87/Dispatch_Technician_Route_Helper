<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <style>
      html, body, #map-canvas {
        height: 100%;
        margin: 0;
        padding: 0;
      }

      .controls {
        margin-top: 16px;
        border: 1px solid transparent;
        border-radius: 2px 0 0 2px;
        box-sizing: border-box;
        -moz-box-sizing: border-box;
        height: 32px;
        outline: none;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
      }

      #pac-input {
        background-color: #fff;
        font-family: Roboto;
        font-size: 15px;
        font-weight: 300;
        margin-left: 12px;
        padding: 0 11px 0 13px;
        text-overflow: ellipsis;
        width: 400px;
      }

      #pac-input:focus {
        border-color: #4d90fe;
      }

      .pac-container {
        font-family: Roboto;
      }

      #type-selector {
        color: #fff;
        background-color: #4d90fe;
        padding: 5px 11px 0px 11px;
      }

      #type-selector label {
        font-family: Roboto;
        font-size: 13px;
        font-weight: 300;
      }

    </style>
    <title>Places search box</title>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&libraries=places"></script>
    <script>
// This example adds a search box to a map, using the Google Place Autocomplete
// feature. People can enter geographical searches. The search box will return a
// pick list containing a mix of places and predicted search terms.

		function initialize() {
			var myCenter = new google.maps.LatLng(latitude, longitude);
	
			var mapProp = {
				center : myCenter,
				zoom : 15,
				mapTypeId : google.maps.MapTypeId.ROADMAP
			};
	
			var map = new google.maps.Map(document
					.getElementById("map-canvas"), mapProp);
	
			var marker1 = new google.maps.Marker({
				position : myCenter,
				draggable : true,
				title: "Your location",
				animation : google.maps.Animation.BOUNCE
			});
	
			marker1.setMap(map);

			//Add listener
			google.maps.event.addListener(marker1,'dragend',function(event){
				latLong = event.latLng;
			});
	
			var markers = [];
			var providerMarker;
			var providerMarkers = [];
			
			if(providerLocations.length > 0){
				for (i = 0; i < providerLocations.length; i++) {
				      providerMarker = new google.maps.Marker({
				        position: new google.maps.LatLng(providerLocations[i][1], providerLocations[i][2]),
				        map: map,
				        icon: "resources/marker3.png"
				      });
				      
				      providerMarkers.push(providerMarker);

				      google.maps.event.addListener(providerMarker, 'click', (function(providerMarker, i) {
				        return function() {
				          infowindow.setContent(providerLocations[i][0]);
				          infowindow.open(map, providerMarker);
				        }
				      })(providerMarker, i));
				    }
			}
			
	
			// Create the search box and link it to the UI element.
			var input = /** @type {HTMLInputElement} */
			(document.getElementById('pac-input'));
			map.controls[google.maps.ControlPosition.TOP_LEFT]
					.push(input);
	
			var searchBox = new google.maps.places.SearchBox((input));
	
			// [START region_getplaces]
			// Listen for the event fired when the user selects an item from the
			// pick list. Retrieve the matching places for that item.
			google.maps.event
					.addListener(
							searchBox,
							'places_changed',
							function() {
								var places = searchBox.getPlaces();
	
								if (places.length == 0) {
									return;
								}
								for (var i = 0, marker; marker = markers[i]; i++) {
									marker.setMap(null);
								}
	
								// For each place, get the icon, place name, and location.
								markers = [];
								marker1.setMap(null);
								var bounds = new google.maps.LatLngBounds();
								for (var i = 0, place; place = places[i]; i++) {
									// Create a marker for each place.
									marker1 = new google.maps.Marker(
									{
										map : map,
				 						draggable : true,
										animation : google.maps.Animation.BOUNCE,
										title : place.name,
										position : place.geometry.location
									});
	
									markers.push(marker1);
	
									bounds.extend(place.geometry.location);
									latLong = place.geometry.location;
								}
								
								map.fitBounds(bounds);
							});
			
			
			// [END region_getplaces]
			// Bias the SearchBox results towards places that are within the bounds of the
			// current map's viewport.
			google.maps.event.addListener(map, 'bounds_changed',
					function() {
						var bounds = map.getBounds();
						searchBox.setBounds(bounds);
					});
	
		}
	
		google.maps.event.addDomListener(window, 'load', initialize);
	</script>
    <style>
      #target {
        width: 345px;
      }
    </style>
  </head>
  <body>
    <input id="pac-input" class="controls" type="text" placeholder="Search Box">
    <div id="map-canvas" style="width:90%; height:350px;"></div>
  </body>
</html>