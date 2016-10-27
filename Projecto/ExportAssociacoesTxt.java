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

public class ExportAssociacoesTxt {

    /**
     * Construtor da Classe ExportAssociacoesTxt para armazenar num ficheiro as
     * associações entre paragens e linhas existentes.
     *
     * @param paragens ArrayList de Paragens.
     * @param fileName Nome do ficheiro + extensão.
     */
    public ExportAssociacoesTxt(ArrayList<Paragem> paragens, String fileName) {

        File f = null;
        FileWriter fw = null;
        PrintWriter pw = null;

        try {
            f = new File("/Users/joaoalves/Desktop", fileName);
            fw = new FileWriter(f);
            pw = new PrintWriter(fw);

            for (Paragem p : paragens) {
                for (Linha l : p.getLinhas()) {
                    pw.println(p.getId() + "%" + l.getId()); // escreve o id da Paragem % Id da Linha associada.
                }
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
