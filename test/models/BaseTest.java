package models;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;

import org.junit.Before;

import play.test.WithApplication;


public abstract class BaseTest extends WithApplication {
    @Before
    public void setup(){
        start(fakeApplication(inMemoryDatabase()));
    }

}
