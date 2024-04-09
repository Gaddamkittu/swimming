package com.service;

import com.model.Learner;

public class LoginService {
	
	LearnerService learnerService=new LearnerService();
	public static Learner logedInLearner=new Learner();
	
	public boolean login(String phone) {
		boolean flag=false;
		
		if(learnerService.getByPhone(phone)!=null) {
			flag=true;
			logedInLearner=learnerService.getByPhone(phone);
		}
		
		
		return flag;
	}
	
}
