package test.vlad.task_xml.builder;

import by.vlad.task_xml.builder.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DeviceBuilderFactoryTest {
    private DeviceBuilderFactory deviceBuilderFactory;

    @BeforeClass
    private void init(){
        deviceBuilderFactory = DeviceBuilderFactory.getInstance();
    }

    @DataProvider(name = "deviceBuilder")
    private Object[][] initDataSet(){
        return new Object[][]{
                {DeviceDomBuilder.class, "dom"},
                {DeviceSaxBuilder.class, "sax"},
                {DeviceStaxBuilder.class, "stax"},
        };
    }

    @Test(dataProvider = "deviceBuilder")
    public void testDeviceFactory(Class<? extends AbstractDeviceBuilder> expected, String type) {
        Class<? extends AbstractDeviceBuilder> actual = deviceBuilderFactory.createDeviceBuilder(type).getClass();
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateBuilderException () {
        deviceBuilderFactory.createDeviceBuilder("qwerty");
    }
}