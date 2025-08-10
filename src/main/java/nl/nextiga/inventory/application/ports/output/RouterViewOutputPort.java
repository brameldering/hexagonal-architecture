package nl.nextiga.inventory.application.ports.output;

import nl.nextiga.inventory.domain.Router;

import java.util.List;

public interface RouterViewOutputPort {

    List<Router> fetchRouters();
}
