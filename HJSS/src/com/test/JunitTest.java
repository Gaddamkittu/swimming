package com.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.service.DataGenerator;
import com.service.LearnerService;
import com.service.LessonService;
import com.service.LoginService;

public class JunitTest {
	
	@BeforeClass
	 public static void setUpBeforeClass() throws Exception {  
        
		DataGenerator.generateDummyLearners(15);
		DataGenerator.generateLessons(50);
		DataGenerator.generateCoaches(4);
		DataGenerator.setLessonsToLearner();
    }  
	
	@Test
	public void testLogin() {
		
		LoginService loginService=new LoginService();
		
		assertEquals(true, loginService.login("(987) 654-3210"));
		assertEquals(false, loginService.login("9876543210"));
		
	}
	
	@Test
	public void testSearchByDay() {
		LessonService lessonService=new LessonService();
		assertEquals(true,lessonService.searcByDay("monday").size()>0);
		assertEquals(false,lessonService.searcByDay("tuesday").size()>0);
	}
	
	@Test
	public void testIsLearnerExist() {
		
		LearnerService learnerService=new LearnerService();
		assertEquals(true, learnerService.isExist(2));
		assertEquals(false, learnerService.isExist(200));
		
	}

}
