package by.vlad.task_xml.builder;

public class DeviceBuilderFactory {
    private static DeviceBuilderFactory instance;

    private enum TypeParser{
        SAX, STAX, DOM
    }

    private DeviceBuilderFactory() {
    }

    public static DeviceBuilderFactory getInstance() {
        if (instance == null){
            instance = new DeviceBuilderFactory();
        }
        return instance;
    }

    public AbstractDeviceBuilder createDeviceBuilder(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());

        switch (type){
            case DOM:{
                return new DeviceDomBuilder();
            }
            case SAX:{
                return new DeviceSaxBuilder();
            }
            case STAX:{
                return new DeviceStaxBuilder();
            }
            default:{
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
            }
        }
    }
}
