package by.vlad.task_xml.builder;

import by.vlad.task_xml.entity.cpu_device.CPUCoreArchitectureEnum;
import by.vlad.task_xml.entity.cpu_device.CPUDevice;
import by.vlad.task_xml.entity.device.*;
import by.vlad.task_xml.entity.gpu_device.GPUArchitectureEnum;
import by.vlad.task_xml.entity.gpu_device.GPUDevice;
import by.vlad.task_xml.entity.gpu_device.GPUMemoryType;
import by.vlad.task_xml.handler.DeviceXmlAttribute;
import by.vlad.task_xml.handler.DeviceXmlTag;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.YearMonth;

public class DeviceStaxBuilder extends AbstractDeviceBuilder {
    private XMLInputFactory xmlInputFactory;

    public DeviceStaxBuilder() {
        this.xmlInputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildSetDevices(String filename) {
        XMLStreamReader streamReader;
        String name;

        try(FileInputStream inputStream = new FileInputStream(new File(filename))) {
            streamReader = xmlInputFactory.createXMLStreamReader(inputStream);
            while (streamReader.hasNext()){
                int type = streamReader.next();
                if (type == XMLStreamConstants.START_ELEMENT){
                    name = streamReader.getLocalName();
                    if (name.equals(DeviceXmlTag.CPU_DEVICE.getName()) || (name.equals(DeviceXmlTag.GPU_DEVICE.getName()))){
                        Device newDevice = buildDevice(streamReader);
                        devices.add(newDevice);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private Device buildDevice(XMLStreamReader reader) throws XMLStreamException {
        Device currDevice = reader.getLocalName().equals(DeviceXmlTag.CPU_DEVICE.getName()) ? new CPUDevice() : new GPUDevice();

        currDevice.setId(reader.getAttributeValue(null, DeviceXmlAttribute.ID.getName()));

        String info = reader.getAttributeValue(null, DeviceXmlAttribute.CRITICAL.getName());

        if (info != null){
            currDevice.setCritical(Boolean.parseBoolean(info));
        }else{
            currDevice.setCritical(Device.DEFAULT_CRITICAL);
        }

        String name;
        while (reader.hasNext()){
            int type = reader.next();
            switch (type){
                case XMLStreamConstants.START_ELEMENT:{
                    name = reader.getLocalName();

                    switch (DeviceXmlTag.valueOfXmlTag(name.toUpperCase())){
                        case NAME:{
                            currDevice.setName(getXMLTextReader(reader));
                            break;
                        }
                        case ORIGIN:{
                            currDevice.setOrigin(CountryEnum.valueOf(getXMLTextReader(reader).toUpperCase()));
                            break;
                        }
                        case PRICE:{
                            currDevice.setPrice(Double.parseDouble(getXMLTextReader(reader)));
                            break;
                        }
                        case RELEASE_DATE:{
                            currDevice.setReleaseDate(YearMonth.parse(getXMLTextReader(reader)));
                            break;
                        }
                        case TYPE:{
                            currDevice.setDeviceTypeSet(getXMLDeviceTypeSet(reader));
                            break;
                        }

                        case CORE_COUNT:{
                            CPUDevice temp = (CPUDevice) currDevice;
                            temp.setCoreCount(Integer.parseInt(getXMLTextReader(reader)));
                            currDevice = temp;
                            break;
                        }

                        case CACHE_SIZE:{
                            CPUDevice temp = (CPUDevice) currDevice;
                            temp.setCacheL3Size(Integer.parseInt(getXMLTextReader(reader)));
                            currDevice = temp;
                            break;
                        }

                        case CORE_ARCHITECTURE:{
                            CPUDevice temp = (CPUDevice) currDevice;
                            temp.setCoreArchitecture(CPUCoreArchitectureEnum.valueOfXmlContent(getXMLTextReader(reader)));
                            currDevice = temp;
                            break;
                        }

                        case THREAD_COUNT:{
                            CPUDevice temp = (CPUDevice) currDevice;
                            temp.setThreadCount(Integer.parseInt(getXMLTextReader(reader)));
                            currDevice = temp;
                            break;
                        }

                        case FREQUENCY:{
                            CPUDevice temp = (CPUDevice) currDevice;
                            temp.setFrequency(Double.parseDouble(getXMLTextReader(reader)));
                            currDevice = temp;
                            break;
                        }

                        case ARCHITECTURE:{
                            GPUDevice temp = (GPUDevice) currDevice;
                            temp.setArchitecture(GPUArchitectureEnum.valueOf(getXMLTextReader(reader).toUpperCase()));
                            currDevice = temp;
                            break;
                        }

                        case MEMORY_SIZE:{
                            GPUDevice temp = (GPUDevice) currDevice;
                            temp.setMemorySize(Integer.parseInt(getXMLTextReader(reader)));
                            currDevice = temp;
                            break;
                        }

                        case MEMORY_TYPE:{
                            GPUDevice temp = (GPUDevice) currDevice;
                            temp.setMemoryType(GPUMemoryType.valueOf(getXMLTextReader(reader).toUpperCase()));
                            currDevice = temp;
                            break;
                        }
                    }
                    break;
                }

                case XMLStreamConstants.END_ELEMENT:{
                    name = reader.getLocalName();
                    if (name.equals(DeviceXmlTag.CPU_DEVICE.getName()) || name.equals(DeviceXmlTag.GPU_DEVICE.getName())){
                        return currDevice;
                    }
                    break;
                }
            }
        }

        throw new XMLStreamException();
    }

    private Device.DeviceTypeSet getXMLDeviceTypeSet(XMLStreamReader reader) throws XMLStreamException {
        Device.DeviceTypeSet deviceTypeSet = new Device().getDeviceTypeSet();

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT: {
                    name = reader.getLocalName();

                    switch (DeviceXmlTag.valueOfXmlTag(name)){
                        case DEVICE_TYPE:{
                            deviceTypeSet.setDeviceTypeEnum(DeviceTypeEnum.valueOfXmlContent(getXMLTextReader(reader)));
                            break;
                        }
                        case POWER_USAGE:{
                            deviceTypeSet.setPowerUsage(Double.parseDouble(getXMLTextReader(reader)));
                            break;
                        }
                        case PRESENCE_OF_COOLER:{
                            deviceTypeSet.setPresenceOfCooler(Boolean.parseBoolean(getXMLTextReader(reader)));
                            break;
                        }
                        case DEVICE_GROUP:{
                            deviceTypeSet.setDeviceGroupEnum(DeviceGroupEnum.valueOfXmlContent(getXMLTextReader(reader)));
                            break;
                        }
                        case PORT:{
                            deviceTypeSet.setPortEnum(PortEnum.valueOf(getXMLTextReader(reader).toUpperCase()));
                            break;
                        }
                    }

                    break;
                }

                case XMLStreamConstants.END_ELEMENT: {
                    name = reader.getLocalName();

                    if (name.equals(DeviceXmlTag.TYPE.getName())){
                        return deviceTypeSet;
                    }
                }
            }
        }

        throw new XMLStreamException();
    }

    private String getXMLTextReader(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()){
            reader.next();
            text = reader.getText();
        }

        return text;
    }
}
