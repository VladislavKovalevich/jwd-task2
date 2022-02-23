package by.vlad.task_xml.builder;

import by.vlad.task_xml.entity.device.Device;
import by.vlad.task_xml.exception.CustomDeviceException;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractDeviceBuilder {
    protected Set<Device> devices;

    public AbstractDeviceBuilder() {
        this.devices = new HashSet<>();
    }

    public AbstractDeviceBuilder(Set<Device> devices) {
        this.devices = devices;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public abstract void buildSetDevices(String filename) throws CustomDeviceException;
}
