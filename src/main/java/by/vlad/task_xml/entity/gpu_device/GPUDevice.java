package by.vlad.task_xml.entity.gpu_device;

import by.vlad.task_xml.entity.device.CountryEnum;
import by.vlad.task_xml.entity.device.Device;

import java.time.YearMonth;

public class GPUDevice extends Device {
    private GPUArchitectureEnum architecture;
    private GPUMemoryType memoryType;
    private int memorySize;

    public GPUDevice() {
    }

    public GPUDevice(String id, String name, CountryEnum origin, Double price, DeviceTypeSet deviceTypeSet, boolean isCritical,
                     GPUArchitectureEnum architecture, GPUMemoryType memoryType, int memorySize, YearMonth releaseDate) {

        super(id, name, origin, price, deviceTypeSet, releaseDate, isCritical);
        this.architecture = architecture;
        this.memoryType = memoryType;
        this.memorySize = memorySize;
    }

    public GPUArchitectureEnum getArchitecture() {
        return architecture;
    }

    public void setArchitecture(GPUArchitectureEnum architecture) {
        this.architecture = architecture;
    }

    public GPUMemoryType getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(GPUMemoryType memoryType) {
        this.memoryType = memoryType;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        GPUDevice gpuDevice = (GPUDevice) o;

        if (architecture != gpuDevice.architecture) return false;
        if (memorySize != gpuDevice.memorySize) return false;
        return memoryType == gpuDevice.memoryType;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + architecture.hashCode();
        result = 31 * result + memoryType.hashCode();
        result = 31 * result + memorySize;
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("\nGPUDevice{").append('\n')
                .append(super.toString())
                .append("\tarchitecture = ")
                .append(architecture).append('\n')
                .append("\tmemory type = ")
                .append(memoryType).append('\n')
                .append("\tmemory size = ")
                .append(memorySize).append(" GB\n")
                .append('}').append('\n').toString();
    }
}
