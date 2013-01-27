package com._3sq.daos;

import com._3sq.domainobjects.GymPlan;

/**
 * @author Vishal B
 * DAO for maintaining list of all gym plans
 */
public interface GymPlanDAO {
	public boolean addGymPlan(GymPlan gymPlan);
	public boolean removeGymPlan(int planId);
	public boolean updateGymPlan(int planId, GymPlan gymPlan);
	
}
