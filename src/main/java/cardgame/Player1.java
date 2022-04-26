package cardgame;

import java.util.List;

public class Player1 implements PlayerStrategy{
	int playerId1;
	List<Integer> opponentIds1;
	List<Card> myCards1;
	Card topPileCard1;
	Card.Suit changedSuit1;
	public void init(int playerId, List<Integer> opponentIds) {
		this.playerId1=playerId;
		this.opponentIds1=opponentIds;
	}

	/**
	 *  Get the card from player1
	 */

	public void receiveInitialCards(List<Card> cards) {
		this.myCards1=cards;
	}

	/**
	 * decides whether to draw  or drop a card
	 */

	public boolean shouldDrawCard(Card topPileCard, Card.Suit changedSuit) {
		this.topPileCard1=topPileCard;
		this.changedSuit1=changedSuit;
		if(changedSuit1 == null) {
			for(int i=0;i<myCards1.size();i++) {
				if(myCards1.get(i).getSuit().equals(topPileCard1.getSuit())||myCards1.get(i).getRank().equals(topPileCard1.getRank())) {
					return false;
				}
			}
		}
		else {
			for(int i=0;i<myCards1.size();i++) {
				if(myCards1.get(i).getSuit().equals(changedSuit1)) {
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
		if(changedSuit1 == null) {
			for(int i=0;i<myCards1.size();i++) {
				if(myCards1.get(i).getSuit().equals(topPileCard1.getSuit())||myCards1.get(i).getRank().equals(topPileCard1.getRank())) {
					System.out.println("Player1 played : "+myCards1.get(i).getRank()+" "+myCards1.get(i).getSuit());
					discarded = myCards1.get(i);
					myCards1.remove(i);
					break;
				}
			}
		}
		else {
			for(int i=0;i<myCards1.size();i++) {
				if(myCards1.get(i).getSuit().equals(changedSuit1)) {
					System.out.println("Player1 played : "+myCards1.get(i).getRank()+" "+myCards1.get(i).getSuit());
					discarded = myCards1.get(i);
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
		Card declaredSuit=myCards1.get(0);
		int max1=0;
		int count1=0;
		for(int i=0;i<myCards1.size();i++) {
			count1=0;
			for(int j=0;j<myCards1.size();j++) {
				if(myCards1.get(i)==myCards1.get(j))
					count1++;
			}
			if(count1>max1) {
				max1=count1;
				declaredSuit=myCards1.get(i);
			}
		}
		System.out.println("\n Suit Declared : "+declaredSuit.getSuit());
		System.out.println();
		return declaredSuit.getSuit();
		
	}
	
	 

	 /**
	 * The player1's score is calculated
	 */
		
	@Override
	public int getScore() {
		int score1=0;
		for(int i=0;i<myCards1.size();i++) {
			if(score1<=200)
				score1+=myCards1.get(i).getPointValue();
		}
		return score1;
	}
}
