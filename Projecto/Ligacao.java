package edu.ufp.inf.lp2.Projecto;

import edu.princeton.cs.algs4.DirectedEdge;
import java.awt.Graphics;
import java.util.HashMap;
import edu.ufp.inf.lp2.Figgeo.Circulo;

public class Ligacao extends DirectedEdge implements java.io.Serializable {

    private HashMap<String, Float> outrosPesos;
    /**
     * Construtor da Class Ligaçao.
     * @param v id da Paragem do inicio da Ligação.
     * @param w id da Paragem do Fim da Ligação.
     * @param weight Peso principal(CUSTO_DISTANCIA) da Ligação.
     */
    public Ligacao(int v, int w, double weight) {
        super(v, w, weight);
        outrosPesos = new HashMap<String, Float>();

    }
    /**
     * Metodo para adicionar um custo à HashMap de Custos da Ligação.
     * @param k Nome do Custo a adicionar.
     * @param v Valor do custo a adicionar.
     */
    public void addCustoHashMap(String k, float v) {

        outrosPesos.put(k, v);
    }
    /**
     * Metodo para obter um determinado custo da HashMap.
     * @param k Nome do Custo a obter.
     * @return Valor do Custo pretendido.
     * @throws CustoHashMapExistenceException 
     */
    public float getCusto(String k) throws CustoHashMapExistenceException {
        if (outrosPesos.containsKey(k)) {
            return outrosPesos.get(k);
        }
        String message = "Erro. O " + k + " não se encontra ainda registado na HashMap .";
        throw new CustoHashMapExistenceException(message);
    }
    /**
     * Metodo para remover um Custo da HashMap.
     * @param k Nome do Custo a remover.
     */
    public void removeCustoHashMap(String k) {
        outrosPesos.remove(k);
    }
    /**
     * Metodo para actualizar um custo na HashMap.
     * @param k Nome do custo a actualizar.
     * @param v Novo valor do custo a actualizar.
     */
    public void updateCustoHashMap(String k, float v) {
        outrosPesos.put(k, v);
    }
    /**
     * Metodo para verificar e retornar qual o custo seleccionado na variavel selectedWeight da classe Rede.
     * @return Valor do custo selecionado.
     */
    @Override
    public double weight() {
        if (Rede.getSelectedWeight().compareTo(Rede.CUSTO_DISTANCIA) == 0) {
            return super.weight();
        }
        // retorna os pesos do hashMap com base no peso selecionado pelo atributo "selectedWeight"
        double peso = outrosPesos.get(Rede.getSelectedWeight());
        return peso;
    }
    /**
     * Metodo para desenhar uma linha recta que representa uma Ligação.
     * @param g Graphics.
     * @param p1 Paragem de Comeco da Ligacao.
     * @param p2 Paragem de Fim da Ligação.
     */
    public void draw(Graphics g, Paragem p1, Paragem p2) {

        int count = 0;
        Circulo c1 = p1.getCirculo();
        Circulo c2 = p2.getCirculo();
        int xc1 = (int) c1.getPoint().getX() + 5;// +5 para ajustar o valor de x da linha ao centro do circulo representante da paragem p1
        int yc1 = (int) c1.getPoint().getY() + 5;
        int xc2 = (int) c2.getPoint().getX() + 5;
        int yc2 = (int) c2.getPoint().getY() + 5;

        // è necessario saber a cor da linha para representa-la e verificar se as 2 Paragens encontram-se na(s) mesma(s) linha(s).
        for (Linha l1 : p1.getLinhas()) {
            for (Linha l2 : p2.getLinhas()) {
                if (l1.getId() == l2.getId()) { // se a linha for a mesma , desenhar a ligacao entre as 2 paragens com a cor da linha envolvente.
                    if (inclinacaoRetaNegativa(xc1, yc1, xc2, yc2) == true) {
                        g.setColor(l1.getCor());
                        g.drawLine(xc1 - count, yc1 + count, xc2 - count, yc2 + count);
                        count = count + 3; // variavel contadora que representa a deslocaçao das linhas a desenhar em relaçao à primeira linha desenhada. Isto para evitar que sejam desenhadas todas umas por cima das outras.
                    } else {
                        g.setColor(l1.getCor());
                        g.drawLine(xc1 + count, yc1 + count, xc2 + count, yc2 + count);
                        count = count + 3;
                    }
                }
            }
        }
    }

    /**
     * Metodo para determinar se a inclinação da reta que une dois Pontos é negativa.
     * @param x1 Coordenada x do Ponto 1.
     * @param y1 Coordenada y do Ponto 1.
     * @param x2 Coordenada x do Ponto 2.
     * @param y2 Coordenada y do Ponto 2.
     * @return True of False.
     */
    public Boolean inclinacaoRetaNegativa(int x1, int y1, int x2, int y2) {

        float i = (y2 - y1) / (x2 - x1);

        return i < 0;

    }

}
