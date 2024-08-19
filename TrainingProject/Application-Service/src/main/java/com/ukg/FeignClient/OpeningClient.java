package com.ukg.FeignClient;

import com.ukg.dto.OpeningDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "opening-service")
public interface OpeningClient {

    @GetMapping("/api/openings/{id}")
    OpeningDTO getOpeningById(@PathVariable("id") Long id);
}
