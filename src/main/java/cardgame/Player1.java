package cardgame;

import java.util.List;

public class Player1 implements PlayerStrategy{
	int playerId;
	List<Integer> opponentIds;
	List<Card> myCards;
	Card topPileCard;
	Card.Suit changedSuit;
	public void init(int playerId, List<Integer> opponentIds) {
		this.playerId=playerId;
		this.opponentIds=opponentIds;
	}

	/**
	 *  Get the card from player1
	 */

	public void receiveInitialCards(List<Card> cards) {
		this.myCards=cards;
	}

	/**
	 * decides whether to draw  or drop a card
	 */

	public boolean shouldDrawCard(Card topPileCard, Card.Suit changedSuit) {
		this.topPileCard=topPileCard;
		this.changedSuit=changedSuit;
		if(changedSuit == null) {
			for(int i=0;i<myCards.size();i++) {
				if(myCards.get(i).getSuit().equals(topPileCard.getSuit())||myCards.get(i).getRank().equals(topPileCard.getRank())) {
					return false;
				}
			}
		}
		else {
			for(int i=0;i<myCards.size();i++) {
				if(myCards.get(i).getSuit().equals(changedSuit)) {
					return false;
				}
			}
		}
		return true;
		
	}
	
	/**
	 * pick the top card from the deck
	 */
	
	public void receiveCard(Card pickCard) {
		System.out.println("Player1 received :"+pickCard.getRank()+" "+pickCard.getSuit());
		myCards.add(pickCard);
	}
	
	public Card playCard() {
		Card discarded = null;
		if(changedSuit == null) {
			for(int i=0;i<myCards.size();i++) {
				if(myCards.get(i).getSuit().equals(topPileCard.getSuit())||myCards.get(i).getRank().equals(topPileCard.getRank())) {
					System.out.println("Player1 played : "+myCards.get(i).getRank()+" "+myCards.get(i).getSuit());
					discarded = myCards.get(i);
					myCards.remove(i);
					break;
				}
			}
		}
		else {
			for(int i=0;i<myCards.size();i++) {
				if(myCards.get(i).getSuit().equals(changedSuit)) {
					System.out.println("Player1 played : "+myCards.get(i).getRank()+" "+myCards.get(i).getSuit());
					discarded = myCards.get(i);
					myCards.remove(i);
					break;
				}
			}
		}
		return discarded;
		
	}

	/**
	 * If the drop card is 8 the player should declare the suit
	 */
	
	public Card.Suit declareSuit(){
		Card declaredSuit=myCards.get(0);
		int max=0,count=0;
		for(int i=0;i<myCards.size();i++) {
			count=0;
			for(int j=0;j<myCards.size();j++) {
				if(myCards.get(i)==myCards.get(j))
					count++;
			}
			if(count>max) {
				max=count;
				declaredSuit=myCards.get(i);
			}
		}
		System.out.println("\n Suit Declared : "+declaredSuit.getSuit());
		System.out.println();
		return declaredSuit.getSuit();
		
	}
	public void processOpponentActions(List<PlayerTurn> opponentActions) {
		
	}
	 public void reset() {
		 
	 }
	 

	 /**
	 * The player1's score is calculated
	 */
		
	@Override
	public int getScore() {
		int score=0;
		for(int i=0;i<myCards.size();i++) {
			if(score<=200)
				score+=myCards.get(i).getPointValue();
		}
		return score;
	}
}
