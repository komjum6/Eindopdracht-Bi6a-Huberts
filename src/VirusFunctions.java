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
import java.util.stream.Collectors;

public class VirusFunctions extends VirusLogica {
    public static void ReadFile() {
        try {
            File selectedFile = chooser.getSelectedFile();

            //Tijdelijk: Hier kan nu gekozen worden de ene uit te commenten of de andere
            BufferedReader r = new BufferedReader(new FileReader("D:/\\Bio-Infmap/Data/Periode 6/virushostdb.tsv"));
            //BufferedReader r = new BufferedReader(new FileReader(selectedFile));

            Viruses = new ArrayList<>();

            VirusHostMap = new HashMap<>();

            for (String x = r.readLine(); x != null; x = r.readLine()) {

                if (!x.startsWith("virus tax id")) {

                    String[] gesplit = x.split("\t", -1);

                    //De lege entries zijn nu 0 bij host id's
                    Virus VirusEntry = new Virus(Integer.parseInt(gesplit[0]), gesplit[1], gesplit[2], Integer.parseInt(gesplit[7].replaceAll("(^(\\r\\n|\\n|\\r)$)|(^(\\r\\n|\\n|\\r))|^\\s*$", "0")), gesplit[8]);
                    Viruses.add(VirusEntry);

                    if(VirusHostMap.containsKey(VirusEntry.getHost_id())){
                        VirusHostMap.get(VirusEntry.getHost_id()).add(VirusEntry);
                    }
                    else {
                        HashSet<Virus> VirusHashSet = new HashSet<>();
                        VirusHashSet.add(VirusEntry);
                        VirusHostMap.put(VirusEntry.getHost_id(), VirusHashSet);
                    }
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

            SelectedVirus1 = (String) Virus1.getSelectedItem();

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

        ArrayList<Integer> AllID = new ArrayList<>();
        ArrayList<String> Allname = new ArrayList<>();
        ArrayList<String> AllClas = new ArrayList<>();

        AllClas.add("Geen Classificatiesort");

        for (Virus vir : Viruses) {
            AllID.add(vir.getHost_id());
            Allname.add(vir.getHost_name());
            AllClas.add(vir.getClassificatie());
        }

        List<Integer> AllID_NoDoubles = new ArrayList<>(new LinkedHashSet<>(AllID));

        List<String> stringsHost1 = AllID_NoDoubles.stream().map(Object::toString).collect(Collectors.toList());
        HostIDone = stringsHost1.toArray(new String[0]);
        List<String> stringsHost2 = AllID_NoDoubles.stream().map(Object::toString).collect(Collectors.toList());
        HostIDtwo = stringsHost2.toArray(new String[0]);

        HostIDoneList.setModel(new DefaultComboBoxModel(HostIDone));
        HostIDtwoList.setModel(new DefaultComboBoxModel(HostIDtwo));

        List<String> Allname_NoDoubles = new ArrayList<>(new LinkedHashSet<>(Allname));
        List<String> AllClas_NoDoubles = new ArrayList<>(new LinkedHashSet<>(AllClas));

        VirusClassification = AllClas_NoDoubles.toArray(new String[AllClas_NoDoubles.size()]);
        VirusClassList.setModel(new DefaultComboBoxModel(VirusClassification));

    }
    public static void ListFiller(){
        StringBuilder sx = new StringBuilder();
        StringBuilder sy = new StringBuilder();
        for (Virus vir : Viruses) {

            if (vir.getClassificatie().equals(SelectedClassification) | !vir.getClassificatie().equals("Geen Classificatiesort")) {

                if (vir.getHost_id() == (Integer.parseInt(SelectedHostIDone))) {
                    sx.append(vir.getVirus_id()).append("\n");
                }
                if (vir.getHost_id() == (Integer.parseInt(SelectedHostIDtwo))) {
                    sy.append(vir.getVirus_id()).append("\n");
                }
            }
            else {
                Viruslijstx.setText("Geen Overeenkomst");
                Viruslijsty.setText("Geen Overeenkomst");
            }
        }
        Viruslijstx.setText(sx.toString());
        Viruslijsty.setText(sy.toString());
        SimilaritySearch(sx,sy);
    }
    public static void SimilaritySearch(StringBuilder sx, StringBuilder sy){
        StringBuilder s = new StringBuilder();

        List<Integer> x  = Arrays.stream(Arrays.stream(sx.toString().split("\n")).mapToInt(Integer::parseInt).toArray()).boxed().collect( Collectors.toList() );
        List<Integer> y  = Arrays.stream(Arrays.stream(sy.toString().split("\n")).mapToInt(Integer::parseInt).toArray()).boxed().collect( Collectors.toList() );

        x.retainAll(y);

        for (Integer IDname: x){
            if(y.contains(IDname))
                s.append(IDname + "\n");
        }
        if (s.length() == 0){  //Dit werkt nog niet
            Overeenkomst.setText("Geen Overeenkomst");
        }
        Overeenkomst.setText(s.toString());
    }
}
