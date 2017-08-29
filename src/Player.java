import java.util.ArrayList;
import java.util.Random;

public class Player {
	
	Deck myDeck;
	int score;
	int minimumScore;
	ArrayList<Integer> memory;
	
	/*
	 * Esta classe representa um jogador sentado à mesa.
	 * A função deste objeto é adicionar alguma entropia
	 * ao jogo (mais cartas retiradas).
	 */
	public Player(Deck deckInUse) {
		Random generator = new Random();
		score = 0;
		myDeck = deckInUse;
		minimumScore = generator.nextInt(3) + 15;
		memory = new ArrayList<Integer>();
	}

	//reset da pontuação e das cartas
	public void clear() {
		score = 0;
		memory.clear();
	}

	//adicionamos uma carta à mão. Portanto incrementamos a
	//pontuação e adicionamo la à memória.
	public void getCard() {
		int cardDrawn = myDeck.getCard();
		score += cardDrawn;
		memory.add(cardDrawn);
	}

	//tiramos cartas até chegarmos a um certo valor random
	//definido na declaraçao deste objeto.
	// Um jogador tira sempre cartas até passar de 15-18 (personalidade)
	public void makePlay() {
		boolean finished = false;
		
		while(!finished) {
			if (score < minimumScore) {
				getCard();
			} else {
				finished = true;
			}
		}
		
	}
	
	public ArrayList<Integer> getAllCards() {
		return memory;
	}

	public String toString() {
		return memory.toString();
	}
}
