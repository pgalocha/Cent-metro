/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.lp2.Projecto;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class ImportObjectoRede {

    public Rede G;
    
     /**
     * Construtor da Classe ImportObjectoRede para ler um objecto do tipo Rede de um ficheiro binário.
     *
     * @param fileName Nome do ficheiro + extensão.
     */
    public ImportObjectoRede(String fileName){
        
        File f =null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        
        try{
            f = new File("/Users/joaoalves/Desktop",fileName);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            
            int v = (int)ois.readObject();
            System.out.println("---"+v);
           
            G = (Rede) ois.readObject();
           
            System.out.println(G.V());
            System.out.println(G.E());
       
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                ois.close();
                fis.close();
            } catch (IOException ex) {
                    System.out.println(ex);
            }
        }
    }
}
