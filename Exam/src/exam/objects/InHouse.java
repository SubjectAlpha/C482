package exam.objects;
/**
 *
 * @author Jacob Starr
 */
public class InHouse extends Part {
    private int machineId;

    /**
     * Constructor method to init object
     */
    public InHouse(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
    }

    /**
     * Sets the id of the machine object.
     * @param machineId the machineId to set
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /**
     * Returns this objects machine ID
     * @return the machineId
     */
    public int getMachineId(){
        return this.machineId;
    }
}
