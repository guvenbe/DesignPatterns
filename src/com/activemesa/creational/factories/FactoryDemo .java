package activemesa.creational.factories;
/*
* Object creation bvecomes to convoluted
* Constuctor is not decriptive
*       -Name mandated by containing type
*       -Cannot overload with same set of arguments with different names
*       -Can turn i t overloding hell
* Wholesale Object creation (non-piecewise, unlike builder) ca be outsourced to
*       -A seperate function(Factoy Method)
*       -That may exist in seperatory clas (Factory)
*       -Can create hierarcy of factories with Abstract Factory
* */


class Point
{
    private double x, y;

    private Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }

    // singleton field
    public static final Point ORIGIN = new Point(0,0);

    //Inner static class so it can access constructor
    public static class Factory
    {
        public static Point newCartesianPoint(double x, double y)
        {
            return new Point(x,y);
        }

        public static Point newPolarPoint(double rho, double theta)
        {
            return new Point(rho*Math.cos(theta), rho*Math.sin(theta));
        }
    }
}


class FactoryDemo
{
    public static void main(String[] args)
    {

        Point origin = Point.ORIGIN;

        Point pointx = Point.Factory.newCartesianPoint(1, 2);

        System.out.println(pointx);

        Point point2 = Point.Factory.newPolarPoint(1.,2);

        System.out.println(point2);

    }
}
