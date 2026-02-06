
package utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

    public class LoggerUtil {

        // Get logger for the calling class
        public static Logger getLogger(Class<?> clazz) {
            return LogManager.getLogger(clazz);
        }

        // Quick access methods for common classes
        public static Logger getBaseClassLogger() {
            return LogManager.getLogger("base.BaseClass");
        }

        public static Logger getElementUtilLogger() {
            return LogManager.getLogger("utility.ElementUtil");
        }

        public static Logger getBrowserFactoryLogger() {
            return LogManager.getLogger("factory.BrowserFactory");
        }
    }

