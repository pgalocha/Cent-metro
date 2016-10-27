/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.lp2.Projecto;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class ImportLinhasTxt {

    ArrayList<Linha> linhas = new ArrayList<>();
    
     /**
     * Construtor da Classe ImportLinhasTxt para ler Linhas de um ficheiro.
     *
     * @param fileName Nome do ficheiro + extensão.
     */
    public ImportLinhasTxt(String fileName) {

        File f = null;
        FileReader fr = null;
        BufferedReader br = null;
        String line = null;

        try {
            f = new File("/Users/joaoalves/Desktop", fileName);
            fr = new FileReader(f);
            br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                stringTokenizer(line);
            }

        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());

        } finally {

            if (br != null) {
                try {
                    br.close();
                    if (fr != null) {
                        fr.close();
                    }
                } catch (IOException ex) {
                        System.out.println(ex);
                }
            }
        }
    }
    /**
     * Metodo que dado uma string , irá fazer a leitura de informação que se encontra divida por um Token.
     * @param line Linha onde será feita a extração da informação por partes.
     */
    private void stringTokenizer(String line) {

        StringTokenizer st = new StringTokenizer(line, "%");
        String name = null;
        int rgbValue = 0;
        int i = 1;

        while (st.hasMoreTokens()) {
            String t = st.nextToken();
            switch (i) {
                case 1:
                    name = t;
                    break;
                case 2:
                    rgbValue = Integer.parseInt(t);
                    break;
            }
            i++;
        }

        linhas.add(new Linha(name, new Color(rgbValue)));
    }
}
