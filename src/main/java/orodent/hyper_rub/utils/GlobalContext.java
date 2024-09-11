package orodent.hyper_rub.utils;

import orodent.hyper_rub.framework.Framework;

public abstract class GlobalContext {
    public static DBManager dbManager = new DBManager();
    public static Framework framework = new Framework(dbManager);
}
