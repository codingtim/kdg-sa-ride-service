package be.codingtim.velo.ride.domain.point;

import org.locationtech.jts.operation.distance.DistanceOp;

public class Distance implements Comparable<Distance> {
    private final double distance;

    public Distance(double distance) {
        this.distance = distance;
    }

    static Distance distance(GpsPoint point, GpsPoint point1) {
        return new Distance(DistanceOp.distance(point.getPoint(), point1.getPoint()));
    }

    @Override
    public int compareTo(Distance o) {
        return Double.compare(distance, o.distance);
    }
}
