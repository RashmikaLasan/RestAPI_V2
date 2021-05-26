package Executor;
import io.cucumber.core.cli.Main;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CucumberIT {
    @Test
    public void executor() {
        Main.run(new String[]{
                "--threads", "1",
                "-p", "io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm",
                "-g", "StepDefs", "src/main/resources/",
                "-t","@htlnopromo"
        }, CucumberIT.class.getClassLoader());
    }

    @Before
    public void before(){

        System.out.println("@Before Execution");
    }

    @After
    public void after(){
        System.out.println("@After Execution");

    }

}
