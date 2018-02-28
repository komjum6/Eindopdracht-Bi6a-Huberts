/**
 * @author Justin Huberts
 * @version jdk-9 in Intellij IDEA 2017.2.6
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VirusLogica extends VirusGUI{
    public VirusLogica(){
        actionall = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    VirusFunctions.ReadFile();

                    VirusFunctions.getHostAmount();

                    VirusFunctions.DataFill();

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
                    VirusFunctions.Sorting();

                } catch (Exception g) {
                    System.out.println("Een Exception vond plaats");
                    System.out.println(g.toString());
                }
            }};
        actioninput = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    VirusFunctions.PrintSelected();

                    VirusFunctions.ListFiller();

                } catch (Exception x) {
                    System.out.println("Een Exception vond plaats");
                    System.out.println(x.toString());
                }
            }};
}}
