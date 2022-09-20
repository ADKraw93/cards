package com.app.cards.services;

import com.app.cards.domain.Card;
import com.app.cards.domain.CardsList;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
@Service
public class CardsListGenerator {
    public CardsList generateCards() {

        Card card1 = new Card(1421, "72240000000004002000", "PLN", "normal");
        Card card2 = new Card(1421, "72251234000004002000", "EUR", "gold");
        Card card3 = new Card(634, "11110000000004002000", "EUR", "platinum");
        Card card4= new Card(521, "22220000000004005555", "USD", "normal");

        CardsList cardsList = new CardsList();
        cardsList.setCards(List.of(card1, card2, card3, card4));

        return cardsList;
    }

    public CardsList filterCards(long id) {

        CardsList cardsList = generateCards();
        List<Card> filteredList = cardsList.getCards()
                .stream()
                .filter(t -> t.getId()==id)
                .collect(Collectors.toList());
        CardsList result = new CardsList(filteredList);
        return result;
    }
}
