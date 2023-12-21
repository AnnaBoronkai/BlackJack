package Model;

public enum EndOfRound {
    WIN("You win!"),
    LOSE("You lose!"),
    DRAW("It's a draw!"),;

    private String resultOfRound;

    EndOfRound(String endOfRound) {
        this.resultOfRound = endOfRound;
    }

    public String getEndOfRound() {
        return resultOfRound;
    }
}
