package ru.stqa.pft.addressbook.sandbox;

public class MyPointProgram {

    public static void main(String[] args) {

        Point p1 = new Point(5.2,-2.0);
        Point p2 = new Point(-7.75,8);

        System.out.println("Расстояние между точками равно " + distance(p1,p2) + " см. /функция");
        System.out.println("Расстояние между точками равно " + p1.distance(p2) + " см. /метод");


    }
        public static double distance(Point p1, Point p2){
            return Math.sqrt((p2.x- p1.x)*(p2.x- p1.x)+(p2.y- p1.y)*(p2.y- p1.y));

        }


    }