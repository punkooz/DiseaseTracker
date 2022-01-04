package entities;

public class Counter {

    int total;
    int cured;
    int fatality;
    int active;

    public Counter(int total, int cured, int fatality, int active) {
        this.total = total;
        this.cured = cured;
        this.fatality = fatality;
        this.active = active;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCured() {
        return cured;
    }

    public void setCured(int cured) {
        this.cured = cured;
    }

    public int getFatality() {
        return fatality;
    }

    public void setFatality(int fatality) {
        this.fatality = fatality;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
