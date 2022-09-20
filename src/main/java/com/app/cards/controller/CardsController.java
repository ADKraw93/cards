package com.app.cards.controller;

import com.app.cards.domain.CardsList;
import com.app.cards.domain.CardsListDto;
import com.app.cards.services.CardsListGenerator;
import com.app.cards.services.CardsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CardsController {
    private final CardsListGenerator generator;
    private final CardsMapper mapper;

    @Value("${application.allow-get-cards}")
    private boolean allowGetCards;

    @GetMapping("/allCards")
    public ResponseEntity<CardsListDto> getCards() {
        CardsList cards = generator.generateCards();
        System.out.println("getCards method used");
        return ResponseEntity.ok(mapper.mapToCardsListDto(cards));
    }

    @GetMapping("/cards")
    public ResponseEntity<CardsListDto> getCustomerData(@RequestParam Long customerId) throws CardNotFoundException {
        if(!allowGetCards) {
            log.info("Getting cards is disabled");
            throw  new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Getting cards is disabled");
        }
        System.out.println("getCustomerData method used");
        return ResponseEntity.ok(mapper.mapToCardsListDto(generator.filterCards(customerId)));
    }
}
