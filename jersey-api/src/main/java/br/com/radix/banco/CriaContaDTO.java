package br.com.radix.banco;

public class CriaContaDTO {
    private String cliente;
    private Long numero;

    public String getCliente() {
        return cliente;
    }

    public Long getNumero() {
        return numero;
    }

    public Conta gerarConta() {
        return new Conta(cliente, numero);
    }
}
