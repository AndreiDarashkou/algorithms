import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class Catch22 extends ClassLoader {

    public Catch22(ClassLoader context) {
        super("app2", context);
    }

    public static Yossarian loophole() throws Throwable {
        ClassLoader classLoader = Catch22.class.getClassLoader();
        System.out.println(classLoader.getName());
        System.out.println(classLoader.getParent().getName());
        System.out.println(classLoader.getParent().getParent().getName());

        Catch22 ca = new Catch22(classLoader);
        Object cl1 = ca.loadNewClass();

        return (Yossarian) cl1;
    }

    public Object loadNewClass() throws Exception {
        String source = "public class Yossarian { public Yossarian(){} "
                + " public int xxx; "
                + " public boolean isCrazy() {\n" +
                "     return true;\n" +
                "  }}";

        URL location = Catch22.class.getProtectionDomain().getCodeSource().getLocation();
        File root = new File(location.getPath());

        Class<?> yossarianClass = compileAndLoad(root, source, "Yossarian");
        Field f = yossarianClass.getFields()[0];
        System.out.println(f.getName());
        return yossarianClass.getDeclaredConstructor().newInstance();
    }

    public Class<?> compileAndLoad(File root, String source, String name) throws Exception {
        File sourceFile = new File(root, name + ".java");
        Files.write(sourceFile.toPath(), source.getBytes(StandardCharsets.UTF_8));

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, sourceFile.getPath());

        //if (name.equals("Yossarian2")) {
            return this.loadClass(name, true);
        //} else {
        //    return Catch22.class.getClassLoader().loadClass(name);
        //}
    }
}
