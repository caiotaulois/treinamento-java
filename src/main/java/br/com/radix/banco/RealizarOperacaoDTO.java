package br.com.radix.banco;

public class RealizarOperacaoDTO {
    private Long numeroContaOrigem;
    private Long numeroContaDestino;
    private Double valor;

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
