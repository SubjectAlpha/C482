package exam.objects;
/**
 *
 * @author Jacob Starr
 */
public class Outsourced extends Part{
    private String companyName;

    /**
     * Constructor method to init object.
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
    }

    /**
     * Sets this objects company name.
     * @param name the company name to set
     */
    public void setCompanyName(String name){
        this.companyName = name;
    }

    /**
     * Returns this objects company name.
     * @return the company name
     */
    public String getCompanyName(){
        return this.companyName;
    }
}
