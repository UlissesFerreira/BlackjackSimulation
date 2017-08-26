
public class Blackjack {

	Myself me;
	Dealer dealer;
	Player[] players;
	Deck myDeck;
	int numOfPlayers = 4;
	
	public Blackjack() {
		myDeck = new Deck(6);
		me = new Myself(myDeck);
		dealer = new Dealer(myDeck);
		players = new Player[numOfPlayers];
		for (int i = 0; i <= numOfPlayers - 1; i++) {
			players[i] = new Player(myDeck);
		}

	}
	
	public Blackjack(int numOfPlayers, int numOfDecks) {
		this.numOfPlayers = numOfPlayers;
		myDeck = new Deck(numOfDecks);
		me = new Myself(myDeck);
		dealer = new Dealer(myDeck);
		players = new Player[numOfPlayers];
		for (int i = 0; i <= numOfPlayers - 1; i++) {
			players[i] = new Player(myDeck);
		}
	}

	/*
	 * Este metodo corre o numero de simulações desejado
	 */
	public void runSimulation(int iterations) {
		while (iterations > 0) {
			//distribuir cartas
			prepareGame();
			//System.out.println("Dealer - " + dealer.toString());
			//System.out.println("Me - " + me.toString());
			for (Player individual : players) {
				individual.makePlay();
			}
			me.makePlay(dealer.getVisibleCard());
			dealer.makePlay();
			//System.out.println("--- After Play ---");
			//System.out.println("Dealer - " + dealer.toString());
			//System.out.println("Me - " + me.toString());
			me.compareScore(dealer.getFinalScore(), dealer.isBlackjack());
			//System.out.println("##################");
			iterations -= 1;
		}
		System.out.println("Final result: ");
		System.out.println(me.getData());
	}

	public void prepareGame() {
		myDeck.shuffle();
		myDeck.shuffle();
		myDeck.shuffle();
		me.clear();
		dealer.clear();
		for(Player individual : players) {
			individual.clear();
		}
		dealer.burnCard();
		for (Player individual : players) {
			individual.getCard();
		}
		me.getCard(); 
		dealer.getCard();
		for (Player individual : players) {
			individual.getCard();
		}
		me.getCard();
		dealer.getCard();
	}

}
