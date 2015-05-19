package com.netflix.archaius.samples.simple;

import com.netflix.config.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SomeService {
    private static Logger logger = LoggerFactory.getLogger(SomeService.class);
    private DynamicIntProperty int1prop;
    private DynamicIntProperty int2prop;

    public SomeService() {
        int1prop = DynamicPropertyFactory.getInstance().getIntProperty("intprop", 42);
        int1prop = DynamicPropertyFactory.getInstance().getIntProperty("intprop", 42, new Runnable() {
            @Override
            public void run() {
                logger.debug("int1prop changed to " + int1prop.get());
            }
            // no equivalent of parseError callback?
        });
        int2prop = DynamicPropertyFactory.getInstance().getIntProperty("intprop2", 42);

//        int1prop.addCallback(new Runnable() {
//            @Override
//            public void run() {
//                logger.debug("int1prop changed to " + int1prop.get());
//            }
//            // no equivalent of parseError callback?
//        });
    }

    public void getDirectly() {
        int int1 = DynamicProperty.getInstance("intprop").getInteger();
        logger.debug("int1 = " + int1);
        int int2 = DynamicProperty.getInstance("intprop2").getInteger(42);
        logger.debug("int2 = " + int2);
        String string1 = DynamicProperty.getInstance("stringprop").getString();
        logger.debug("string1 = " + string1);
        String string2 = DynamicProperty.getInstance("stringprop2").getString("noThere");
        logger.debug("string2 = " + string2);
        boolean bool1 = DynamicProperty.getInstance("boolprop").getBoolean();
        logger.debug("bool1 = " + bool1);
        boolean bool2 = DynamicProperty.getInstance("boolprop2").getBoolean(false);
        logger.debug("bool2 = " + bool2);
    }

    public void getAgainstProperty() {
        logger.debug("int1 = " + int1prop.get());
        logger.debug("int2 = " + int2prop.get());
    }

    // obviously this isn't what you'd do typically, this would be more from a polling source
    public void changePropertyThatIsMonitored() {
        // this doesn't seem to cause a property change event, not change the value
        ConfigurationManager.getConfigInstance().setProperty("intprop", 42*42);
        logger.debug("after change property");
        int int1 = DynamicProperty.getInstance("intprop").getInteger();
        logger.debug("int1 = " + int1);
    }
}
