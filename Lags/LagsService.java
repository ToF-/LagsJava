import java.util.ArrayList;
import java.util.List;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.util.Collections;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;

class LagsService {
        private List<Ordre> listOrdre = new ArrayList<Ordre>();

        // lit le fihier des ordres et calcule le CA
        public void getFichierOrder(String fileName)
        {
            try{

            for (String line : Files.readAllLines(Paths.get(fileName))) {
                String[] champs = line.split(";");
                String chp1 = champs[0];
                int chp2 = Integer.parseInt(champs[1]);
                int champ3 = Integer.parseInt(champs[2]);
                double chp4 = Double.parseDouble(champs[3]);
                Ordre ordre = new Ordre(chp1, chp2, champ3, chp4);
                listOrdre.add(ordre);

        }
            }
            catch (IOException e)
            {
                System.out.println("FICHIER ORDRES.CSV NON TROUVE. CREATION FICHIER.");
                writeOrdres(fileName);
            }
        }

        // écrit le fichier des ordres
        void writeOrdres(String nomFich)
        {
            List<String> lines = new ArrayList<String>();
            for(int i=0; i<listOrdre.size(); i++) {
                Ordre ordre = listOrdre.get(i);
                String ligneCSV = new String();
                ligneCSV = ordre.getId() + ";" + Integer.toString(ordre.getDebut()) +";"+Integer.toString(ordre.getDuree())+";"+Double.toString(ordre.prix());
                lines.add(ligneCSV);
                }
            try{
                Files.write(Paths.get(nomFich), lines,StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND, StandardOpenOption.WRITE);
            }
            catch (IOException e)
            {
                System.out.println("PROBLEME AVEC FICHIER");
            }
        }


        // affiche la liste des ordres
        public void liste()
        {
            listOrdre.sort((o1, o2) -> o1.getDebut()-o2.getDebut());
            System.out.println("LISTE DES ORDRES\n");
            System.out.format("%8s %8s %5s %13s", "ID", "DEBUT", "DUREE", "PRIX\n");
            System.out.format("%8s %8s %5s %13s", "--------", "-------", "-----", "----------\n");
            // listOrdre.ForEach(AfficherOrdre);
            for(int i=0; i<listOrdre.size(); i++) {
                Ordre ordre = listOrdre.get(i);
                afficherOrdre(ordre);
            }
            System.out.format("%8s %8s %5s %13s", "--------", "-------", "-----", "----------\n");
        }

        public void afficherOrdre(Ordre ordre)
        {
            System.out.format("%s8 %08d %05d %10.2f\n", ordre.getId(), ordre.getDebut(), ordre.getDuree(), ordre.prix());

        }
}
//        // Ajoute un ordre; le CA est recalculé en conséquence
//        public void ajouterOrdre()
//        {
//            System.out.println("AJOUTER UN ORDRE");
//            System.out.println("FORMAT = ID;DEBUT;FIN;PRIX");
//            String line = Console.ReadLine().ToUpper();
//            var champs = line.Split(';');
//            String id = champs[0];
//            int dep = Int32.Parse(champs[1]);
//            int dur = Int32.Parse(champs[2]);
//            double prx = Double.Parse(champs[3]);
//            Ordre ordre = new Ordre(id, dep, dur, prx);
//            ListOrdre.Add(ordre);
//            WriteOrdres("ordres.csv");
//        }
//
//        //public void calculerLeCA()
//        //{
//        //    System.out.println("CALCUL CA..");
//        //    laListe = laListe.OrderBy(ordre => ordre.debut).ToList();
//        //    double ca = CA(laListe);
//        //    System.out.println("CA: {0,10:N2}", ca);
//        //}
//
//        private double ca(List<Ordre> ordres, bool debug)
//        {
//            // si aucun ordre, job done, TROLOLOLO..
//            if (ordres.Count() == 0)
//                return 0.0;
//            Ordre order = ordres.ElementAt(0);
//            // attention ne marche pas pour les ordres qui depassent la fin de l'année 
//            // voir ticket PLAF nO 4807 
//            List<Ordre> liste = ordres.Where(ordre => ordre.debut >= order.debut + order.duree).ToList();
//            List<Ordre> liste2 = ordres.GetRange(1, ordres.Count() - 1);
//            double ca = order.prix + CA(liste, debug);
//            // Lapin compris?
//            double ca2 = CA(liste2, debug);
//            Console.Write(debug ? String.Format("{0,10:N2}\n", Math.Max(ca, ca2)):".");
//            return Math.Max(ca, ca2); // LOL
//        }
//
//        // MAJ du fichier
//        public void suppression()
//        {
//            System.out.println("SUPPRIMER UN ORDRE");
//            Console.Write("ID:");
//            string id = Console.ReadLine().ToUpper();
//            this.ListOrdre = ListOrdre.Where(ordre => ordre.id != id).ToList();
//            WriteOrdres("ORDRES.CSV");
//        }
//
//
//
//        private void calculerLeCA(bool debug)
//        {
//            System.out.println("CALCUL CA..");
//            ListOrdre = ListOrdre.OrderBy(ordre => ordre.debut).ToList();
//            double ca = CA(ListOrdre, debug);
//            System.out.println("CA: {0,10:N2}", ca);
//        }
//
//    }
//}
//}
