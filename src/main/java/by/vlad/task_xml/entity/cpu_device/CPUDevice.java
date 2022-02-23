package by.vlad.task_xml.entity.cpu_device;

import by.vlad.task_xml.entity.device.CountryEnum;
import by.vlad.task_xml.entity.device.Device;

import java.time.YearMonth;

public class CPUDevice extends Device {
    private CPUCoreArchitectureEnum coreArchitecture;
    private int coreCount;
    private int threadCount;
    private double frequency;
    private int cacheL3Size;

    public CPUDevice() {
    }

    public CPUDevice(String id, String name, CountryEnum origin, Double price, DeviceTypeSet deviceTypeSet,
                     boolean isCritical, int coreCount, int threadCount, int cacheL3Size,
                     CPUCoreArchitectureEnum cpuCoreArchitectureEnum, YearMonth releaseDate, double frequency) {

        super(id, name, origin, price, deviceTypeSet, releaseDate, isCritical);
        this.coreCount = coreCount;
        this.cacheL3Size = cacheL3Size;
        this.coreArchitecture = cpuCoreArchitectureEnum;
        this.threadCount = threadCount;
        this.frequency = frequency;
    }

    public CPUCoreArchitectureEnum getCoreArchitecture() {
        return coreArchitecture;
    }

    public void setCoreArchitecture(CPUCoreArchitectureEnum coreArchitecture) {
        this.coreArchitecture = coreArchitecture;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public int getCoreCount() {
        return coreCount;
    }

    public void setCoreCount(int coreCount) {
        this.coreCount = coreCount;
    }

    public int getCacheL3Size() {
        return cacheL3Size;
    }

    public void setCacheL3Size(int cacheL3Size) {
        this.cacheL3Size = cacheL3Size;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CPUDevice cpuDevice = (CPUDevice) o;

        if (coreCount != cpuDevice.coreCount) return false;
        if (threadCount != cpuDevice.threadCount) return false;
        if (Double.compare(cpuDevice.frequency, frequency) != 0) return false;
        if (cacheL3Size != cpuDevice.cacheL3Size) return false;
        return coreArchitecture == cpuDevice.coreArchitecture;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + coreArchitecture.hashCode();
        result = 31 * result + coreCount;
        result = 31 * result + threadCount;
        temp = Double.doubleToLongBits(frequency);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + cacheL3Size;
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("\nCPUDevice{").append('\n')
                .append(super.toString())
                .append("\tcore architecture = ")
                .append(coreArchitecture).append('\n')
                .append("\tfrequency = ")
                .append(frequency).append("MHz\n")
                .append("\tcore count = ")
                .append(coreCount).append('\n')
                .append("\tthread count = ")
                .append(threadCount).append('\n')
                .append("\tcache L3 size = ")
                .append(cacheL3Size).append("MB\n")
                .append('}').append('\n').toString();
    }
}
