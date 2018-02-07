/**
 * @author Justin Huberts
 * @version jdk-9 in Intellij IDEA 2017.2.6
 */

public class Virus implements Comparable{
    private int Virus_id;
    private String Soort;
    //private ArrayList<Integer> hostList = new ArrayList<>();
    private String Classificatie;
    private int Host_id;
    private String Host_name;
    private Integer Aantal;

    public enum Order {ID, Classificatie, AantalHosts}
    private Order sortingBy;

    public Virus(int id, String chr, String chrc, int hostid, String hostname) {
        Virus_id = id;
        Soort = chr;
        Classificatie = chrc;
        Host_id = hostid;
        Host_name = hostname;
    }

    public int getVirus_id() {
        return Virus_id;
    }

    public int getHost_id() {
        return Host_id;
    }

    public String getSoort() {
        return Soort;
    }

    public String getClassificatie() {
        return Classificatie;
    }

    public void setVirus_id(int id) {
        this.Virus_id = id;
    }

    public void setHost_id(int hostid) {
        this.Host_id = hostid;
    }

    public void setSoort(String chr) {
        this.Soort = chr;
    }

    public void setClassificatie(String chrc) {
        this.Classificatie = chrc;
    }

    public String getHost_name() {
        return Host_name;
    }

    public void setHost_name(String hostname) {
        this.Host_name = hostname;
    }

    public void setAantal(Integer Aantal) {
        this.Aantal = Aantal;
    }

    public Integer getAantal() {
        return Aantal;
    }

    @Override
    public int compareTo(Object o) {
        Virus g = (Virus)o;
        return this.Classificatie.compareTo(g.Classificatie);
            }
    public void setSortingBy(Order sortBy) {
        this.sortingBy = sortingBy;
    }
}
