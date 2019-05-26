package com.activemesa.creational.factories;
/*
* Object creation bvecomes to convoluted
* Constuctor is not decriptive
*       -Nam mandated by containing type
*       -Cannot overload with same set of arguments with different names
*       -Can turn i t overloding hell
* Wholesale Object creation (non-piecewise, unlike builder) ca be outsourced to
*       -A seperate function(Factoy Method)
*       -That may exist in seperatory clas (Factory)
*       -Can create hierarcy of factories with Abstract Factory
* */

enum CoordinateSystem3
{
    CARTESIAN,
    POLAR
}

class Pointx
{
    private double x, y;

    protected Pointx(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }

    // singleton field
    public static final Pointx ORIGIN = new Pointx(0,0);

    public static class Factory
    {
        public static Pointx newCartesianPoint(double x, double y)
        {
            return new Pointx(x,y);
        }

        public static Pointx newPolarPoint(double rho, double theta)
        {
            return new Pointx(rho*Math.cos(theta), rho*Math.sin(theta));
        }
    }
}


class FactoryDemox
{
    public static void main(String[] args)
    {

        Pointx origin = Pointx.ORIGIN;

        Pointx pointx = Pointx.Factory.newCartesianPoint(1, 2);

        System.out.println(pointx);

        Pointx point2 = Pointx.Factory.newPolarPoint(1.,2);

        System.out.println(point2);

    }
}
