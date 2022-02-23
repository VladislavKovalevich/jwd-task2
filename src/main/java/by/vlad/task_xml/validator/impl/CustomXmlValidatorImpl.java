package by.vlad.task_xml.validator.impl;

import by.vlad.task_xml.handler.DeviceErrorHandler;
import by.vlad.task_xml.validator.CustomXmlValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class CustomXmlValidatorImpl implements CustomXmlValidator {
    private static Logger logger = LogManager.getLogger();
    private static CustomXmlValidatorImpl instance;

    private CustomXmlValidatorImpl(){
    }

    public static CustomXmlValidatorImpl getInstance() {
        if (instance == null){
            instance = new CustomXmlValidatorImpl();
        }

        return instance;
    }


    @Override
    public boolean validateXmlDocument(String xmlPath, String xsdPath) {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(xsdPath);

        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlPath);

            DeviceErrorHandler errorHandler = new DeviceErrorHandler();

            validator.setErrorHandler(errorHandler);
            validator.validate(source);

            if (errorHandler.isError()){
                return false;
            }

        } catch (SAXException | IOException e) {
            System.err.println(xmlPath + " is not correct or valid");
            return false;
        }

        logger.info("xml file is correct");
        return true;
    }
}
