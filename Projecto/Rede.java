package edu.ufp.inf.lp2.Projecto;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.KosarajuSharirSCC;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.ufp.inf.lp2.Figgeo.Point;

import java.util.ArrayList;
import javax.swing.JTextArea;

public class Rede extends EdgeWeightedDigraph implements java.io.Serializable {

    public static final String CUSTO_TEMPORAL = "CUSTO_TEMPORAL";
    public static final String CUSTO_MONETARIO = "CUSTO_MONETARIO";
    public static final String CUSTO_DISTANCIA = "CUSTO_DISTANCIA";

    public static String selectedWeight = Rede.CUSTO_DISTANCIA;
    public static Point upPoint = null;
    public static Point downPoint = null;
    public RedBlackBST<Integer, Passageiro> passageiros;
    public KosarajuSharirSCC conexo;

    public DijkstraSP sp;

    // radius of the earth
    public static final double R = 6372.8;

    public ArrayList<Paragem> paragens = new ArrayList<>();

    public ArrayList<Linha> linhas = new ArrayList<>();

    public static ArrayList<Zona> zonas = new ArrayList<>();

    /**
     * Contrutor da classe Rede.
     *
     * @param vertices Numero de vertices que Rede irá ter.
     */
    public Rede(int vertices) {
        super(vertices);
        passageiros = new RedBlackBST<>();

    }

    /**
     * Metodo para retornar o Peso seleccionado na variavel "selectedWeight".
     *
     * @return Peso Seleccionado.
     */
    public static String getSelectedWeight() {
        return Rede.selectedWeight;
    }

    /**
     * Metodo para atribuir um peso à variavel selectedWeight. Existem 3 pesos
     * possiveis: Rede.CUSTO_MONETARIO ,Rede.CUSTO_DISTANCIA ,
     * Rede.CUSTO_TEMPORAL.
     *
     * @param selectedWeight Peso a atribuir.
     */
    public static void setSelectedWeight(String selectedWeight) {
        switch (selectedWeight) {
            case Rede.CUSTO_MONETARIO:
                Rede.selectedWeight = selectedWeight;
                break;
            case Rede.CUSTO_TEMPORAL:
                Rede.selectedWeight = selectedWeight;
                break;
            case Rede.CUSTO_DISTANCIA:
                Rede.selectedWeight = selectedWeight;
            default:
                Rede.selectedWeight = Rede.CUSTO_DISTANCIA;
        }
    }

    /**
     * Metodo para adicionar uma paragem ao array list de Paragens da classe
     * Rede.
     *
     * @param p Paragem a adicionar.
     * @throws ParagemExistenceException
     */
    public void addParagem(Paragem p) throws ParagemExistenceException {

        for (Paragem nova : paragens) {
            if (p.getNome().compareTo(nova.getNome()) == 0) {
                String message = "Erro. A paragem a adicionar com o nome = " + p.getNome() + " ja se encontra inserida na Rede.";
                throw new ParagemExistenceException(message);
            }
        }
        paragens.add(p);
        // sempre que se adiciona uma nova paragem, calcular o ponto superior mais à esquerda e o ponto inferior mais à direita com o objectivo de ir calculando o tamanho da janela minimo necessario para repreesentar todas as paragens na janela do ecrã. 
        determineMaxUpPoint(p);
        determineMaxDownPoint(p);
    }
    /*
     public void removeParagem(int id){
     Bag<DirectedEdge> bag = new Bag<>();
     for(DirectedEdge e1 : adj(id)){
     for(DirectedEdge e2 : adj(e1.to())){
     if(e2.to()!=id){
     bag.add(e2);
                   
     }
     }
       
     }
     }
     */

    /**
     * Metodo para adicionar uma linha ao Array List de Linhas da classe Rede.
     *
     * @param l Linha a adicionar.
     * @throws LinhaExistenceException
     */
    public void addLinha(Linha l) throws LinhaExistenceException {
        for (Linha nova : linhas) {
            if (l.getId() == nova.getId()) {
                String message = "Erro. A Linha a adicionar com o id = " + l.getId() + " ja se encontra inserida na Rede.";
                throw new LinhaExistenceException(message);
            }
        }
        linhas.add(l);
    }

