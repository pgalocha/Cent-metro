/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.lp2.Projecto;

import edu.princeton.cs.algs4.DirectedEdge;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class ExportGrafoTxt {

    /**
     * Construtor da Classe ExportGrafoTxt para armazenar o numero de vertices e
     * ligacoes, todas as ligações e os seus custos associados.
     *
     * @param r Objecto do tipo Rede.
     * @param fileName Nome do ficheiro + extensão.
     */
    public ExportGrafoTxt(Rede r, String fileName) {

        File f = null;
        FileWriter fw = null;
        PrintWriter pw = null;

        try {
            f = new File("/Users/joaoalves/Desktop", fileName);
            fw = new FileWriter(f);
            pw = new PrintWriter(fw);
            pw.println(r.V());
            pw.println(r.E());

            // selecionar o custo_distancia como o custo por defeito para o correcto funcionamento da função lig.weight().
            Rede.selectedWeight = Rede.CUSTO_DISTANCIA;
            for (DirectedEdge aux : r.edges()) {
                Ligacao lig = ((Ligacao) aux);

                try {
                    pw.println(lig.from() + " " + lig.to() + " " + lig.weight() + " " + lig.getCusto(Rede.CUSTO_MONETARIO) + " " + lig.getCusto(Rede.CUSTO_TEMPORAL));
                } catch (CustoHashMapExistenceException ex) {
                    ex.printStackTrace();
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
