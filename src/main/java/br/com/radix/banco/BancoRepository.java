package br.com.radix.banco;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BancoRepository {
    static List<Conta> contas = new ArrayList<>();

    public List<Conta> listarContas() {
        return contas;
    }

    public Optional<Conta> obterConta(Long numero) {
        return contas.stream().filter(conta -> conta.getNumero().equals(numero)).findFirst();
    }

    public void criarConta(Conta conta) {
        contas.add(conta);
    }

    public void deletarConta(Conta conta) {
        contas.remove(conta);
    }
}
