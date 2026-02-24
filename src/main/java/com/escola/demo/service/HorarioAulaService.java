package com.escola.demo.service;

import com.escola.demo.dto.HorarioAulaDTO;
import com.escola.demo.dto.HorarioAulaRequest;
import com.escola.demo.model.Alocacao;
import com.escola.demo.model.DiaSemana;
import com.escola.demo.model.HorarioAula;
import com.escola.demo.repository.AlocacaoRepository;
import com.escola.demo.repository.HorarioAulaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HorarioAulaService {

    private final HorarioAulaRepository repository;
    private final AlocacaoRepository alocacaoRepository;

    public HorarioAulaService(HorarioAulaRepository repository, AlocacaoRepository alocacaoRepository) {
        this.repository = repository;
        this.alocacaoRepository = alocacaoRepository;
    }

    // --- Converter entidade para DTO ---
    private HorarioAulaDTO toDTO(HorarioAula h) {
        HorarioAulaDTO dto = new HorarioAulaDTO();
        dto.setId(h.getId());
        dto.setDiaSemana(h.getDiaSemana() != null ? h.getDiaSemana().name() : null);
        dto.setHoraInicio(h.getHoraInicio() != null ? h.getHoraInicio().toString() : null);
        dto.setHoraFim(h.getHoraFim() != null ? h.getHoraFim().toString() : null);
        dto.setSala(h.getSala());

        if (h.getAlocacao() != null) {
            Alocacao a = h.getAlocacao();
            dto.setAlocacaoId(a.getId());
            dto.setProfessorNome(a.getProfessor() != null ? a.getProfessor().getNome() : "-");
            dto.setDisciplinaNome(a.getDisciplina() != null ? a.getDisciplina().getNome() : "-");
            dto.setTurmaNome(a.getTurma() != null ? a.getTurma().getNome() : "-");
        } else {
            dto.setAlocacaoId(null);
            dto.setProfessorNome("-");
            dto.setDisciplinaNome("-");
            dto.setTurmaNome("-");
        }

        return dto;
    }

    // --- Converter DTO para entidade ---
    private HorarioAula fromRequest(HorarioAulaRequest request) {
        HorarioAula horario = new HorarioAula();

        // Buscar alocação
        Alocacao alocacao = alocacaoRepository.findById(request.getAlocacaoId())
                .orElseThrow(() -> new RuntimeException("Alocação não encontrada"));
        horario.setAlocacao(alocacao);

        // Tratar DiaSemana para aceitar minúsculas
        String dia = request.getDiaSemana().toUpperCase().trim();
        horario.setDiaSemana(DiaSemana.valueOf(dia));

        // Horários
        horario.setHoraInicio(LocalTime.parse(request.getHoraInicio()));
        horario.setHoraFim(LocalTime.parse(request.getHoraFim()));

        horario.setSala(request.getSala());
        return horario;
    }

    // --- Listar todos ---
    public List<HorarioAulaDTO> listarTodosDTO() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // --- Buscar por id ---
    public Optional<HorarioAulaDTO> buscarPorIdDTO(Long id) {
        return repository.findById(id).map(this::toDTO);
    }

    // --- Criar ---
    public HorarioAulaDTO salvarDTO(HorarioAulaRequest request) {
        HorarioAula horario = fromRequest(request);
        HorarioAula salvo = repository.save(horario);
        return toDTO(salvo);
    }

    // --- Atualizar ---
    public HorarioAulaDTO atualizarDTO(Long id, HorarioAulaRequest request) {
        HorarioAula existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horário não encontrado"));

        Alocacao alocacao = alocacaoRepository.findById(request.getAlocacaoId())
                .orElseThrow(() -> new RuntimeException("Alocação não encontrada"));
        existente.setAlocacao(alocacao);

        // Tratar DiaSemana para aceitar minúsculas
        String dia = request.getDiaSemana().toUpperCase().trim();
        existente.setDiaSemana(DiaSemana.valueOf(dia));

        existente.setHoraInicio(LocalTime.parse(request.getHoraInicio()));
        existente.setHoraFim(LocalTime.parse(request.getHoraFim()));
        existente.setSala(request.getSala());

        HorarioAula atualizado = repository.save(existente);
        return toDTO(atualizado);
    }

    // --- Deletar ---
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}