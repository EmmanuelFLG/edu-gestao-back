package com.escola.demo.service;

import com.escola.demo.dto.HorarioAulaDTO;
import com.escola.demo.dto.HorarioAulaRequest;
import com.escola.demo.model.Alocacao;
import com.escola.demo.model.DiaSemana;
import com.escola.demo.model.HorarioAula;
import com.escola.demo.repository.AlocacaoRepository;
import com.escola.demo.repository.HorarioAulaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // --- MÉTODOS PARA O PORTAL DO ALUNO (Entity direta) ---

    public List<HorarioAula> buscarPorTurma(Long turmaId) {
        return repository.findByAlocacaoTurmaId(turmaId);
    }

    public List<HorarioAula> listarTodos() {
        return repository.findAll();
    }

    public Optional<HorarioAula> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // --- MÉTODOS ADMINISTRATIVOS (CRUD com DTO) ---

    public List<HorarioAulaDTO> listarTodosDTO() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<HorarioAulaDTO> buscarPorIdDTO(Long id) {
        return repository.findById(id).map(this::toDTO);
    }

    @Transactional
    public HorarioAulaDTO salvarDTO(HorarioAulaRequest request) {
        HorarioAula horario = fromRequest(request);
        HorarioAula salvo = repository.save(horario);
        return toDTO(salvo);
    }

    @Transactional
    public HorarioAulaDTO atualizarDTO(Long id, HorarioAulaRequest request) {
        HorarioAula existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horário não encontrado"));

        Alocacao alocacao = alocacaoRepository.findById(request.getAlocacaoId())
                .orElseThrow(() -> new RuntimeException("Alocação não encontrada"));
        
        existente.setAlocacao(alocacao);

        String dia = request.getDiaSemana().toUpperCase().trim();
        existente.setDiaSemana(DiaSemana.valueOf(dia));

        LocalTime inicio = LocalTime.parse(request.getHoraInicio());
        existente.setHoraInicio(inicio);
        existente.setHoraFim(LocalTime.parse(request.getHoraFim()));
        existente.setSala(request.getSala());

        // Atualiza o aulaNumero automaticamente na edição também
        existente.setAulaNumero(definirAulaNumero(inicio));

        HorarioAula atualizado = repository.save(existente);
        return toDTO(atualizado);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    // --- LÓGICA DE DEDUÇÃO DO NÚMERO DA AULA ---

    private Integer definirAulaNumero(LocalTime horaInicio) {
        if (horaInicio == null) return null;
        
        // Mapeamento automático baseado na hora de início
        if (horaInicio.equals(LocalTime.of(7, 0))) return 1;
        if (horaInicio.equals(LocalTime.of(7, 50))) return 2;
        if (horaInicio.equals(LocalTime.of(8, 40))) return 3;
        if (horaInicio.equals(LocalTime.of(9, 50))) return 4;
        if (horaInicio.equals(LocalTime.of(10, 40))) return 5;
        
        return 0; // Caso o horário não bata com os slots padrão
    }

    // --- MÉTODOS AUXILIARES DE CONVERSÃO ---

    private HorarioAulaDTO toDTO(HorarioAula h) {
        HorarioAulaDTO dto = new HorarioAulaDTO();
        dto.setId(h.getId());
        dto.setDiaSemana(h.getDiaSemana() != null ? h.getDiaSemana().name() : null);
        dto.setHoraInicio(h.getHoraInicio() != null ? h.getHoraInicio().toString() : null);
        dto.setHoraFim(h.getHoraFim() != null ? h.getHoraFim().toString() : null);
        dto.setSala(h.getSala());
        dto.setAulaNumero(h.getAulaNumero()); // Incluído no DTO para conferência

        if (h.getAlocacao() != null) {
            Alocacao a = h.getAlocacao();
            dto.setAlocacaoId(a.getId());
            dto.setProfessorNome(a.getProfessor() != null ? a.getProfessor().getNome() : "-");
            dto.setDisciplinaNome(a.getDisciplina() != null ? a.getDisciplina().getNome() : "-");
            dto.setTurmaNome(a.getTurma() != null ? a.getTurma().getNome() : "-");
        }
        return dto;
    }

    private HorarioAula fromRequest(HorarioAulaRequest request) {
        HorarioAula horario = new HorarioAula();
        Alocacao alocacao = alocacaoRepository.findById(request.getAlocacaoId())
                .orElseThrow(() -> new RuntimeException("Alocação não encontrada"));
        
        horario.setAlocacao(alocacao);
        
        String dia = request.getDiaSemana().toUpperCase().trim();
        horario.setDiaSemana(DiaSemana.valueOf(dia));
        
        LocalTime inicio = LocalTime.parse(request.getHoraInicio());
        horario.setHoraInicio(inicio);
        horario.setHoraFim(LocalTime.parse(request.getHoraFim()));
        horario.setSala(request.getSala());
        
        // Define o número da aula automaticamente antes de salvar
        horario.setAulaNumero(definirAulaNumero(inicio));
        
        return horario;
    }
}