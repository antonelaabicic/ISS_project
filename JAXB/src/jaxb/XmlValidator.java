package jaxb;

import generated.KeywordSuggestionsList;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEventLocator;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

public class XmlValidator {

    public static ValidationResult validate(File xmlFile, File xsdFile) {
        List<String> errors = new ArrayList<>();

        try {
            JAXBContext context = JAXBContext.newInstance(KeywordSuggestionsList.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(xsdFile);
            unmarshaller.setSchema(schema);

            unmarshaller.setEventHandler(event -> {
                ValidationEventLocator locator = event.getLocator();
                String message = String.format(
                        "Error at line %d, column %d: %s",
                        locator.getLineNumber(),
                        locator.getColumnNumber(),
                        event.getMessage()
                );
                errors.add(message);
                return false;
            });

            unmarshaller.unmarshal(xmlFile);
        } catch (JAXBException | SAXException e) {
            if (errors.isEmpty()) {
                errors.add("Validation failed: " + e.getMessage());
            }
        }

        return new ValidationResult(errors.isEmpty(), errors);
    }
}
