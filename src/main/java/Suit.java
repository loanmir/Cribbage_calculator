public enum Suit {
    SPADES, CLUBS, HEARTS, DIAMONDS;

    public static Suit suitFromCharacter(char c){
        char lowercaseC = Character.toLowerCase(c);
        switch(lowercaseC){
            case 's':
                return SPADES;
            case 'c':
                return CLUBS;
            case 'h':
                return HEARTS;
            case 'd':
                return DIAMONDS;
            default:
                return null;
        }
    }

}
