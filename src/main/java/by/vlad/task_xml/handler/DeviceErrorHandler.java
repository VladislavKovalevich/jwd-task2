package by.vlad.task_xml.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DeviceErrorHandler implements ErrorHandler {
    private static Logger logger = LogManager.getLogger();
    private boolean isError = false;

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        logger.warn(getLineColumnNumber(exception) + " - " + exception.getMessage());
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        logger.error(getLineColumnNumber(exception) + " - " + exception.getMessage());
        isError = true;
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        logger.fatal(getLineColumnNumber(exception) + " - " + exception.getMessage());
    }

    private String getLineColumnNumber(SAXParseException e){
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }

    public boolean isError(){
        return this.isError;
    }
}
