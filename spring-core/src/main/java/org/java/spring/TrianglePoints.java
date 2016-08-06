package org.java.spring;

import java.util.List;

public class TrianglePoints {

    private List<Point> points;

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public void draw() {
        for (Point point : points) {
            System.out.printf("Point A's X is: " + point.getX() + " Point A's Y is: " + point.getY());
        }


    }
}
