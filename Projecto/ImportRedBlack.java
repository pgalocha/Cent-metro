/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.lp2.Projecto;

import edu.princeton.cs.algs4.RedBlackBST;
import edu.ufp.inf.lp2.intro.Date;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ImportRedBlack {
    
    public RedBlackBST<Integer, Passageiro> rb;
    
     /**
     * Construtor da Classe ImportRedBlack para ler Passageiros de um ficheiro.
     *
     * @param fileName Nome do ficheiro + extensão.
     */
    public ImportRedBlack( String fileName) {
        rb = new RedBlackBST<>();
        File f = null;
        FileReader fr = null;
        BufferedReader br = null;
      
        try{
             f = new File("/Users/joaoalves/Desktop",fileName);
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String line = null;
     
            while((line = br.readLine())!=null){
                stringTokenizer(line);
            }        
            
            
        }catch(IOException e){
            System.out.println(e);

        }finally{
            
            if(br!=null) try {
                br.close();
                if(fr!=null) fr.close();
            } catch (IOException ex) {
                  System.out.println(ex);
            }      
        }
    } 
    
    /**
     * Metodo que dado uma string , irá fazer a leitura de informação que se encontra divida por um Token.
     * @param line Linha onde será feita a extração da informação por partes.
     */
    private void stringTokenizer(String line){
        
        StringTokenizer st = new StringTokenizer(line,"%");
        int i=1;
        int id = 0;
        String nome = null;
        String data = null;
        int dia,mes,ano;
        Double lat = null;
        Double longi = null;
        float saldo;
        
        while(st.hasMoreTokens()){
            String t = st.nextToken();
            switch(i){
                case 1 : id = Integer.parseInt(t); break;
                case 2 : nome = t; break;
                case 3 : data=t;break;
                case 4 : lat=Double.parseDouble(t);break;
                case 5 : longi=Double.parseDouble(t);break;
                case 6 : saldo = Float.parseFloat(t);break;
            }
            i++;   
        }
        
        StringTokenizer dt = new StringTokenizer(data,"/");
        dia=Integer.parseInt(dt.nextToken());
        mes=Integer.parseInt(dt.nextToken());
        ano=Integer.parseInt(dt.nextToken());
            
        rb.put(id, new Passageiro(nome,new Date(dia,mes,ano),new Localizacao(lat,longi)));
    }   
}
    
