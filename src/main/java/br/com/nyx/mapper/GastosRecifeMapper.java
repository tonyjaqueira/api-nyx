package br.com.nyx.mapper;

import br.com.nyx.model.response.DespesaGeralResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GastosRecifeMapper {

    public List<DespesaGeralResponse> jsonToObejctResponse(String jsonResponse) {
        var listRetorno = new ArrayList<DespesaGeralResponse>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode recordsNode = rootNode.path("result").path("records");
            if ( recordsNode.isArray() ) {
                recordsNode.forEach(result -> mapperToObject(listRetorno, objectMapper, result));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listRetorno;
    }

    private static void mapperToObject(ArrayList<DespesaGeralResponse> listRetorno, ObjectMapper objectMapper, JsonNode result) {
        try {
            var despesaGeralResponse = objectMapper.readValue(result.toString(), DespesaGeralResponse.class);
            listRetorno.add(despesaGeralResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
