package converter;

public class UMLToJavaConverter {

    public static String convertUMLToJavaAttributes(String umlInput) {
        StringBuilder javaCode = new StringBuilder();
        String[] lines = umlInput.split("\n");

        for (String line : lines) {
            line = line.trim();

            if (line.isEmpty()) continue;

            int colonIndex = line.indexOf(":");
            if (colonIndex == -1) continue;

            String modifierAndName = line.substring(0, colonIndex).trim();
            String type = line.substring(colonIndex + 1).trim();

            String accessModifier;
            if (modifierAndName.startsWith("-")) {
                accessModifier = "private";
                modifierAndName = modifierAndName.substring(1).trim();
            } else if (modifierAndName.startsWith("+")) {
                accessModifier = "public";
                modifierAndName = modifierAndName.substring(1).trim();
            } else if (modifierAndName.startsWith("#")) {
                accessModifier = "protected";
                modifierAndName = modifierAndName.substring(1).trim();
            } else {
                accessModifier = "";
            }

            String javaType = switch (type) {
                case "int" -> "int";
                case "double" -> "double";
                case "String" -> "String";
                case "float" -> "float";
                default -> "Object";
            };

            javaCode.append(String.format("%s %s %s;\n", accessModifier, javaType, modifierAndName));
        }

        return javaCode.toString();
    }

    public static void main(String[] args) {

        String umlAttributes = """
               -firstName: String
               -lastName: String
               -address: String
               -email: String
            """;

        String javaAttributes = convertUMLToJavaAttributes(umlAttributes);
        System.out.println(javaAttributes);
    }
}
