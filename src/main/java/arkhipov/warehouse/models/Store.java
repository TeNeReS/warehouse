package arkhipov.warehouse.models;

public class Store extends BaseModel {
    private String name;
    private String address;

    public Store() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("Store id: %s name: %s address: %s", getId(), getName(), getAddress());
    }
}
