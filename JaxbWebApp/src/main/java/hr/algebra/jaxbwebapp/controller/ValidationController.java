package hr.algebra.jaxbwebapp.controller;

import jaxb.ValidationResult;
import jaxb.XmlValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("/api/xml")
public class ValidationController {

    @GetMapping("/validate")
    public ResponseEntity<?> validateXml() {
        File xmlFile = new File("../keyword_suggestions.xml");
        File xsdFile = new File("../JAXB/xml-resources/jaxb/KeywordSuggestionsBinding/jaxb_project.xsd");

        ValidationResult result = XmlValidator.validate(xmlFile, xsdFile);

        return ResponseEntity.ok(result);
    }
}
