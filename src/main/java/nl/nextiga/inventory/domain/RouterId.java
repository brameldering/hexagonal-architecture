package nl.nextiga.inventory.domain;

public class RouterId {

    private final String id;

    private RouterId(String id){
        this.id = id;
    }

    public static RouterId of(String id){
        return new RouterId(id);
    }

    @Override
    public String toString() {
        return "RouterId{" +
                "id='" + id + '\'' +
                '}';
    }
}
