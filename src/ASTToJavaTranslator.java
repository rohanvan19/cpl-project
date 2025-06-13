import java.io.*;

public class ASTToJavaTranslator {

    public static void translate(SimpleNode root, PrintStream out) {
        for (int i = 0; i < root.jjtGetNumChildren(); i++) {
            translateNode((SimpleNode) root.jjtGetChild(i), out);
        }
    }

    private static void translateNode(SimpleNode node, PrintStream out) {
        String name = node.toString();

        switch (name) {
            case "VarDecl":
                out.print("String ");
                out.print(node.jjtGetChild(0).toString().replaceAll("\"", ""));
                out.print(" = ");
                out.print(node.jjtGetChild(1).toString());
                out.println(";");
                break;

            case "PrintStmt":
                out.print("System.out.println(");
                out.print(node.jjtGetChild(0).toString());
                out.println(");");
                break;

            case "IfStmt":
                out.print("if (");
                translateNode((SimpleNode) node.jjtGetChild(0), out);
                out.println(") {");
                for (int i = 1; i < node.jjtGetNumChildren(); i++) {
                    translateNode((SimpleNode) node.jjtGetChild(i), out);
                }
                out.println("}");
                break;

            case "WhileStmt":
                out.print("while (");
                translateNode((SimpleNode) node.jjtGetChild(0), out);
                out.println(") {");
                for (int i = 1; i < node.jjtGetNumChildren(); i++) {
                    translateNode((SimpleNode) node.jjtGetChild(i), out);
                }
                out.println("}");
                break;

            case "Condition":
                out.print(node.jjtGetChild(0).toString() + " == " + node.jjtGetChild(1).toString());
                break;

            default:
                for (int i = 0; i < node.jjtGetNumChildren(); i++) {
                    translateNode((SimpleNode) node.jjtGetChild(i), out);
                }
                break;
        }
    }
}