    /**
     * Metodo para determinar o ponto inferior mais à direita da janela para
     * representação gráfica de todas as paragens posteiormente.
     *
     * @param p Paragem que acabou de ser adicionada.
     */
    public void determineMaxDownPoint(Paragem p) {
        if (downPoint == null) {
            downPoint = new Point(p.getLocalizacao().getLongi(), p.getLocalizacao().getLat());
            return;
        }
        if (p.getLocalizacao().getLongi() <= downPoint.getX() && p.getLocalizacao().getLat() <= downPoint.getY()) {
            // downPoint.setX(p.getLocalizacao().getLongi());
            downPoint.setY(p.getLocalizacao().getLat());

        } else if (p.getLocalizacao().getLongi() >= downPoint.getX() && p.getLocalizacao().getLat() >= downPoint.getY()) {
            downPoint.setX(p.getLocalizacao().getLongi());
            //downPoint.setY(p.getLocalizacao().getLat());

        } else if (p.getLocalizacao().getLongi() >= downPoint.getX() && p.getLocalizacao().getLat() <= downPoint.getY()) {
            downPoint.setX(p.getLocalizacao().getLongi());
            downPoint.setY(p.getLocalizacao().getLat());

        }
    }

    /**
     * Metodo para determinar o ponto supeior mais à esquerda da janela para
     * representação gráfica de todas as paragens posteiormente.
     *
     * @param p Paragem que acabou de ser adicionada.
     */
    public void determineMaxUpPoint(Paragem p) {
        if (upPoint == null) {
            upPoint = new Point(p.getLocalizacao().getLongi(), p.getLocalizacao().getLat());
            return;
        }
        if (p.getLocalizacao().getLongi() <= upPoint.getX() && p.getLocalizacao().getLat() <= upPoint.getY()) {
            upPoint.setX(p.getLocalizacao().getLongi());
            //upPoint.setY(p.getLocalizacao().getLat());

        } else if (p.getLocalizacao().getLongi() >= upPoint.getX() && p.getLocalizacao().getLat() >= upPoint.getY()) {
            //upPoint.setX(p.getLocalizacao().getLongi());
            upPoint.setY(p.getLocalizacao().getLat());

        } else if (p.getLocalizacao().getLongi() <= upPoint.getX() && p.getLocalizacao().getLat() >= upPoint.getY()) {
            upPoint.setX(p.getLocalizacao().getLongi());
            upPoint.setY(p.getLocalizacao().getLat());

        }
    }

    /**
     * Metodo para adicionar uma ligacao entre duas paragens no Grafo.
     *
     * @param l Ligacao entre 2 paragens.
     */
    public void addEdgeToDigraph(Ligacao l) {
        super.addEdge(l);

    }

    /**
     * Metodo para quando o passageiro pretende efectuar uma viagem.
     *
     * @param p Passageiro a efectuar a viagem.
     * @param ta JTextArea onde irá ser apresentado o plano da viagem.
     * @param locActual Localizacao actual/origem onde o passageiro se encontra.
     * @param destino Localizacao de destino para onde o passageiro pretende
     * deslocar.
     * @param custo String contendo o custo(selectedWeight) selecionado para a
     * Viagem.
     */
    public void passageiroEfectuaViagem(Passageiro p, JTextArea ta, Localizacao locActual, Localizacao destino, String custo) {

        float precoViagem = simularViagem(p, ta, locActual, destino, custo);

        float preco = precoViagem - (precoViagem * p.obterPercentagemDisconto());
        // arredonda o preco para 2 casas decimais
        float precoFinal = ((float) Math.round(preco * 100.0f) / 100.0f);
        System.out.printf("Preco da Viagem para o Passageiro %s  = %.2f €\n", p.getNome(), precoFinal);
        try {
            p.retirarSaldo(precoFinal);

        } catch (NotEnoughMoneyException ex) {
            String message = "Erro. Nao tem saldo suficiente para efectuar esta Viagem. Por favor carregue a sua conta.";
            System.out.println(message);
        }
    }

