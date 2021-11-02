package exam.objects;

public class Outsourced extends Part{
    private String companyName;

    public Outsourced(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
    }

    public void setCompanyName(String name){
        this.companyName = name;
    }

    public String getCompanyName(){
        return this.companyName;
    }
}
