package com.esb.forumdemo.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ForummembersRepository extends JpaRepository<Forummembers, Integer> {

	//Forummembers findByFnickname(String fnickname);
	Forummembers findByForummemberID(Integer forummemberID);
	
	Forummembers findByUserName(String userName);
	
	Forummembers findByFnickname(String fnickname);

	Forummembers findByEmail(String email);

	Forummembers findByPhoneNumber(String phoneNumber);
	
	Forummembers  findFnicknameByForummemberID(Integer forummemberID);
	
	//Forummembers  findForummemberIDByFnickname(String fnickname);


}
