package dev.jessefu.component_base.router;
/**
 * 路由常量表*/
public final class RouterConstants {
    private static final String TAG = "RouterConstants";

    private RouterConstants(){
        throw new AssertionError("should not instantiate this class");
    }

    public static class ComponentBase{

    }

    public static class ModuleMain{
        private static final String PATH = "/main/";

        public static final String ACTIVITY_MAIN = PATH + "activity_main";

    }

    public static class ModuleAbout{
        private static final String PATH = "/about/";

        public static final String ACTIVITY_ABOUT = PATH + "activity_about";
    }

    public static class ModuleDetails{
        private static final String PATH = "/details/";

    }

    public static class ModuleEntrance{
        private static final String PATH = "/entrance/";

        public static final String ACTIVITY_ENTRANCE = PATH + "activity_entrance";
    }

    public static class ModuleModify{
        private static final String PATH = "/modify/";
    }

    public static class ModuleSearch{
        private static final String PATH = "/search/";

        public static final String ACTIVITY_SEARCH = PATH + "activity_search";
    }

}
