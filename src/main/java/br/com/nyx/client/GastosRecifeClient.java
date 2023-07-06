package br.com.nyx.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "dadosrecife", url = "http://dados.recife.pe.gov.br")
public interface GastosRecifeClient {

    @GetMapping("/api/3/action/datastore_search")
    String filtrarDaddos(@RequestParam(name = "resource_id") String resourceId, @RequestParam(name = "filters") String filters, @RequestParam(name = "limit") int limit);


}
