import java.io.*;

public class MiniLangRunner {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("example.minilang");
        MiniLangParser parser = new MiniLangParser(fis);
        SimpleNode root = parser.Program();

        PrintStream out = new PrintStream(System.out);
        out.println("// Translated Java Code:\npublic class Output {\n    public static void main(String[] args) {");
        ASTToJavaTranslator.translate(root, out);
        out.println("    }\n}");
    }
}
