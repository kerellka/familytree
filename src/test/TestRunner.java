package test;

public class TestRunner {
    public static void main(String[] args) {
        FamilyTreeTest treeTest = new FamilyTreeTest();
        System.out.print("addDirectRelative method test result: ");
        System.out.println(treeTest.addDirectRelativeTest());
        System.out.print("addNonDirectRelative method test result: ");
        System.out.println(treeTest.addNonDirectRelativeTest());
        System.out.println("Tree tests passed: " + treeTest.getSuccessTests() + "/" + treeTest.getCountTests());

        FamilyReaderTest treeReaderTest = new FamilyReaderTest();
        System.out.print("saveTree method with correct file test result: ");
        System.out.println(treeReaderTest.treeLoadTestCorrectFile());
        System.out.print("saveTree method with incorrect file test result: ");
        System.out.println(treeReaderTest.treeLoadTestIncorrectFile());
        System.out.println("TreeReader tests passed: " + treeReaderTest.getSuccessTests() + "/" + treeReaderTest.getCountTests());

    }
}
