# Ride service

## Requests

Start a station ride

    curl -XPOST -H "Content-Type: application/json" -d '{"userId": 19534, "stationId": 1}' 'http://localhost:8082/api/rides'

