package compositecurve;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CompositeCurveDemo {
    public CompositeCurveDemo()
    {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Composite Bezier Curve Demo");
        frame.setSize(800, 800);
        JPanel panel = createPreviewPane();
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private JPanel createPreviewPane() {
        return new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                List<Point2D> rndPoints = generateRandomPointList(6, 100, 600, 100, 600);
                CompositeBezierCurvedPath compositeCurvePath = new CompositeBezierCurvedPath(rndPoints);
                CompositeBezierCurve compositeCurve = new CompositeBezierCurve(rndPoints);

                g2d.setStroke(new BasicStroke(4.0f));
                g2d.draw(compositeCurvePath);

                for (BezierCurve curve: compositeCurve.getCurves()){
                    g2d.setColor(Color.blue);
                    g2d.fillOval((int)curve.start.x - 8, (int)curve.start.y - 8, 16, 16);
                    g2d.fillOval((int)curve.finish.x - 8, (int)curve.finish.y - 8, 16, 16);

                    g2d.setColor(Color.yellow);
                    g2d.fillOval((int)curve.control1.x - 8, (int)curve.control1.y - 8, 16, 16);
                    g2d.drawLine((int)curve.start.x, (int)curve.start.y, (int)curve.control1.x, (int)curve.control1.y);

                    g2d.setColor(Color.red);
                    g2d.fillOval((int)curve.control2.x - 8, (int)curve.control2.y - 8, 16, 16);
                    g2d.drawLine((int)curve.finish.x, (int)curve.finish.y, (int)curve.control2.x, (int)curve.control2.y);
                }
            }
        };
    }

    public static List<Point2D> generateRandomPointList(int size, double minX, double maxX, double minY, double maxY) {
        List<Point2D> points = new ArrayList<Point2D>();
        Random rng = new Random();

        for (int i=0; i < size; i++){
            double x = rng.nextDouble(minX, maxX);
            double y = rng.nextDouble(minY, maxY);
            points.add(new Point2D.Double(x, y));
        }

        return points;
    }

    public static void main(String[] args) {
        new CompositeCurveDemo();
    }
}