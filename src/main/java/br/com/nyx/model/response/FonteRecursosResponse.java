package br.com.nyx.model.response;


import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record FonteRecursosResponse(
        String origemRecurso,
        BigDecimal valorEmpenhado
) {
}
