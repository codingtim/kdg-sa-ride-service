# Ride service

## clean install

In order to run `mvn clean install`:
- external price server has to be installed with `mvn install:install-file -Dfile=price-service-1.1.jar -DgroupId=be.kdg.sa -DartifactId=price-service -Dversion=1.1 -Dpackaging=jar`
- a local sql server db has to be running

## Requests

Start a station ride

    curl -XPOST -H "Content-Type: application/json" -d '{"userId": 19534, "stationId": 1}' 'http://localhost:8082/api/rides'

