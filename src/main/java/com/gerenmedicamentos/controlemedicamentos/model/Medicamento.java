package com.gerenmedicamentos.controlemedicamentos.model;

import java.time.LocalTime;

public class Medicamento {

    private Long id;
    private String nome;
    private String dosagem;
    private String descricao;
    private LocalTime horario;
    private boolean ativo;

    public Medicamento(Long id, String nome, String dosagem, String descricao, LocalTime horario) {
        this.id = id;
        this.nome = nome;
        this.dosagem = dosagem;
        this.descricao = descricao;
        this.horario = horario;
        this.ativo = true; // Nasce ativo por padrão
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDosagem() { return dosagem; }
    public void setDosagem(String dosagem) { this.dosagem = dosagem; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalTime getHorario() { return horario; }
    public void setHorario(LocalTime horario) { this.horario = horario; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
