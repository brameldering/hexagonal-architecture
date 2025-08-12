package nl.nextiga.inventory.framework.adapters.output.file;

import nl.nextiga.inventory.application.ports.output.RouterViewOutputPort;
import nl.nextiga.inventory.domain.Router;
import nl.nextiga.inventory.domain.RouterId;
import nl.nextiga.inventory.domain.RouterType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class RouterViewFileAdapter implements RouterViewOutputPort {

    private static RouterViewFileAdapter instance;

    @Override
    public List<Router> fetchRouters() {
        return readFileAsString();
    }

    private static List<Router> readFileAsString() {
        List<Router> routers = new ArrayList<>();
        try (Stream<String> stream = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(RouterViewFileAdapter.class.getClassLoader().
                                getResourceAsStream("routers.txt")))).lines()) {
            stream.forEach(line ->{
                String[] routerEntry = line.split(";");
                var id = routerEntry[0];
                var type = routerEntry[1];
                Router router = new Router(RouterType.valueOf(type), RouterId.of(id));
                routers.add(router);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return routers;
    }

    // SingleTon
    private RouterViewFileAdapter() {
    }

    // Singleton
    public static RouterViewFileAdapter getInstance() {
        if (instance == null) {
            instance = new RouterViewFileAdapter();
        }
        return instance;
    }
}
