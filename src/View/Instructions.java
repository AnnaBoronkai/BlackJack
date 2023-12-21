package View;

public enum Instructions {

    PLACE_BET("Välj en summa att satsa"),
    DECIDE_NEXT_MOVE("Det är din tur. Dra ett nytt kort (Hit me!) eller stanna (Stop!)"),
    BUSTED("Du gick över 21. Du har tyvärr förlorat. Klicka på 'New game' för att försöka igen"),
    WON_ROUND("Grattis! Du vann. Klicka på 'New game' för en ny runda"),
    LOST_ROUND("Tyvärr, du förlorade. Klicka på 'New game' för att försöka igen"),
    DRAW_ROUND("Det blev oavgjort. Klicka på 'New game' för en ny runda");

    private final String instruction;

    Instructions(String instruction) {
        this.instruction = instruction;
    }

    public String getInstruction() {
        return instruction;
    }
}

