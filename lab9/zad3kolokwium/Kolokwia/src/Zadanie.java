public class Zadanie {
    private int  liczbaPkt,liczbaPustychLinii;
    private String trescZadania;
    public Zadanie(int liczbaPkt,String trescZadania, int liczbaPustychLinii){
        this.liczbaPkt=liczbaPkt;
        this.trescZadania=trescZadania;
        this.liczbaPustychLinii=liczbaPustychLinii;

    }
    public Zadanie(String linia){
        this.liczbaPkt=Integer.parseInt(linia.substring(0,1));
        this.liczbaPustychLinii=Integer.parseInt(linia.substring(2,3));
        this.trescZadania=linia.substring(4);

    }

    public int getLiczbaPkt() {
        return liczbaPkt;
    }

    public int getLiczbaPustychLinii() {
        return liczbaPustychLinii;
    }

    public String getTrescZadania() {
        return trescZadania;
    }

    public void setLiczbaPkt(int liczbaPkt) {
        this.liczbaPkt = liczbaPkt;
    }

    public void setLiczbaPustychLinii(int liczbaPustychLinii) {
        this.liczbaPustychLinii = liczbaPustychLinii;
    }

    public void setTrescZadania(String trescZadania) {
        this.trescZadania = trescZadania;
    }
    private String pusteLinie(){
        String linie="";
        for(int  i=0;i<this.getLiczbaPustychLinii();i++){
            linie+=" \n";

        }
            return linie;
    }

    @Override
    public String toString() {
        return
            "("+this.getLiczbaPkt()+ "pkt) " +this.getTrescZadania() + pusteLinie();
    }
}
