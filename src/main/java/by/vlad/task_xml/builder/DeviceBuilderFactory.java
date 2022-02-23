package by.vlad.task_xml.builder;

public class DeviceBuilderFactory {
    private enum TypeParser{
        SAX, STAX, DOM
    }

    private DeviceBuilderFactory() {
    }

    public static AbstractDeviceBuilder createDeviceBuilder(String typeParser){
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
                //log
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
            }
        }
    }
}
