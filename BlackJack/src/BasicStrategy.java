import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class BasicStrategy {
    //fields
    private HashMap<String, String> pairSplitting;      // if a player has a pair
    private HashMap<String, String> softTotals;         // if a player has an Ace and card with number value
    private Map<String, String> hardTotals;         // if a player has a cards with number values
    private HashMap<String, String> caption;            // list of options, what to do based on cards in game

    // constructor
    public BasicStrategy() {
        this.pairSplitting = new HashMap<>(); // texták, tahat to přes loop
        pairSplitting.put("A-2", "Y");
        pairSplitting.put("A-3", "Y");
        pairSplitting.put("A-4", "Y");
        pairSplitting.put("A-5", "Y");
        pairSplitting.put("A-6", "Y");
        pairSplitting.put("A-7", "Y");
        pairSplitting.put("A-8", "Y");
        pairSplitting.put("A-9", "Y");
        pairSplitting.put("A-10", "Y");
        pairSplitting.put("A-A", "Y");

        pairSplitting.put("T-2", "N");
        pairSplitting.put("T-3", "N");
        pairSplitting.put("T-4", "N");
        pairSplitting.put("T-5", "N");
        pairSplitting.put("T-6", "N");
        pairSplitting.put("T-7", "N");
        pairSplitting.put("T-8", "N");
        pairSplitting.put("T-9", "N");
        pairSplitting.put("T-10", "N");
        pairSplitting.put("T-A", "N");

        pairSplitting.put("9-2", "Y");
        pairSplitting.put("9-3", "Y");
        pairSplitting.put("9-4", "Y");
        pairSplitting.put("9-5", "Y");
        pairSplitting.put("9-6", "Y");
        pairSplitting.put("9-7", "N");
        pairSplitting.put("9-8", "Y");
        pairSplitting.put("9-9", "Y");
        pairSplitting.put("9-10", "N");
        pairSplitting.put("9-A", "N");

        pairSplitting.put("8-2", "Y");
        pairSplitting.put("8-3", "Y");
        pairSplitting.put("8-4", "Y");
        pairSplitting.put("8-5", "Y");
        pairSplitting.put("8-6", "Y");
        pairSplitting.put("8-7", "Y");
        pairSplitting.put("8-8", "Y");
        pairSplitting.put("8-9", "Y");
        pairSplitting.put("8-10", "Y");
        pairSplitting.put("8-A", "Y");

        pairSplitting.put("7-2", "Y");
        pairSplitting.put("7-3", "Y");
        pairSplitting.put("7-4", "Y");
        pairSplitting.put("7-5", "Y");
        pairSplitting.put("7-6", "Y");
        pairSplitting.put("7-7", "Y");
        pairSplitting.put("7-8", "N");
        pairSplitting.put("7-9", "N");
        pairSplitting.put("7-10", "N");
        pairSplitting.put("7-A", "N");

        pairSplitting.put("6-2", "Y/N");
        pairSplitting.put("6-3", "Y");
        pairSplitting.put("6-4", "Y");
        pairSplitting.put("6-5", "Y");
        pairSplitting.put("6-6", "Y");
        pairSplitting.put("6-7", "N");
        pairSplitting.put("6-8", "N");
        pairSplitting.put("6-9", "N");
        pairSplitting.put("6-10", "N");
        pairSplitting.put("6-A", "N");

        pairSplitting.put("5-2", "N");
        pairSplitting.put("5-3", "N");
        pairSplitting.put("5-4", "N");
        pairSplitting.put("5-5", "N");
        pairSplitting.put("5-6", "N");
        pairSplitting.put("5-7", "N");
        pairSplitting.put("5-8", "N");
        pairSplitting.put("5-9", "N");
        pairSplitting.put("5-10", "N");
        pairSplitting.put("5-A", "N");

        pairSplitting.put("4-2", "N");
        pairSplitting.put("4-3", "N");
        pairSplitting.put("4-4", "N");
        pairSplitting.put("4-5", "Y/N");
        pairSplitting.put("4-6", "Y/N");
        pairSplitting.put("4-7", "N");
        pairSplitting.put("4-8", "N");
        pairSplitting.put("4-9", "N");
        pairSplitting.put("4-10", "N");
        pairSplitting.put("4-A", "N");

        pairSplitting.put("3-2", "Y/N");
        pairSplitting.put("3-3", "Y/N");
        pairSplitting.put("3-4", "Y");
        pairSplitting.put("3-5", "Y");
        pairSplitting.put("3-6", "Y");
        pairSplitting.put("3-7", "Y");
        pairSplitting.put("3-8", "N");
        pairSplitting.put("3-9", "N");
        pairSplitting.put("3-10", "N");
        pairSplitting.put("3-A", "N");

        pairSplitting.put("2-2", "Y/N");
        pairSplitting.put("2-3", "Y/N");
        pairSplitting.put("2-4", "Y");
        pairSplitting.put("2-5", "Y");
        pairSplitting.put("2-6", "Y");
        pairSplitting.put("2-7", "Y");
        pairSplitting.put("2-8", "N");
        pairSplitting.put("2-9", "N");
        pairSplitting.put("2-10", "N");
        pairSplitting.put("2-A", "N");

        this.softTotals = new HashMap<>();
        softTotals.put("9-2", "S");
        softTotals.put("9-3", "S");
        softTotals.put("9-4", "S");
        softTotals.put("9-5", "S");
        softTotals.put("9-6", "S");
        softTotals.put("9-7", "S");
        softTotals.put("9-8", "S");
        softTotals.put("9-9", "S");
        softTotals.put("9-10", "S");
        softTotals.put("9-A", "S");

        softTotals.put("8-2", "S");
        softTotals.put("8-3", "S");
        softTotals.put("8-4", "S");
        softTotals.put("8-5", "S");
        softTotals.put("8-6", "Ds");
        softTotals.put("8-7", "S");
        softTotals.put("8-8", "S");
        softTotals.put("8-9", "S");
        softTotals.put("8-10", "S");
        softTotals.put("8-A", "S");

        softTotals.put("7-2", "Ds");
        softTotals.put("7-3", "Ds");
        softTotals.put("7-4", "Ds");
        softTotals.put("7-5", "Ds");
        softTotals.put("7-6", "Ds");
        softTotals.put("7-7", "S");
        softTotals.put("7-8", "S");
        softTotals.put("7-9", "H");
        softTotals.put("7-10", "H");
        softTotals.put("7-A", "H");

        softTotals.put("6-2", "H");
        softTotals.put("6-3", "D");
        softTotals.put("6-4", "D");
        softTotals.put("6-5", "D");
        softTotals.put("6-6", "D");
        softTotals.put("6-7", "H");
        softTotals.put("6-8", "H");
        softTotals.put("6-9", "H");
        softTotals.put("6-10", "H");
        softTotals.put("6-A", "H");

        softTotals.put("5-2", "H");
        softTotals.put("5-3", "H");
        softTotals.put("5-4", "D");
        softTotals.put("5-5", "D");
        softTotals.put("5-6", "D");
        softTotals.put("5-7", "H");
        softTotals.put("5-8", "H");
        softTotals.put("5-9", "H");
        softTotals.put("5-10", "H");
        softTotals.put("5-A", "H");

        softTotals.put("4-2", "H");
        softTotals.put("4-3", "H");
        softTotals.put("4-4", "D");
        softTotals.put("4-5", "D");
        softTotals.put("4-6", "D");
        softTotals.put("4-7", "H");
        softTotals.put("4-8", "H");
        softTotals.put("4-9", "H");
        softTotals.put("4-10", "H");
        softTotals.put("4-A", "H");

        softTotals.put("3-2", "H");
        softTotals.put("3-3", "H");
        softTotals.put("3-4", "H");
        softTotals.put("3-5", "D");
        softTotals.put("3-6", "D");
        softTotals.put("3-7", "H");
        softTotals.put("3-8", "H");
        softTotals.put("3-9", "H");
        softTotals.put("3-10", "H");
        softTotals.put("3-A", "H");

        softTotals.put("2-3", "H");
        softTotals.put("2-4", "H");
        softTotals.put("2-5", "D");
        softTotals.put("2-6", "D");
        softTotals.put("2-7", "H");
        softTotals.put("2-8", "H");
        softTotals.put("2-9", "H");
        softTotals.put("2-10", "H");
        softTotals.put("2-A", "H");

        this.hardTotals = Map.ofEntries(
                entry("17-2", "S"), entry("17-3", "S"), entry("17-4", "S"), entry("17-5", "S"),
                entry("17-6", "S"), entry("17-7", "S"), entry("17-8", "S"), entry("17-9", "S"),
                entry("17-10", "S"), entry("17-A", "S"),

                entry("16-2", "S"), entry("16-3", "S"), entry("16-4", "S"), entry("16-5", "S"),
                entry("16-6", "S"), entry("16-7", "H"), entry("16-8", "H"), entry("16-9", "H"),
                entry("16-10", "H"), entry("16-A", "H"),

                entry("15-2", "S"), entry("15-3", "S"), entry("15-4", "S"), entry("15-5", "S"),
                entry("15-6", "S"), entry("15-7", "H"), entry("15-8", "H"), entry("15-9", "H"),
                entry("15-10", "H"), entry("15-A", "H"),

                entry("14-2", "S"), entry("14-3", "S"), entry("14-4", "S"), entry("14-5", "S"),
                entry("14-6", "S"), entry("14-7", "H"), entry("14-8", "H"), entry("14-9", "H"),
                entry("14-10", "H"), entry("14-A", "H"),

                entry("13-2", "S"), entry("13-3", "S"), entry("13-4", "S"), entry("13-5", "S"),
                entry("13-6", "S"), entry("13-7", "H"), entry("13-8", "H"), entry("13-9", "H"),
                entry("13-10", "H"), entry("13-A", "H"),

                entry("12-2", "H"), entry("12-3", "H"), entry("12-4", "S"), entry("12-5", "S"),
                entry("12-6", "S"), entry("12-7", "H"), entry("12-8", "H"), entry("12-9", "H"),
                entry("12-10", "H"), entry("12-A", "H"),

                entry("11-2", "D"), entry("11-3", "D"), entry("11-4", "D"), entry("11-5", "D"),
                entry("11-6", "D"), entry("11-7", "D"), entry("11-8", "D"), entry("11-9", "D"),
                entry("11-10", "D"), entry("11-A", "D"),

                entry("10-2", "D"), entry("10-3", "D"), entry("10-4", "D"), entry("10-5", "D"),
                entry("10-6", "D"), entry("10-7", "D"), entry("10-8", "D"), entry("10-9", "D"),
                entry("10-10", "H"), entry("10-A", "H"),

                entry("9-2", "H"), entry("9-3", "D"), entry("9-4", "D"), entry("9-5", "D"),
                entry("9-6", "D"), entry("9-7", "H"), entry("9-8", "H"), entry("9-9", "H"),
                entry("9-10", "H"), entry("9-A", "H"),

                entry("8-2", "H"), entry("8-3", "H"), entry("8-4", "H"), entry("8-5", "H"),
                entry("8-6", "H"), entry("8-7", "H"), entry("8-8", "H"), entry("8-9", "H"),
                entry("8-10", "H"), entry("8-A", "H")
        );

        this.caption = new HashMap<>();
        caption.put("Y", "Split the Pair");
        caption.put("N", "Don't Split the Pair");
        caption.put("Y/N", "Split if 'Double After Split (DAS)' is offered, otherwise do not split");
        caption.put("H", "Hit");
        caption.put("S", "Stand");
        caption.put("Ds", "Double if allowed, otherwise stand");
        caption.put("D", "Double if allowed, otherwise hit");
    }


    // getters and setters
    public HashMap<String, String> getPairSplitting() {
        return pairSplitting;
    }

    public void setPairSplitting(HashMap<String, String> pairSplitting) {
        this.pairSplitting = pairSplitting;
    }

    public HashMap<String, String> getSoftTotals() {
        return softTotals;
    }

    public void setSoftTotals(HashMap<String, String> softTotals) {
        this.softTotals = softTotals;
    }

    public Map<String, String> getHardTotals() {
        return hardTotals;
    }

    public void setHardTotals(Map<String, String> hardTotals) {
        this.hardTotals = hardTotals;
    }

    public HashMap<String, String> getCaption() {
        return caption;
    }

    public void setCaption(HashMap<String, String> caption) {
        this.caption = caption;
    }


    // class methods
    public void getAdvice(Player player, Dealer dealer) {
        if (player.getHand().stream().map(Card::getValue).distinct().count() == 1) {
            String key = player.getHand().get(0).getValue() + "-" + dealer.getHand().get(0).getValue();
            System.out.println("Basic Strategy: " + caption.get(pairSplitting.get(key)));
        } else if (player.getHand().stream().map(Card::getValue).anyMatch(value -> value.equals("A")) && player.getHand().size() == 2) {
            Card playersCard = player.getHand().stream().filter(card -> !card.getValue().equals("A")).findFirst().orElse(null);
            String key = playersCard + "-" + dealer.getHand().get(0).getValue();
            System.out.println("Basic Strategy: " + caption.get(softTotals.get(key)));
        } else if (player.showScore() > 17 && player.showScore() < 22) {
            System.out.println("Basic Strategy: Stand");
        } else {
            String key = "";
            if (dealer.getHand().get(0).getValue().equals("A")) {
                key = player.showScore() + "-" + dealer.getHand().get(0).getValue();
            } else if (dealer.getHand().get(0).getValue().equals("J") || dealer.getHand().get(0).getValue().equals("Q")
                    || dealer.getHand().get(0).getValue().equals("K")) {
                key = player.showScore() + "-10";
            } else {
                key = player.showScore() + "-" + dealer.showScore();
            }

            System.out.println("Basic Strategy: " + caption.get(hardTotals.get(key)));
        }
    }
}
