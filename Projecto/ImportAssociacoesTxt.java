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




public class ImportAssociacoesTxt {
    
    Rede G;
    
     /**
     * Construtor da Classe ImportAssociacoesTxt para ler associações que já se encontram armazenadas num ficheiro.
     *
     * @param rede Objecto do tipo Rede.
     * @param fileName Nome do ficheiro + extensão.
     */
    public ImportAssociacoesTxt(Rede rede, String fileName) {
        
     File f = null;
        FileReader fr = null;
        BufferedReader br = null;
        String line = null;
        G=rede;
        try {
            f = new File("/Users/joaoalves/Desktop", fileName);
            fr = new FileReader(f);
            br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                G = stringTokenizer(G,line);
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

    private Rede stringTokenizer(Rede rede, String line) {

        StringTokenizer st = new StringTokenizer(line, "%");
        int idParagem = 0;
        int idLinha = 0;
        int i = 1;

        while (st.hasMoreTokens()) {
            String t = st.nextToken();
            switch (i) {
                case 1:
                    idParagem = Integer.parseInt(t);
                    break;
                case 2:
                    idLinha = Integer.parseInt(t);
                    break;
            }
            i++;
        }
        Paragem p = rede.paragens.get(idParagem);
        Linha l = rede.linhas.get(idLinha);
        
        p.getLinhas().add(l);
        l.getParagens().add(p);
        return rede;
    }
    
    
    
}
