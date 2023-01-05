package com.works.restcontrollers;

import com.works.services.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerRestController {

    final ProducerService producerService;

    @GetMapping("/send/{message}")
    public String sendMessage(@PathVariable String message) {
        producerService.sendMessage(message);
        return "Succsess";
    }

}
