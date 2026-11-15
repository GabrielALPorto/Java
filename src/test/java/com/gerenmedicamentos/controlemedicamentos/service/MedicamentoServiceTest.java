package com.gerenmedicamentos.controlemedicamentos.service;

import com.gerenmedicamentos.controlemedicamentos.dto.MedicamentoDTO;
import com.gerenmedicamentos.controlemedicamentos.exception.RegraDeNegocioException;
import com.gerenmedicamentos.controlemedicamentos.model.Medicamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class MedicamentoServiceTest {

    private MedicamentoService service;
    
    @BeforeEach
    void setUp() {
        // Cria uma nova instância do service antes de cada teste
        service = new MedicamentoService();
    }
    
    @Test
    @DisplayName("Deve cadastrar um medicamento com sucesso e gerar ID")
    void deveCadastrarMedicamento(){
        // Arrange
        MedicamentoDTO dto = new MedicamentoDTO("Donepezila", "10mg", "Tomar após o café", LocalTime.of(8, 0));

        // Act
        Medicamento resultado = service.cadastrar(dto);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Donepezila", resultado.getNome());
        assertTrue(resultado.isAtivo());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar cadastrar medicamento duplicado")
    void deveLancarExcecaoAoDuplicar() {
        // Arrange
        MedicamentoDTO dto = new MedicamentoDTO("Donepezila", "null", "Tomar após o café", LocalTime.of(8, 0));
        service.cadastrar(dto); // Cadastra o primeiro

        // Act & Assert
        // Verifica se, ao tentar cadastrar o mesmo medicamento, lança a nossa exceção
        assertThrows(RegraDeNegocioException.class, () -> {
            service.cadastrar(dto);
        });
    }

    @Test
    @DisplayName("Deve listar apenas medicamentos ativos")
    void deveListarApenasAtivos(){
        // Arrange
        MedicamentoDTO dto1 = new MedicamentoDTO("Donezepila", "10mg", "Tomar após o café", LocalTime.of(8,0));
        MedicamentoDTO dto2 = new MedicamentoDTO("Memantina", "20mg", "Tomar à noite", LocalTime.of(20,0));

        Medicamento med1 = service.cadastrar(dto1);
        service.cadastrar(dto2);

        // Desativa o primeiro soft delete
        service.deletar(med1.getId());

        // Act
        List<Medicamento> ativos = service.listarTodos();

        // Assert
        assertEquals(1, ativos.size());
        assertEquals("Memantina", ativos.get(0).getNome());
    }

    @Test
    @DisplayName("Deve buscar um medicamento pelo ID")
    void deveBuscarPorId() {
        // Arrange
        MedicamentoDTO dto = new MedicamentoDTO("Donepezila", "10mg", "Tomar após o café", LocalTime.of(8,0));
        Medicamento criado = service.cadastrar(dto);

        // Act
        Optional<Medicamento> encontrado = service.buscarPorId(criado.getId());

        // Assert
        assertTrue(encontrado.isPresent());
        assertEquals("Donepezila", encontrado.get().getNome());
    }

    @Test
    @DisplayName("Deve atualizar os dados de um medicamento")
    void deveAtualizarMedicamento() {
        // Arrange
        MedicamentoDTO dto = new MedicamentoDTO("Donepezila", "10mg", "Tomar após o café", LocalTime.of(8,0));
        Medicamento criado = service.cadastrar(dto);

        MedicamentoDTO dtoAtualizado = new MedicamentoDTO("Donepezila", "5mg", "Dose reduzida", LocalTime.of(9, 0));

        // Act
        Medicamento atualizado = service.atualizar(criado.getId(), dtoAtualizado);

        // Assert
        assertEquals("5mg", atualizado.getDosagem());
        assertEquals(LocalTime.of(9, 0), atualizado.getHorario());
    }
}
