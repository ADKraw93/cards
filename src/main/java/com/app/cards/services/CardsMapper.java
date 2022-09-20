package com.app.cards.services;

import com.app.cards.domain.Card;
import com.app.cards.domain.CardDto;
import com.app.cards.domain.CardsList;
import com.app.cards.domain.CardsListDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Service
public class CardsMapper {
    public CardDto mapToCardDto(Card card) {
        CardDto result = new CardDto(card.getId(), card.getCardNrb(), card.getCurrency(), card.getType());
        return result;
    }

    public CardsListDto mapToCardsListDto(CardsList accountsList) {
        List<CardDto> cardsDtoList = accountsList.getCards()
                .stream()
                .map(this::mapToCardDto)
                .collect(Collectors.toList());
        CardsListDto result = new CardsListDto(cardsDtoList);
        return result;
    }
}
