import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by arkon92 on 14/09/2017.
 */
public class JSR223Test {

    ExecutorService pool = Executors.newFixedThreadPool(1);

    @Test
    public void whatIsJsr223WithGroovy() throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        List<ScriptEngineFactory> factories = manager.getEngineFactories();
        for (ScriptEngineFactory factory : factories) {
            System.out.println("Name : " + factory.getEngineName());
            System.out.println("Version : " + factory.getEngineVersion());
            System.out.println("Language name : " + factory.getLanguageName());
            System.out.println("Language version : " + factory.getLanguageVersion());
            System.out.println("Extensions : " + factory.getExtensions());
            System.out.println("Mime types : " + factory.getMimeTypes());
            System.out.println("Names : " + factory.getNames());
        }


        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("groovy");

        Future<String> futureResult = startDownloading(engine);

        System.out.println(futureResult.get());

        Future<String> futureResult2 = startDownloading(engine);
        Future<String> futureResult3 = startDownloading(engine);
        Future<String> futureResult4 = startDownloading(engine);
        Future<String> futureResult5 = startDownloading(engine);

        System.out.println(futureResult2.get());
        System.out.println(futureResult3.get());
        System.out.println(futureResult4.get());
        System.out.println(futureResult5.get());
    }


    public Future<String> startDownloading(final ScriptEngine engine) throws IOException {
        return pool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                int sleepingTime = new Random().nextInt(10);
                System.out.println("Sleeping: " + sleepingTime);
                Thread.sleep(sleepingTime * 1000);

                File f = new File(getClass().getClassLoader().getResource("main.groovy").getFile());
                f.exists();

                engine.eval(new FileReader(getClass().getResource("main.groovy").getFile()));

                Invocable inv = (Invocable) engine;
                Object[] params = {5};
                Object result = inv.invokeFunction("factorial", params);

                System.out.println(result);

                return (String) engine.eval(new FileReader(getClass().getResource("main.groovy").getFile()));
            }
        });
    }


}
