package by.vlad.task_xml.entity.device;

public enum DeviceTypeEnum {
    NOT_A_PERIPHERAL("not a peripheral"),
    PERIPHERAL("peripheral");

    private static final char UNDERSCORE = '_';
    private static final char WHITESPACE = ' ';

    private String name;

    DeviceTypeEnum(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public  static DeviceTypeEnum valueOfXmlContent(String content){
        return DeviceTypeEnum.valueOf(content.toUpperCase().replace(WHITESPACE, UNDERSCORE));
    }
}