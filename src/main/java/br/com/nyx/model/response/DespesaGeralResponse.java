package br.com.nyx.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record DespesaGeralResponse(
        @JsonProperty("_id")
        Integer id,
        @JsonProperty("ano_movimentacao")
        Integer anoMovimentacao,
        @JsonProperty("mes_movimentacao")
        Integer mesMovimentacao,
        @JsonProperty("orgao_codigo")
        Integer orgaoCodigo,
        @JsonProperty("orgao_nome")
        String orgaoNome,
        @JsonProperty("unidade_codigo")
        double unidadeCodigo,
        @JsonProperty("unidade_nome")
        String unidadeNome,
        @JsonProperty("categoria_economica_codigo")
        Integer categoriaEconomicaCodigo,
        @JsonProperty("categoria_economica_nome")
        String categoriaEconomicaNome,
        @JsonProperty("grupo_despesa_codigo")
        Integer grupoDespesaCodigo,
        @JsonProperty("grupo_despesa_nome")
        String grupoDespesaNome,
        @JsonProperty("modalidade_aplicacao_codigo")
        Integer modalidadeAplicacaoCodigo,
        @JsonProperty("modalidade_aplicacao_nome")
        String modalidadeAplicacaoNome,
        @JsonProperty("elemento_codigo")
        Integer elementoCodigo,
        @JsonProperty("elemento_nome")
        String elementoNome,
        @JsonProperty("subelemento_codigo")
        Integer subelementoCodigo,
        @JsonProperty("subelemento_nome")
        String subelementoNome,
        @JsonProperty("funcao_codigo")
        Integer funcaoCodigo,
        @JsonProperty("funcao_nome")
        String funcaoNome,
        @JsonProperty("subfuncao_codigo")
        Integer subfuncaoCodigo,
        @JsonProperty("subfuncao_nome")
        String subfuncaoNome,
        @JsonProperty("programa_codigo")
        Integer programaCodigo,
        @JsonProperty("programa_nome")
        String programaNome,
        @JsonProperty("acao_codigo")
        Integer acaoCodigo,
        @JsonProperty("acao_nome")
        String acaoNome,
        @JsonProperty("fonte_recurso_codigo")
        Integer fonteRecursoCodigo,
        @JsonProperty("fonte_recurso_nome")
        String fonteRecursoNome,
        @JsonProperty("empenho_ano")
        Integer empenhoAno,
        @JsonProperty("empenho_modalidade_nome")
        String empenhoModalidadeNome,
        @JsonProperty("empenho_modalidade_codigo")
        Integer empenhoModalidadeCodigo,
        @JsonProperty("empenho_numero")
        Integer empenhoNumero,
        @JsonProperty("subempenho")
        Integer subempenho,
        @JsonProperty("indicador_subempenho")
        String indicadorSubempenho,
        @JsonProperty("credor_codigo")
        Integer credorCodigo,
        @JsonProperty("credor_nome")
        String credorNome,
        @JsonProperty("modalidade_licitacao_codigo")
        Integer modalidadeLicitacaoCodigo,
        @JsonProperty("modalidade_licitacao_nome")
        String modalidadeLicitacaoNome,
        @JsonProperty("valor_empenhado")
        String valorEmpenhado,
        @JsonProperty("valor_liquidado")
        String valorLiquidado,
        @JsonProperty("valor_pago")
        String valorPago
) {
}
