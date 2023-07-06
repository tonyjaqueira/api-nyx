package br.com.nyx.controller;


import br.com.nyx.model.response.DespesaGeralResponse;
import br.com.nyx.model.response.DespesasTotaisCategoriaResponse;
import br.com.nyx.model.response.DespesasTotaisMesResponse;
import br.com.nyx.model.response.FonteRecursosResponse;
import br.com.nyx.service.GastosRecifeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nyx")
@AllArgsConstructor
@Tag(name = "GastosRecifeController", description = "Filtra informações de gastos da Prefeitura do Recife")
public class GastosRecifeController {

    private final GastosRecifeService gastosRecifeService;

    @GetMapping("/filtrar-gastos")
    public ResponseEntity<List<DespesaGeralResponse>> filtrarGastos(@Valid @RequestParam("filters") String filters) {
        var listaResultado = gastosRecifeService.filtarGastosRecife(filters);
        return ResponseEntity.ok(listaResultado);
    }

    @GetMapping("/despesas-mes")
    public ResponseEntity<List<DespesasTotaisMesResponse>> despesasTotaisPorMes() {
        var listaResultado = gastosRecifeService.despesasTotaisPorMes();
        return ResponseEntity.ok(listaResultado);
    }

    @GetMapping("/despesas-categoria")
    public ResponseEntity<List<DespesasTotaisCategoriaResponse>> despesasTotaisPorCategoria() {
        var listaResultado = gastosRecifeService.despesasTotaisPorCategoria();
        return ResponseEntity.ok(listaResultado);
    }

    @GetMapping("/fonte-recursos")
    public ResponseEntity<List<FonteRecursosResponse>> fonteDosRecursos() {
        var listaResultado = gastosRecifeService.origemFonteRecursos();
        return ResponseEntity.ok(listaResultado);
    }

}
