/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.lp2.Projecto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;



public class ExportLinhasTxt {

    /**
     * Construtor da Classe ExportLinhasTxt para armazenar informação sobre todas as linhas existentes na Rede.
     *
     * @param linhas ArrayList de Linhas a armazenar.
     * @param fileName Nome do ficheiro + extensão.
     */
    public ExportLinhasTxt(ArrayList<Linha> linhas, String fileName) {

        File f = null;
        FileWriter fw = null;
        PrintWriter pw = null;

        try {
            f = new File("/Users/joaoalves/Desktop", fileName);
            fw = new FileWriter(f);
            pw = new PrintWriter(fw);

            for (Linha l : linhas) {
                pw.println(l.getLetra() + "%" + l.getCor().getRGB()); // armazena a letra representante da Linha e a sua cor em RBG.
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
