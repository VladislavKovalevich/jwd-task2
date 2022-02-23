package test.vlad.task_xml.validator;

import by.vlad.task_xml.validator.CustomXmlValidator;
import by.vlad.task_xml.validator.impl.CustomXmlValidatorImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CustomXmlValidatorTest {
    private CustomXmlValidator customXmlValidator;

    @BeforeClass
    private void init(){
        customXmlValidator = CustomXmlValidatorImpl.getInstance();
    }

    @DataProvider(name = "validator")
    private Object[][] initDataSets(){
        return new Object[][]{
                {"src\\test\\resources\\devicesTest.xml", "src\\test\\resources\\devicesSchemaTest.xsd", true},
                {"src\\test\\resources\\wrongDevicesTest.xml", "src\\test\\resources\\devicesSchemaTest.xsd", false},
                {"src\\test\\resources\\devicesTest.xml", "src\\test\\resources\\wrongXsdPath.xsd", false},
        };
    }

    @Test(dataProvider = "validator")
    public void testValidator(String xmlPath, String xsdPath, boolean expected) {
        boolean actual = customXmlValidator.validateXmlDocument(xmlPath, xsdPath);
        Assert.assertEquals(actual, expected);
    }
}