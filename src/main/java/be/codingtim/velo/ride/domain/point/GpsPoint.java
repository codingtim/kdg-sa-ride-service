package be.codingtim.velo.ride.domain.point;

import org.locationtech.jts.geom.CoordinateXY;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

import java.util.Objects;

public class GpsPoint {

    private static final GeometryFactory GEOMETRY_FACTORY = new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING), 4326);

    private final Point point;

    private GpsPoint(Point point) {
        this.point = point;
    }

    public static GpsPoint of(double x, double y) {
        return new GpsPoint(
                GEOMETRY_FACTORY.createPoint(new CoordinateXY(x, y))
        );
    }

    public static GpsPoint of(Point point) {
        return new GpsPoint(point);
    }

    public Point getPoint() {
        return point;
    }

    public double getX() {
        return point.getX();
    }

    public double getY() {
        return point.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GpsPoint point1 = (GpsPoint) o;
        return Objects.equals(point, point1.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }

    @Override
    public String toString() {
        return point.toString();
    }

    public Distance distanceTo(GpsPoint gpsPoint) {
        return Distance.distance(this, gpsPoint);
    }
}
