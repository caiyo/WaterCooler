package models;

import org.junit.Test;
import static play.test.Helpers.*;


public class CommentTest {
    @Test
    public void testCommentCreation(){
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
               
            }
          });
        
    }
}
