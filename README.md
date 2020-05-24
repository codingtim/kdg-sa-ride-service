# Ride service

## clean install

In order to run `mvn clean install`:
- external price server has to be installed with `mvn install:install-file -Dfile=price-service-1.1.jar -DgroupId=be.kdg.sa -DartifactId=price-service -Dversion=1.1 -Dpackaging=jar`

In order to run with integration tests `mvn clean install -DskipITs=false`:
- a local sql server db has to be running

## Requests

Start a station ride

    curl -XPOST -H "Content-Type: application/json" -d '{"userId": 19534, "stationId": 1}' 'http://localhost:8082/api/rides/station'

Get free locks of a station

    curl -H "Accept: application/json" 'http://localhost:8082/api/stations/1/free-locks'

End a station ride at a lock

    curl -XPOST -H "Content-Type: application/json" -d '{"userId": 19534, "lockId": 2}' 'http://localhost:8082/api/rides/station/end'

Send vehicle location

    curl -XPOST -H "Content-Type: application/json" -d '{"vehicleId": 4943, "xCoord": 1.0, "yCoord": 1.2}' 'http://localhost:8082/api/vehicles/location'

Start a free vehicle ride

    curl -XPOST -H "Content-Type: application/json" -d '{"userId": 19534, "vehicleId": 4943}' 'http://localhost:8082/api/rides/free'

End a free vehicle ride

    curl -XPOST -H "Content-Type: application/json" -d '{"userId": 19534, "vehicleId": 4943}' 'http://localhost:8082/api/rides/free/end'
