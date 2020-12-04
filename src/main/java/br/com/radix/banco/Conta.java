package br.com.radix.banco;

import java.util.ArrayList;
import java.util.List;

class Conta {
    private String cliente;
    private Long numero;
    private List<Operacao> operacoes;

    public Conta() {
        this.operacoes = new ArrayList<>();
    }

    public Conta(String cliente, Long numero) {
        this.cliente = cliente;
        this.numero = numero;
        this.operacoes = new ArrayList<>();
    }

    public String getCliente() {
        return cliente;
    }

    public Long getNumero() {
        return numero;
    }

    public List<Operacao> getOperacoes() {
        return operacoes;
    }

    public Double getSaldo() {
        return this.operacoes.stream().mapToDouble(Operacao::getValor).sum();
    }

    public void atualizarDados(String cliente) {
        this.cliente = cliente;
    }

    public void realizarOperacao(Operacao operacao) {
        this.operacoes.add(operacao);
    }
}
