public class Main {

    public static void main(String[] args) {
        Proizvod prvi = new Proizvod("Čaj od kamilice", 3.50, 20);
        Proizvod drugi = new Proizvod("Kava Franck", 8.20, 15);
        
        System.out.println(prvi.opis());
        System.out.println(drugi.opis());
        
        Proizvod treci = new Proizvod("Mlijeko zbregov", 1.49);
        System.out.println(treci.opis());
        
        prvi.dodaj(10);
        treci.dodaj(50);
        drugi.prodaj(5);
        System.out.println(prvi.getNaziv() + ": na skladištu još " + prvi.getKolicina() + " kom");
        
        try {
            drugi.prodaj(1000);
        } catch (IllegalArgumentException e) {
            System.out.println("Odbijeno: " + e.getMessage());
        }
        
        try {
            new Proizvod("   ", 10);
        } catch (IllegalArgumentException e) {
            System.out.println("Odbijeno: " + e.getMessage());
        }
        
        try {
            treci.setCijena(-3);
        } catch (IllegalArgumentException e) {
            System.out.println("Odbijeno: " + e.getMessage());
        }
        
        System.out.printf("Ukupna vrijednost za (%s): %.2f EUR%n", drugi.getNaziv(), drugi.ukupnavrijednost());
        System.out.printf("Ukupna vrijednost uz 15%% popusta (%s): %.2f EUR%n", drugi.getNaziv(), drugi.ukupnavrijednost(15));
        
        System.out.println("Broj proizvoda: " + Proizvod.getBrojProizvoda());
        System.out.println("Je li P042 ispravna šifra? " + Proizvod.ispravnaSifra("P042"));
        System.out.println("Je li X042 ispravna šifra? " + Proizvod.ispravnaSifra("X042"));
        
        Proizvod[] inventar = {prvi, drugi, treci};
        int ukupnaKolicina = 0;
        for (Proizvod p : inventar) {
            ukupnaKolicina += p.getKolicina();
        }
        System.out.println("Ukupna količina na skladištu: " + ukupnaKolicina);
        
        Proizvod isti = prvi;
        isti.dodaj(5);
        System.out.println("Preko varijable prvi: " + prvi.getKolicina() + " kom");
        
        int kol = 50;
        dupliraj(kol);
        System.out.println("Varijabla kol nakon 'dupliciranja': " + kol);
        
        povisiciCijenu(treci);
        System.out.println("Cijena nakon podizanja: " + treci.getCijena());
        
        zamijeni(treci);
        System.out.println("Naziv nakon zamjene: " + treci.getNaziv());
        
        String tipkano = new String("kava franck");
        System.out.println("== operator: " + (tipkano == "kava franck"));
        System.out.println("equals metoda: " + tipkano.equals("kava franck"));
        System.out.println("jeNaziv metoda: " + drugi.jeNaziv(tipkano));
        
        StringBuilder katalog = new StringBuilder("Katalog: ");
        for(Proizvod p : inventar) {
            katalog.append(p.getNaziv()).append("; ");
        }
        System.out.println(katalog);
    }
    
    static void dupliraj(int x) {
        x = x * 2;
    }
    
    static void povisiciCijenu(Proizvod p) {
        p.setCijena(p.getCijena() + 1.00);
    }
    
    static void zamijeni(Proizvod p) {
        p = new Proizvod("Novi proizvod", 5.00, 10);
    }
}