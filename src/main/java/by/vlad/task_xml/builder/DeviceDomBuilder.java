package by.vlad.task_xml.builder;

import by.vlad.task_xml.entity.cpu_device.CPUCoreArchitectureEnum;
import by.vlad.task_xml.entity.cpu_device.CPUDevice;
import by.vlad.task_xml.entity.device.*;
import by.vlad.task_xml.entity.gpu_device.GPUArchitectureEnum;
import by.vlad.task_xml.entity.gpu_device.GPUDevice;
import by.vlad.task_xml.entity.gpu_device.GPUMemoryType;
import by.vlad.task_xml.handler.DeviceXmlAttribute;
import by.vlad.task_xml.handler.DeviceXmlTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.YearMonth;

public class DeviceDomBuilder extends AbstractDeviceBuilder {
    private static final Logger logger = LogManager.getLogger();
    private DocumentBuilder docBuilder;

    public DeviceDomBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error("ParserConfigurationException during parser configuration", e);
        }
    }

    @Override
    public void buildSetDevices(String filename) {
        Document doc;

        try {
            doc = docBuilder.parse(filename);
            Element root = doc.getDocumentElement();
            NodeList deviceList = root.getElementsByTagName(DeviceXmlTag.CPU_DEVICE.getName());

            for (int i = 0; i < deviceList.getLength(); i++) {
                Element deviceXMLElement = (Element) deviceList.item(i);
                Device newDevice = buildDevice(deviceXMLElement);
                logger.info("add to set " + newDevice);
                devices.add(newDevice);
            }

            deviceList = root.getElementsByTagName(DeviceXmlTag.GPU_DEVICE.getName());

            for (int i = 0; i < deviceList.getLength(); i++) {
                Element deviceXMLElement = (Element) deviceList.item(i);
                Device newDevice = buildDevice(deviceXMLElement);
                logger.info("add to set " + newDevice);
                devices.add(newDevice);
            }

        } catch (IOException e){
            logger.error("IOException during work with file " + filename, e);
        } catch (SAXException e) {
            logger.error("SAXException during build data set " + filename, e);
        }
    }

    private Device buildDevice(Element deviceXMLElement) {
        Device currentDevice = deviceXMLElement.getTagName()
                .equals(DeviceXmlTag.CPU_DEVICE.getName()) ? new CPUDevice() : new GPUDevice();

        String content;

        content = deviceXMLElement.getAttribute(DeviceXmlAttribute.ID.getName());
        currentDevice.setId(content);
        content = deviceXMLElement.getAttribute(DeviceXmlAttribute.CRITICAL.getName());

        if (!content.isEmpty()){
            currentDevice.setCritical(Boolean.parseBoolean(content));
        }else{
            currentDevice.setCritical(Device.DEFAULT_CRITICAL);
        }

        content = getElementTextContent(deviceXMLElement, DeviceXmlTag.NAME.getName());
        currentDevice.setName(content);
        content = getElementTextContent(deviceXMLElement, DeviceXmlTag.ORIGIN.getName());
        currentDevice.setOrigin(CountryEnum.valueOf(content.toUpperCase()));
        content = getElementTextContent(deviceXMLElement, DeviceXmlTag.PRICE.getName());
        currentDevice.setPrice(Double.parseDouble(content));
        content = getElementTextContent(deviceXMLElement, DeviceXmlTag.RELEASE_DATE.getName());
        currentDevice.setReleaseDate(YearMonth.parse(content));

        content = getElementTextContent(deviceXMLElement, DeviceXmlTag.DEVICE_TYPE.getName());
        currentDevice.getDeviceTypeSet().setDeviceTypeEnum(DeviceTypeEnum.valueOfXmlContent(content));
        content = getElementTextContent(deviceXMLElement, DeviceXmlTag.POWER_USAGE.getName());
        currentDevice.getDeviceTypeSet().setPowerUsage(Double.valueOf(content));
        content = getElementTextContent(deviceXMLElement, DeviceXmlTag.PRESENCE_OF_COOLER.getName());
        currentDevice.getDeviceTypeSet().setPresenceOfCooler(Boolean.parseBoolean(content));
        content = getElementTextContent(deviceXMLElement, DeviceXmlTag.DEVICE_GROUP.getName());
        currentDevice.getDeviceTypeSet().setDeviceGroupEnum(DeviceGroupEnum.valueOfXmlContent(content));
        content = getElementTextContent(deviceXMLElement, DeviceXmlTag.PORT.getName());
        currentDevice.getDeviceTypeSet().setPortEnum(PortEnum.valueOf(content.toUpperCase()));

        if (currentDevice instanceof CPUDevice){
            CPUDevice cpuDevice = (CPUDevice) currentDevice;

            content = getElementTextContent(deviceXMLElement, DeviceXmlTag.CORE_COUNT.getName());
            cpuDevice.setCoreCount(Integer.parseInt(content));
            content = getElementTextContent(deviceXMLElement, DeviceXmlTag.CACHE_SIZE.getName());
            cpuDevice.setCacheL3Size(Integer.parseInt(content));
            content = getElementTextContent(deviceXMLElement, DeviceXmlTag.CORE_ARCHITECTURE.getName());
            cpuDevice.setCoreArchitecture(CPUCoreArchitectureEnum.valueOfXmlContent(content));
            content = getElementTextContent(deviceXMLElement, DeviceXmlTag.THREAD_COUNT.getName());
            cpuDevice.setThreadCount(Integer.parseInt(content));
            content = getElementTextContent(deviceXMLElement, DeviceXmlTag.FREQUENCY.getName());
            cpuDevice.setFrequency(Double.parseDouble(content));

            currentDevice = cpuDevice;

        }else{
            GPUDevice gpuDevice = (GPUDevice) currentDevice;

            content = getElementTextContent(deviceXMLElement, DeviceXmlTag.ARCHITECTURE.getName());
            gpuDevice.setArchitecture(GPUArchitectureEnum.valueOf(content.toUpperCase()));
            content = getElementTextContent(deviceXMLElement, DeviceXmlTag.MEMORY_SIZE.getName());
            gpuDevice.setMemorySize(Integer.parseInt(content));
            content = getElementTextContent(deviceXMLElement, DeviceXmlTag.MEMORY_TYPE.getName());
            gpuDevice.setMemoryType(GPUMemoryType.valueOf(content.toUpperCase()));

            currentDevice = gpuDevice;
        }

        return currentDevice;
    }

    private static String getElementTextContent(Element element, String elementName){
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        String text = node.getTextContent();
        return text;
    }
}