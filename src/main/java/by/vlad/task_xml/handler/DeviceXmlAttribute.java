package by.vlad.task_xml.handler;

public enum DeviceXmlAttribute {
    ID("id"),
    CRITICAL("critical");

    private String name;

    DeviceXmlAttribute(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
