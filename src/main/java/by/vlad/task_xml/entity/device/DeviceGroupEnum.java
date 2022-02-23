package by.vlad.task_xml.entity.device;

public enum DeviceGroupEnum {
    MULTIMEDIA("multimedia"),
    IO_DEVICES("IO devices"),
    PROCESSORS("processors"),
    NETWORK_HARDWARE("network hardware");

    private static final char UNDERSCORE = '_';
    private static final char WHITESPACE = ' ';

    private String name;

    DeviceGroupEnum(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public  static DeviceGroupEnum valueOfXmlContent(String content){
        return DeviceGroupEnum.valueOf(content.toUpperCase().replace(WHITESPACE, UNDERSCORE));
    }
}
