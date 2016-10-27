package edu.ufp.inf.lp2.Projecto;

import java.awt.Color;
import java.util.ArrayList;

public class Linha implements java.io.Serializable {

    private int id;
    private String letra;
    private Color cor;

    private static transient int idCount = 0;

    public ArrayList<Paragem> paragens = new ArrayList<>();
    /**
     * Contrutor da Classe Linha.
     * @param letra Letra representante da Linha.
     * @param cor Cor da Linha.
     */
    public Linha(String letra, Color cor) {
        this.letra = letra;
        this.cor = cor;
        this.id = idCount;
        idCount++;
    }

    /**
     * Metodo para retornar as Paragens associadas à Linha.
     *
     * @return Array List de Paragens.
     */
    public ArrayList<Paragem> getParagens() {
        return paragens;
    }

    /**
     * Metodo para associar uma Paragem à Linha.
     *
     * @param x Paragem a associar..
     * @throws ParagemExistenceException
     */
    public void associarParagem(Paragem x) throws ParagemExistenceException {
        for (Paragem nova : paragens) {
            if (x.getId() == nova.getId()) {
                String message = "Erro. A paragem a associar com o id = " + x.getId() + " ja se encontra associada a esta linha";
                throw new ParagemExistenceException(message);
            }
        }
        paragens.add(x);

    }

    /**
     * Metodo para desassociar uma Paragem à Linha.
     *
     * @param id Id da paragem a desassociar.
     * @throws ParagemExistenceException
     */
    public void desassociarParagem(int id) throws ParagemExistenceException {
        for (Paragem nova : paragens) {
            if (nova.getId() == id) {
                paragens.remove(nova);
                return;
            }
        }
        String message = "Erro. A paragem a dessassociar com o id = " + id + " não se encontra associada a esta linha.";
        throw new ParagemExistenceException(message);
    }

    /**
     * Metodo para retornar o id da Linha.
     *
     * @return id Id da Linha.
     */
    public int getId() {
        return id;
    }

    /**
     * Metodo para retornar a cor da Linha.
     *
     * @return Objecto Cor que representa a cor da linha.
     */
    public Color getCor() {
        return cor;
    }

    /**
     * Metodo para atribuir uma cor à Linha.
     *
     * @param cor Objecto Colorque representa a nova cor da linha.
     */
    public void setCor(Color cor) {
        this.cor = cor;
    }

    /**
     * Metodo par obter a letra que representa a Linha.
     *
     * @return letra Letra da linha.
     */
    public String getLetra() {
        return letra;
    }

    /**
     * Metodo para atribuir uma letra à Linha.
     *
     * @param letra Letra da linha.
     */
    public void setLetra(String letra) {
        this.letra = letra;
    }

    @Override
    public String toString() {
        return "Linha{" + "id=" + id + ", letra=" + letra + ", cor=" + cor + ", paragens=" + paragens + '}';
    }

}
