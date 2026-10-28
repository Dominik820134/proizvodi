public class Proizvod {

    private static int productCount = 0;

    private final String sifra;
    private String naziv;
    private double cijena;
    private int kolicina;

    public Proizvod(String naziv, double cijena, int kolicina) {
        setNaziv(naziv);
        setCijena(cijena);
        checkCount(kolicina);
        this.kolicina = kolicina;

        productCount++;
        this.sifra = "P" + String.format("%03d", productCount); 
    }

    public Proizvod(String naziv, double cijena) {
        this(naziv, cijena, 0);
    }

    public String getSifra() {
        return sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public double getCijena() {
        return cijena;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setNaziv(String naziv) {
        if (naziv == null || naziv.isBlank()) {
            throw new IllegalArgumentException("Naziv ne smije biti prazan.");
        }
        this.naziv = naziv.strip();
    }

    public void setCijena(double cijena) {
        if (cijena < 0) {
            throw new IllegalArgumentException("Cijena ne može biti negativna.");
        }
        this.cijena = cijena;
    }

    private void checkCount(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Količina ne može biti negativna.");
        }
    }

    public void dodaj(int count) {
        checkCount(count);
        kolicina += count;
    }

    public void prodaj(int count) {
        checkCount(count);
        if (count > kolicina) {
            throw new IllegalArgumentException(
                    "Nema dovoljno na skladištu, traženo: " + count + ", na skladištu: " + kolicina + ".");
        }
        kolicina -= count;
    }

    public double ukupnaVrijednost() {
        return cijena * kolicina;
    }

    public double ukupnaVrijednost(double popust) {
        if (popust < 0 || popust > 100) {
            throw new IllegalArgumentException("Popust mora biti između 0 i 100.");
        }
        return ukupnaVrijednost() * (1 - popust / 100);
    }

    public String opis() {
        return sifra + " | " + naziv + " | " + String.format("%.2f", cijena) + " EUR | "
                + kolicina + " kom";
    }

    public boolean jeNaziv(String name) {
        return naziv.equalsIgnoreCase(name.strip());
    }

    public static int getBrojProizvoda() {
        return productCount;
    }

    public static boolean ispravnaSifra(String kod) {
        if (kod == null || kod.length() != 4 || !kod.startsWith("P")) {
            return false;
        }

        for (int i = 1; i < kod.length(); i++) {
            if (!Character.isDigit(kod.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}