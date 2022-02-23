package by.vlad.task_xml.builder;

import by.vlad.task_xml.handler.DeviceErrorHandler;
import by.vlad.task_xml.handler.DeviceHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class DeviceSaxBuilder extends AbstractDeviceBuilder {
    private static final Logger logger = LogManager.getLogger();
    private DeviceHandler deviceHandler = new DeviceHandler();
    private XMLReader reader;

    public DeviceSaxBuilder() {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = factory.newSAXParser();
            this.reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException e){
            logger.error("ParserConfigurationException during SAX parser configuration", e);
        } catch (SAXException e) {
            logger.error("SAXError during create sax parser");
        }

        reader.setErrorHandler(new DeviceErrorHandler());
        reader.setContentHandler(deviceHandler);
    }

    @Override
    public void buildSetDevices(String filename) {
        try {
            reader.parse(filename);
        } catch (IOException e) {
            logger.error("IOException during work with data file", e);
        } catch(SAXException e) {
            logger.error("SAXException during xml file parsing", e);
        }

        devices = deviceHandler.getDevices();
    }
}
