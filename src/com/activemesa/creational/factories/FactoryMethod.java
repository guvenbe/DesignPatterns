package activemesa.creational.factories;

enum CoordinateSystem1
{
    CARTESIAN,
    POLAR
}

class PointXY
{
    private double x, y;

    protected PointXY(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    //This is ugly Factory method will help solve this
    public PointXY(double a,
                   double b, // names do not communicate intent
                   CoordinateSystem1 cs)
    {
        switch (cs)
        {
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

    // steps to add a new system
    // 1. augment CoordinateSystem
    // 2. change ctor

    // singleton field
    public static final PointXY ORIGIN = new PointXY(0,0);

    // factory methods
    public static PointXY newCartesianPoint(double x, double y)
    {
        return new PointXY(x,y);
    }

    public static PointXY newPolarPoint(double rho, double theta)
    {
        return new PointXY(rho*Math.cos(theta), rho*Math.sin(theta));
    }

}

class PointFactory
{
    public static PointXY newCartesianPoint(double x, double y)
    {
        return new PointXY(x,y);
    }
}

class FactoryMethodDemo
{
    public static void main(String[] args)
    {
        PointXY pointXY = new PointXY(2, 3, CoordinateSystem1.CARTESIAN);
        PointXY origin = PointXY.ORIGIN;

    }
}