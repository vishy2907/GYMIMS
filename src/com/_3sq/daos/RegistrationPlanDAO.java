package com._3sq.daos;

import com._3sq.domainobjects.RegistrationPlan;

public interface RegistrationPlanDAO {

	public boolean addRegistrationInfo(int memberid,RegistrationPlan registrationplan);
}
