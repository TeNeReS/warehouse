package arkhipov.warehouse.models;

public class Product extends BaseModel {
    private String name;
    private String producer;
    private Integer storeId;

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return String.format("Store id: %s name: %s producer: %s", getId(), getName(), getProducer());
    }
}
