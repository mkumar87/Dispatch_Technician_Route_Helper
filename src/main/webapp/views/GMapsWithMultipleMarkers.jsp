<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html> 
<head> 
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" /> 
  <title>Google Maps Multiple Markers</title> 
  <script src="http://maps.google.com/maps/api/js?sensor=false"></script>
  <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.10.1.min.js"></script>
</head> 
<body>
  <div id="map" style="width: 98%; height: 500px;"></div>

  <script type="text/javascript">    

    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 15,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    var infowindow = new google.maps.InfoWindow();

    var providerMarker, reciverMarker, i;
    var markers = new Array();

    reciverMarker = new google.maps.Marker({
        position: new google.maps.LatLng(takerlatitude,takerlongitude),
        map: map,
        icon: "resources/marker1.png"
      });
    
    markers.push(reciverMarker);
    
    for (i = 0; i < providerLocations.length; i++) {
      providerMarker = new google.maps.Marker({
        position: new google.maps.LatLng(providerLocations[i][1], providerLocations[i][2]),
        map: map,
        //animation : google.maps.Animation.BOUNCE
        icon: "resources/marker2.png"
      });

      markers.push(providerMarker);

      google.maps.event.addListener(providerMarker, 'click', (function(providerMarker, i) {
        return function() {
          infowindow.setContent(providerLocations[i][0]);
          infowindow.open(map, providerMarker);
        }
      })(providerMarker, i));
    }

    function AutoCenter() {
      //  Create a new viewpoint bound
      var bounds = new google.maps.LatLngBounds();
      //  Go through each...
      $.each(markers, function (index, reciverMarker) {
      bounds.extend(reciverMarker.position);
      });
      //  Fit these bounds to the map
      map.fitBounds(bounds);
    }
    AutoCenter();

  </script> 
</body>
</html>
