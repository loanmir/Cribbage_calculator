import java.util.*;


public class CribCalculator {
    public static void main(String[] args) {
        // Example hand and starter card
        //String[] hand = {"5H", "5D", "5S", "JC"}; // Hand cards
        //String starter = "5C"; // Starter card
        ArrayList<Card> hand = new ArrayList<>(4);
        Card starter = null;

        // User inputs the cards on the keyboard
        Scanner sc = new Scanner(System.in);

        while (hand.size() < 4) {
            System.out.println("Please enter card number " + (hand.size() + 1) + " in your hand");
            System.out.println("The cards should be represented in the format RS, where R denotes the rank and S represents the suit.");
            Card card = getCard(sc.nextLine());
            if (card != null)
                hand.add(card);
            else
                System.out.println("Invalid card entered\n");
        }

        //entering starter card
        while (starter == null) {
            System.out.println("Please enter the starter card");
            System.out.println("The card should be represented in the format RS, where R denotes the rank and S represents the suit.");
            Card card = getCard(sc.nextLine());
            if (card != null)
                starter = card;
            else
                System.out.println("Invalid card entered\n");
        }


        int total = calculateScore(hand, starter);

        System.out.println("Total points for the hand: " + total);
    }


    // GetCard method for getting the Rank and the suit of the inserted card!


    private static Card getCard(String card) {
        String rankTyped;

        //Extract the rank
        if (card.length() == 2)
            rankTyped = card.substring(0, 1);
        else return null; //Badly typed input
        Rank rank = Rank.rankFromChar(rankTyped);
        if (rank == null) return null; //Badly typed rank

        //Getting the suit
        char suitC = card.charAt(card.length() - 1); // selecting the last character of inserted string
        Suit suit = Suit.suitFromCharacter(suitC);
        if (suit == null) return null; //Badly typed suit

        return new Card(rank, suit);
    }


    // Calculating the total points

    public static int calculateScore(List<Card> hand, Card starter) {

        int score = 0;

        // Count pairs
        score += countPairs(hand, starter);

        // Count runs
        score += countRuns(hand, starter);

        // Count combinations that total fifteen
        score += countFifteens(hand, starter);

        // Check for flush
        score += checkFlush(hand, starter);

        score += samesuitJ(hand, starter);

        return score;
    }



    // Recursive function for count score of all possible pairs in a Cribbage hand.
    public static int countPairs(List<Card> hand, Card starter) {
        List<Card> handPlusStarter = new LinkedList<>(hand);
        handPlusStarter.add(starter);
        return countPairs(handPlusStarter);
    }
    private static int countPairs(List<Card> handPlusStarter){

       int score = 0;

       Card lastElt = handPlusStarter.remove(handPlusStarter.size() - 1);
       for (Card card : handPlusStarter) {
            if (lastElt.getRank() == card.getRank())
                score += 2;
        }
        if (handPlusStarter.size() > 1)
            return score + countPairs(handPlusStarter);
        return score;
    }


    public static int countRuns(List<Card> hand, Card starter) {
        List<Card> handPlusStarter = new LinkedList<>(hand);
        handPlusStarter.add(starter);
        return countPairs(handPlusStarter);
    }


    private static int countRuns(List<Card> handPlusStarter) {
        Set<Card> ranks = new HashSet<>();
        for (Card card : handPlusStarter) {
            ranks.add(getRank(card.substring(0, card.length() - 1)));
        }

        List<Integer> sortedRanks = new ArrayList<>(ranks);
        Collections.sort(sortedRanks);

        int runs = 0;
        int currentRunLength = 1;

        for (int i = 1; i < sortedRanks.size(); i++) {
            if (sortedRanks.get(i) == sortedRanks.get(i - 1) + 1) {
                currentRunLength++;
            } else {
                if (currentRunLength >= 3) {
                    runs += currentRunLength; // Add run points
                }
                currentRunLength = 1;
            }
        }

        if (currentRunLength >= 3) {
            runs += currentRunLength; // Add last run points
        }

        return runs;
    }



    public static int countFifteens(List<Card> hand, Card starter) {
        List<Card> handPlusStarter = new LinkedList<>(hand);
        handPlusStarter.add(starter);
        return countFifteens(handPlusStarter);
    }


    private static int countFifteens(List<Card> handPlusStarter) {
        int score = 0;
        if(handPlusStarter.size() == 5){
            int totalTempScore = 0;
            for(Card c : handPlusStarter) {
                totalTempScore += c.getRank().getScore();
            }
            if(totalTempScore == 15)
                score+=2;
        }
        Card lastElt = handPlusStarter.remove(handPlusStarter.size() - 1); //Remove last element to avoid array recalculation of combinations with same card set
        for (int i = 0; i < handPlusStarter.size(); i++) {
            int cardIScore = handPlusStarter.get(i).getRank().getScore();
            if (lastElt.getRank().getScore() + cardIScore == 15)
                score += 2;
            for (int j = i + 1; j < handPlusStarter.size(); j++) {
                int cardJScore = handPlusStarter.get(j).getRank().getScore();
                if (cardIScore + cardJScore + lastElt.getRank().getScore() == 15)
                    score += 2;
                for (int k = j + 1; k < handPlusStarter.size(); k++) {
                    int cardKScore = handPlusStarter.get(k).getRank().getScore();
                    if (cardIScore + cardJScore + cardKScore + lastElt.getRank().getScore() == 15)
                        score += 2;
                }
            }
        }

        if (handPlusStarter.size() > 1)
            return score + countFifteens(handPlusStarter);
        return score;
    }



    // computing score of flushes of the current hand

    private static int checkFlush(List<Card> hand, Card starter) {
        Suit suit = hand.get(0).getSuit();
       //boolean flush = true;

        for (Card card : hand) {
            if (card.getSuit() != suit)
                return 0;
        }

        if (starter.getSuit() == suit)
            return 5;


    }

    // Checking if there is a JACK in the hand that has same suit as the starter card.

    private static int samesuitJ(List<Card> hand, Card starter){
        for(Card card : hand)
            if(card.getRank() == Rank.JACK && card.getSuit() == starter.getSuit())
                return 1;

        return 0;
    }


}

//else if (card.length() == 3) //If the user writes 10X
  //      rankTyped = card.substring(0, 2);