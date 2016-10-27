package edu.ufp.inf.lp2.Projecto;

import edu.ufp.inf.lp2.Figgeo.Circulo;
import edu.ufp.inf.lp2.Figgeo.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Paragem implements java.io.Serializable {

    public static transient final Color cor = Color.BLUE;

    private int id;

    private Circulo circulo;

    private String nome;

    private Localizacao localizacao;

    private static transient int idCount = 0;

    private ArrayList<Linha> linhas = new ArrayList<>();

    private Zona zona;
    /**
     * Constutor da Classe Paragem.
     * @param nome Nome da Paragem.
     * @param localizacao Localizacao da Paragem.
     * @param zona Zona da Paragem.
     */
    public Paragem(String nome, Localizacao localizacao, Zona zona) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.zona = zona;
        this.id = idCount;
        idCount++;
    }

    /**
     * Metodo para adaptar a localizacao da paragem à dimensão da janela onde
     * irá ser representada determinando inclusive as coordenadas x e y na
     * janela a representar.
     *
     * @param w largura da janela.
     * @param h altura da janela.
     */
    protected void adaptLocationToCirculo(int w, int h) {

        Point down = Rede.downPoint;
        Point up = Rede.upPoint;

        double x = 0, y = 0;

        double aux = 0;
        aux = (double) Math.abs((localizacao.getLongi() - up.getX()) * w);
        x = (double) (aux / (down.getX() - up.getX()));

        aux = 0;

        aux = (double) Math.abs((localizacao.getLat() - down.getY()) * h);
        y = (double) (aux / (up.getY() - down.getY()));

        // y increase from north do south
        y = h - y;

        this.circulo = new Circulo(new Point(x, y), 7, Paragem.cor);

    }

    /**
     * Metodo para chamar a funcao draw da classe circulo.
     *
     * @param g Graphics
     * @throws PositionNotInitializedException
     */
    public void draw(Graphics g) throws PositionNotInitializedException {
        if (this.circulo != null) {
            this.circulo.draw(g);
        } else {
            throw new PositionNotInitializedException("Erro. Posicao do circulo ainda não inicializada!");
        }
    }

    /**
     * Metodo para associar uma linha a esta paragem.
     *
     * @param l Linha a associar.
     * @throws LinhaExistenceException
     */
    public void associarLinha(Linha l) throws LinhaExistenceException {
        for (Linha nova : linhas) {
            if (nova.getId() == l.getId()) {
                String message = "Erro. A linha a associar com o id = " + l.getId() + " ja se encontra associada a esta paragem";
                throw new LinhaExistenceException(message);
            }
        }
        linhas.add(l);
    }

    /**
     * Metodo para desassociar uma linha em relação a esta paragem.
     *
     * @param id Id da linha a desassociar
     * @throws LinhaExistenceException
     */
    public void desassociarLinha(int id) throws LinhaExistenceException {
        for (Linha s : linhas) {
            if (s.getId() == id) {
                linhas.remove(s);
                return;
            }
        }
        String message = "Erro. A linha a dessassociar com o id = " + id + " não se encontra associada a esta paragem.";
        throw new LinhaExistenceException(message);
    }

    /**
     * Metodo para retornar o id da Paragem.
     *
     * @return id Id da Paragem.
     */
    public int getId() {
        return id;
    }

    /**
     * Metodo para retornar o nome da Paragem.
     *
     * @return nome Nome da Paragem.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Metodo para retornar a Localizacao da Paragem.
     *
     * @return localizacao Localizacao da Paragem.
     */
    public Localizacao getLocalizacao() {
        return localizacao;
    }

    /**
     * Metodo para retornar as linhas associadas à Paragem.
     *
     * @return linhas Array List de Linhas.
     */
    public ArrayList<Linha> getLinhas() {
        return linhas;
    }

    /**
     * Metodo para retornar a zona a que pertence a Paragem.
     *
     * @return zona Zona da Paragem.
     */
    public Zona getZona() {
        return zona;
    }

    /**
     * Metodo para atribuir um nome à Paragem.
     *
     * @param nome Novo nome da Paragem.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo para atribuir uma localizacão à Paragem.
     *
     * @param localizacao Localização da Paragem.
     */
    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    /**
     * Metodo para atribuir uma Zona à Paragem.
     *
     * @param zona Zona da Paragem.
     */
    public void setZona(Zona zona) {
        this.zona = zona;
    }

    /**
     * Metodo para obter o objecto circulo associado à Paragem.
     *
     * @return circulo Circulo que representa a Paragem na janela de de desenho
     * de todas as Paragens.
     */
    public Circulo getCirculo() {
        return circulo;
    }

    @Override
    public String toString() {
        return "Paragem{" + "id=" + id + ", nome=" + nome + ", localizacao=" + localizacao + ", linhas=" + linhas + ", zona=" + zona + '}';
    }

}
