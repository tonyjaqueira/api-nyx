package br.com.nyx.service;

import br.com.nyx.client.GastosRecifeClient;
import br.com.nyx.exception.ExceptionCustomer;
import br.com.nyx.mapper.GastosRecifeMapper;
import br.com.nyx.model.response.DespesaGeralResponse;
import br.com.nyx.model.response.DespesasTotaisCategoriaResponse;
import br.com.nyx.model.response.DespesasTotaisMesResponse;
import br.com.nyx.model.response.FonteRecursosResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class GastosRecifeServiceImpl implements GastosRecifeService {

    private final GastosRecifeClient gastosRecifeClient;
    private final GastosRecifeMapper mapper;
    public static final String ANO_DEFAULT = "{\"ano_movimentacao\": \"2017\"}";
    public static final Integer LIMIT_DEFAULT = 200;
    public static final String RESOURCE_ID = "d4d8a7f0-d4be-4397-b950-f0c991438111";


    @Override
    public List<DespesaGeralResponse> filtarGastosRecife(String filters) {
        try {
            val jsonRetorno = gastosRecifeClient.filtrarDaddos(RESOURCE_ID, filters, LIMIT_DEFAULT);
            return mapper.jsonToObejctResponse(jsonRetorno);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ExceptionCustomer("Ocorreu um erro ao tentar consultar o serviço de dados de gastos da Prefeitura do Recife.");
        }
    }

    @Override
    public List<DespesasTotaisMesResponse> despesasTotaisPorMes() {
        var listGastos = filtarGastosRecife(ANO_DEFAULT);
        var listReponse = new ArrayList<DespesasTotaisMesResponse>();
        //TODO: trecho pode virar método genérico usando reflection.
        Map<Integer, Double> despesasPorMes = listGastos.stream()
                .collect(Collectors.groupingBy(DespesaGeralResponse::mesMovimentacao,
                        Collectors.summingDouble(despesa -> getValorDecimal(despesa.valorPago()))));
        //TODO:até aqui
        despesasPorMes.forEach((key, value) -> listReponse.add(DespesasTotaisMesResponse.builder().mes(key).valorTotal(BigDecimal.valueOf(value)).build()));
        return listReponse;
    }

    @Override
    public List<DespesasTotaisCategoriaResponse> despesasTotaisPorCategoria() {
        var listGastos = filtarGastosRecife(ANO_DEFAULT);
        var listReponse = new ArrayList<DespesasTotaisCategoriaResponse>();
        Map<String, Double> despesasPorCategoria = listGastos.stream()
                .collect(Collectors.groupingBy(DespesaGeralResponse::categoriaEconomicaNome,
                        Collectors.summingDouble(despesa -> getValorDecimal(despesa.valorPago()))));
        despesasPorCategoria.forEach((key, value) -> listReponse.add(DespesasTotaisCategoriaResponse.builder().categoria(key).valorTotal(BigDecimal.valueOf(value)).build()));
        return listReponse;
    }

    @Override
    public List<FonteRecursosResponse> origemFonteRecursos() {
        var listGastos = filtarGastosRecife(ANO_DEFAULT);
        var listReponse = new ArrayList<FonteRecursosResponse>();
        Map<String, Double> recursosPorFonte = listGastos.stream()
                .collect(Collectors.groupingBy(DespesaGeralResponse::fonteRecursoNome,
                        Collectors.summingDouble(despesa -> getValorDecimal(despesa.valorEmpenhado()))));
        recursosPorFonte.forEach((key, value) -> listReponse.add(FonteRecursosResponse.builder().origemRecurso(key).valorEmpenhado(BigDecimal.valueOf(value)).build()));
        return listReponse;
    }


    private static double getValorDecimal(String valor) {
        return Double.parseDouble(valor.replace(",", "."));
    }

}
