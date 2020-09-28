package view;

import io.FamilyReader;
import model.FamilyTree;

import javax.swing.*;
import java.io.IOException;

public class MainFrame extends JFrame {

    public MainFrame(String filenameTree, String filenameTreePersonsInfo) throws IOException {

        FamilyTree tree = FamilyReader.loadTree(filenameTree, filenameTreePersonsInfo);
        add(new FamilyTreeView(tree));

        setSize(1200, 700);

        setTitle("FamilyTree");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            MainFrame mf = new MainFrame("src/files/myTree.txt",
                    "src/files/myTreePersonsInfo.txt");
        } catch (IOException e) {
            System.out.println("IO problems");
        }
    }
}
