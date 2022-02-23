package by.vlad.task_xml.entity.cpu_device;

public enum CPUCoreArchitectureEnum {
    SANDY_BRIDGE("Sandy Bridge"),
    IVY_BRIDGE("Ivy Bridge"),
    BROADWELL("Broadwell"),
    SKYLAKE("Skylake"),
    COFFE_LAKE("Coffee Lake"),
    COOPER_LAKE("Cooper Lake"),
    CANNON_LAKE("Cannon Lake");

    private static final char UNDERSCORE = '_';
    private static final char WHITESPACE = ' ';

    private String name;

    CPUCoreArchitectureEnum(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  static CPUCoreArchitectureEnum valueOfXmlContent(String content){
        return CPUCoreArchitectureEnum.valueOf(content.toUpperCase().replace(WHITESPACE, UNDERSCORE));
    }
}
