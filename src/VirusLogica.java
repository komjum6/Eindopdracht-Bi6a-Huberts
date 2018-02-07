/**
 * @author Justin Huberts
 * @version jdk-9 in Intellij IDEA 2017.2.6
 */

import javax.swing.*;
import java.util.List;

public class VirusLogica extends JFrame{
    public static List<Integer> AllID_NoDoubles;
    public static List<String> Allname_NoDoubles;
    public static List<String> AllClas_NoDoubles;
    static boolean performed = false;

    public VirusLogica(List<Integer> AllID_NoDoubles,List<String> Allname_NoDoubles,List<String> AllClas_NoDoubles, boolean performed){
        AllID_NoDoubles = this.AllID_NoDoubles;
        Allname_NoDoubles = this.Allname_NoDoubles;
        AllClas_NoDoubles = this.AllClas_NoDoubles;
        performed = this.performed;
    }
    public VirusLogica(){
        AllID_NoDoubles = VirusLogica.AllID_NoDoubles;
        Allname_NoDoubles = VirusLogica.Allname_NoDoubles;
        AllClas_NoDoubles = VirusLogica.AllClas_NoDoubles;
        performed = VirusLogica.performed;
    }
    public List<Integer> getAllID_NoDoubles() {
        return AllID_NoDoubles;
    }
    public void setAllID_NoDoubles(List<Integer> AllID_NoDoubles) {
        this.AllID_NoDoubles = AllID_NoDoubles;
    }

    public List<String> getAllname_NoDoubles() {
        return Allname_NoDoubles;
    }
    public void setAllname_NoDoubles(List<String> Allname_NoDoubles) {
        this.Allname_NoDoubles = Allname_NoDoubles;
    }

    public List<String> getAllClas_NoDoubles() {
        return AllClas_NoDoubles;
    }
    public void setAllClas_NoDoubles(List<String> AllClas_NoDoubles) {
        this.AllClas_NoDoubles = AllClas_NoDoubles;
    }

    public boolean getPerformed(){return performed;}
    public void setPerformed(boolean performed) {
        this.performed = performed;
    }

    public static void main(String[] args) {
        VirusLogica virlog = new VirusLogica();
    }

}
