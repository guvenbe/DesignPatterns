package com.activemesa.creational.factories;


enum CoordinateSystem {
    CARTESIAN,POLAR

}

class Point {
    private double x, y;

    //This is bad now we have to know to pass CoordianteSytem
    public Point(double a,
                 double b, // names do not communicate intent
                 CoordinateSystem1 cs) {

        switch (cs) {
            case CARTESIAN:
                this.x = a;
                this.y = b;
                break;
            case POLAR:
                this.x = a * Math.cos(b);
                this.y = a * Math.sin(b);
                break;
        }
    }

    //Bring back the constructor, Make it private
    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static final PointXY ORIGIN = new PointXY(0,0);

    //Factory Methods
    public static PointXY newCartesianPoint(double x, double y) {
        return new PointXY(x, y);
    }

    public static PointXY newPolarPoint(double rho, double theta) {
        return new PointXY(rho * Math.cos(theta), rho * Math.sin(theta));
    }
}

public class MethodDemo {

    public static void main(String[] args) {
        //old school
        PointXY pointXY = new PointXY(2, 3, CoordinateSystem1.CARTESIAN);

        PointXY origin = PointXY.ORIGIN;

        //Intent is more expresive
        PointXY pointXY1 = PointXY.newCartesianPoint(1, 2);

    }
}
