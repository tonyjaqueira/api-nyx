package br.com.nyx.service;


import br.com.nyx.client.GastosRecifeClient;
import br.com.nyx.exception.ExceptionCustomer;
import br.com.nyx.mapper.GastosRecifeMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Slf4j
class GastosRecifeServiceTest {

    public static final String ANO_MOVIMENTACAO_2017 = "{\"ano_movimentacao\": \"2017\"}";
    @InjectMocks
    GastosRecifeServiceImpl gastosRecifeService;
    @Spy
    GastosRecifeMapper mapper;
    @Mock
    GastosRecifeClient client;


    @Test
    void deveRetornarOsGastosDoRecifePorFiltroPadrao() {
        when(client.filtrarDaddos(any(), any(), anyInt())).thenReturn(readJson());
        var listaResutados = gastosRecifeService.filtarGastosRecife(ANO_MOVIMENTACAO_2017);
        assertFalse(listaResutados.isEmpty());
        assertEquals(10, listaResutados.size());
        assertTrue(listaResutados.stream().anyMatch(despesas -> despesas.anoMovimentacao().equals(2017)));
    }

    @Test
    void deveRetornarOsGastosDoRecifeComFiltroNulo() {
        when(client.filtrarDaddos(any(), any(), anyInt())).thenReturn(readJson());
        var listaResutados = gastosRecifeService.filtarGastosRecife(null);
        assertFalse(listaResutados.isEmpty());
        assertEquals(10, listaResutados.size());
        assertTrue(listaResutados.stream().anyMatch(despesas -> despesas.anoMovimentacao().equals(2017)));
    }

    @Test
    void deveRetornarUmaListaVaziaAoFiltrarDados() {
        when(client.filtrarDaddos(any(), any(), anyInt())).thenReturn("");
        var listaResutados = gastosRecifeService.filtarGastosRecife(null);
        assertTrue(listaResutados.isEmpty());
    }


    @Test
    void deveRetornarUmaExcecaoAoFiltrarDados() {
        when(client.filtrarDaddos("123246", ANO_MOVIMENTACAO_2017, 1)).thenThrow(ExceptionCustomer.class);
        var exception = assertThrows(ExceptionCustomer.class, () -> gastosRecifeService.filtarGastosRecife(ANO_MOVIMENTACAO_2017));
        assertEquals("Ocorreu um erro ao tentar consultar o serviço de dados de gastos da Prefeitura do Recife.", exception.getMessage());
    }

    @Test
    void deveRetornarDespesasAgrupadasPorMes() {
        when(client.filtrarDaddos(any(), any(), anyInt())).thenReturn(readJson());
        var listaResutados = gastosRecifeService.despesasTotaisPorMes();
        assertFalse(listaResutados.isEmpty());
        assertEquals(7, listaResutados.size());
        listaResutados.forEach(despesasTotaisMesResponse -> {
            if ( despesasTotaisMesResponse.mes().equals(2) )
                assertEquals(101912.17, despesasTotaisMesResponse.valorTotal().doubleValue());
            if ( despesasTotaisMesResponse.mes().equals(3) )
                assertEquals(280807.62, despesasTotaisMesResponse.valorTotal().doubleValue());
            if ( despesasTotaisMesResponse.mes().equals(4) )
                assertEquals(159933.78, despesasTotaisMesResponse.valorTotal().doubleValue());
            if ( despesasTotaisMesResponse.mes().equals(6) )
                assertEquals(10627.1, despesasTotaisMesResponse.valorTotal().doubleValue());
            if ( despesasTotaisMesResponse.mes().equals(7) )
                assertEquals(77521.68, despesasTotaisMesResponse.valorTotal().doubleValue());
            if ( despesasTotaisMesResponse.mes().equals(10) )
                assertEquals(109139.14000000001, despesasTotaisMesResponse.valorTotal().doubleValue());
            if ( despesasTotaisMesResponse.mes().equals(11) )
                assertEquals(86559.28, despesasTotaisMesResponse.valorTotal().doubleValue());
        });
    }

    @Test
    void deveRetornarDespesasAgrupadasPorCategoria() {
        when(client.filtrarDaddos(any(), any(), anyInt())).thenReturn(readJson());
        var listaResutados = gastosRecifeService.despesasTotaisPorCategoria();
        assertFalse(listaResutados.isEmpty());
        assertEquals(1, listaResutados.size());
        assertTrue(listaResutados.stream().anyMatch(despesas -> despesas.categoria().equals("DESPESAS CORRENTES")));
        assertTrue(listaResutados.stream().anyMatch(despesas -> despesas.valorTotal().doubleValue() == 826500.77));
    }

    @Test
    void deveRetornarA_OrigemDosRecursosDaPrefeituraDoRecife() {
        when(client.filtrarDaddos(any(), any(), anyInt())).thenReturn(readJson());
        var listaResutados = gastosRecifeService.origemFonteRecursos();
        assertFalse(listaResutados.isEmpty());
        assertEquals(1, listaResutados.size());
        assertTrue(listaResutados.stream().anyMatch(fonte -> fonte.origemRecurso().equals("RECURSOS ORDINÁRIOS - NÃO VINCULADOS")));
        assertTrue(listaResutados.stream().anyMatch(fonte -> fonte.valorEmpenhado().doubleValue() == 826500.77));
    }


    @SneakyThrows
    private String readJson() {
        ClassPathResource resource = new ClassPathResource("gastos_recife.json");
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }

}
