package by.vlad.task_xml.handler;

import by.vlad.task_xml.entity.cpu_device.CPUCoreArchitectureEnum;
import by.vlad.task_xml.entity.cpu_device.CPUDevice;
import by.vlad.task_xml.entity.device.*;
import by.vlad.task_xml.entity.gpu_device.GPUArchitectureEnum;
import by.vlad.task_xml.entity.gpu_device.GPUDevice;
import by.vlad.task_xml.entity.gpu_device.GPUMemoryType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.YearMonth;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class DeviceHandler extends DefaultHandler {
    private static final Logger logger = LogManager.getLogger();

    private Set<Device> devices;
    private Device currentDevice = null;
    private DeviceXmlTag currentTag = null;
    private EnumSet<DeviceXmlTag> withText;

    public DeviceHandler() {
        devices = new HashSet<>();
        withText = EnumSet.range(DeviceXmlTag.NAME, DeviceXmlTag.MEMORY_SIZE);
    }

    public Set<Device> getDevices() {
        return devices;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        String cpuTag = DeviceXmlTag.CPU_DEVICE.getName();
        String gpuTag = DeviceXmlTag.GPU_DEVICE.getName();

        if (cpuTag.equals(qName) || gpuTag.equals(qName)){
            currentDevice = cpuTag.equals(qName) ? new CPUDevice() : new GPUDevice();
            defineAttributes(attributes);
        }else{
            DeviceXmlTag temp = DeviceXmlTag.valueOfXmlTag(qName);
            if (withText.contains(temp)){
                currentTag = temp;
            }
        }
    }

    private void defineAttributes(Attributes attributes) {
        String deviceId = attributes.getValue(DeviceXmlAttribute.ID.getName());
        currentDevice.setId(deviceId);

        String isCritical = attributes.getValue(DeviceXmlAttribute.CRITICAL.getName());

        if (isCritical != null) {
            currentDevice.setCritical(Boolean.parseBoolean(isCritical));
        }else{
            currentDevice.setCritical(Device.DEFAULT_CRITICAL);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String cpuTag = DeviceXmlTag.CPU_DEVICE.getName();
        String gpuTag = DeviceXmlTag.GPU_DEVICE.getName();

        if (cpuTag.equals(qName) || gpuTag.equals(qName)) {
            devices.add(currentDevice);
            logger.info("add to set" + currentDevice);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length).trim();

        if (currentTag != null){
            switch (currentTag){
                case NAME : {currentDevice.setName(data); break;}
                case ORIGIN: {currentDevice.setOrigin(CountryEnum.valueOf(data.toUpperCase())); break;}
                case PRICE: {currentDevice.setPrice(Double.parseDouble(data)); break;}
                case RELEASE_DATE:{currentDevice.setReleaseDate(YearMonth.parse(data)); break;}
                case DEVICE_TYPE:{currentDevice.getDeviceTypeSet().setDeviceTypeEnum(DeviceTypeEnum.valueOfXmlContent(data)); break;}
                case POWER_USAGE:{currentDevice.getDeviceTypeSet().setPowerUsage(Double.valueOf(data)); break;}
                case PRESENCE_OF_COOLER:{currentDevice.getDeviceTypeSet().setPresenceOfCooler(Boolean.parseBoolean(data)); break;}
                case DEVICE_GROUP:{currentDevice.getDeviceTypeSet().setDeviceGroupEnum(DeviceGroupEnum.valueOfXmlContent(data)); break;}
                case PORT:{currentDevice.getDeviceTypeSet().setPortEnum(PortEnum.valueOf(data.toUpperCase())); break;}

                case CORE_ARCHITECTURE:{
                    CPUDevice temp = (CPUDevice) currentDevice;
                    temp.setCoreArchitecture(CPUCoreArchitectureEnum.valueOfXmlContent(data));
                    currentDevice = temp;
                    break;
                }

                case FREQUENCY:{
                    CPUDevice temp = (CPUDevice) currentDevice;
                    temp.setFrequency(Double.parseDouble(data));
                    currentDevice = temp;
                    break;
                }

                case CORE_COUNT:{
                    CPUDevice temp = (CPUDevice) currentDevice;
                    temp.setCoreCount(Integer.parseInt(data));
                    currentDevice = temp;
                    break;
                }

                case THREAD_COUNT:{
                    CPUDevice temp = (CPUDevice) currentDevice;
                    temp.setThreadCount(Integer.parseInt(data));
                    break;
                }

                case CACHE_SIZE:{
                    CPUDevice temp = (CPUDevice) currentDevice;
                    temp.setCacheL3Size(Integer.parseInt(data));
                    currentDevice = temp;
                    break;
                }

                case ARCHITECTURE:{
                    GPUDevice temp = (GPUDevice) currentDevice;
                    temp.setArchitecture(GPUArchitectureEnum.valueOf(data.toUpperCase()));
                    currentDevice = temp;
                    break;
                }

                case MEMORY_TYPE:{
                    GPUDevice temp = (GPUDevice) currentDevice;
                    temp.setMemoryType(GPUMemoryType.valueOf(data.toUpperCase()));
                    currentDevice = temp;
                    break;
                }

                case MEMORY_SIZE:{
                    GPUDevice temp = (GPUDevice) currentDevice;
                    temp.setMemorySize(Integer.valueOf(data));
                    currentDevice = temp;
                    break;
                }

                default:{
                    throw new EnumConstantNotPresentException(currentTag.getDeclaringClass(), currentTag.getName());
                }
            }
        }

        currentTag = null;
    }
}
