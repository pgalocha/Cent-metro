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
import java.util.StringTokenizer;

public class ImportGrafoTxt {
    Rede G;
    
    /**
     * Construtor da Classe ImportGrafoTxt para ler um Digrafo de um ficheiro.
     *
     * @param fileName Nome do ficheiro + extensão.
     */
    public ImportGrafoTxt(String fileName) {
        
        File f = null;
        FileReader fr = null;
        BufferedReader br = null;
        String line = null;
        
        try{
             f = new File("/Users/joaoalves/Desktop",fileName);
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            
            int V = Integer.parseInt(br.readLine());
            G = new Rede (V);
            int E = Integer.parseInt(br.readLine());
            if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
                   
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
        
        StringTokenizer st = new StringTokenizer(line," ");
        int i = 1;
        int v = 0;
        int w = 0;
        float custo_distancia = 0.0f;
        float custo_monetario = 0.0f;
        float custo_temporal = 0.0f;

        
        while(st.hasMoreTokens()){
            String t = st.nextToken();
            switch(i){
                case 1 : v = Integer.parseInt(t); break;
                case 2 : w = Integer.parseInt(t); break;
                case 3 : custo_distancia = Float.parseFloat(t);break;
                case 4 : custo_monetario = Float.parseFloat(t);break;
                case 5 : custo_temporal = Float.parseFloat(t);break;
            }
            i++;   
        }

        Ligacao aux = new Ligacao(v,w,custo_distancia);
        aux.addCustoHashMap(Rede.CUSTO_MONETARIO, custo_monetario);
        aux.addCustoHashMap(Rede.CUSTO_TEMPORAL, custo_temporal);
        G.addEdgeToDigraph(aux);
    }   
}
