public enum Rank {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;

    public int fifteenScore, runPosition;

    static{
        ACE.fifteenScore = 1;
        TWO.fifteenScore = 2;
        THREE.fifteenScore = 3;
        FOUR.fifteenScore = 4;
        FIVE.fifteenScore = 5;
        SIX.fifteenScore = 6;
        SEVEN.fifteenScore = 7;
        EIGHT.fifteenScore = 8;
        NINE.fifteenScore = 9;
        TEN.fifteenScore = 10;
        JACK.fifteenScore = 10;
        QUEEN.fifteenScore = 10;
        KING.fifteenScore = 10;

        ACE.runPosition = 1;
        TWO.runPosition = 2;
        THREE.runPosition = 3;
        FOUR.runPosition = 4;
        FIVE.runPosition = 5;
        SIX.runPosition = 6;
        SEVEN.runPosition = 7;
        EIGHT.runPosition = 8;
        NINE.runPosition = 9;
        TEN.runPosition = 10;
        JACK.runPosition = 11;
        QUEEN.runPosition = 12;
        KING.runPosition = 13;
    }

    public int getScore(){
        return fifteenScore;
    }

    public int getRunPosition(){
        return runPosition;
    }

    public static Rank rankFromChar(String ranktmp){
        ranktmp = ranktmp.toUpperCase();

        switch (ranktmp) {
            case "A":
                return Rank.ACE;
            case "2":
                return Rank.TWO;
            case "3":
                return Rank.THREE;
            case "4":
                return Rank.FOUR;
            case "5":
                return Rank.FIVE;
            case "6":
                return Rank.SIX;
            case "7":
                return Rank.SEVEN;
            case "8":
                return Rank.EIGHT;
            case "9":
                return Rank.NINE;
            //case "10":
            case "0":
                return Rank.TEN;
            case "J":
                return Rank.JACK;
            case "Q":
                return Rank.QUEEN;
            case "K":
                return Rank.KING;
            default:
                return null;
        }
    }
}
