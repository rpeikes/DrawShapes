package draw.shapes;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class BombFactory {
    private final int xOffset = Bomb.RADIUS;
    private final int yOffset = Bomb.RADIUS + 10;
    private final ShapeFactory shapeFactory;
    private final Random random = new Random();

    public BombFactory(ShapeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
    }

    public Bomb newInstance(int numShapes) {
        return new Bomb(getNewShapeQueue(numShapes), getRandomLocation());
    }

    private Queue<Shape> getNewShapeQueue(int numShapes) {
        Queue<Shape> shapeQueue = new LinkedList<>();
        for (int i = 0; i < numShapes; i++) {
            shapeQueue.add(shapeFactory.newInstance());
        }
        return shapeQueue;
    }

    private Point getRandomLocation() {
        return new Point(random.nextInt(DrawShapesFrame.WIDTH - 2 * xOffset) + xOffset, getRandY());
    }


    private int getRandY() {
        int y = random.nextInt(DrawShapesFrame.HEIGHT);
        return y < DrawShapesFrame.HEIGHT - yOffset ? notTooHigh(y) : y - yOffset;

    }

    private int notTooHigh(int y) {
        return y < 35 ? y + 35 : y;
    }
}
