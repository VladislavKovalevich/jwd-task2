package by.vlad.task_xml.handler;

public enum  DeviceXmlTag {
    DEVICES("devices"),
    CPU_DEVICE("cpu-device"),
    GPU_DEVICE("gpu-device"),

    NAME("name"),
    ORIGIN("origin"),
    PRICE("price"),

    DEVICE_TYPE("device-type"),
    POWER_USAGE("power-usage"),
    PRESENCE_OF_COOLER("presence-of-cooler"),
    DEVICE_GROUP("device-group"),
    PORT("port"),

    RELEASE_DATE("release-date"),

    CORE_ARCHITECTURE("core-architecture"),
    FREQUENCY("frequency"),
    CORE_COUNT("core-count"),
    THREAD_COUNT("thread-count"),
    CACHE_SIZE("cache-size"),

    ARCHITECTURE("architecture"),
    MEMORY_TYPE("memory-type"),
    MEMORY_SIZE("memory-size"),

    TYPE("type");

    private static final char UNDERSCORE = '_';
    private static final char HYPHEN = '-';

    private String name;

    DeviceXmlTag(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static DeviceXmlTag valueOfXmlTag(String tag){
        return DeviceXmlTag.valueOf(tag.toUpperCase().replace(HYPHEN, UNDERSCORE));
    }
}
