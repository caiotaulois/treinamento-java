package br.com.radix.banco;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BancoService {
    private static List<Conta> contas = new ArrayList<>();

    public List<Conta> listarContas() {
        return contas;
    }

    public Conta obterConta(Long numero) {

        Optional<Conta> contaEncontrada = contas.stream().filter(conta -> conta.getNumero().equals(numero)).findFirst();
        if (contaEncontrada.isPresent()) {
            return contaEncontrada.get();
        }
        throw new NotFoundException();
    }

    public void criarConta(Conta conta) {

        contas.add(conta);
    }

    public void editarConta(Long numero, String novoCliente) {

        Optional<Conta> contaEditada = contas.stream().filter(conta -> conta.getNumero().equals(numero)).findFirst();
        contaEditada.orElseThrow(NotFoundException::new).atualizarDados(novoCliente);
    }

    public void deletarConta(Long numero) {

        Optional<Conta> contaExcluida = contas.stream().filter(conta -> conta.getNumero().equals(numero)).findFirst();
        contas.remove(contaExcluida.orElseThrow(NotFoundException::new));
    }

    public List<Operacao> listarOperacoes(Long numeroConta) {

        Optional<Conta> contaEncontrada = contas.stream().filter(conta -> conta.getNumero().equals(numeroConta)).findFirst();
        if (contaEncontrada.isPresent()) {
            return contaEncontrada.get().getOperacoes();
        }
        throw new NotFoundException();
    }

    public void realizarOperacao(Long numeroContaOrigem, Long numeroContaDestino, Double valor) {
        Optional<Conta> contaOrigem = contas.stream().filter(conta -> conta.getNumero().equals(numeroContaOrigem)).findFirst();
        Optional<Conta> contaDestino = contas.stream().filter(conta -> conta.getNumero().equals(numeroContaDestino)).findFirst();

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
