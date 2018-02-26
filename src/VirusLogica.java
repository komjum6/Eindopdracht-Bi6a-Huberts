/**
 * @author Justin Huberts
 * @version jdk-9 in Intellij IDEA 2017.2.6
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VirusLogica extends VirusGUI{
    public VirusLogica(){
        actioninput = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    VirusFunctions.PrintSelected();

                } catch (Exception x) {
                    System.out.println("Een Exception vond plaats");
                    System.out.println(x.toString());
                }
            }};
        actionall = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    VirusFunctions.ReadFile();

                    VirusFunctions.getHostAmount();

                    VirusFunctions.Sorting();

                    System.out.println("Het aantal Viruses is: " + Viruses.size());

                    VirusFunctions.DataFill();

                    buttonentries.doClick();

                } catch (java.lang.NumberFormatException jln) {
                    System.out.println("Dit is geen goed Bestand");

                } catch (Exception x) {
                    System.out.println("Een Exception vond plaats");
                    System.out.println(x.toString());
                }
            }
        };
        actionprocess = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    VirusFunctions.ListFiller();

                } catch (Exception g) {
                    System.out.println("Een Exception vond plaats");
                    System.out.println(g.toString());
                }
            }};
}}
