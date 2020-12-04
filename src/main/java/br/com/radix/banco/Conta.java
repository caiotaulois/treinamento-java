package br.com.radix.banco;

class Conta {
    private String cliente;
    private Long numero;
    private Double saldo;

    public Conta() {
    }

    public Conta(String cliente, Long numero, Double saldo) {
        this.cliente = cliente;
        this.numero = numero;
        this.saldo = saldo;
    }

    public String getCliente() {
        return cliente;
    }

    public Long getNumero() {
        return numero;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void atualizarDados(String cliente, Double saldo) {
        this.cliente = cliente;
        this.saldo = saldo;
    }

    public void realizarOperacao(Double operacao) {
        this.saldo += operacao;
    }
}
