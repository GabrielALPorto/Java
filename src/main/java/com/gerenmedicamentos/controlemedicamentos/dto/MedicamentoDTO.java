package com.gerenmedicamentos.controlemedicamentos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

public record MedicamentoDTO(
    
    @NotBlank(message = "O nome do medicamento é obrigatório")
    String nome,
    
    @NotBlank(message = "A dosagem é obrigatória")
    String dosagem,
    
    String descricao,
    
    @NotNull(message = "O horário é obrigatório")
    LocalTime horario
) {}
