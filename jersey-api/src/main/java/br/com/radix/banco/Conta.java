package br.com.radix.banco;

import java.util.ArrayList;
import java.util.List;

class Conta {
    private String cliente;
    private Long numero;
    private List<Operacao> operacoes;

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

    public void sacar(double valor) {
        sacar(null, valor);
    }

    public void sacar(Long numeroContaDestino, double valor) {
        realizarOperacao(this.numero, numeroContaDestino, -valor);
    }

    public void depositar(double valor) {
        depositar(this.numero, valor);
    }

    public void depositar(Long numeroContaOrigem, double valor) {
        realizarOperacao(numeroContaOrigem, this.numero, valor);
    }

    private void realizarOperacao(Long numeroContaOrigem, Long numeroContaDestino, double valor) {
        Operacao operacao = new Operacao(numeroContaOrigem, numeroContaDestino, valor);
        this.operacoes.add(operacao);
    }
}
