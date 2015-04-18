package models;

import static org.junit.Assert.*;

import org.junit.Test;

import play.db.jpa.JPA;

public class CommunityTest extends BaseTest{
    @Test
    public void testCommunityCreation(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                Community comm = new Community("Google", "google.com");
                Community.createCommunity(comm);
                
                //If community is created, then id should be generated and set by hibernate
                assertEquals(comm.getId(), 1);

            }
        });   
    }
    
    @Test
    public void testCommunityCreationDulicateName(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                Community.createCommunity(new Community("Google", "google.com"));
                Community.createCommunity(new Community("Google", "gmail.com"));
                
                //There should only be 1 community in the db, so id=2 shouldn't exist
                assertNull(Community.findCommunityById(2));

            }
        });   
    }
    @Test
    public void testCommunityCreationDulicateEmail(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                Community.createCommunity(new Community("Google", "google.com"));
                Community.createCommunity(new Community("goog", "google.com"));
                
                //There should only be 1 community in the db, so id=2 shouldn't exist
                assertNull(Community.findCommunityById(2));

            }
        });   
    }
    @Test
    public void testCommunityFindById(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                String communityName = "Google";
                String emailDomain = "google.com";
                Community comm = new Community(communityName, emailDomain);
                Community.createCommunity(comm);
                
                Community dbComm = Community.findCommunityById(1);
                
                assertNotNull(dbComm);
                assertEquals(dbComm.getCommunityName(), communityName);
            }
        });   
    }
    @Test
    public void testCommunityFindByName(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                String communityName = "Google";
                String emailDomain = "google.com";
                Community comm = new Community(communityName, emailDomain);
                Community.createCommunity(comm);
                
                //checks to see if it can find name ignoring case
                Community dbComm = Community.findCommunityByName("GOOGLE");
                
                assertNotNull(dbComm);
            }
        });   
    }
    
    @Test
    public void testCommunityFindByEmail(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                String communityName = "Google";
                String emailDomain = "google.com";
                Community comm = new Community(communityName, emailDomain);
                Community.createCommunity(comm);
                
                //checks to see if it can find name ignoring case
                Community dbComm = Community.findCommunityByEmail("GOOGLE.com");
                
                assertNotNull(dbComm);
            }
        });   
    }
    @Test
    public void testCommunityDelete(){
        JPA.withTransaction(new play.libs.F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                String communityName = "Google";
                String emailDomain = "google.com";
                Community comm = new Community(communityName, emailDomain);
                Community.createCommunity(comm);
                
                //checks to see if it can find name ignoring case
                Community dbComm = Community.findCommunityById(1);
                Community.deleteCommunity(dbComm);
                
                assertNull(Community.findCommunityById(1));
                
                //TODO Check that all posts and emails associated with community are also deleted
            }
        });   
    }
}
