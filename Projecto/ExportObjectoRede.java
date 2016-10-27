/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.lp2.Projecto;


import edu.ufp.inf.lp2.Files.ObjectOutputAppp;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExportObjectoRede {

    
    /**
     * Construtor da Classe ExportObjectoRede para armazenar o Objecto Rede num ficheiro em modo binário.
     *
     * @param G Objecto Rede.
     * @param fileName Nome do ficheiro + extensão(.bin).
     */
    public ExportObjectoRede(Rede G,String fileName){
        
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        
        
        try{
           f = new File("/Users/joaoalves/Desktop",fileName);
           fos = new FileOutputStream(f);
           oos = new ObjectOutputStream(fos);

           oos.writeObject(G.V());
           oos.writeObject((Rede)G);
           
        }catch(IOException ioe){
            ioe.printStackTrace();
        }finally{
            try {
                oos.close();
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(ObjectOutputAppp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }  
}
