/*
 Nome : Joao Alves
 Numero : 27785
 Data : 17/5/2016
 Descricao: Avalicaçao PR LPII
 */
package edu.ufp.inf.lp2.Projecto;

import edu.ufp.inf.lp2.intro.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Passageiro implements java.io.Serializable {

    private int id;

    private String nome;

    private Date data_nascimento;

    private Localizacao localizacao;

    private float saldo;

    private static transient int idCount = 0;

    /**
     * Construtor da classe Passageiro.
     *
     * @param nome Nome do Passageiro a adicionar.
     * @param data_nascimento Date de Nasciomento do passageiro.
     * @param loc Localização actual do Pasageiro.
     */
    public Passageiro(String nome, Date data_nascimento, Localizacao loc) {
        this.id = idCount;
        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.localizacao = loc;
        this.saldo = 0.0f;
        idCount++;
    }

    /**
     * Metodo para obter a idade do Passageiro com base na data actual em que é
     * chamada a função.
     *
     * @return idade do Passageiro.
     */
    public int getIdade() {
        int anos = 0;
        int meses = 0;
        int dias = 0;

        Calendar gregCalendar = new GregorianCalendar();
        anos = gregCalendar.get(Calendar.YEAR) - data_nascimento.getYear();
        meses = gregCalendar.get(Calendar.MONTH) + 1 - data_nascimento.getMonth();

        if (meses < 0) {
            return anos - 1;
        } else if (meses >= 1) {
            return anos;
        } else if (meses == 0) {
            dias = gregCalendar.get(Calendar.DAY_OF_MONTH) - data_nascimento.getDay();
            if (dias < 0) {
                return anos - 1;
            } else if (dias >= 0) {
                return anos;
            }
        }
        return -1;
    }

    /**
     * Metodo para obter a percentagem de Desconto que o Passageiro poderá
     * usufruir com base na sua idade.
     *
     * @return Percentagem de Desconto que o Passageiro detém.
     */
    public float obterPercentagemDisconto() {
        int idade = this.getIdade();

        if (idade >= 4 && idade <= 18) {
            return 0.25f;
        } else if (idade >= 65) {
            return 0.50f;
        } else {
            return 0.0f;
        }
    }

    /**
     * Metodo para carregar o saldo ao Pasasgeiro.
     *
     * @param valor Valor a carregar no saldo.
     * @return saldo actual.
     */
    public float carregarSaldo(float valor) {

        this.saldo = this.saldo + valor;
        return this.saldo;
    }

    /**
     * Metodo para retirar saldo à conta do Passageiro.
     *
     * @param valor Valor a retirar da conta.
     * @return Saldo actual do Passageiro.
     * @throws NotEnoughMoneyException
     */
    public float retirarSaldo(float valor) throws NotEnoughMoneyException {
        if (this.saldo >= valor) {
            this.saldo = this.saldo - valor;
        } else {
            throw new NotEnoughMoneyException("Erro. Saldo insuficiente para debito.");
        }
        return this.saldo;
    }

    /**
     * Metodo para obter o saldo do Passageiro.
     *
     * @return Saldo Actual.
     */
    public float getSaldo() {
        return this.saldo;
    }

    /**
     * Metodo para obter o id do Passageiro.
     *
     * @return Id do Passageiro.
     */
    public int getId() {
        return id;
    }

    /**
     * Metodo para obter o nome do Passageiro.
     *
     * @return Nome do Passageiro.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Metodo para obter a data de nascimento do Passageiro.
     *
     * @return Data de Nascimento.
     */
    public Date getData_nascimento() {
        return data_nascimento;
    }

    /**
     * Metodo para obter a localizacao do Passageiro.
     *
     * @return Localizacao do Passageiro.
     */
    public Localizacao getLocalizacao() {
        return localizacao;
    }

    /**
     * Metodo para actualizar a Localizacao do Passageiro.
     *
     * @param localizacao Localizacao nova do Passageiro a actualizar.
     */
    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return "Passageiro{" + "id=" + id + ", nome=" + nome + ", data_nascimento=" + data_nascimento + ", localizacao=" + localizacao + '}';
    }

}
