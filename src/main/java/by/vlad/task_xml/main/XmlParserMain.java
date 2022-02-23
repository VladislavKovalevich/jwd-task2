package by.vlad.task_xml.main;

import by.vlad.task_xml.builder.AbstractDeviceBuilder;
import by.vlad.task_xml.builder.DeviceBuilderFactory;

public class XmlParserMain {

    public static void main(String[] args) {

        String xmlPath = "src\\main\\resources\\devices.xml";

        AbstractDeviceBuilder deviceBuilder = DeviceBuilderFactory.createDeviceBuilder("dom");
        deviceBuilder.buildSetDevices(xmlPath);
        System.out.println(deviceBuilder.getDevices().toString());
    }
}