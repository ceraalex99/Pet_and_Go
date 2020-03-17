package io.github.ceraalex99.petandgo.hibernate;

import java.io.File;

public class HibernateConfig{
    static File F;
    public HibernateConfig(){
        if (F == null) F  = new File("src\\io.github.ceraalex99.petandgo.hibernate.cfg.xml");
    }
    public static File getConfigFile() {
        return F;
    }
}
