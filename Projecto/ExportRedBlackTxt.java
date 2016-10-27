/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.lp2.Projecto;

import edu.princeton.cs.algs4.RedBlackBST;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class ExportRedBlackTxt {

     /**
     * Construtor da Classe ExportRedBlackTxt para armazenar informação sobre todas os Passageiros num ficheiro.
     *
     * @param rb Objecto do tipo RedBlackBST onde se encontram armazenados os passsageiros.
     * @param fileName Nome do ficheiro + extensão.
     */
    public ExportRedBlackTxt(RedBlackBST rb, String fileName) {
        File f = null;
        FileWriter fw = null;
        PrintWriter pw = null;

        try {
            f = new File("/Users/joaoalves/Desktop", fileName);
            fw = new FileWriter(f);
            pw = new PrintWriter(fw);
           
            for (int i = 0; i < rb.size(); i++) {
                Passageiro p = (Passageiro) rb.get(i);
                pw.println(p.getId()+"%"+p.getNome()+"%"+p.getData_nascimento().getDay()+"/"+p.getData_nascimento().getMonth()+"/"+p.getData_nascimento().getYear()+"%"+p.getLocalizacao().getLat()+"%"+p.getLocalizacao().getLongi()+"%"+p.getSaldo());
            }

        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }

                if (fw != null) {
                    fw.close();
                }

            } catch (IOException e) {
                System.out.println(e);

            }
        }
    }
}
    
    

