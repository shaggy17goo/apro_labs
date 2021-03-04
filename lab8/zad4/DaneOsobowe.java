class DaneOsobowe {
     private  String name;
     private  String surName;
     private  String bDay;
     private  long PESEL;

    public DaneOsobowe(String name, String surName, String bDay, long PESEL){
        this.name=name;
        this.surName=surName;
        this.bDay=bDay;
        this.PESEL=PESEL;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getbDay() {
        return bDay;
    }

    public long getPESEL() {
        return PESEL;
    }
}
