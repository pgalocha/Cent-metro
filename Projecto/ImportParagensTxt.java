/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.lp2.Projecto;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class ImportParagensTxt {
    
    ArrayList<Paragem> paragens = new ArrayList<>();

    /**
     * Construtor da Classe ImportParagensTxt para ler Paragens de um ficheiro.
     *
     * @param fileName Nome do ficheiro + extensão.
     */
    public ImportParagensTxt(String fileName) {
        
        File f = null;
        FileReader fr = null;
        BufferedReader br = null;
        String line = null;
        
        try{
             f = new File("/Users/joaoalves/Desktop",fileName);
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            
            int V = Integer.parseInt(br.readLine());
         
            while((line = br.readLine())!=null){
                stringTokenizer(line);
            }
            
        }catch(IOException e){
            System.out.println(e.getLocalizedMessage());
            System.out.println(e);
            e.printStackTrace();
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
    private void stringTokenizer(String line) {
        
        StringTokenizer st = new StringTokenizer(line,"%");
        String nome=null;
        String nomeZona=null;
        double lat=0.0;
        double longi=0.0;
        float precoZona=0.0f;
        int i=1;
        
        while(st.hasMoreTokens()){
            String t = st.nextToken();
            switch(i){
                case 1 : nome = t; break; 
                case 2 : lat = Double.parseDouble(t);break;
                case 3 : longi = Double.parseDouble(t);break;
                case 4 : nomeZona = t;break;
                case 5 : precoZona = Float.parseFloat(t); break;
            }
            i++;
        }
        
        paragens.add(new Paragem(nome,new Localizacao(lat,longi),new Zona(nomeZona,precoZona)));
    }
}
