package funproject.virus.model;

public class VirusInfo {

    private String city;
    private String state;
    private int totalCases;


    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getTotalCases() {
        return this.totalCases;
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }


    @Override
    public String toString() {
        return "{" +
            " state='" + getState() + "'" +
            ", country='" + getCity() + "'" +
            ", totalCases='" + getTotalCases() + "'" +
            "}";
    }

    
}