    /**
     * Metodo para simular uma viagem de menor custo com base no peso
     * selecionado pelo Passageiro.
     *
     * @param p Passageiro a efectuar a viagem.
     * @param ta JTextArea onde irá ser apresentado o plano da viagem.
     * @param locActual Localizacao actual/origem onde o passageiro se encontra.
     * @param destino Localizacao de destino para onde o passageiro pretende
     * deslocar.
     * @param custo String contendo o custo(selectedWeight) selecionado para a
     * Viagem.
     * @return preco da Viagem sem associar o Passageiro.
     */
    public float simularViagem(Passageiro p, JTextArea ta, Localizacao locActual, Localizacao destino, String custo) {

        // determinar a paragem mais proxima da localizacao do Passageiro. 
        Paragem pinicial = paragemMaisProxima(locActual);
        // determinar a paragem mais proxia da localizacao de destino do Passageiro.
        Paragem pfinal = paragemMaisProxima(destino);

        String representCusto = " ";
        if (custo.compareTo(Rede.CUSTO_MONETARIO) == 0) {
            representCusto = "€";
        } else if (custo.compareTo(Rede.CUSTO_TEMPORAL) == 0) {
            representCusto = "min";
        } else if (custo.compareTo(Rede.CUSTO_DISTANCIA) == 0) {
            representCusto = "km";
        }

        Rede.selectedWeight = custo;
        float precoViagem = 0.0f;
        float precoLigacao = 0.0f;
        String concatenate = "";

        try {
            sp = new DijkstraSP(this, pinicial.getId());
            for (DirectedEdge e : sp.pathTo(pfinal.getId())) {
                System.out.println(e.from() + "->" + e.to() + " " + (float) e.weight() + "" + representCusto);
                System.out.println(this.paragens.get(e.from()).getNome() + " -> " + this.paragens.get(e.to()).getNome());
                //ta.setText(this.paragens.get(e.from()).getNome() + " -> " + this.paragens.get(e.to()).getNome());
                concatenate = concatenate + this.paragens.get(e.from()).getNome() + " -> " + this.paragens.get(e.to()).getNome() + "\n";
                Ligacao aux = ((Ligacao) e);
                precoLigacao = aux.getCusto(Rede.CUSTO_MONETARIO);
                precoViagem += precoLigacao;
            }
            float precoPass = precoViagem - (precoViagem * p.obterPercentagemDisconto());
            // arredonda o preco para 2 casas decimais
            float precoFinal = ((float) Math.round(precoPass * 100.0f) / 100.0f);
            String precoSemPass = "Preco sem associar o passageiro : " + precoViagem + "€";
            String precoComPass = "Preco associando o passageiro : " + precoFinal + "€";

            concatenate = concatenate + "\n" + precoSemPass + "\n" + precoComPass;
            ta.setText(concatenate);

        } catch (CustoHashMapExistenceException e) {
            System.out.println(e);
        }
        return (float) precoViagem;
    }

    /**
     * Metodo para quando o passageiro pretende efectuar uma viagem.
     *
     * @param p Passageiro a efectuar a viagem.
     * @param ta JTextArea onde irá ser apresentado o plano da viagem.
     * @param o Id da Paragem de origem.
     * @param d Id da Paragem de destino.
     * @param custo String contendo o custo(selectedWeight) selecionado para a
     * Viagem.
     */
    public void passageiroEfectuaViagem(Passageiro p, JTextArea ta, int o, int d, String custo) {

        float precoViagem = simularViagem(p, ta, o, d, custo);

        float preco = precoViagem - (precoViagem * p.obterPercentagemDisconto());
        // arredonda o preco para 2 casas decimais
        float precoFinal = ((float) Math.round(preco * 100.0f) / 100.0f);
        System.out.printf("Preco da Viagem para o Passageiro %s  = %.2f € \n", p.getNome(), precoFinal);
        try {
            p.retirarSaldo(precoFinal);

        } catch (NotEnoughMoneyException ex) {
            String message = "Erro. Nao tem saldo suficiente para efectuar esta Viagem. Por favor carregue a sua conta.";
            System.out.println(message);
        }
    }

