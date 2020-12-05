package br.com.radix.banco;

public class ContaDTO {
    private final String cliente;
    private final Long numero;
    private final Double saldo;

    public ContaDTO(Conta conta) {
        this.cliente = conta.getCliente();
        this.numero = conta.getNumero();
        this.saldo = conta.getSaldo();
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
