        var map;
        var infoWindow;

        function initMap() {
            map = new google.maps.Map(document.getElementById('map'), {
                center: {lat: -34.397, lng: 150.644},
                zoom: 15
            });

            infoWindow = new google.maps.InfoWindow();

            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function(position) {
                    var userLocation = {
                        lat: position.coords.latitude,
                        lng: position.coords.longitude
                    };

                    map.setCenter(userLocation);

                    var userMarker = new google.maps.Marker({
                        position: userLocation,
                        map: map,
                        title: 'Your Location'
                    });

                    // marker event of your location
                    userMarker.addListener('click', function() {
                        infoWindow.setContent('Your Location');
                        infoWindow.open(map, userMarker);
                    });

                    var request = {
                        location: userLocation,
                        radius: 2000,
                        keyword: 'recycling center'
                    };

                    var service = new google.maps.places.PlacesService(map);
                    service.nearbySearch(request, function(results, status) {
                        if (status == google.maps.places.PlacesServiceStatus.OK) {

                            for (var i = 0; i < results.length; i++) {
                                createMarker(results[i]);
                            }
                        }
                    });
                });
            }
        }

        function createMarker(place) {
            console.log(JSON.stringify(place, null, 2));
            var marker = new google.maps.Marker({
                map: map,
                position: place.geometry.location,
                title: place.name
            });

            // marker event of place
            marker.addListener('click', function() {
                infoWindow.setContent(place.name);
                infoWindow.open(map, marker);
            });
        }