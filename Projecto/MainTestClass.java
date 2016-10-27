/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.lp2.Projecto;

/**
 *
 * @author joaoalves
 */
import edu.ufp.inf.lp2.intro.Date;
import java.awt.Color;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

public class MainTestClass {

    public static final double R = 6372.8;

    public static void main(String[] args) {

        Passageiro pass0 = new Passageiro("joao Alves", new Date(1, 2, 2006), new Localizacao(41.182600, -8.589700));
        Passageiro pass1 = new Passageiro("Jessica", new Date(1, 2, 1993), new Localizacao(41.210476, -8.710560));
        Passageiro pass2 = new Passageiro("galocha", new Date(1, 2, 1993), new Localizacao(41.210476, -8.710560));

        System.out.println("idade do joao alves " + pass0.getIdade());
        System.out.println("");

        Linha l1 = new Linha("a", Color.RED);
        Linha l2 = new Linha("b", Color.BLUE);
        Linha l3 = new Linha("c", Color.BLACK);

        Zona z1 = new Zona("c1", 0.10f);
        Zona z2 = new Zona("c2", 0.20f);

        // criar paragems
        Paragem p0 = new Paragem("Trindade", new Localizacao(41.205789, -8.677817), z1);
        Paragem p1 = new Paragem("Salgueiros", new Localizacao(41.198556, -8.658248), z1);
        Paragem p2 = new Paragem("IPO", new Localizacao(41.182602, -8.589766), z1);
        Paragem p3 = new Paragem("Sao Joao", new Localizacao(41.149637, -8.582556), z1);
        Paragem p4 = new Paragem("Areosa", new Localizacao(41.145759, -8.577749), z1);

        // criar ligacoes
        Ligacao l01 = new Ligacao(p0.getId(), p1.getId(), 10.0);
        Ligacao l10 = new Ligacao(p1.getId(), p0.getId(), 10.0);

        Ligacao l02 = new Ligacao(p0.getId(), p2.getId(), 40.0);
        Ligacao l20 = new Ligacao(p2.getId(), p0.getId(), 40.0);

        Ligacao l13 = new Ligacao(p1.getId(), p3.getId(), 20.0);
        Ligacao l31 = new Ligacao(p3.getId(), p1.getId(), 20.0);

        Ligacao l23 = new Ligacao(p2.getId(), p3.getId(), 50.0);
        Ligacao l32 = new Ligacao(p3.getId(), p2.getId(), 50.0);

        Ligacao l34 = new Ligacao(p3.getId(), p4.getId(), 40.0);
        Ligacao l43 = new Ligacao(p4.getId(), p3.getId(), 40.0);

        // criar objecto do tipo Rede com 5 paragens
        Rede r1 = new Rede(5);

        // add pasageiros
        try {
            r1.addPassageiro(pass0);
            r1.addPassageiro(pass1);
            r1.addPassageiro(pass2);
        } catch (PassageiroAlreadyExistException ex) {
            Logger.getLogger(MainTestClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        // add paragens to array list 
        try {
            r1.addParagem(p0);
            r1.addParagem(p1);
            r1.addParagem(p2);
            r1.addParagem(p3);
            r1.addParagem(p4);
        } catch (ParagemExistenceException paee) {
            System.out.println(paee);
        }

        try {
            r1.addLinha(l1);
            r1.addLinha(l2);
            r1.addLinha(l3);
        } catch (LinhaExistenceException ex) {
            //Logger.getLogger(JFrameFormGraph.class.getName()).log(Level.SEVERE, null, ex);
        }

        // add ligacoes to the EdgeWeightedDigraph
        r1.addEdgeToDigraph(l01);
        r1.addEdgeToDigraph(l10);

        r1.addEdgeToDigraph(l02);
        r1.addEdgeToDigraph(l20);

        r1.addEdgeToDigraph(l13);
        r1.addEdgeToDigraph(l31);

        r1.addEdgeToDigraph(l23);
        r1.addEdgeToDigraph(l32);
        r1.addEdgeToDigraph(l43);
        r1.addEdgeToDigraph(l34);

       // adicionar custo TEMPORAL à hasmap--------------------------------------------
        l01.addCustoHashMap(Rede.CUSTO_TEMPORAL, 4.0f);
        l10.addCustoHashMap(Rede.CUSTO_TEMPORAL, 4.0f);

        l02.addCustoHashMap(Rede.CUSTO_TEMPORAL, 1.0f);
        l20.addCustoHashMap(Rede.CUSTO_TEMPORAL, 1.0f);

        l13.addCustoHashMap(Rede.CUSTO_TEMPORAL, 5.0f);
        l31.addCustoHashMap(Rede.CUSTO_TEMPORAL, 5.0f);

        l23.addCustoHashMap(Rede.CUSTO_TEMPORAL, 2.0f);
        l32.addCustoHashMap(Rede.CUSTO_TEMPORAL, 2.0f);

        l34.addCustoHashMap(Rede.CUSTO_TEMPORAL, 3.0f);
        l43.addCustoHashMap(Rede.CUSTO_TEMPORAL, 3.0f);

        // adicionar custo MONETARIO à hasmap é obrigatorio para o correto funcionamento--------------------------------------------
        l01.addCustoHashMap(Rede.CUSTO_MONETARIO, 0.10f);
        l10.addCustoHashMap(Rede.CUSTO_MONETARIO, 0.10f);

        l02.addCustoHashMap(Rede.CUSTO_MONETARIO, 0.15f);
        l20.addCustoHashMap(Rede.CUSTO_MONETARIO, 0.15f);

        l13.addCustoHashMap(Rede.CUSTO_MONETARIO, 0.12f);
        l31.addCustoHashMap(Rede.CUSTO_MONETARIO, 0.12f);

        l23.addCustoHashMap(Rede.CUSTO_MONETARIO, 0.05f);
        l32.addCustoHashMap(Rede.CUSTO_MONETARIO, 0.05f);

        l34.addCustoHashMap(Rede.CUSTO_MONETARIO, 0.10f);
        l43.addCustoHashMap(Rede.CUSTO_MONETARIO, 0.10f);

        // carregar saldo do passageiro antes de efectuar viagem
        pass0.carregarSaldo(10.0f);
        JTextArea ja = new JTextArea();
        // simular viagem introduzindo o id da paragen de origem e destino 
        System.out.println("Custo Temporal");
        r1.passageiroEfectuaViagem(pass0, ja, 0, 4, Rede.CUSTO_TEMPORAL);
        System.out.println("_________________________________________________");
        System.out.println("Custo  Distancia");
        r1.passageiroEfectuaViagem(pass0, ja, 0, 4, Rede.CUSTO_DISTANCIA);

        // testar as distancias entre 2 cordenadas
        System.out.println("");
        System.out.println("Verificar Distancia entre 2 coordenadas");
        r1.distanciaCoordenadasDecimais(41.166387, -8.679877, 41.163013, -8.660382);

        // ver qual o Ponto up e Ponto down no conjunto de todas as paragens
        System.out.println("Ponto up  = " + Rede.upPoint.getY() + " | " + Rede.upPoint.getX());
        System.out.println("Ponto down = " + Rede.downPoint.getY() + " | " + Rede.downPoint.getX());

        System.out.println("_________________________________________________");

       // simular uma viagem com base na localizacao de origem do Cliente e com base na localizacao de destino pretendido pelo cliente.
        //r1.passageiroEfectuaViagem(pass0,pass1.getLocalizacao(), new Localizacao(41.144741,-8.570701), Rede.CUSTO_TEMPORAL);
        System.out.printf("Saldo do passageiro pass0 = %.2f \n", pass0.getSaldo());
        // test redBlack
        try {
            System.out.println(r1.getPassageiro(2));
        } catch (PassageiroNotFoundException ex) {
            System.out.println(ex);
        }
        // teste ponto R6)c)
        r1.AgeLessXorMoreY(18, 65);

        // verifica se o digrafo é conexo.
        System.out.println("Digrafo conexo ?" + r1.isConexo(r1));

      //outputObjectGraph oog = new outputObjectGraph(r1);
        //inputObjectGraph iog = new inputObjectGraph();
    }
}
