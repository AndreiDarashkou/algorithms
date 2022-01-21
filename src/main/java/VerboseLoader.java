import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class VerboseLoader {

    public static void main(String[] args) throws Exception {
        ClassLoader classloader = Catch22.class.getClassLoader();
        loadNewClass();
    }

    public static void loadNewClass() throws Exception {
        String source = "public class Yossarian { public Yossarian(){} "
                + " public final boolean isCrazy() {\n" +
                "    return true;\n" +
                "  }}";

        URL location = Test.class.getProtectionDomain().getCodeSource().getLocation();

        File root = new File(location.getPath());
        File sourceFile = new File(root, "Yossarian.java");
        Files.write(sourceFile.toPath(), source.getBytes(StandardCharsets.UTF_8));

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, sourceFile.getPath());

        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{root.toURI().toURL()});
        Class<?> cls = Class.forName("Yossarian", true, classLoader);
        Object instance = cls.getDeclaredConstructor().newInstance();
        System.out.println(((Yossarian) instance).isCrazy());
    }
}