    /**
     * Metodo para simular uma viagem de menor custo com base no peso
     * selecionado pelo Passageiro.
     *
     * @param p Passageiro a efectuar a viagem.
     * @param ta JTextArea onde irá ser apresentado o plano da viagem.
     * @param o Id da Paragem de origem.
     * @param d Id da Paragem de destino.
     * @param custo String contendo o custo(selectedWeight) selecionado para a
     * Viagem.
     * @return preco da Viagem sem associar o Passageiro.
     */
    public float simularViagem(Passageiro p, JTextArea ta, int o, int d, String custo) {

        String representCusto = " ";
        if (custo.compareTo(Rede.CUSTO_MONETARIO) == 0) {
            representCusto = "€";
        } else if (custo.compareTo(Rede.CUSTO_TEMPORAL) == 0) {
            representCusto = "min";
        } else if (custo.compareTo(Rede.CUSTO_DISTANCIA) == 0) {
            representCusto = "km";
        }
        Rede.selectedWeight = custo;
        System.out.println(Rede.selectedWeight);
        float precoViagem = 0.0f;
        float precoLigacao = 0.0f;
        String concatenate = "";
        try {
            sp = new DijkstraSP(this, o);
            for (DirectedEdge e : sp.pathTo(d)) {
                System.out.println(e.from() + "->" + e.to() + " " + (float) e.weight() + "" + representCusto);
                System.out.println(this.paragens.get(e.from()).getNome() + " -> " + this.paragens.get(e.to()).getNome());
                //ta.setText(this.paragens.get(e.from()).getNome() + " -> " + this.paragens.get(e.to()).getNome());
                concatenate = concatenate + this.paragens.get(e.from()).getNome() + " -> " + this.paragens.get(e.to()).getNome() + "\n";
                Ligacao aux = ((Ligacao) e);
                precoLigacao = aux.getCusto(Rede.CUSTO_MONETARIO);
                precoViagem += precoLigacao;
            }
            float precoPass = precoViagem - (precoViagem * p.obterPercentagemDisconto());
            // arredonda o preco para 2 casas decimais
            float precoFinal = ((float) Math.round(precoPass * 100.0f) / 100.0f);

            String precoSemPass = "Preco sem associar o passageiro : " + precoViagem + "€";
            String precoComPass = "Preco associando o passageiro : " + precoFinal + "€";

            concatenate = concatenate + "\n" + precoSemPass + "\n" + precoComPass;
            ta.setText(concatenate);
        } catch (CustoHashMapExistenceException e) {
            System.out.println(e);
        }

        return (float) precoViagem;
    }

    /**
     * Metodo para encontrar a paragem mais proxima de um determinado ponto
     * geografico.
     *
     * @param LocalActual Localizacao do ponto geográfico.
     * @return Paragem mais proxima desse Ponto Geográfico.
     */
    public Paragem paragemMaisProxima(Localizacao LocalActual) {
        Paragem pMaisProx = null;
        double dist = 0;
        double disMin = 999999999.9;

        for (Paragem p : this.paragens) {
            dist = distanciaCoordenadasDecimais(LocalActual.getLat(), LocalActual.getLongi(), p.getLocalizacao().getLat(), p.getLocalizacao().getLongi());
            if (dist < disMin) {
                pMaisProx = p;
                disMin = dist;
            }
        }
        return pMaisProx;
    }

    /**
     * Metodo para calcular a distancia em km entre 2 coordenadas.
     *
     * @param lat1 latitude da primeira localizacao
     * @param lon1 longitude da primeira localizacao
     * @param lat2 latitude da segunda localizacao
     * @param lon2 longitude da segundas localizacao
     * @return distancia em km.
     */
    public double distanciaCoordenadasDecimais(double lat1, double lon1, double lat2, double lon2) {

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double d = R * c;
        return d;
    }

    /**
     * Metodo para retornar as Paragens presentes numa dada Linha.
     *
     * @param l Linha onde se pretende procurar as paragens existentes.
     * @return Array List das Paragens presentes na Linha.
     */
    public ArrayList<Paragem> paragensNumaLinha(Linha l) {
        ArrayList<Paragem> nestalinha = l.getParagens();
        return nestalinha;
    }

    /**
     * Metodo para retornar as Paragens presentes numa dada Zona.
     *
     * @param z Zona onde se pretende procurar as paragens existentes.
     * @return Array List das Paragens presentes na Zona.
     */
    public ArrayList<Paragem> paragensNumaZona(Zona z) {
        ArrayList<Paragem> paragenszona = new ArrayList<>();
        for (Paragem nova : paragens) {
            if (nova.getZona().getId() == z.getId()) {
                paragenszona.add(nova);
            }
        }
        return paragenszona;
    }

