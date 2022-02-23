package by.vlad.task_xml.entity.device;

public enum CountryEnum {
    CHINA("China"),
    ENGLAND("England"),
    RUSSIA("Russia"),
    USA("USA"),
    FRANCE("France");

    private String name;

    CountryEnum(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
