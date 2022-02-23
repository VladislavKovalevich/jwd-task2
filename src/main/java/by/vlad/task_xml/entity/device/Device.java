package by.vlad.task_xml.entity.device;

import java.time.YearMonth;

public class Device {
    public static final boolean DEFAULT_CRITICAL = true;

    private String id;
    private String name;
    private CountryEnum origin;
    private double price;
    private DeviceTypeSet deviceTypeSet = new DeviceTypeSet();
    private YearMonth releaseDate;
    private boolean isCritical;

    public Device() {
    }

    public Device(String id, String name, CountryEnum origin, Double price, DeviceTypeSet deviceTypeSet, YearMonth releaseDate, boolean isCritical) {
        this.id = id;
        this.name = name;
        this.origin = origin;
        this.price = price;
        this.deviceTypeSet = deviceTypeSet;
        this.releaseDate = releaseDate;
        this.isCritical = isCritical;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryEnum getOrigin() {
        return origin;
    }

    public void setOrigin(CountryEnum origin) {
        this.origin = origin;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public DeviceTypeSet getDeviceTypeSet() {
        return deviceTypeSet;
    }

    public void setDeviceTypeSet(DeviceTypeSet deviceTypeSet) {
        this.deviceTypeSet = deviceTypeSet;
    }

    public YearMonth getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(YearMonth releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setCritical(boolean critical) {
        isCritical = critical;
    }

    public boolean isCritical() {
        return isCritical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Device device = (Device) o;

        if (Double.compare(device.price, price) != 0) return false;
        if (isCritical != device.isCritical) return false;
        if (!name.equals(device.name)) return false;
        if (origin != device.origin) return false;
        return deviceTypeSet.equals(device.deviceTypeSet);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        result = 31 * result + origin.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + deviceTypeSet.hashCode();
        result = 31 * result + (isCritical ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("\tid = ").
                append(id).append('\n').
                append("\tname = ").
                append(name).append('\n').
                append("\torigin = ").
                append(origin).append('\n').
                append("\trelease date = ").
                append(releaseDate).append('\n').
                append("\tprice = ").
                append(price).append('\n').
                append("\tdevice type set = ").
                append(deviceTypeSet).append('\n').
                append("\tis critical = ").
                append(isCritical).append('\n').toString();
    }

    public class DeviceTypeSet {
        private DeviceTypeEnum deviceTypeEnum;
        private double powerUsage;
        private boolean presenceOfCooler;
        private DeviceGroupEnum deviceGroupEnum;
        private PortEnum portEnum;

        DeviceTypeSet() {
        }

        public DeviceTypeEnum getDeviceTypeEnum() {
            return deviceTypeEnum;
        }

        public void setDeviceTypeEnum(DeviceTypeEnum deviceTypeEnum) {
            this.deviceTypeEnum = deviceTypeEnum;
        }

        public Double getPowerUsage() {
            return powerUsage;
        }

        public void setPowerUsage(Double powerUsage) {
            this.powerUsage = powerUsage;
        }

        public boolean isPresenceOfCooler() {
            return presenceOfCooler;
        }

        public void setPresenceOfCooler(boolean presenceOfCooler) {
            this.presenceOfCooler = presenceOfCooler;
        }

        public DeviceGroupEnum getDeviceGroupEnum() {
            return deviceGroupEnum;
        }

        public void setDeviceGroupEnum(DeviceGroupEnum deviceGroupEnum) {
            this.deviceGroupEnum = deviceGroupEnum;
        }

        public PortEnum getPortEnum() {
            return portEnum;
        }

        public void setPortEnum(PortEnum portEnum) {
            this.portEnum = portEnum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            DeviceTypeSet that = (DeviceTypeSet) o;

            if (Double.compare(that.powerUsage, powerUsage) != 0) return false;
            if (presenceOfCooler != that.presenceOfCooler) return false;
            if (deviceTypeEnum != that.deviceTypeEnum) return false;
            if (deviceGroupEnum != that.deviceGroupEnum) return false;
            return portEnum == that.portEnum;
        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            result = deviceTypeEnum.hashCode();
            temp = Double.doubleToLongBits(powerUsage);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            result = 31 * result + (presenceOfCooler ? 1 : 0);
            result = 31 * result + deviceGroupEnum.hashCode();
            result = 31 * result + portEnum.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return new StringBuilder().append("{" + "\n\t\tdevice type set = ")
                    .append(deviceTypeEnum).append('\n')
                    .append("\t\tpowerUsage = ")
                    .append(powerUsage).append('\n')
                    .append("\t\tpresence of cooler = ")
                    .append(presenceOfCooler).append('\n')
                    .append("\t\tdevice group = ")
                    .append(deviceGroupEnum).append('\n')
                    .append("\t\tport type = ")
                    .append(portEnum).append('\n')
                    .append("\t}").toString();
        }
    }
}
