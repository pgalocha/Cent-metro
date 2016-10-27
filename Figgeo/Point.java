/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.lp2.Figgeo;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 *
 * @author joaoalves
 */
public class Point implements java.io.Serializable{
    
    
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
     
    
    double distanceX(Point p){
  
        if(p.x<this.x){
            return this.x - p.x;
        }
        else
            return p.x - this.x;
    }
    
    double distanceY(Point p){
        if(p.y<this.y){
            return this.y - p.y;
        }
        else
            return p.y - this.y;
    }
    
    double distanciaX_Y(Point p){
        return sqrt(pow(this.distanceX(p),2.0)+pow(this.distanceY(p),2.0));   
    }
    
    boolean xBetweenPoints(Point leftPt, Point rightPt){
        return (this.x>leftPt.x && this.x<rightPt.x);
    }
        
      
    boolean yBetweenPoints(Point leftPt, Point rightPt){
       return (this.y>leftPt.y && this.y<rightPt.y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }
    
    public void setY(double y) {
        this.y = y;
    }

    
    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }
    
}
