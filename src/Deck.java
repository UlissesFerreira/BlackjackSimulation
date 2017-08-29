import java.util.ArrayList;
import java.util.Random;

public class Deck {
	
	private ArrayList<Integer> deck;
	private int size;
	private int currCard;
	/*
	 * Represents the cards on the table. We have the shoe with the played
	 * cards and we have the remaining cards. Every card on an index
	 * smaller than currCard have already been played. The others are still
	 * playable.
	 */
	public Deck(int numOfDecks) {	
		size = 52 * numOfDecks;
		currCard = -1;
		deck = new ArrayList<Integer>();
		/* do 2 ate ao 9 são adicionadas 4*n de baralhos cartas */
		
		for (int z = 1; z <= 4 * numOfDecks; z++) { //cartas de 2 a 9
			for (int i = 2; i <= 9; i++) {
				deck.add(i);
			}
		}
		for (int x = 1; x <= 4 * numOfDecks; x++) { //ases
			deck.add(11);
		}
		for (int y = 1; y <= 16 * numOfDecks; y++) { //dezes + figuras
			deck.add(10);
		}
		
	}
	
	public void shuffle() { //Durstenfeld Shuffle -> Fisher Yattes optimized
		Random rnd = new Random();
		for (int i = deck.size() - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			// Simple swap
			int a = deck.get(index);
			deck.set(index, deck.get(i));
			deck.set(i, a);
		}
		
		currCard = -1;
	}
	
	public int getCard() {
		currCard += 1;
		
		if (currCard < size) {
			return deck.get(currCard);
		}
		return 0;
	}
	
	public int getSize() {
		return size;
	}
	
}
