package by.vlad.task_xml.entity.device;

public enum PortEnum {
    COM("COM"),
    USB("USB"),
    LPT("LPT"),
    VGA("VGA"),
    NONE("NONE");

    private String name;

    PortEnum(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
