package draw.shapes;

import java.util.List;

//Determine which shape- if any
//Ricki
public class ShapeAnalyzer {
    //private List<Point> stroke;
    private final int ERROR_ALLOWANCE = 5;

    public boolean isHorizontal(List<Point> stroke) {
        Point origin = stroke.get(0);

        for (Point point : stroke) {
            if (veersVertical(point, origin)) {
                return false;
            }
        }
        return true;
    }

    private boolean veersVertical(Point point, Point origin) {
        return point.getY() > origin.getY() + ERROR_ALLOWANCE || point.getY() < origin.getY() - ERROR_ALLOWANCE;
    }

    public boolean isVertical(List<Point> stroke) {

        boolean topDown = stroke.get(0).getY() < stroke.get(1).getY();
        if (topDown) {
            return isTopDownLine(stroke);
        } else {
            return isBottomUpLine(stroke);
        }

    }

    private boolean isBottomUpLine(List<Point> stroke) {
        Point origin = stroke.get(0);
        int minY = 0;
        for (int i = 1; i < stroke.size(); i++) {

            if (veersHorizontal(stroke.get(i), origin)) {
                return false;
            } else {
                if (stroke.get(i).getY() > minY) {
                    return false;
                } else {
                    minY = stroke.get(i).getY();
                }
            }
        }
        return true;
    }

    private boolean isTopDownLine(List<Point> stroke) {
        Point origin = stroke.get(0);
        int maxY = 0;
        for (int i = 1; i < stroke.size(); i++) {
            if (veersHorizontal(stroke.get(i), origin)) {
                return false;
            }

            if (stroke.get(i).getY() < maxY) {
                return false;
            } else {
                maxY = stroke.get(i).getY();
            }
        }
        return true;
    }

    private boolean veersHorizontal(Point point, Point origin) {
        return point.getX() > origin.getX() + ERROR_ALLOWANCE || point.getX() < origin.getX() - ERROR_ALLOWANCE;
    }


}
