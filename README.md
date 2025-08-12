# inventory

This project implements a Telco inventory system according to the hexagonal architecture principles.

## Architecture Overview

This design follows a hexagonal (ports-and-adapters) architecture. Incoming HTTP requests into the REST layer delegate to a CLI adapter, which drives the core use case through clearly defined input and output ports. The outbound file adapter implements the port to load domain entities from a text resource.

## Components and Responsibilities

### Adapters

nl.nextiga.inventory.restAPIs.**RouterRestAPI** («REST API») 
- Exposes the HTTP GET /test endpoint. 
- Instantiates RouterViewCLIAdapter and returns the filtered router list as plain text.

nl.nextiga.inventory.framework.adapters.input.stdin.**RouterViewCLIAdapter** («Adapter»)
- Holds a reference to RouterViewUseCase. 
- In its constructor it wires the input port to the file adapter. 
- Its obtainRelatedRouters(type) method applies the domain filter and returns matching routers.

### Ports & Use Case

nl.nextiga.inventory.application.usecases.**RouterViewUseCase** («Input Port», interface) 
- Declares the core application operation: List<Router> getRouters(Predicate<Router> filter);

nl.nextiga.inventory.application.ports.input.**RouterViewInputPort** («Use Case») 
- Implements RouterViewUseCase. 
- Depends on RouterViewOutputPort to fetch raw routers, then applies the provided Predicate<Router> filter via Router.retrieveRouter(...).

nl.nextiga.inventory.application.ports.output.**RouterViewOutputPort** («Output Port», interface) 
- Declares the outbound operation: List<Router> fetchRouters();

### Infrastructure

nl.nextiga.inventory.framework.adapters.output.file.**RouterViewFileAdapter** («File Adapter, Singleton») 
- Implements RouterViewOutputPort. 
- Lazily loads routers.txt from the classpath, parses each line into Router (using RouterType and RouterId), and returns the list.

### Domain Entities

nl.nextiga.inventory.domain.**Router** («Entity») 
- Holds RouterType and RouterId. 
- Provides:
  - static Predicate<Router> filterRouterByType(RouterType)
  - static List<Router> retrieveRouter(List<Router>, Predicate<Router>)
  - toString() for logging

nl.nextiga.inventory.domain.**RouterId** (Value Object) 
- Wraps the router’s unique ID. 
- Provides a factory of(String) and toString().

nl.nextiga.inventory.domain.**RouterType** (Enum) 
- Defines two values: EDGE and CORE
