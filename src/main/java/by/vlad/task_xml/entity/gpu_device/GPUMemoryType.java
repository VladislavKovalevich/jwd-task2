package by.vlad.task_xml.entity.gpu_device;

public enum GPUMemoryType {
    SDR("SDR"),
    DDR("DDR"),
    DDR2("DDR2"),
    GDDR3("GDDR3"),
    DDR3("DDR3"),
    GDDR5("GDDR5"),
    GDDR5X("GDDR5X");

    private String name;

    GPUMemoryType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
