/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.lp2.FiggeoDraw;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author joaoalves
 */
public class BagFigGeo {
    
    private ArrayList<FigGeoDrawI> figs = new ArrayList<>();
    
    public void add(FigGeoDrawI f){
        figs.add(f);
    }
    
    public void draw(Graphics g){
        if(!figs.isEmpty()){
            for(FigGeoDrawI f : figs){
              f.draw(g);
            }   
        }
    }
   
 
}
