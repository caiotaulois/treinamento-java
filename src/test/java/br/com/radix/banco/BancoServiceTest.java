package br.com.radix.banco;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class BancoServiceTest {

    @Test
    public void depositar() {
        BancoRepository repo = Mockito.mock(BancoRepository.class);
        Conta conta = new Conta("cliente 1", 1L);
        Mockito.when(repo.obterConta(1L)).thenReturn(Optional.of(conta));
        Mockito.when(repo.obterConta(null)).thenReturn(Optional.empty());
        BancoService service = new BancoService(repo);
        service.realizarOperacao(conta.getNumero(), null, 10.0);
        assertEquals(10.0, conta.getSaldo(), 0.01);
    }

    @Test
    public void sacar() {
        BancoRepository repo = Mockito.mock(BancoRepository.class);
        Conta conta = new Conta("cliente 1", 1L);
        Mockito.when(repo.obterConta(1L)).thenReturn(Optional.of(conta));
        Mockito.when(repo.obterConta(null)).thenReturn(Optional.empty());
        BancoService service = new BancoService(repo);
        service.realizarOperacao(conta.getNumero(), null, -10.0);
        assertEquals(-10.0, conta.getSaldo(), 0.01);
    }

    @Test
    public void transferir() {
        BancoRepository repo = Mockito.mock(BancoRepository.class);
        Conta conta1 = new Conta("cliente 1", 1L);
        Conta conta2 = new Conta("cliente 2", 2L);
        Mockito.when(repo.obterConta(1L)).thenReturn(Optional.of(conta1));
        Mockito.when(repo.obterConta(2L)).thenReturn(Optional.of(conta2));
        BancoService service = new BancoService(repo);
        service.realizarOperacao(1L, 2L, 10.0);
        assertEquals(-10.0, conta1.getSaldo(), 0.01);
        assertEquals(10.0, conta2.getSaldo(), 0.01);
    }
}
