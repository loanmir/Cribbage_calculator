import java.util.*;



import java.util.ArrayList;


public class CribCalculator {
    public static void main(String[] args) {
        // Example hand and starter card
        String[] hand = {"5H", "5D", "5S", "JC"}; // Hand cards
        String starter = "5C"; // Starter card

        int score = calculateScore(hand, starter);
        System.out.println("Total score for the hand: " + score);
    }

    public static int calculateScore(String[] hand, String starter) {
        List<String> allCards = new ArrayList<>(Arrays.asList(hand));
        allCards.add(starter);

        int score = 0;

        // Count pairs
        score += countPairs(allCards);

        // Count runs
        score += countRuns(allCards);

        // Count combinations that total fifteen
        score += countFifteens(allCards);

        // Check for flush
        score += checkFlush(hand, starter);

        return score;
    }

    private static int countPairs(List<String> cards) {
        Map<String, Integer> cardCount = new HashMap<>();
        int pairs = 0;

        for (String card : cards) {
            String rank = card.substring(0, card.length() - 1); // Get rank
            cardCount.put(rank, cardCount.getOrDefault(rank, 0) + 1);
        }

        for (int count : cardCount.values()) {
            if (count == 2) {
                pairs += 2; // 2 points for each pair
            }
        }

        return pairs;
    }

    private static int countRuns(List<String> cards) {
        Set<Integer> ranks = new HashSet<>();
        for (String card : cards) {
            ranks.add(getRankValue(card.substring(0, card.length() - 1)));
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

    private static int countFifteens(List<String> cards) {
        int count = 0;
        int n = cards.size();

        // Check all combinations of cards
        for (int i = 1; i < (1 << n); i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    sum += getRankValue(cards.get(j).substring(0, cards.get(j).length() - 1));
                }
            }
            if (sum == 15) {
                count += 2; // 2 points for each combination that totals fifteen
            }
        }

        return count;
    }

    private static int checkFlush(String[] hand, String starter) {
        String suit = hand[0].substring(hand[0].length() - 1);
        boolean flush = true;

        for (int i = 1; i < hand.length; i++) {
            if (!hand[i].endsWith(suit)) {
                flush = false;
                break;
            }
        }

        if (flush && starter.endsWith(suit)) {
            return 5; // 5 points for a flush including starter
        } else if (flush) {
            return 4; // 4 points for a flush without starter
        }

        return 0; // No flush
    }

    private static int getRankValue(String rank) {
        switch (rank) {
            case "A": return 1;  // Ace
            case "2": return 2;  // Two
            case "3": return 3;  // Three
            case "4": return 4;  // Four
            case "5": return 5;  // Five
            case "6": return 6;  // Six
            case "7": return 7;  // Seven
            case "8": return 8;  // Eight
            case "9": return 9;  // Nine
            case "0": return 10; // Ten
            case "J": return 10;  // Jack
            case "Q": return 10;  // Queen
            case "K": return 10;  // King
            default: return 0;    // Invalid rank
        }
    }
}
