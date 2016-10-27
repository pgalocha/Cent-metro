/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.lp2.Projecto;

public class PassageiroAlreadyExistException extends Exception implements java.io.Serializable  {

    public PassageiroAlreadyExistException(String string) {
        super(string);
    }

    public PassageiroAlreadyExistException() {
    }
    
}
