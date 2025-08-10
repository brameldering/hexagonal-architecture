package nl.nextiga.inventory.framework.adapters.input.stdin;

import nl.nextiga.inventory.application.ports.input.RouterViewInputPort;
import nl.nextiga.inventory.application.usecases.RouterViewUseCase;
import nl.nextiga.inventory.domain.Router;
import nl.nextiga.inventory.domain.RouterType;
import nl.nextiga.inventory.framework.adapters.output.file.RouterViewFileAdapter;

import java.util.List;

public class RouterViewCLIAdapter {

    private RouterViewUseCase routerViewUseCase;

    public RouterViewCLIAdapter(){
        setAdapters();
    }

    public List<Router> obtainRelatedRouters(String type) {
        return routerViewUseCase.getRouters(
                Router.filterRouterByType(RouterType.valueOf(type)));
    }

    private void setAdapters(){
        this.routerViewUseCase = new RouterViewInputPort(RouterViewFileAdapter.getInstance());
    }
}
