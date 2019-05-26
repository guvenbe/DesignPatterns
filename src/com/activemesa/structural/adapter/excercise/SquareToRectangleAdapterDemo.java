package com.activemesa.structural.adapter.excercise;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

class Square
{
    public int side;

    public Square(int side) {
        this.side = side;
    }
}


interface  Rectangle{
    int getWidth();
    int getHeight();

    default int getArea(){
        return getWidth() * getHeight();
    }
}


class SquareToRectangleAdapter  implements Rectangle{

    private Square square;

    public SquareToRectangleAdapter(Square square) {
        this.square = square;
    }

    @Override
    public int getWidth(){
        return square.side;
    }

    @Override
    public int getHeight() {
        return square.side;
    }


}


public class SquareToRectangleAdapterDemo{
    @Test
    public void test()
    {
        Square sq = new Square(11);
        SquareToRectangleAdapter adapter = new SquareToRectangleAdapter(sq);
        assertEquals(121, adapter.getArea());
    }
}