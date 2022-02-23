package by.vlad.task_xml.exception;

public class CustomDeviceException extends Exception{

    public CustomDeviceException() {
    }

    public CustomDeviceException(String message) {
        super(message);
    }

    public CustomDeviceException(String message, Exception e) {
        super(message, e);
    }

    public CustomDeviceException(Exception e) {
        super(e);
    }
}
