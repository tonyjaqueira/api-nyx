package br.com.nyx.service;

import br.com.nyx.model.response.DespesaGeralResponse;
import br.com.nyx.model.response.DespesasTotaisCategoriaResponse;
import br.com.nyx.model.response.DespesasTotaisMesResponse;
import br.com.nyx.model.response.FonteRecursosResponse;

import java.util.List;

public interface GastosRecifeService {
    List<DespesaGeralResponse> filtarGastosRecife(String filters);

    List<DespesasTotaisMesResponse> despesasTotaisPorMes();

    List<DespesasTotaisCategoriaResponse> despesasTotaisPorCategoria();

    List<FonteRecursosResponse> origemFonteRecursos();
}
