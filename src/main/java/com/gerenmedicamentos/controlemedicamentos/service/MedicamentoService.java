package com.gerenmedicamentos.controlemedicamentos.service;

import com.gerenmedicamentos.controlemedicamentos.dto.MedicamentoDTO;
import com.gerenmedicamentos.controlemedicamentos.model.Medicamento;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MedicamentoService {

    // A tabela de Medicamentos em memória
    private final List<Medicamento> bancoEmMemoria = new ArrayList<>();
    // Gerador de IDs únicos (Thread-safe)
    private final AtomicLong contadorId = new AtomicLong(1);

    // 1. CADASTRAR
    public Medicamento cadastrar(MedicamentoDTO dto) {
        // Evitar a duplicidade (mesmo nome e dosagem)
        boolean jaExiste = bancoEmMemoria.stream()
                .anyMatch(m -> m.getNome().equalsIgnoreCase(dto.nome())
                        && m.getDosagem().equalsIgnoreCase(dto.dosagem()));

        if (jaExiste) {
            throw new RuntimeException("Já existe um medicamento com esse nome e dosagem.");
        }

        // Pegando os dados do DTO e criando a Entidade
        Medicamento novo = new Medicamento(
                contadorId.getAndIncrement(),
                dto.nome(),
                dto.dosagem(),
                dto.descricao(),
                dto.horario()
        );

        bancoEmMemoria.add(novo);
        return novo;
    }

    // 2. LISTAR TODOS (Apenas os ativos)
    public List<Medicamento> listarTodos() {
        return bancoEmMemoria.stream()
                .filter(Medicamento::isAtivo)
                .collect(Collectors.toList());
    }

    // 3. BUSCAR POR ID
    public Optional<Medicamento> buscarPorId(Long id) {
        return bancoEmMemoria.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();
    }

    // 4. ATUALIZAR
    public Medicamento atualizar(Long id, MedicamentoDTO dto) {
        Medicamento medicamento = buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Medicamento não encontrado."));

        // Atualizando os dados da entidade com os dados do DTO
        medicamento.setNome(dto.nome());
        medicamento.setDosagem(dto.dosagem());
        medicamento.setDescricao(dto.descricao());
        medicamento.setHorario(dto.horario());

        return medicamento;
    }

    // 5. DELETAR (Soft Delete)
    public void deletar(Long id) {
        Medicamento medicamento = buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Medicamento não encontrado."));

        medicamento.setAtivo(false);
    }
}