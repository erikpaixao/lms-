// package com.erik.gestao_aprendizagem.services;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.util.List;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageImpl;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;

// import com.erik.gestao_aprendizagem.dtos.CursoDTO;
// import com.erik.gestao_aprendizagem.repositorys.IEventoRepository;
// import com.erik.gestao_aprendizagem.services.EventoService;

// @ExtendWith(MockitoExtension.class)
// class EventoServiceTest {

// @Mock
// IEventoRepository repository;

// @InjectMocks
// EventoService service;

// @Test
// void buscarTodos_returnsPageOfEventoDTOs() {
// int page = 0;
// int count = 2;
// Pageable pageable = PageRequest.of(page, count);

// CursoDTO dto1 = new CursoDTO();
// CursoDTO dto2 = new CursoDTO();
// Page<CursoDTO> expectedPage = new PageImpl<>(List.of(dto1, dto2), pageable,
// 2);

// when(repository.buscarTodosAtivos(any())).thenReturn(expectedPage);

// Page<CursoDTO> result = service.buscarTodos(page, count);

// assertEquals(2, result.getTotalElements());
// assertEquals(dto1, result.getContent().get(0));
// assertEquals(dto2, result.getContent().get(1));
// verify(repository).buscarTodosAtivos(pageable);
// }

// @Test
// void buscarTodos_emptyPage() {
// int page = 1;
// int count = 5;
// Pageable pageable = PageRequest.of(page, count);

// Page<CursoDTO> expectedPage = new PageImpl<>(List.of(), pageable, 0);

// when(repository.buscarTodosAtivos(any())).thenReturn(expectedPage);

// Page<CursoDTO> result = service.buscarTodos(page, count);

// assertTrue(result.isEmpty());
// verify(repository).buscarTodosAtivos(pageable);
// }
// }