import java.util.*;

public class Deck {

    // Карта — просто номер. equals/hashCode автоматически по int.
    record Card(int id) {
        @Override public String toString() { return "Карта#" + id; }
    }

    static class DeckOfCards {
        private final List<Card> cards = new ArrayList<>();

        DeckOfCards(int size) {
            for (int i = 1; i <= size; i++) cards.add(new Card(i));
        }

        void shuffle() {
            Collections.shuffle(cards);
            System.out.println("Перетасовано: " + cards.size() + " карт");
        }

        // Сдать: карта удаляется из колоды и возвращается
        Card deal() {
            if (cards.isEmpty()) throw new IllegalStateException("Колода пуста");
            return cards.remove(cards.size() - 1);
        }

        // Вернуть: с контролем дубля
        boolean returnCard(Card card) {
            if (cards.contains(card)) {
                System.out.println(card + " уже в колоде — дубль отклонён");
                return false;
            }
            cards.add(card);
            System.out.println(card + " возвращена в колоду");
            return true;
        }

        int size() { return cards.size(); }
    }

    public static void main(String[] args) {
        DeckOfCards deck = new DeckOfCards(36); // 36 карт
        deck.shuffle();

        // Сдаём 3 карты в сторонний список
        List<Card> hand = new ArrayList<>();
        for (int i = 0; i < 3; i++) hand.add(deck.deal());
        System.out.println("На руке: " + hand);
        System.out.println("В колоде осталось: " + deck.size());

        // Возврат
        deck.returnCard(hand.get(0));   // успешно
        deck.returnCard(hand.get(0));   // дубль — отказ
    }
}