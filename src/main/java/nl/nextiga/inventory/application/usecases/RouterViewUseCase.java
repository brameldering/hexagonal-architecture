package nl.nextiga.inventory.application.usecases;

import nl.nextiga.inventory.domain.Router;

import java.util.List;
import java.util.function.Predicate;

public interface RouterViewUseCase {

    List<Router> getRouters(Predicate<Router> filter);
}
