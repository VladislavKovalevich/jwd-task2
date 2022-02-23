package by.vlad.task_xml.builder;

import by.vlad.task_xml.handler.DeviceErrorHandler;
import by.vlad.task_xml.handler.DeviceHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class DeviceSaxBuilder extends AbstractDeviceBuilder {
    private DeviceHandler deviceHandler = new DeviceHandler();
    private XMLReader reader;

    public DeviceSaxBuilder() {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = factory.newSAXParser();
            this.reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();//log()
        }

        reader.setErrorHandler(new DeviceErrorHandler());
        reader.setContentHandler(deviceHandler);
    }

    @Override
    public void buildSetDevices(String filename) {
        try {
            reader.parse(filename);
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }

        devices = deviceHandler.getDevices();
    }
}
