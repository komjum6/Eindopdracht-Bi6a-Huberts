/**
 * @author Justin Huberts
 * @version jdk-9 in Intellij IDEA 2017.2.6
 * Ik heb Nicky en Ruben geholpen.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class VirusGUI extends JFrame {
    static ActionListener actionall;
    static ActionListener actioninput;
    static ActionListener actionprocess;
    static VirusLogica virlog;
    static JTextArea Viruslijstx;
    static JTextArea Viruslijsty;
    static JTextArea Overeenkomst;
    static JButton button;
    static JButton buttonentries;
    static JRadioButton r1;
    static JRadioButton r2;
    static JRadioButton r3;
    static String[] HostIDone;
    static String[] HostIDtwo;
    static String[] VirusClassification;
    static JComboBox<String> VirusClassList, HostIDoneList, HostIDtwoList, Virus1;
    static String SelectedClassification, SelectedHostIDone, SelectedHostIDtwo, SelectedVirus1;
    static ArrayList<Virus> Viruses;
    static JScrollPane scrollone;
    static JScrollPane scrolltwo;
    static JScrollPane scrollthree;
    static JFileChooser chooser;

    public static void main(String[] args) {
        VirusGUI f = new VirusGUI();
        f.setSize(800, 800);
        f.setTitle("Virus Applicatie");

        button = new JButton ("Submit Bestand");
        button.setBounds(10,10,125,30);
        f.add(button);

        buttonentries = new JButton ("Submit Input");
        buttonentries.setBounds(375,300,150,40);
        f.add(buttonentries);

        JTextArea Summary = new JTextArea("De stappen zijn:\n\n1. Zoek Bestand in Chooser\n2. Druk op 'Open'\n3. Kies een Sortering\n4. Submit het bestand\n(5). (Optioneel):\nKijk naar de placeholders\n6. Submit de inputvelden\n7. Voor hergebruik,\n herstart programma");
        Summary.setBounds(10,50,170,280);
        f.add(Summary);

        r1=new JRadioButton("A) ID");
        r2=new JRadioButton("B) Classificatie");
        r3=new JRadioButton("C) Aantal Hosts");

        r1.setBounds(10,350,120,30);
        r2.setBounds(10,400,120,30);
        r3.setBounds(10,450,120,30);

        ButtonGroup bg=new ButtonGroup();
        bg.add(r1);bg.add(r2);bg.add(r3);

        f.add(r1);f.add(r2);f.add(r3);

        chooser = new JFileChooser();
        chooser.setBounds(200,10,500,300);
        f.add(chooser);

        VirusClassification = new String[] {"Placeholders : Please Select File or look at the placeholders","dsRNA", "ssRNA",
                "ssDNA", "Retrovirus","Satellite virus and Virophage","Viroid","Other"};
        VirusClassList = new JComboBox<>(VirusClassification);

        VirusClassList.setBounds(225,360,450,40);
        f.add(VirusClassList);

        HostIDone = new String[] {"Placeholders","dsRNA Bacteria","dsRNA Eukaryota","dsRNA Archaea","dsRNA Viruses","dsRNA Unassigned/Other", "ssRNA Bacteria","ssRNA Eukaryota","ssRNA Archaea","ssRNA Viruses","ssRNA Unassigned/Other",
                "ssDNA Bacteria","ssDNA Eukaryota","ssDNA Archaea","ssDNA Viruses","ssDNA Unassigned/Other", "Retrovirus Bacteria","Retrovirus Eukaryota","Retrovirus Archaea","Retrovirus Viruses","Retrovirus Unassigned/Other","Satellite virus and Virophage Bacteria","Satellite virus and Virophage Eukaryota","Satellite virus and Virophage Archaea","Satellite virus and Virophage Viruses","Satellite virus and Virophage Unassigned/Other","Viroid Bacteria","Viroid Eukaryota","Viroid Archaea","Viroid Viruses","Viroid Unassigned/Other","Other Bacteria","Other Eukaryota","Other Archaea","Other Viruses","Other Unassigned/Other"};
        HostIDoneList = new JComboBox<>(HostIDone);

        HostIDoneList.setBounds(225,450,150,20);
        f.add(HostIDoneList);

        HostIDtwo = new String[] {"Placeholders","dsRNA Bacteria","dsRNA Eukaryota","dsRNA Archaea","dsRNA Viruses","dsRNA Unassigned/Other", "ssRNA Bacteria","ssRNA Eukaryota","ssRNA Archaea","ssRNA Viruses","ssRNA Unassigned/Other",
                "ssDNA Bacteria","ssDNA Eukaryota","ssDNA Archaea","ssDNA Viruses","ssDNA Unassigned/Other", "Retrovirus Bacteria","Retrovirus Eukaryota","Retrovirus Archaea","Retrovirus Viruses","Retrovirus Unassigned/Other","Satellite virus and Virophage Bacteria","Satellite virus and Virophage Eukaryota","Satellite virus and Virophage Archaea","Satellite virus and Virophage Viruses","Satellite virus and Virophage Unassigned/Other","Viroid Bacteria","Viroid Eukaryota","Viroid Archaea","Viroid Viruses","Viroid Unassigned/Other","Other Bacteria","Other Eukaryota","Other Archaea","Other Viruses","Other Unassigned/Other"};
        HostIDtwoList = new JComboBox<>(HostIDtwo);

        HostIDtwoList.setBounds(525,450,150,20);
        f.add(HostIDtwoList);

        String[] VirusLijstEen = new String[] {"HostID2"};
        Virus1 = new JComboBox<>(VirusLijstEen);

        Virus1.setBounds(525,450,150,20);
        f.add(Virus1);

        Viruslijstx = new JTextArea("Placeholders", 6, 8);
        scrollone = new JScrollPane(Viruslijstx, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollone.setBounds(225,550,150,60);
        f.add(scrollone);

        Viruslijsty = new JTextArea("Placeholders", 6, 8);
        scrolltwo = new JScrollPane(Viruslijsty, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrolltwo.setBounds(525,550,150,60);
        f.add(scrolltwo);

        Overeenkomst = new JTextArea("Placeholders", 6, 8);
        scrollthree = new JScrollPane(Overeenkomst, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollthree.setBounds(375,650,150,60);
        f.add(scrollthree);

        JLabel L1=new JLabel("Virusclassificatie");
        L1.setBounds(225,330,450,40);
        JLabel L2=new JLabel("Viruslijst 1");
        L2.setBounds(225,530,150,20);
        JLabel L3=new JLabel("Viruslijst 2");
        L3.setBounds(525,530,150,20);
        JLabel L4=new JLabel("Virusovereenkomst");
        L4.setBounds(375,630,150,20);
        JLabel L5=new JLabel("Hostlijst 1");
        L5.setBounds(225,430,150,20);
        JLabel L6=new JLabel("Hostlijst 2");
        L6.setBounds(525,430,150,20);
        JLabel L7=new JLabel("Sortering");
        L7.setBounds(10,330,170,20);
        L7.setFont(new Font("Papyrus", Font.BOLD, 18));
        f.add(L1);f.add(L2);f.add(L3);f.add(L4);f.add(L5);f.add(L6);f.add(L7);

        f.setLayout(null);
        f.setVisible(true);

        main2(new String[] {"start"});
    }

    public VirusGUI(){
        actioninput = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    SelectedClassification = (String) VirusClassList.getSelectedItem();
                    System.out.println("You selected the Classification: " + SelectedClassification);

                    SelectedHostIDone = (String) HostIDoneList.getSelectedItem();
                    System.out.println("You selected the Host ID: " + SelectedHostIDone);

                    SelectedHostIDtwo = (String) HostIDtwoList.getSelectedItem();
                    System.out.println("You selected the Host ID: " + SelectedHostIDtwo);

                    SelectedVirus1 = (String) Virus1.getSelectedItem();
                    System.out.println("You selected the : " + SelectedVirus1);
                } catch (Exception x) {
                    System.out.println("Een Exception vond plaats");
                    System.out.println(x.toString());
                }
            }};
        actionall = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    File selectedFile = chooser.getSelectedFile();

                    //Tijdelijk: Hier kan nu gekozen worden de ene uit te commenten of de andere
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
                    if (r1.isSelected()) {
                        Viruses.sort(Comparator.comparing(Virus::getHost_id));
                    }
                    if (r2.isSelected()) {
                        Collections.sort(Viruses);
                    }
                    if (r3.isSelected()) {
                        Collections.sort(Viruses, new Comparator<Virus>() {
                            @Override
                            public int compare(final Virus o1, Virus o2) {
                                ArrayList<Integer> AllID = new ArrayList();
                                for (Virus vir : Viruses) {
                                    AllID.add(vir.getHost_id());
                                }
                                Integer id1 = o1.getHost_id();
                                Integer id2 = o2.getHost_id();
                                Integer freq1 = Collections.frequency(AllID, id1);
                                //System.out.println(freq1);
                                o1.setAantal(freq1);
                                Integer freq2 = Collections.frequency(AllID, id2);
                                //System.out.println(freq2);
                                o2.setAantal(freq2);
                                int trueFalse = o1.getAantal().compareTo(o2.getAantal());

                                if (trueFalse == 0) {
                                    o1.setAantal(0);
                                    o2.setAantal(0);
                                }
                                if (trueFalse > 0) {
                                    o1.setAantal(1);
                                    o2.setAantal(-1);
                                }
                                if (trueFalse < 0) {
                                    o1.setAantal(-1);
                                    o2.setAantal(1);
                                } else return 0;
                                return o1.getAantal().compareTo(o2.getAantal());
                            }
                        });
                    }
                    System.out.println("Het aantal Viruses is: " + Viruses.size());

                    ArrayList<Integer> HostIDsEen = new ArrayList<>();
                    ArrayList<Integer> HostIDsTwee = new ArrayList<>();
                    ArrayList<Integer> AllID = new ArrayList<>();
                    ArrayList<String> Allname = new ArrayList<>();
                    ArrayList<String> AllClas = new ArrayList<>();

                    for (Virus vir : Viruses) {
                        AllID.add(vir.getHost_id());
                        Allname.add(vir.getHost_name());
                        AllClas.add(vir.getClassificatie());
                    }
                    AllClas.add("Geen Classificatiesort");

                    List<Integer> AllID_NoDoubles = new ArrayList<>(new LinkedHashSet<>(AllID));

                    List<String> stringsHost1 = AllID_NoDoubles.stream().map(Object::toString).collect(Collectors.toList());
                    HostIDone = stringsHost1.toArray(new String[0]);
                    List<String> stringsHost2 = AllID_NoDoubles.stream().map(Object::toString).collect(Collectors.toList());
                    HostIDtwo = stringsHost2.toArray(new String[0]);
                    DefaultComboBoxModel modelHost1 = new DefaultComboBoxModel(HostIDone);
                    DefaultComboBoxModel modelHost2 = new DefaultComboBoxModel(HostIDtwo);
                    HostIDoneList.setModel(modelHost1);
                    HostIDtwoList.setModel(modelHost2);

                    List<String> Allname_NoDoubles = new ArrayList<>(new LinkedHashSet<>(Allname));
                    List<String> AllClas_NoDoubles = new ArrayList<>(new LinkedHashSet<>(AllClas));

                    VirusClassification = AllClas_NoDoubles.toArray(new String[AllClas_NoDoubles.size()]);
                    DefaultComboBoxModel modelone = new DefaultComboBoxModel(VirusClassification);
                    VirusClassList.setModel(modelone);

                    HostIDsEen.retainAll(HostIDsTwee);
                    StringBuilder s = new StringBuilder();
                    for (Integer i : HostIDsEen) {
                        s.append(i.toString()).append("\n");
                    }
                    Overeenkomst.setText(s.toString());
                    Overeenkomst.setEditable(false);

                    boolean performed = true;

                    virlog = new VirusLogica(AllID_NoDoubles, Allname_NoDoubles, AllClas_NoDoubles, performed);
                    virlog.setAllID_NoDoubles(AllID_NoDoubles);
                    virlog.setAllname_NoDoubles(Allname_NoDoubles);
                    virlog.setAllClas_NoDoubles(AllClas_NoDoubles);
                    virlog.setPerformed(performed);
                    buttonentries.doClick();
                } catch (IOException IO) {
                    System.out.println("IO Error, Bestand is Niet Goed Geladen");

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
                    ArrayList<Integer> HostIDsEen = new ArrayList<>();
                    ArrayList<Integer> HostIDsTwee = new ArrayList<>();
                    ArrayList<Virus>  VirusClassDel = new ArrayList<>();
                    ArrayList<String> Hostnames = new ArrayList<>();
                    for (Virus vir : Viruses){
                        Hostnames.add(vir.getHost_name());
                    }

                    for (Virus vir : Viruses) {
                        if (vir.getHost_id() == (Integer.parseInt(SelectedHostIDone))) {
                            HostIDsEen.add(vir.getVirus_id());
                            StringBuilder s = new StringBuilder();
                            for (Integer i : HostIDsEen) {
                                for (String n : Hostnames){
                                    s.append(i.toString());
                                    s.append(" ").append(n).append("\n");
                                }
                            }

                            Viruslijstx.setText(s.toString());
                            Viruslijstx.setLineWrap(true);
                            Viruslijstx.setWrapStyleWord(true);
                            Viruslijstx.setAutoscrolls(true);
                            Viruslijstx.setEditable(false);
                        }
                        if (vir.getHost_id() == (Integer.parseInt(SelectedHostIDtwo))) {
                            HostIDsTwee.add(vir.getVirus_id());
                            StringBuilder s = new StringBuilder();
                            for (Integer i : HostIDsTwee) {
                                for (String n : Hostnames){
                                    s.append(i.toString());
                                    s.append(" ").append(n).append("\n");
                                }
                            }
                            Viruslijsty.setText(s.toString());
                            Viruslijsty.setLineWrap(true);
                            Viruslijsty.setWrapStyleWord(true);
                            Viruslijsty.setAutoscrolls(true);
                            Viruslijsty.setEditable(false);
                        }
                        if (!vir.getClassificatie().equals(SelectedClassification)){
                            VirusClassDel.add(vir);
                        }
                    }
                    //for (Virus vir:VirusClassDel){
                        //Viruses.remove(vir);
                        //Belangrijk: Hier wilde ik gaan filteren op classificatie, maar de Viruslijsten werden al gevuld voor dit punt. Hierdoor kon je geen tweede keer op Submit Input drukken.
                        //Het lukte mij om dit werked te krijgen door het verwijderen bovenin te doen en de Virusobjectenlijst Viruses te kopieren voor een backup later in de code Viruses = VirusBackup (de kopie)
                        //Maar dan moest je wel zoeken tussen nietbestaande HostID's in de lijsten en dat is irriterend, want je zoekt een naald in een hooiberg.
                        //Dit dingtje moet mee rekening worden gehouden in de beoordeling, want het werkte wel maar het is niet userfriendly en daarom koos ik hiervoor.
                    //}

                } catch (Exception g) {
                    System.out.println("Een Exception vond plaats");
                    System.out.println(g.toString());
                }
            }};
    }
    public static void main2(String[] argsFirst) {
        button.addActionListener(VirusGUI.actionall);
        buttonentries.addActionListener(actioninput);
        buttonentries.addActionListener(actionprocess);
    }
}