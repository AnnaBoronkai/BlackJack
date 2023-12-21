package Model;

public class Statistics {
    private int wins;
    private int losses;
    private int draws;

    public Statistics() {
        this.wins = 0;
        this.losses = 0;
        this.draws = 0;
    }

    public void incrementWins() {
        wins++;
    }

    public void incrementLosses() {
        losses++;
        System.out.println(losses);
    }

    public void incrementDraws() {
        draws++;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }


    public String getStatsSummary() {
        //return "Vinster: " + wins + "Förluster: " + losses + ", Oavgjort: " + draws;
        return "Vinster: " + wins + "\nFörluster: " + losses + "\nOavgjort: " + draws;
    }
}

