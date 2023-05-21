import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.DirectedGraph;

import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.graph.util.EdgeType;

import org.apache.commons.collections15.Factory;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Class used to represent a graph.
 * 
 * @author Hammadullah Nasir
 *
 * @param <V> for generics
 * @param <E> for generics
 */
class ThreeTenGraph<V extends ThreeTenGraphComponent, E extends ThreeTenGraphComponent>
		implements Graph<V, E>, DirectedGraph<V, E> {

	/**
	 * Minimum node id.
	 */
	private static final int MIN_NODE_ID = 0;
	/**
	 * Max node id.
	 */
	private static final int MAX_NODE_ID = 200;

	/**
	 * Number of edges in the graph.
	 */
	private int numEdges = 0;
	/**
	 * Number of vertices in the graph.
	 */
	private int numVertices = 0;

	/**
	 * Array list used to keep the graph's vertices.
	 */
	private ArrayList<V> graphV = new ArrayList<>();
	/**
	 * Array list used to keep the graph's edges but as vertexes.
	 */
	private ArrayList<LinkedList<V>> graphEV = new ArrayList<>();
	/**
	 * Array list used to keep the graph's edges E.
	 */
	private ArrayList<LinkedList<E>> graphE = new ArrayList<>();

	/**
	 * Creates a new graph. Initializing all appropriate instance variables.
	 */
	@SuppressWarnings("unchecked")
	public ThreeTenGraph() {

	}

	/**
	 * Returns a view of all edges in this graph. In general, this obeys the
	 * Collection contract, and therefore makes no guarantees about the ordering of
	 * the vertices within the set.
	 * 
	 * @return a Collection view of all edges in this graph
	 */
	public Collection<E> getEdges() { // DONE
		// Tip... all JCF classes (including ArrayList and LinkedList) are subclasses of
		// Collection
		ArrayList<E> temp = new ArrayList<>();

		for (LinkedList<E> list : graphE) {
			temp.addAll(list);
		}

		return temp;
	}

	/**
	 * Returns a view of all vertices in this graph. In general, this obeys the
	 * Collection contract, and therefore makes no guarantees about the ordering of
	 * the vertices within the set.
	 * 
	 * @return a Collection view of all vertices in this graph
	 */
	public Collection<V> getVertices() {
		return graphV;
	}

	/**
	 * Returns the number of edges in this graph.
	 * 
	 * @return the number of edges in this graph
	 */
	public int getEdgeCount() {
		return numEdges;
	}

	/**
	 * Returns the number of vertices in this graph.
	 * 
	 * @return the number of vertices in this graph
	 */
	public int getVertexCount() {
		return numVertices;
	}

	/**
	 * Returns a Collection view of the incoming edges incident to vertex in this
	 * graph.
	 * 
	 * @param vertex the vertex whose incoming edges are to be returned
	 * @return a Collection view of the incoming edges incident to vertex in this
	 *         graph
	 */
	public Collection<E> getInEdges(V vertex) {
		ArrayList<E> temp = new ArrayList<>();
		int index = 0;
		int indexInner = 0;

		for (LinkedList<V> list : graphEV) {
			if (list.contains(vertex)) {
				indexInner = list.indexOf(vertex);
				temp.add(graphE.get(index).get(indexInner));
			}
			index += 1;
		}

		return temp;
	}

	/**
	 * Returns a Collection view of the outgoing edges incident to vertex in this
	 * graph.
	 * 
	 * @param vertex the vertex whose outgoing edges are to be returned
	 * @return a Collection view of the outgoing edges incident to vertex in this
	 *         graph
	 */
	public Collection<E> getOutEdges(V vertex) {
		ArrayList<E> temp = new ArrayList<>();
		int index = 0;

		if (graphV.contains(vertex)) {
			index = graphV.indexOf(vertex);
			temp.addAll(graphE.get(index));
		}

		return temp;
	}

	/**
	 * Returns a Collection view of the predecessors of vertex in this graph. A
	 * predecessor of vertex is defined as a vertex v which is connected to vertex
	 * by an edge e, where e is an outgoing edge of v and an incoming edge of
	 * vertex.
	 * 
	 * @param vertex the vertex whose predecessors are to be returned
	 * @return a Collection view of the predecessors of vertex in this graph
	 */
	public Collection<V> getPredecessors(V vertex) {
		ArrayList<V> temp = new ArrayList<>();
		int index = 0;

		for (LinkedList<V> list : graphEV) {
			if (list.contains(vertex)) {
				temp.add(graphV.get(index));
			}
			index += 1;
		}

		return temp;
	}

	/**
	 * Returns a Collection view of the successors of vertex in this graph. A
	 * successor of vertex is defined as a vertex v which is connected to vertex by
	 * an edge e, where e is an incoming edge of v and an outgoing edge of vertex.
	 * 
	 * @param vertex the vertex whose predecessors are to be returned
	 * @return a Collection view of the successors of vertex in this graph
	 */
	public Collection<V> getSuccessors(V vertex) {
		ArrayList<V> temp = new ArrayList<>();
		int index = 0;

		if (graphV.contains(vertex)) {
			index = graphV.indexOf(vertex);
			temp.addAll(graphEV.get(index));
		}

		return temp;
	}

	/**
	 * If directed_edge is a directed edge in this graph, returns the source;
	 * otherwise returns null. The source of a directed edge d is defined to be the
	 * vertex for which d is an outgoing edge. directed_edge is guaranteed to be a
	 * directed edge if its EdgeType is DIRECTED.
	 * 
	 * @param directedEdge used to get source of
	 * @return the source of directed_edge if it is a directed edge in this graph,
	 *         or null otherwise
	 */
	public V getSource(E directedEdge) {
		V returnVertex = null;
		int index = 0;

		for (LinkedList<E> list : graphE) {
			if (list.contains(directedEdge)) {
				returnVertex = graphV.get(index);
				break;
			}
			index += 1;
		}

		return returnVertex;
	}

	/**
	 * If directed_edge is a directed edge in this graph, returns the destination;
	 * otherwise returns null. The destination of a directed edge d is defined to be
	 * the vertex incident to d for which d is an incoming edge. directed_edge is
	 * guaranteed to be a directed edge if its EdgeType is DIRECTED.
	 * 
	 * @param directedEdge used to get destination of
	 * @return the destination of directed_edge if it is a directed edge in this
	 *         graph, or null otherwise
	 */
	public V getDest(E directedEdge) {
		V returnVertex = null;
		int index = 0;
		int indexInner = 0;

		for (LinkedList<E> list : graphE) {
			if (list.contains(directedEdge)) {
				indexInner = list.indexOf(directedEdge);
				returnVertex = graphEV.get(index).get(indexInner);
				break;
			}
			index += 1;
		}

		return returnVertex;
	}

	/**
	 * Returns the endpoints of edge as a Pair.
	 * 
	 * @param edge the edge whose endpoints are to be returned
	 * @return the endpoints (incident vertices) of edge
	 */
	public Pair<V> getEndpoints(E edge) {
		ArrayList<V> temp = (ArrayList<V>) getIncidentVertices(edge);
		V vertex1 = null;
		V vertex2 = null;

		if (temp.size() == 2) {
			vertex1 = temp.get(0);
			vertex2 = temp.get(1);
		}

		return new Pair<>(vertex1, vertex2);
	}

	/**
	 * Returns the collection of vertices which are connected to vertex via any
	 * edges in this graph. If vertex is connected to itself with a self-loop, then
	 * it will be included in the collection returned.
	 * 
	 * @param vertex the vertex whose neighbors are to be returned
	 * @return the collection of vertices which are connected to vertex, or null if
	 *         vertex is not present
	 */
	public Collection<V> getNeighbors(V vertex) {
		ArrayList<V> temp = new ArrayList<>();
		int vertexIndex = 0;
		int index = 0;

		if (graphV.contains(vertex) == false) {
			return null;
		}

		vertexIndex = graphV.indexOf(vertex);
		temp.addAll(graphEV.get(vertexIndex));

		for (LinkedList<V> list : graphEV) {
			if (list.contains(vertex) && index != vertexIndex) {
				temp.add(graphV.get(index));
			}
			index += 1;
		}

		return temp;
	}

	/**
	 * Returns the collection of edges in this graph which are connected to vertex.
	 * 
	 * @param vertex the vertex whose incident edges are to be returned
	 * @return the collection of edges which are connected to vertex, or null if
	 *         vertex is not present
	 */
	public Collection<E> getIncidentEdges(V vertex) {
		ArrayList<E> temp = new ArrayList<>();
		int index;
		int index2;
		int indexInner;

		if (graphV.contains(vertex) == false) {
			return null;
		}

		index = graphV.indexOf(vertex);
		temp.addAll(graphE.get(index));

		index2 = 0;
		;
		for (LinkedList<V> list : graphEV) {
			if (list.contains(vertex) && index2 != index) {
				indexInner = list.indexOf(vertex);
				temp.add(graphE.get(index2).get(indexInner));
			}
			index2 += 1;
		}

		return temp;
	}

	/**
	 * Returns the collection of vertices in this graph which are connected to edge.
	 * Note that for some graph types there are guarantees about the size of this
	 * collection (i.e., some graphs contain edges that have exactly two endpoints,
	 * which may or may not be distinct). Implementations for those graph types may
	 * provide alternate methods that provide more convenient access to the
	 * vertices.
	 * 
	 * @param edge the edge whose incident vertices are to be returned
	 * @return the collection of vertices which are connected to edge, or null if
	 *         edge is not present
	 */
	public Collection<V> getIncidentVertices(E edge) {
		ArrayList<V> temp = new ArrayList<>();
		int index = 0;
		int indexInner = 0;

		for (LinkedList<E> list : graphE) {
			if (list.contains(edge)) {
				temp.add(graphV.get(index));
				indexInner = list.indexOf(edge);
				temp.add(graphEV.get(index).get(indexInner));
				break;
			}
			index += 1;
		}

		if (temp.size() == 0) {
			return null;
		}

		return temp;
	}

	/**
	 * Finds an edges that connects v1 and v2.
	 * 
	 * @param v1 vertex 1
	 * @param v2 vertex 2
	 * @return an edge that connects v1 to v2, or null if no such edge exists (or
	 *         either vertex is not present)
	 */
	public E findEdge(V v1, V v2) {
		int index1;
		int indexInner = 0;
		E returnEdge = null;

		if (graphV.contains(v1) && graphV.contains(v2)) {
			index1 = graphV.indexOf(v1);

			if (graphEV.get(index1).contains(v2)) {
				indexInner = graphEV.get(index1).indexOf(v2);
				returnEdge = graphE.get(index1).get(indexInner);
			}
		}

		return returnEdge;
	}

	/**
	 * Adds edge e to this graph such that it connects vertex v1 to v2.
	 * If this graph does not contain v1, v2, or
	 * both, implementations may choose to either silently add the vertices to the
	 * graph or throw an IllegalArgumentException. If this graph assigns edge types
	 * to its edges, the edge type of e will be the default for this graph. See
	 * Hypergraph.addEdge() for a listing of possible reasons for failure.
	 * 
	 * @param e  the edge to be added
	 * @param v1 the first vertex to be connected
	 * @param v2 the second vertex to be connected
	 * @return true if the add is successful, false otherwise
	 */
	public boolean addEdge(E e, V v1, V v2) {
		if (graphV.contains(v1) && graphV.contains(v2)) {
			graphE.get(graphV.indexOf(v1)).add(e);
			graphEV.get(graphV.indexOf(v1)).add(v2);
			numEdges += 1;
		} else {
			throw new IllegalArgumentException("Not in list");
		}

		return true;
	}

	/**
	 * Adds vertex to this graph. Fails if vertex is null or already in the graph.
	 * 
	 * @param vertex the vertex to add
	 * @return true if the add is successful, and false otherwise
	 * @throws IllegalArgumentException if vertex is null
	 */
	public boolean addVertex(V vertex) {
		if (vertex == null) {
			return false;
		} else if (graphV.contains(vertex)) {
			return false;
		} else {
			graphV.add(vertex);
			graphEV.add(new LinkedList<V>());
			graphE.add(new LinkedList<E>());
			numVertices += 1;
		}

		return true;
	}

	/**
	 * Removes edge from this graph. Fails if edge is null, or is otherwise not an
	 * element of this graph.
	 * 
	 * @param edge the edge to remove
	 * @return true if the removal is successful, false otherwise
	 */
	public boolean removeEdge(E edge) {
		boolean isIn = false;
		int index = 0;
		int indexInner = 0;

		if (edge == null) {
			return false;
		}
		for (LinkedList<E> list : graphE) {
			if (list.contains(edge)) {
				isIn = true;
				indexInner = list.indexOf(edge);
				list.remove(edge);
				graphEV.get(index).remove(indexInner);
				break;
			}
			index += 1;
		}
		if (isIn == false) {
			return false;
		}

		numEdges -= 1;
		return true;
	}

	/**
	 * Removes vertex from this graph.
	 * 
	 * @param vertex the vertex to remove
	 * @return true if the removal is successful, false otherwise
	 */
	public boolean removeVertex(V vertex) {
		int index = 0;
		int indexInner = 0;
		int edgesRemoved = 0;
		ArrayList<E> temp = null;

		if (vertex == null) {
			return false;
		} else if (graphV.contains(vertex) == false) {
			return false;
		} else {
			temp = (ArrayList<E>) getIncidentEdges(vertex);
			index = graphV.indexOf(vertex);
			graphV.remove(vertex);
			numVertices -= 1;

			edgesRemoved = graphE.get(index).size();
			graphE.remove(index);
			graphEV.remove(index);

			index = 0;
			for (LinkedList<V> list : graphEV) {
				if (list.contains(vertex)) {
					indexInner = list.indexOf(vertex);
					list.remove(vertex);
					graphE.get(index).remove(indexInner);
					edgesRemoved += 1;
				}
				index += 1;
			}
			numEdges -= edgesRemoved;
		}

		return true;
	}

	/**
	 * Returns true if this graph's vertex collection contains vertex. Equivalent to
	 * getVertices().contains(vertex).
	 * 
	 * @param vertex the vertex whose presence is being queried
	 * @return true iff this graph contains a vertex vertex
	 */
	public boolean containsVertex(V vertex) {
		return getVertices().contains(vertex);
	}

	/**
	 * Returns true if this graph's edge collection contains edge. Equivalent to
	 * getEdges().contains(edge).
	 * 
	 * @param edge the edge whose presence is being queried
	 * @return true iff this graph contains an edge edge
	 */
	public boolean containsEdge(E edge) {
		return getEdges().contains(edge);
	}

	/**
	 * Returns true if vertex and edge are incident to each other. Equivalent to
	 * getIncidentEdges(vertex).contains(edge) and to
	 * getIncidentVertices(edge).contains(vertex).
	 * 
	 * @param vertex to be used
	 * @param edge to be used
	 * @return true if vertex and edge are incident to each other
	 */
	public boolean isIncident(V vertex, E edge) {
		return getIncidentEdges(vertex).contains(edge);
	}

	/**
	 * Returns true if v1 and v2 share an incident edge. Equivalent to
	 * getNeighbors(v1).contains(v2).
	 * 
	 * @param v1 the first vertex to test
	 * @param v2 the second vertex to test
	 * @return true if v1 and v2 share an incident edge
	 */
	public boolean isNeighbor(V v1, V v2) {
		return getNeighbors(v1).contains(v2);
	}

	/**
	 * Returns true if v1 is a predecessor of v2 in this graph. Equivalent to
	 * v1.getPredecessors().contains(v2).
	 * 
	 * @param v1 the first vertex to be queried
	 * @param v2 the second vertex to be queried
	 * @return true if v1 is a predecessor of v2, and false otherwise.
	 */
	public boolean isPredecessor(V v1, V v2) {
		return getPredecessors(v1).contains(v2);
	}

	/**
	 * Returns true if v1 is a successor of v2 in this graph. Equivalent to
	 * v1.getSuccessors().contains(v2).
	 * 
	 * @param v1 the first vertex to be queried
	 * @param v2 the second vertex to be queried
	 * @return true if v1 is a successor of v2, and false otherwise.
	 */
	public boolean isSuccessor(V v1, V v2) {
		return getSuccessors(v1).contains(v2);
	}

	/**
	 * Returns the number of edges incident to vertex.
	 * Equivalent to getIncidentEdges(vertex).size().
	 * 
	 * @param vertex the vertex whose degree is to be returned
	 * @return the degree of this node
	 * @see Hypergraph#getNeighborCount(Object)
	 */
	public int degree(V vertex) {
		return getIncidentEdges(vertex).size();
	}

	/**
	 * Returns the number of vertices that are adjacent to vertex (that is, the
	 * number of vertices that are incident to edges in vertex's incident edge set).
	 * Equivalent to getNeighbors(vertex).size().
	 * 
	 * @param vertex the vertex whose neighbor count is to be returned
	 * @return the number of neighboring vertices
	 */
	public int getNeighborCount(V vertex) {
		return getNeighbors(vertex).size();
	}

	/**
	 * Returns the number of incoming edges incident to vertex. Equivalent to
	 * getInEdges(vertex).size().
	 * 
	 * @param vertex the vertex whose indegree is to be calculated
	 * @return the number of incoming edges incident to vertex
	 */
	public int inDegree(V vertex) {
		return getInEdges(vertex).size();
	}

	/**
	 * Returns the number of outgoing edges incident to vertex. Equivalent to
	 * getOutEdges(vertex).size().
	 * 
	 * @param vertex the vertex whose outdegree is to be calculated
	 * @return the number of outgoing edges incident to vertex
	 */
	public int outDegree(V vertex) {
		return getOutEdges(vertex).size();
	}

	/**
	 * Returns the number of predecessors that vertex has in this graph. Equivalent
	 * to vertex.getPredecessors().size().
	 * 
	 * @param vertex the vertex whose predecessor count is to be returned
	 * @return the number of predecessors that vertex has in this graph
	 */
	public int getPredecessorCount(V vertex) {
		return getPredecessors(vertex).size();
	}

	/**
	 * Returns the number of successors that vertex has in this graph. Equivalent to
	 * vertex.getSuccessors().size().
	 * 
	 * @param vertex the vertex whose successor count is to be returned
	 * @return the number of successors that vertex has in this graph
	 */
	public int getSuccessorCount(V vertex) {
		return getSuccessors(vertex).size();
	}

	/**
	 * Returns the vertex at the other end of edge from vertex. (That is, returns
	 * the vertex incident to edge which is not vertex.)
	 * 
	 * @param vertex the vertex to be queried
	 * @param edge the edge to be queried
	 * @return the vertex at the other end of edge from vertex
	 */
	public V getOpposite(V vertex, E edge) {
		Pair<V> p = getEndpoints(edge);
		if (p.getFirst().equals(vertex)) {
			return p.getSecond();
		} else {
			return p.getFirst();
		}
	}

	/**
	 * Returns all edges that connects v1 to v2. If this edge is not uniquely
	 * defined (that is, if the graph contains more than one edge connecting v1 to
	 * v2), any of these edges may be returned. findEdgeSet(v1, v2) may be used to
	 * return all such edges. Returns null if v1 is not connected to v2. <br/>
	 * Returns an empty collection if either v1 or v2 are not present in this graph.
	 * @param v1 to use
	 * @param v2 to use
	 * @return a collection containing all edges that connect v1 to v2, or null if
	 *         either vertex is not present
	 * @see Hypergraph#findEdge(Object, Object)
	 */
	public Collection<E> findEdgeSet(V v1, V v2) {
		E edge = findEdge(v1, v2);
		if (edge == null) {
			return null;
		}

		ArrayList<E> ret = new ArrayList<>();
		ret.add(edge);
		return ret;

	}

	/**
	 * Returns true if vertex is the source of edge. Equivalent to
	 * getSource(edge).equals(vertex).
	 * 
	 * @param vertex the vertex to be queried
	 * @param edge the edge to be queried
	 * @return true iff vertex is the source of edge
	 */
	public boolean isSource(V vertex, E edge) {
		return getSource(edge).equals(vertex);
	}

	/**
	 * Returns true if vertex is the destination of edge. Equivalent to
	 * getDest(edge).equals(vertex).
	 * 
	 * @param vertex the vertex to be queried
	 * @param edge the edge to be queried
	 * @return true if vertex is the destination of edge
	 */
	public boolean isDest(V vertex, E edge) {
		return getDest(edge).equals(vertex);
	}

	/**
	 * Adds edge e to this graph such that it connects vertex v1 to v2. Equivalent
	 * to addEdge(e, new Pair(v1, v2)). If this graph does not contain v1, v2, or
	 * both, implementations may choose to either silently add the vertices to the
	 * graph or throw an IllegalArgumentException. If edgeType is not legal for this
	 * graph, this method will throw IllegalArgumentException. See
	 * Hypergraph.addEdge() for a listing of possible reasons for failure.
	 * 
	 * @param e the edge to be added
	 * @param v1 the first vertex to be connected
	 * @param v2 the second vertex to be connected
	 * @param edgeType the type to be assigned to the edge
	 * @return true if the add is successful, false otherwise
	 * @see Hypergraph#addEdge(Object, Collection)
	 * @see #addEdge(Object, Object, Object)
	 */
	public boolean addEdge(E e, V v1, V v2, EdgeType edgeType) {
		// NOTE: Only directed edges allowed

		if (edgeType == EdgeType.UNDIRECTED) {
			throw new IllegalArgumentException();
		}

		return addEdge(e, v1, v2);
	}

	/**
	 * Adds edge to this graph. 
	 * 
	 * @param edge to added
	 * @param vertices to use
	 * @return true if the add is successful, and false otherwise
	 * @throws IllegalArgumentException if edge or vertices is null, or if a
	 *                                  different vertex set in this graph is
	 *                                  already connected by edge, or if vertices
	 *                                  are not a legal vertex set for edge
	 */
	@SuppressWarnings("unchecked")
	public boolean addEdge(E edge, Collection<? extends V> vertices) {
		if (edge == null || vertices == null || vertices.size() != 2) {
			return false;
		}

		V[] vs = (V[]) vertices.toArray();
		return addEdge(edge, vs[0], vs[1]);
	}

	/**
	 * Adds edge to this graph with type edge_type.
	 * 
	 * @param edge to use
	 * @param vertices to use
	 * @param edgeType to use
	 * @return true if the add is successful, and false otherwise
	 * @throws IllegalArgumentException if edge or vertices is null, or if a
	 *                                  different vertex set in this graph is
	 *                                  already connected by edge, or if vertices
	 *                                  are not a legal vertex set for edge
	 */
	@SuppressWarnings("unchecked")
	public boolean addEdge(E edge, Collection<? extends V> vertices, EdgeType edgeType) {
		if (edge == null || vertices == null || vertices.size() != 2) {
			return false;
		}

		V[] vs = (V[]) vertices.toArray();
		return addEdge(edge, vs[0], vs[1], edgeType);
	}

	/**
	 * Returns the number of edges of type edgeType in this graph.
	 * 
	 * @param edgeType the type of edge for which the count is to be returned
	 * @return the number of edges of type edge_type in this graph
	 */
	public int getEdgeCount(EdgeType edgeType) {
		if (edgeType == EdgeType.DIRECTED) {
			return getEdgeCount();
		}
		return 0;
	}

	/**
	 * Returns the collection of edges in this graph which are of type edge_type.
	 * 
	 * @param edgeType the type of edges to be returned
	 * @return the collection of edges which are of type edge_type, or null if the
	 *         graph does not accept edges of this type
	 * @see EdgeType
	 */
	public Collection<E> getEdges(EdgeType edgeType) {
		if (edgeType == EdgeType.DIRECTED) {
			return getEdges();
		}
		return null;
	}

	/**
	 * Used to make a string representation for the graph and testing.
	 * @return string representation
	 */
	public String toString() {
		return super.toString();
	}

	/**
	 * Returns a {@code Factory} that creates an instance of this graph type.
	 * 
	 * @param <V> the vertex type for the graph factory
	 * @param <E> the edge type for the graph factory
	 * @return factory that creates graph instance
	 */
	public static <V extends ThreeTenGraphComponent, E extends ThreeTenGraphComponent> Factory<Graph<V, E>> getFactory() {
		return new Factory<Graph<V, E>>() {
			public Graph<V, E> create() {
				return new ThreeTenGraph<>();
			}
		};
	}

	/**
	 * Returns the edge type of edge in this graph.
	 * 
	 * @param edge to use
	 * @return the EdgeType of edge, or null if edge has no defined type
	 */
	public EdgeType getEdgeType(E edge) {
		return EdgeType.DIRECTED;
	}

	/**
	 * Returns the default edge type for this graph.
	 * 
	 * @return the default edge type for this graph
	 */
	public EdgeType getDefaultEdgeType() {
		return EdgeType.DIRECTED;
	}

	/**
	 * Returns the number of vertices that are incident to edge. For hyperedges,
	 * this can be any nonnegative integer; for edges this must be 2 (or 1 if
	 * self-loops are permitted).
	 * Equivalent to getIncidentVertices(edge).size().
	 * 
	 * @param edge the edge whose incident vertex count is to be returned
	 * @return the number of vertices that are incident to edge.
	 */
	public int getIncidentCount(E edge) {
		return 2;
	}
}
