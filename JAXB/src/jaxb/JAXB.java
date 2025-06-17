package jaxb;

import java.io.File;

public class JAXB {

    public static void main(String[] args) {
        File xmlFile = new File("../keyword_suggestions.xml");
        File xsdFile = new File("xml-resources/jaxb/KeywordSuggestionsBinding/jaxb_project.xsd");
        
        ValidationResult result = XmlValidator.validate(xmlFile, xsdFile);

        if (result.isValid) {
            System.out.println("XML is valid!");
        } else {
            System.err.println("Validation failed with errors:");
            result.errors.forEach(System.err::println);
        }
    }
}
