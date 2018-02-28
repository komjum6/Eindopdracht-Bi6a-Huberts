/**
 * @author Justin Huberts
 * @version jdk-9 in Intellij IDEA 2017.2.6
 */

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class VirusFunctions extends VirusLogica {
    public static void ReadFile() {
        try {
            File selectedFile = chooser.getSelectedFile();

            //BufferedReader r = new BufferedReader(new FileReader("D:/\\Bio-Infmap/Data/Periode 6/virushostdb.tsv"));
            BufferedReader r = new BufferedReader(new FileReader(selectedFile));

            Viruses = new ArrayList<>();

            for (String x = r.readLine(); x != null; x = r.readLine()) {

                if (!x.startsWith("virus tax id")) {

                    String[] gesplit = x.split("\t", -1);

                    //De lege entries zijn nu 0 bij host id's
                    Virus VirusEntry = new Virus(Integer.parseInt(gesplit[0]), gesplit[1], gesplit[2], Integer.parseInt(gesplit[7].replaceAll("(^(\\r\\n|\\n|\\r)$)|(^(\\r\\n|\\n|\\r))|^\\s*$", "0")), gesplit[8]);
                    Viruses.add(VirusEntry);
                }
            }

        } catch (IOException IO) {
            System.out.println("IO Error, Bestand is Niet Goed Geladen");

        } catch (java.lang.NumberFormatException jln) {
            System.out.println("Dit is geen goed Bestand");

        } catch (Exception x) {
            System.out.println("Een Exception vond plaats");
            System.out.println(x.toString());
        }
    }

    public static void getHostAmount() {
        /**
         * Hier wordt een hashmap gebruikt om per Virusobject in de lijst met Virussen (Viruses) een waarde te geven van het aantal aan hosts.
         * Hierbij is de waarde de frequentie en de key is de hostID. Als een key zich reeds in de hashmap bevind zal de waarde ervan stijgen met 1.
         */
        HashMap<Integer,Integer> NumberHostMap = new HashMap<>();
        for(int i=0; i<Viruses.size(); i++){

            if(NumberHostMap.containsKey(Viruses.get(i).getHost_id())){
                NumberHostMap.put(Viruses.get(i).getHost_id(), NumberHostMap.get(Viruses.get(i).getHost_id())+1);
            } else {
                NumberHostMap.put(Viruses.get(i).getHost_id(), 1);
            }
        }

        for(Virus vir: Viruses){

            if(NumberHostMap.containsKey(vir.getHost_id())){
                vir.setAantal(NumberHostMap.get(vir.getHost_id()));
            }
        }
    }


    public static void PrintSelected() {
        try {
            SelectedClassification = (String) VirusClassList.getSelectedItem();

            SelectedHostIDone = (String) HostIDoneList.getSelectedItem();

            SelectedHostIDtwo = (String) HostIDtwoList.getSelectedItem();

        } catch (Exception x) {
            System.out.println("Een Exception vond plaats");
            System.out.println(x.toString());
        }
    }

    public static void CompareHostNumber() {
        try {
            Collections.sort(Viruses, new Comparator<Virus>() {
                @Override
                public int compare(final Virus o1, Virus o2) {

                    return o1.getAantal()- o2.getAantal();
                }
            });
        } catch (java.lang.NumberFormatException jln) {
            System.out.println("Dit is geen goed Bestand");

        } catch (Exception x) {
            System.out.println("Een Exception vond plaats");
            System.out.println(x.toString());
        }
    }

    public static void Sorting(){

        if (r1.isSelected()) {
            Viruses.sort(Comparator.comparing(Virus::getHost_id));
            DataFill();
        }
        if (r2.isSelected()) {
            Collections.sort(Viruses);
            DataFill();
        }
        if (r3.isSelected()) {
            VirusFunctions.CompareHostNumber();
            DataFill();
        }
        else {
            DataFill();
        }
    }

    public static void DataFill(){

        LinkedHashSet<String> Allname = new LinkedHashSet<>();
        LinkedHashSet<String> AllClas = new LinkedHashSet<>();

        AllClas.add("Geen Classificatiesort");

        for (Virus vir : Viruses) {
            Allname.add(Integer.toString(vir.getHost_id()) + " " + vir.getHost_name());
            AllClas.add(vir.getClassificatie());
        }

        HostIDone = Allname.toArray(new String[Allname.size()]);

        HostIDoneList.setModel(new DefaultComboBoxModel(HostIDone));
        HostIDtwoList.setModel(new DefaultComboBoxModel(HostIDone));

        VirusClassification = AllClas.toArray(new String[AllClas.size()]);
        VirusClassList.setModel(new DefaultComboBoxModel(VirusClassification));

    }
    public static void ListFiller(){

        StringBuilder sx = new StringBuilder();
        StringBuilder sy = new StringBuilder();
        for (Virus vir : Viruses) {

            if (vir.getClassificatie().equals(SelectedClassification) | !vir.getClassificatie().equals("Geen Classificatiesort")) {

                if (vir.getHost_id() == (Integer.parseInt(SelectedHostIDone.replaceAll("[^\\d.]|\\.", "")))) {
                    sx.append(vir.getVirus_id()).append("\n");
                }
                if (vir.getHost_id() == (Integer.parseInt(SelectedHostIDtwo.replaceAll("[^\\d.]|\\.", "")))) {
                    sy.append(vir.getVirus_id()).append("\n");
                }
            }
        }
        Viruslijstx.setText(sx.toString());
        Viruslijsty.setText(sy.toString());
        SimilaritySearch(sx,sy);
    }
    public static void SimilaritySearch(StringBuilder sx, StringBuilder sy){
        StringBuilder s = new StringBuilder();

        Set<String> x = new HashSet<>(Arrays.asList(sx.toString().split("\n")));
        Set<String> y = new HashSet<>(Arrays.asList(sy.toString().split("\n")));

        x.retainAll(y);

        for (String IDname: x){
            if(y.contains(IDname))
                s.append(IDname + "\n");
        }
        Overeenkomst.setText(s.toString());
        if (s.length() == 0){
            Overeenkomst.setText("Geen Overeenkomst");
        }
    }
}
