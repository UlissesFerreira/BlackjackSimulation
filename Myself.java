import java.util.ArrayList;

public class Myself {
	
	Deck myDeck;
	int score;
	int score2;
	boolean hasTwoHands;
	boolean doubleDown;
	ArrayList<Integer> memory;
	ArrayList<Integer> memory2;
	
	int wins;
	int losses;
	
	public Myself(Deck deckInUse) {
		myDeck = deckInUse;
		score = 0;
		score2 = 0;
		hasTwoHands = false;
		doubleDown = false;
		memory = new ArrayList<Integer>();
		memory2 = new ArrayList<Integer>();
		
		wins = 0;
		losses = 0;
		
	}

	public void clear() {
		score = 0;
		score2 = 0;
		memory.clear();
		memory2.clear();
		hasTwoHands = false;
		doubleDown = false;
	}

	public void getCard() {
		int cardDrawn = myDeck.getCard();
		score += cardDrawn;
		memory.add(cardDrawn);
	}
	
	public void getCardHandTwo() {
		int cardDrawn = myDeck.getCard();
		score2 += cardDrawn;
		memory2.add(cardDrawn);
	}
	
	public void split() {
		memory2.add(memory.get(1));
		memory.remove(1);
		
		score = score - memory2.get(0);
		score2 = memory2.get(0);
		
		getCard();
		getCardHandTwo();
	}
	
	public void makePlay(int dealersCard) {
		boolean donePlay = false;
		int firstCard = memory.get(0);
		int secondCard = memory.get(1);
		
		if (firstCard == secondCard) {
			if (firstCard == 11) {
				hasTwoHands = true;
				split();
				donePlay = true;
			} else if (firstCard == 8){
				hasTwoHands = true;
				split();
			} else if (firstCard == 9 && (dealersCard <= 6 || dealersCard == 8 || dealersCard == 9)) {
				hasTwoHands = true;
				split();
			} else if (firstCard == 7 && dealersCard <= 8) {
				hasTwoHands = true;
				split();
			} else if ((firstCard == 6 || firstCard == 3 || firstCard == 2) && dealersCard <= 7) {
				hasTwoHands = true;
				split();
			} else if (firstCard == 4 && dealersCard == 5) {
				hasTwoHands = true;
				split();
			}
		}
		
		if (!hasTwoHands) {
			//verificar o double down
			if (score == 11) {
				doubleDown = true;
				getCard();
				donePlay = true;
			} else if (score == 10 && dealersCard <= 9) {
				doubleDown = true;
				getCard();
				donePlay = true;
			} else if (score == 9 && dealersCard <= 6) {
				doubleDown = true;
				getCard();
				donePlay = true;
			}
		}
		
		while(!donePlay) {
			
			if (hasAce()) {
				//soft hands
				
				if (score <= 17 || (score == 18 && (dealersCard == 9 || dealersCard == 10)) ) {
					getCard();
				} else if (score > 21){
					replaceAce();	
				} else {
					donePlay = true;
				}
				
			} else {

				if (firstCard == secondCard && firstCard == 7 && dealersCard == 10) {
					donePlay = true;
				} else if (score == 16 && memory.size() > 2) {
					donePlay = true;
				} else if (score <= 12 && dealersCard <= 3) {
					getCard();
				} else if (score < 12 && dealersCard <= 6) {
					getCard();
				} else if (score < 17 && dealersCard >= 7) {
					getCard();
				} else {
					donePlay = true;
				}
			}
			
		}
		
		if (hasTwoHands) {
			//agora temos que jogar a segunda mão
			donePlay = false;
			
			while(!donePlay) {
				
				if (hasAceHandTwo()) {
					//soft hands
					if (score2 <= 17 || (score2 == 18 && (dealersCard == 9 || dealersCard == 10)) ) {
						getCardHandTwo();
					} else if (score2 > 21){
						replaceAceHandTwo();	
					} else {
						donePlay = true;
					}
					
				} else {
					//hard hands
					if (memory2.get(0) == memory2.get(1) && memory2.get(0) == 7 && dealersCard == 10) {
						donePlay = true;
					} else if (score2 == 16 && memory2.size() > 2) {	
						donePlay = true;
					} else if (score2 <= 12 && dealersCard <= 3) {
						getCardHandTwo();
					} else if (score2 < 12 && dealersCard <= 6) {
						getCardHandTwo();
					} else if (score2 < 17 && dealersCard >= 7) {
						getCardHandTwo();
					} else {
						donePlay = true;
					}
				}
			}
				
		}
	}
	
	public boolean hasAce() {
		for (int i = 0; i < memory.size(); i++) {
			if (memory.get(i) == 11) {
				return true;
			}
		}
		return false;
	}
	
	public boolean replaceAce() {
		for (int i = 0; i < memory.size(); i++) {
			if (memory.get(i) == 11) {
				memory.set(i, 1);
				score -= 10;
				return true;
			}
		}
		return false;
	}
	
	public boolean hasAceHandTwo() {
		for (int i = 0; i < memory2.size(); i++) {
			if (memory2.get(i) == 11) {
				return true;
			}
		}
		return false;
	}
	
	public boolean replaceAceHandTwo() {
		for (int i = 0; i < memory2.size(); i++) {
			if (memory2.get(i) == 11) {
				memory2.set(i, 1);
				score2 -= 10;
				return true;
			}
		}
		return false;
	}

	public void compareScore(int dealerFinalScore, boolean blackjack) {

		if (score > 21) {
			losses += doubleDown ? 2 : 1;
		} else if (score == 21 && dealerFinalScore == 21) {

			if (memory.size() == 2 && blackjack) {

			} else if (memory.size() != 2 && !blackjack) {

			} else if (memory.size() == 2 && !blackjack) {

				wins += doubleDown ? 2 : 1;
			} else {

				losses += doubleDown ? 2 : 1;
			}
			
		} else if (score == dealerFinalScore) {

		} else if (score > dealerFinalScore) {
			wins += doubleDown ? 2 : 1;
		} else if (dealerFinalScore > 21) {
			wins += doubleDown ? 2 : 1;
		} else if (score < dealerFinalScore) {
			losses += doubleDown ? 2 : 1;
		}
		
		if (hasTwoHands) {
			
			if (score2 > 21) {
				losses += 1;
			} else if (score2 == 21 && dealerFinalScore == 21) {
				if (memory2.size() == 2 && blackjack) {

				} else if (memory2.size() != 2 && !blackjack) {

				} else if (memory2.size() == 2 && !blackjack) {
					wins += 1;
				} else {
					losses += 1;
				}
				
			} else if (score2 == dealerFinalScore) {

			} else if (score2 > dealerFinalScore) {
				wins += 1;
			} else if (dealerFinalScore > 21) {
				wins += 1;
			} else if (score < dealerFinalScore) {
				losses += 1;
			}	
		}
	}

	public String getData() {
		return "Wins: " + wins + "\n" + "Losses: " + losses;
	}

	public String toString() {
		if (!hasTwoHands) {
			return memory.toString();
		} else {
			return memory.toString() + "\n" + memory2.toString();
		}
	}
}
