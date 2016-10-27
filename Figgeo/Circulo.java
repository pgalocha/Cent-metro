/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.lp2.Figgeo;

import edu.ufp.inf.lp2.FiggeoDraw.FigGeoDrawI;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
 *
 * @author joaoalves
 */
public class Circulo extends FigGeo implements FigGeoDrawI, Serializable{

    private double raio;
    final double PI = 3.1456;


    public Circulo(Point center, float raio, Color color) {
        super.color = color;
        super.addPoint(center);
        this.raio = raio;
    }

    @Override
    public double area() {
        Point center = super.getPoint(0);
        if (center != null) {
            double area = raio * raio * PI;
            return area;
        }
        return -1;
    }

    @Override
    public double perimeter() {
        Point center = super.getPoint(0);
        if (center != null) {
            double peri = 2 * raio * PI;
            return peri;
        }
        return -1;
    }

    @Override
    public boolean isInside(FigGeo f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isIntereptedBy(FigGeo f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(Graphics g) {
        Point c = super.getPoint(0);
        int x= (int) c.getX();
        int y= (int) c.getY();
        int w= (int) ((int) 2*raio);
        int h= (int) ((int ) 2*raio);
       
        System.out.println("x="+x+"| y="+y);
        g.setColor(color);
        g.fillOval(x, y, w, h);
        //g.drawString("x="+x+"|y="+y+"", x, y);
    }

    public Color getColor() {
        return color;
    }

    public Point getPoint() {
        return super.getPoint(0);
    }

}
