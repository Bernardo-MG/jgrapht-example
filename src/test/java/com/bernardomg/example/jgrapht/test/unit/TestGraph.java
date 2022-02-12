
package com.bernardomg.example.jgrapht.test.unit;

import java.util.Arrays;
import java.util.Iterator;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.SingleSourcePaths;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.ClosestFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.bernardomg.example.jgrapht.model.DefaultDefinition;
import com.bernardomg.example.jgrapht.model.Definition;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestGraph {

    private final Graph<Definition, DefaultEdge> graph = getGraph();

    @Test
    public final void testPath_VehicleToCar() {
        final DijkstraShortestPath<Definition, DefaultEdge> dijkstraAlg;
        final SingleSourcePaths<Definition, DefaultEdge> iPaths;

        dijkstraAlg = new DijkstraShortestPath<>(graph);
        iPaths = dijkstraAlg.getPaths(vehicle);
        for (final Definition def : iPaths.getPath(car).getVertexList()) {
            log.info("{}", def.getName());
        }
    }

    @Test
    public final void testSearch_ByName() {
        final Definition def;

        def = graph.vertexSet().stream()
                .filter(d -> "bicycle".equals(d.getName())).findAny().get();

        Assertions.assertEquals("bicycle", def.getName());
    }

    @Test
    public final void testTraverse_BreadthFirst() {
        final Definition def;
        final Iterator<Definition> iterator;

        def = graph.vertexSet().stream()
                .filter(d -> "vehicle".equals(d.getName())).findAny().get();

        iterator = new BreadthFirstIterator<>(graph, def);
        log.info("-- Breadth first --");
        while (iterator.hasNext()) {
            final Definition d = iterator.next();
            log.info("{} ", d.getName());
        }
        log.info("----");
    }

    @Test
    public final void testTraverse_ClosestFirst() {
        final Definition def;
        final Iterator<Definition> iterator;

        def = graph.vertexSet().stream()
                .filter(d -> "vehicle".equals(d.getName())).findAny().get();

        iterator = new ClosestFirstIterator<>(graph, def);
        log.info("-- Closest first --");
        while (iterator.hasNext()) {
            final Definition d = iterator.next();
            log.info("{} ", d.getName());
        }
        log.info("----");
    }

    @Test
    public final void testTraverse_DepthFirst() {
        final Definition def;
        final Iterator<Definition> iterator;

        def = graph.vertexSet().stream()
                .filter(d -> "vehicle".equals(d.getName())).findAny().get();

        iterator = new DepthFirstIterator<>(graph, def);
        log.info("-- Depth first --");
        while (iterator.hasNext()) {
            final Definition d = iterator.next();
            log.info("{} ", d.getName());
        }
        log.info("----");
    }

    private Definition vehicle;

    private Definition car;

    private final Graph<Definition, DefaultEdge> getGraph() {
        final Graph<Definition, DefaultEdge> g;
        final Definition automobile;
        final Definition bike;
        final Definition bicycle;

        vehicle = new DefaultDefinition();
        vehicle.setName("vehicle");
        vehicle.setProperties(Arrays.asList("Moves around"));

        automobile = new DefaultDefinition();
        automobile.setName("automobile");
        automobile.setProperties(Arrays.asList("Moves itself"));

        car = new DefaultDefinition();
        car.setName("car");
        car.setProperties(Arrays.asList("Four wheels"));

        bike = new DefaultDefinition();
        bike.setName("bike");
        bike.setProperties(Arrays.asList("Two wheels"));

        bicycle = new DefaultDefinition();
        bicycle.setName("bicycle");
        bicycle.setProperties(Arrays.asList("Two wheels"));

        g = new SimpleGraph<>(DefaultEdge.class);
        g.addVertex(vehicle);
        g.addVertex(automobile);
        g.addVertex(car);
        g.addVertex(bike);
        g.addVertex(bicycle);

        g.addEdge(vehicle, automobile);
        g.addEdge(automobile, car);
        g.addEdge(automobile, bike);
        g.addEdge(vehicle, bicycle);

        return g;
    }

}
