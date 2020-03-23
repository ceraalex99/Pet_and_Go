package hibernate;

import java.io.File;

public class HibernateConfig{
    private static File f = new File ("src/hibernate.cfg.xml");

    private HibernateConfig() {
        throw new IllegalStateException("Utility class");
    }

    public static File getConfigFile() {
        return f;
    }
}
