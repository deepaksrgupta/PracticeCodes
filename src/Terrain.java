import java.io.*;
import java.util.*;
import java.util.Map;
import java.util.Set;
import java.util.Objects;

/**
 * Terrain (2D - land and water)
 * <p>
 * 1. Matrix (boolean 2D)
 * space: O(MxN)
 * isLand:  O(1)
 * addLand: O(1)
 * <p>
 * 2. Map/Set of land coordinates
 * space: O(No. of lands)
 * isLand:  O(1)
 * addLand: O(1) ammortized
 * <p>
 * -----
 * - isLand(x, y) : true if (x, y) is a land
 * - addLand(x, y) : set that point to land
 * - getIslands() :
 * <p>
 * . . . . . . .
 * . . . x x x x
 * . . x . . . . = getIslands() == 2
 * . x x . . . .
 * . . . . . . .
 * <p>
 * 1. Traversal (BFS/DFS)
 * time:  O(No. of lands)
 * space: O(No. of lands)
 * <p>
 * <p>
 * 2. DSU:
 * isLand:  O(1)
 * addLand: O(1)
 * getIslands: O(1)
 * <p>
 * . . . . . . .
 * . . . x x x x
 * . . x . . . . = getIslands() == 2
 * . a b . . . .
 * . . . . . . .
 * <p>
 * <p>
 * DSU = {ab , }
 * <p>
 * DSU -> find, union
 */
// Main class should be named 'Solution'
class TerrainTemp {
    public static void main(String[] args) {
        Terrain terrain = new SetTerrain(100);

        System.out.println(terrain.isLand(1, 1));

        /*
        c . . .
        d a z .
        . . b .
        . x x .
        */
        terrain.addLand(1, 1);
        terrain.addLand(2, 2);

        System.out.println(terrain.getIslands()); // 2
        terrain.addLand(0, 0);
        System.out.println(terrain.getIslands()); // 3

        terrain.addLand(1, 0);
        System.out.println(terrain.getIslands()); // 2

        terrain.addLand(3, 1);
        terrain.addLand(3, 2);
        System.out.println(terrain.getIslands()); // 2

        terrain.addLand(1, 2);
        System.out.println(terrain.getIslands()); // 2
    }
}

interface Terrain {
    public boolean isLand(int x, int y);

    public boolean addLand(int x, int y);

    public int getIslands();
}

class SetTerrain implements Terrain {

    private Set<Point> set;
    private DSU dsu;

    SetTerrain(int size) {
        set = new HashSet<>(size);
        dsu = new DSU();
    }

    public boolean isLand(int x, int y) {
        return set.contains(new Point(x, y));
    }

    public boolean addLand(int x, int y) {
        // TODO: ??
        Point point = new Point(x, y);

        if (set.contains(point)) {
            return false;
        }

        dsu.add(point);
        return set.add(point);
    }

    public int getIslands() {
        return dsu.getNumberOfConnectedComponents();
    }
}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object that) {

        if (that == null || !(that instanceof Point)) {
            return false;
        }

        Point newPoint = (Point) that;
        return this.x == newPoint.x && this.y == newPoint.y;
    }

    @Override
    public int hashCode() {
        // TODO: Is there a more deterministic way
        return Objects.hash(this.x, this.y);
    }

}


class DSU {

    private int sizeOfConnectedComponents;

    private Map<Point, Point> parent;
    private int[][] dirs;

    DSU() {
        parent = new HashMap<>();
        dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    }


    public Point find(Point point) {
        if (parent.containsKey(point)) {

            Point newParent = parent.get(point);

            if (newParent.equals(point)) {
                return point;
            } else {
                return find(newParent);
            }
        }

        return point;
    }

    public void add(Point firstIsland) {

        parent.put(firstIsland, firstIsland);

        int islandsConnected = 0;
        sizeOfConnectedComponents++;

        for (int[] dir : dirs) {

            Point neighbour = new Point(firstIsland.x + dir[0], firstIsland.y + dir[1]);

            if (parent.containsKey(neighbour)) {

                boolean isUnion = union(neighbour, firstIsland);
                if (isUnion) {
                    islandsConnected++;
                }
            }

        }

        sizeOfConnectedComponents -= islandsConnected;
    }

    public boolean union(Point firstIsland, Point secondIsland) {

        Point firstParent = find(firstIsland);
        Point secondParent = find(secondIsland);

        if (firstParent.equals(secondParent)) {
            return false;
        }


        parent.put(firstParent, secondParent);
        return true;
    }


    public int getNumberOfConnectedComponents() {
        return sizeOfConnectedComponents;
    }
}

