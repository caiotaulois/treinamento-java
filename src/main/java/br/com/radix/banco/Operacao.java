package br.com.radix.banco;

public class Operacao {
    private Long numeroContaOrigem;
    private Long numeroContaDestino;
    private Double valor;

    public Operacao() {
    }

    public Operacao(Long numeroContaOrigem, Long numeroContaDestino, Double valor) {
        this.numeroContaOrigem = numeroContaOrigem;
        this.numeroContaDestino = numeroContaDestino;
        this.valor = valor;
    }

    public Long getNumeroContaOrigem() {
        return numeroContaOrigem;
    }

    public Long getNumeroContaDestino() {
        return numeroContaDestino;
    }

    public Double getValor() {
        return valor;
    }
}
