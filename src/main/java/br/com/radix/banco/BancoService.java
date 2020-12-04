package br.com.radix.banco;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

public class BancoService {

    private final BancoRepository repo = new BancoRepository();

    public List<Conta> listarContas() {
        return repo.listarContas();
    }

    public Conta obterConta(Long numero) {

        Optional<Conta> contaEncontrada = repo.obterConta(numero);
        if (contaEncontrada.isPresent()) {
            return contaEncontrada.get();
        }
        throw new NotFoundException();
    }

    public void criarConta(Conta conta) {
        // TODO: REGRAS DE CRIACAO DE CONTA
        repo.criarConta(conta);
    }

    public void editarConta(Long numero, String novoCliente) {

        Optional<Conta> contaEditada = repo.obterConta(numero);
        contaEditada.orElseThrow(NotFoundException::new).atualizarDados(novoCliente);
    }

    public void deletarConta(Long numero) {

        Optional<Conta> contaExcluida = repo.obterConta(numero);
        repo.deletarConta(contaExcluida.orElseThrow(NotFoundException::new));
    }

    public List<Operacao> listarOperacoes(Long numeroConta) {

        Optional<Conta> contaEncontrada = repo.obterConta(numeroConta);
        if (contaEncontrada.isPresent()) {
            return contaEncontrada.get().getOperacoes();
        }
        throw new NotFoundException();
    }

    public void realizarOperacao(Long numeroContaOrigem, Long numeroContaDestino, Double valor) {
        Optional<Conta> contaOrigem = repo.obterConta(numeroContaOrigem);
        Optional<Conta> contaDestino = repo.obterConta(numeroContaDestino);

        if (!contaOrigem.isPresent()) {
            throw new NotFoundException();
        }
        if (contaDestino.isPresent()) {
            Operacao operacaoSaque = new Operacao(contaOrigem.get().getNumero(), contaDestino.get().getNumero(), -valor);
            contaOrigem.ifPresent(conta -> conta.realizarOperacao(operacaoSaque));
            Operacao operacaoDeposito = new Operacao(contaOrigem.get().getNumero(), contaDestino.get().getNumero(), valor);
            contaDestino.ifPresent(conta -> conta.realizarOperacao(operacaoDeposito));
        } else {
            Operacao operacao = new Operacao(contaOrigem.get().getNumero(), null, valor);
            contaOrigem.ifPresent(conta -> conta.realizarOperacao(operacao));
        }
    }
}
