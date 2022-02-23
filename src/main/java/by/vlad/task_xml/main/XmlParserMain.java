package by.vlad.task_xml.main;

import by.vlad.task_xml.builder.AbstractDeviceBuilder;
import by.vlad.task_xml.builder.DeviceBuilderFactory;
import by.vlad.task_xml.exception.CustomDeviceException;

public class XmlParserMain {

    public static void main(String[] args) {
        String xmlPath = "src\\main\\resources\\devices.xml";

        DeviceBuilderFactory factory = DeviceBuilderFactory.getInstance();
        AbstractDeviceBuilder deviceBuilder = null;

        deviceBuilder = factory.createDeviceBuilder("dom");

        try {
            deviceBuilder.buildSetDevices(xmlPath);
            System.out.println(deviceBuilder.getDevices().toString());
        } catch (CustomDeviceException e) {
            e.printStackTrace();
        }
    }
}