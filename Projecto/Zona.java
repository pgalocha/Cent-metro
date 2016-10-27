package edu.ufp.inf.lp2.Projecto;


import java.awt.Color;
import java.util.ArrayList;

public class Zona implements java.io.Serializable{

  private int id;

  private float preco;

  private Color cor;
  
  private String nome;
  
  private static transient int idCount=0;

  private ArrayList<Paragem> paragens = new ArrayList<>();
    /**
     * Construtor da Classe Zona.
     * @param nome Nome da Zona.
     * @param preco Preço da Zona.
     */
    public Zona(String nome , float preco) {
        this.id = idCount;
        this.preco = preco;
        this.nome=nome;
        idCount++;
    }
    /**
     * Metodo para associar uma paragem a esta zona.
     * @param p Paragem a associar.
     * @throws ParagemExistenceException 
     */
    public void associarParagem(Paragem p) throws ParagemExistenceException{
        
    
        for(Paragem paux : this.paragens){
            if(paux.getId()==p.getId()){
                throw new ParagemExistenceException("Paragem com o id = " + p.getId()+" já se encontra associada a esta Zona");
            }
        }
        this.paragens.add(p);          
        
    }
    /**
     * Metodo para desassociar uma paragem a esta zona.
     * @param p Paragem a desassociar.
     * @throws ParagemExistenceException 
     */
    public void desassociarParagem(Paragem p)throws ParagemExistenceException{
        
        for(Paragem paux : this.paragens){
            if(paux.getId()==p.getId()){
                throw new ParagemExistenceException("Paragem com o id = " + p.getId()+" não se encontra associada a esta Zona");
            }
        }
        this.paragens.remove(p);      
    }
    /**
     * Metodo para retornar o array de paragens associadas a esta zona.
     * @return Array List de Paragens.
     */
     public ArrayList<Paragem> getParagens() {
        return paragens;
    }
    /**
     * Metodo para retornar o id da zona.
     * @return id Id da da Zona. 
     */
    public int getId() {
        return id;
    }
    /**
     * Metodo para retornar o preco associado à zona.
     * @return preco Preco da zona.
     */
    public float getPreco() {
        return preco;
    }
    /**
     * Metodo para atribuir um preco à zona.
     * @param preco Preco da zona.
     */
    public void setPreco(float preco) {
        this.preco = preco;
    }
    /**
     * Metodo para retornar o nome da zona.
     * @return nome Nome da zona.
     */
    public String getNome() {
        return nome;
    }
    /**
     * Metodo para atribuir o nome à zona.
     * @param nome Noma da zona.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * Metodo para retornar uma string contendo informacao do objecto zona.
     * @return string.
     */
    @Override
    public String toString() {
        return "Zona{" + "id=" + id + ", preco=" + preco + ", cor=" + cor + ", paragens=" + paragens + '}';
    }
  

}