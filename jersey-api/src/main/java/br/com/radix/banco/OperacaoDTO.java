package br.com.radix.banco;

public class OperacaoDTO {
    private final Long numeroContaOrigem;
    private final Long numeroContaDestino;
    private final Double valor;

    public OperacaoDTO(Operacao operacao) {
        this.numeroContaOrigem = operacao.getNumeroContaOrigem();
        this.numeroContaDestino = operacao.getNumeroContaDestino();
        this.valor = operacao.getValor();
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
