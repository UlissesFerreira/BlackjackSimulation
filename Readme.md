# Blackjack Simulation - Casino Lisboa

This project was developed with statistical purposes. The following code allows anyone to run a desired number of iterations on a virtual Blackjack game.

The objetive of this simulation is evaluate whether it's possible to play Blackjack in Lisbon's Casino and win money consistently following **Edward O. Thorps** basic strategy, described in his book **Beat the Dealer**.

# Description

Here's a small description of each class in the source code. Almost all represent an important object.

  - **Blackjack.java** - This is the object that sticks all the other ones together. It represents a Blackjack game following all the game's steps.
  - **Dealer.java** - Represents the Dealer of the game and his cards.
  - **Deck.java** - The "shoe" used in the game. It can be initialized with more than one deck (normal in nowadays' casinos). Includes the **Durstenfeld Shuffle** algorithm (Fisher-Yattes optimized) to guarantee a realistic shuffle.
  - **Myself.java** - One of the most important objects. This class represents the player and includes all the important decision-making algorithms. It saves the number of bets made, the number of won bets and the number of lost bets. Also includes the card counting methods.
  - **Player.java** - Represents another player at the table. These player's game choices are fairly simple. Each player draws until they reach a desired score or bust (much like a dealer). Their purpose is to add more randomness to the game and allow the "Myself" object to have more cards to count.
  - **Teste.java** - Creates a Blackjack object and runs the simulation ("main" method).

# Casino Lisboa - Rule Set

Each casino has his own rules of the game. These variations have a big influence in the odds of the player. This casino also uses CSMs (Continuos Shuffle Machines) which makes card counting between each round useless, since all the cards are put back in the deck and are in theory shuffled, resetting the counts.

- Each table uses 4 to 6 decks.
- You can only split once (two hands maximum).
- You can't double down after a split.
- Split aces only get one card each, you are not allowed to hit.
- 21's obtained in splitted aces are considered 21, not blackjacks.
- You can double down on 9 to 11 only.
- You can surrender after you get your hand if the dealer's card isn't an ace.
- You can insure.
- Dealer doesn't peak for blackjack.
- Dealer stands on soft 17
- Blackjack pays 3 to 2
