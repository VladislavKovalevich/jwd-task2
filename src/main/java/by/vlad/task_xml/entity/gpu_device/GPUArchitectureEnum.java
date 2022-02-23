package by.vlad.task_xml.entity.gpu_device;

public enum GPUArchitectureEnum {
    AMPERE("Ampere"),
    MAXWELL("Maxwell"),
    PASCAL("Pascal"),
    TURING("Turing"),
    KEPLER("Kepler");

    private String name;

    GPUArchitectureEnum(String name){
        this.name = name;
    }
}
