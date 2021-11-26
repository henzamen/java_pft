package ru.stqa.pft.addressbook.sandbox;


import org.testng.Assert;
import org.testng.annotations.Test;
import static ru.stqa.pft.addressbook.sandbox.MyPointProgram.distance;

public class PointTests {

    @Test
    public void testTwoPointsDistanceMethod() {

        Point p1 = new Point(5.2,-2.0);
        Point p2 = new Point(-7.75,8);
        Assert.assertEquals(p1.distance(p2),15.361616668288008);

    }

    @Test
    public void testTwoPointsDistanceFunction() {

        Point p1 = new Point(5.2,-2.0);
        Point p2 = new Point(-7.75,8);
        Assert.assertEquals(distance(p1,p2),16.361616668288008);

    }

    @Test
    public void testTwoPointsDistanceFunctionNegative() {

        Point p1 = new Point(5.2,-2.0);
        Point p2 = new Point(-7.75,8);
        Assert.assertNotEquals(distance(p1,p2),16);

    }

    @Test
    public void testTwoPointsDistanceMethodTrue() {

        Point p1 = new Point(5.2,-2.0);
        Point p2 = new Point(-7.75,8);
        Assert.assertTrue(p1.distance(p2) == 16.361616668288008);

    }

}