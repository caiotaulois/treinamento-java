package br.com.radix.banco;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

public class BancoService {

    private final BancoRepository repo;

    public BancoService() {
        repo = new BancoRepository();
    }

    public BancoService(BancoRepository repoInjetado) {
        repo = repoInjetado;
    }

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
            contaOrigem.get().sacar(numeroContaDestino, valor);
            contaDestino.get().depositar(numeroContaOrigem, valor);
        } else {
            if (valor > 0.0) {
                contaOrigem.get().depositar(valor);
            } else {
                contaOrigem.get().sacar(-valor);
            }
        }
    }
}
