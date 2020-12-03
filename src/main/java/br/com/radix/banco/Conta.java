package br.com.radix.banco;

class Conta {
    private String cliente;
    private Long numero;
    private Double saldo;

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
}