package view;

import model.Person;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PersonInfo extends JPanel {

    private final Color c;
    private final JDialog dialogInfo;
    private final boolean isRight;
    private Person person;


    public class MyMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            dialogInfo.setVisible(true);
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setBorder(new LineBorder(Color.green));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBorder(new LineBorder(c));
        }
    }

    public PersonInfo(Point point, Dimension dim, Person person, boolean isRight) {
        this.isRight = isRight;
        this.person = person;
        setBounds(point.x, point.y, dim.width, dim.height);

        if (person == null) {
            c = null;
            dialogInfo = null;
            return;
        }

        if (person.getSex()) {
            c = Color.blue;
        } else {
            c = Color.magenta;
        }

        dialogInfo = new JDialog();
        dialogInfo.setTitle("Person Info");
        dialogInfo.setLocation(point.x, point.y);

        JTextArea textInfo = new JTextArea(person.toString());
        textInfo.setFont(new Font("Helvetica", Font.PLAIN, 16));
        textInfo.setEditable(false);

        dialogInfo.setVisible(false);
        dialogInfo.setResizable(false);
        dialogInfo.add(textInfo);
        dialogInfo.pack();

        setBorder(new LineBorder(c));
        setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel name = new JLabel(person.getName());
        name.setFont(new Font("Helvetica", Font.PLAIN, 12));
        add(name);

        MyMouseListener ml = new MyMouseListener();
        addMouseListener(ml);
    }

    public Person getPerson() {
        return person;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Point getLeftSide() {
        return new Point(getX(), getY() + getHeight() / 2);
    }

    public Point getRightSide() {
        return new Point(getX() + getWidth(), getY() + getHeight() / 2);
    }

    public Point getTopSide() {
        return new Point(getX() + getWidth() / 2, getY());
    }

    public Point getBotSide() {
        return new Point(getX() + getWidth() / 2, getY() + getHeight());
    }
}