    /**
     * Metodo para remover uma Passageiro da RedBlackBST de passageiros.
     *
     * @param id Id do passageiro a remover.
     * @param name Nome do Passageiro a remover.
     * @throws PassageiroNotFoundException
     */
    public void removePassageiro(int id, String name) throws PassageiroNotFoundException {

        Passageiro checkP = passageiros.get(id);
        if (checkP == null) {
            String message = "Erro. O passageiro com existe. Impossivel remove-lo.";
            throw new PassageiroNotFoundException(message);
        } else if (checkP.getNome().compareTo(name) == 0) {
            passageiros.delete(id);
            System.out.println("Passageiro removido com sucesso");
        } else {
            String message = "Erro. O passageiro nao existe. Impossivel remove-lo.";
            throw new PassageiroNotFoundException(message);
        }
    }

    /**
     * Metodo para adicionar um novo Passageiro à RedBlackBST de Passageiros.
     *
     * @param p Passageiro a adicionar.
     * @throws PassageiroAlreadyExistException
     */
    public void addPassageiro(Passageiro p) throws PassageiroAlreadyExistException {

        Boolean bool = passageiros.contains(p.getId());
        if (bool == false) {
            passageiros.put(p.getId(), p);
            System.out.println("Passageiro inserido com sucesso. id do Cliente : " + p.getId());
        } else {
            String message = "Erro. O passageiro com indentificao : " + p.getId() + " ja existe.";
            throw new PassageiroAlreadyExistException(message);
        }
    }

    /**
     * Metodo para retornar uma passageiro na RedBlackBST.
     *
     * @param id Id do Passageiro a obter.
     * @return Passageiro encontrado na RedBlackBST.
     * @throws PassageiroNotFoundException
     */
    public Passageiro getPassageiro(Integer id) throws PassageiroNotFoundException {
        Passageiro p = null;
        p = passageiros.get(id);
        if (p == null) {
            String message = "Erro. O passageiro com indentificacao : " + id + " nao existe.";
            throw new PassageiroNotFoundException(message);
        }
        return p;

    }

    /**
     * Metodo para remover uma Linha do ArrayList de Linhas da class Rede.
     *
     * @param id Id da linha a removel.
     * @throws LinhaExistenceException
     */
    public void removeLinha(int id) throws LinhaExistenceException {
        for (Linha l : linhas) {
            if (l.getId() == id) {
                linhas.remove(id);
                // a todas a paragens da linha l , desassociar a propria linha pois irá ser removida.
                for (Paragem p : l.getParagens()) {
                    p.desassociarLinha(id);
                }
            }
        }
        String message = "Erro. A linha a dessassociar com o id = " + id + " não se encontra registada.";
        throw new LinhaExistenceException(message);
    }

    /**
     * Metodo para adaptar a Localizacao da Paragem ao tamanho da janela do ecrã
     * onde irão ser representadas as paragens.
     *
     * @param screenWidht Largura da janela do ecra.
     * @param screenHeight Altura da janela do ecra.
     *
     */
    public void adaptsarPosicaoParagens(int screenWidht, int screenHeight) {
        for (Paragem p : this.paragens) {
            p.adaptLocationToCirculo(screenWidht, screenHeight);
        }
    }

    /**
     * Metodo para determinar quais os Passageiros cuja idade é inferior a x ou
     * superior a y.
     *
     * @param x Idade limite.
     * @param y Idade limite.
     * @return Array de Passageiros que cumprem o critério.
     */
    public ArrayList<Passageiro> AgeLessXorMoreY(int x, int y) {
        ArrayList<Passageiro> array = new ArrayList<>();
        for (int i = 0; i < passageiros.size(); i++) {
            Passageiro paux = passageiros.get(i);
            if (paux.getIdade() < x || paux.getIdade() > y) {
                array.add(paux);
            }
        }
        return array;
    }

    /**
     * Metodo para verificar se o EdgeWeightedDigraph é conexo ou não.
     *
     * @param G EdgeWeightedDigraph.
     * @return True or False.
     */
    public Boolean isConexo(EdgeWeightedDigraph G) {
        // Compute the strongly-connected components of a digraph using the Kosaraju-Sharir algorithm.
        Digraph grafo = new Digraph(G.V());
        for (int i = 0; i < G.V(); i++) {
            for (DirectedEdge de : G.adj(i)) {
                grafo.addEdge(de.from(), de.to());
            }
        }

        conexo = new KosarajuSharirSCC(grafo);
        System.out.println("ssc" + conexo.count()); // retorna o numero de strong components.
        return conexo.count() == 1;
    }

}
