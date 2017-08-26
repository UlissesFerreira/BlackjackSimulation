import java.util.ArrayList;

public class Dealer {
	
	Deck myDeck;
	int score;
	boolean blackjack;
	ArrayList<Integer> memory;
	
	public Dealer(Deck deckInUse) {
		myDeck = deckInUse;
		score = 0;
		memory = new ArrayList<Integer>();
	}
	
	public void clear() {
		score = 0;
		memory.clear();
	}

	public void burnCard() {
		myDeck.getCard();
	}

	public void getCard() {
		int cardDrawn = myDeck.getCard();
		score += cardDrawn;
		memory.add(cardDrawn);
	}

	public void makePlay() {
		//o dealer tem que sacar até chegar ao 17 inclusive
		if (score == 21)
				blackjack = true;
		while (score < 17) {
			getCard();
			if (score > 21 && hasAce()) {
				score -= 10; //para o às contar como 1
			}
		}
		
	}
	
	public boolean hasAce() {
		
		for (int i = 0; i < memory.size(); i++) {
			if (memory.get(i) == 11) {
				memory.set(i, 1);
				return true;
			}
		}
		return false;
	}

	public int getVisibleCard() {
		return memory.get(0);
	}

	public int getFinalScore() {
		return score;
	}
	
	public boolean isBlackjack() {
		return blackjack;
	}
	
	public String toString() {
		return memory.toString();
	}
}
