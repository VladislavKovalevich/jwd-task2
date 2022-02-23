package test.vlad.task_xml.builder;

import by.vlad.task_xml.builder.DeviceDomBuilder;
import by.vlad.task_xml.builder.DeviceSaxBuilder;
import by.vlad.task_xml.entity.cpu_device.CPUCoreArchitectureEnum;
import by.vlad.task_xml.entity.cpu_device.CPUDevice;
import by.vlad.task_xml.entity.device.*;
import by.vlad.task_xml.entity.gpu_device.GPUArchitectureEnum;
import by.vlad.task_xml.entity.gpu_device.GPUDevice;
import by.vlad.task_xml.entity.gpu_device.GPUMemoryType;
import by.vlad.task_xml.exception.CustomDeviceException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

public class DeviceSaxBuilderTest {
    private DeviceSaxBuilder deviceSaxBuilder;

    @BeforeTest
    public void init(){
        deviceSaxBuilder = new DeviceSaxBuilder();
    }

    @Test
    public void testBuildDeviceSet() throws CustomDeviceException {
        CPUDevice cpuDevice = new CPUDevice();
        GPUDevice gpuDevice = new GPUDevice();

        cpuDevice.setId("index-23");
        cpuDevice.setName("Intel Core i5-5200U");
        cpuDevice.setOrigin(CountryEnum.USA);
        cpuDevice.setPrice(600.0);
        cpuDevice.getDeviceTypeSet().setPortEnum(PortEnum.NONE);
        cpuDevice.getDeviceTypeSet().setPresenceOfCooler(false);
        cpuDevice.getDeviceTypeSet().setPowerUsage(15.0);
        cpuDevice.getDeviceTypeSet().setDeviceGroupEnum(DeviceGroupEnum.PROCESSORS);
        cpuDevice.getDeviceTypeSet().setDeviceTypeEnum(DeviceTypeEnum.NOT_A_PERIPHERAL);
        cpuDevice.setFrequency(2.2);
        cpuDevice.setThreadCount(4);
        cpuDevice.setCoreCount(2);
        cpuDevice.setCoreArchitecture(CPUCoreArchitectureEnum.BROADWELL);
        cpuDevice.setCacheL3Size(3);
        cpuDevice.setCritical(true);
        cpuDevice.setReleaseDate(YearMonth.parse("2015-02"));

        gpuDevice.setId("index-43");
        gpuDevice.setName("GeForce GTX 780 Ti");
        gpuDevice.setOrigin(CountryEnum.USA);
        gpuDevice.setPrice(500.0);
        gpuDevice.getDeviceTypeSet().setPortEnum(PortEnum.VGA);
        gpuDevice.getDeviceTypeSet().setPresenceOfCooler(true);
        gpuDevice.getDeviceTypeSet().setPowerUsage(250.0);
        gpuDevice.getDeviceTypeSet().setDeviceGroupEnum(DeviceGroupEnum.PROCESSORS);
        gpuDevice.getDeviceTypeSet().setDeviceTypeEnum(DeviceTypeEnum.NOT_A_PERIPHERAL);
        gpuDevice.setArchitecture(GPUArchitectureEnum.KEPLER);
        gpuDevice.setMemorySize(3);
        gpuDevice.setMemoryType(GPUMemoryType.GDDR5);
        gpuDevice.setCritical(true);
        gpuDevice.setReleaseDate(YearMonth.parse("2013-11"));

        Set<Device> expected = new HashSet<>();
        expected.add(cpuDevice);
        expected.add(gpuDevice);

        String xmlPath = "src\\test\\resources\\devicesTest.xml";
        deviceSaxBuilder.buildSetDevices(xmlPath);
        Set<Device> actual = deviceSaxBuilder.getDevices();

        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = CustomDeviceException.class)
    public void testBuildDevicesIOException() throws CustomDeviceException {
        String xmlPath = "src\\test\\resources\\wrong.xml";

        deviceSaxBuilder.buildSetDevices(xmlPath);
    }

    @Test(expectedExceptions = CustomDeviceException.class)
    public void testBuildDevicesSAXException() throws CustomDeviceException {
        String xmlPath = "src\\test\\resources\\devicesTestError.xml";

        deviceSaxBuilder.buildSetDevices(xmlPath);
    }
}
