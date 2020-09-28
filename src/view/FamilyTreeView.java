package view;

import model.FamilyTree;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

public class FamilyTreeView extends JPanel {

    private final FamilyTree tree;
    private final List<Line> edgeList;

    private static class Line {
        private final Point a;
        private final Point b;

        public Line(Point a, Point b) {
            this.a = a;
            this.b = b;
        }
    }

    public FamilyTreeView(FamilyTree tree) {
        this.tree = tree;
        edgeList = new LinkedList<>();
        setLayout(null);

        initTree();
    }

    public void initTree() {
        ArrayDeque<Person> q = new ArrayDeque<>();
        LinkedList<Person> graph = (LinkedList<Person>) tree.getTree();
        q.add(graph.getFirst());
        List<Person> used = new LinkedList<>();
        used.add(graph.getFirst());

        boolean root = true;
        boolean right;

        int lvlCount = 1;
        int lvlCheck = 2;

        ArrayDeque<PersonInfo> qInfo = new ArrayDeque<>();
        PersonInfo personInfo = new PersonInfo(new Point(565, 350), new Dimension(75, 25), graph.getFirst(), false);
        qInfo.add(personInfo);
        add(personInfo);

        int shiftX;
        int shiftY;
        int yShiftSize = personInfo.getHeight() * ((int) Math.pow(2, tree.getMaxGenerationNum() - 1));


        while (!q.isEmpty()) {
            Person v = q.peek();
            while (qInfo.peek().getPerson() == null) {
                qInfo.poll();
            }
            PersonInfo vInfo = qInfo.peek();
            qInfo.poll();
            q.poll();

            if (lvlCount == lvlCheck) {
                lvlCheck *= 2;
                yShiftSize = yShiftSize - (yShiftSize / 2);
            }


            shiftY = root ? 0 : yShiftSize;

            if (!v.getSisAndBro().isEmpty()) {
                int i = 1;
                int shiftSisBro = v.getSex() ? 25 : -25;

                for (Person sisBro : v.getSisAndBro()) {
                    personInfo = new PersonInfo(new Point(vInfo.getX(), vInfo.getY() - (shiftSisBro * i)),
                            new Dimension(75, 25), sisBro, false);
                    add(personInfo);
                    i++;
                }
            }
            if (vInfo.getPerson() != null) {
                right = vInfo.isRight();
                shiftX = right ? 100 : -100;


                if (v.hasMother() && !used.contains(v.getMother())) {
                    used.add(v.getMother());
                    q.add(v.getMother());

                    if (root) {
                        shiftX = -100;
                        right = false;
                    }

                    personInfo = new PersonInfo(new Point(vInfo.getX() + shiftX, vInfo.getY() + shiftY),
                            new Dimension(75, 25), v.getMother(), right);

                    add(personInfo);
                    qInfo.add(personInfo);

                    if (personInfo.isRight()) {
                        edgeList.add(new Line(vInfo.getRightSide(), personInfo.getLeftSide()));
                    } else {
                        edgeList.add(new Line(vInfo.getLeftSide(), personInfo.getRightSide()));
                    }
                } else {
                    personInfo = new PersonInfo(new Point(vInfo.getX() + shiftX, vInfo.getY() + shiftY),
                            new Dimension(75, 25), null, right);
                    add(personInfo);
                    qInfo.add(personInfo);
                }

                if (v.hasFather() && !used.contains(v.getFather())) {
                    used.add(v.getFather());
                    q.add(v.getFather());

                    if (root) {
                        shiftX = 100;
                        right = true;
                    }

                    personInfo = new PersonInfo(new Point(vInfo.getX() + shiftX, vInfo.getY() - shiftY),
                            new Dimension(75, 25), v.getFather(), right);
                    add(personInfo);
                    qInfo.add(personInfo);

                    if (personInfo.isRight()) {
                        edgeList.add(new Line(vInfo.getRightSide(), personInfo.getLeftSide()));
                    } else {
                        edgeList.add(new Line(vInfo.getLeftSide(), personInfo.getRightSide()));
                    }
                } else {
                    personInfo = new PersonInfo(new Point(vInfo.getX() + shiftX, vInfo.getY() + shiftY),
                            new Dimension(75, 25), null, right);
                    add(personInfo);
                    qInfo.add(personInfo);
                }
            }
            lvlCount++;
            root = false;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTree(g);
    }

    public void drawTree(Graphics g) {
        for (Line l : edgeList) {
            g.drawLine(l.a.x, l.a.y, l.b.x, l.b.y);
        }
    }
}
