package com._3sq.daos;

import com._3sq.domainobjects.GymPlan;

/**
 * @author Vishal B
 * DAO for maintaining list of all gym plans
 */
public interface GymPlanDAO {
	public boolean addGymPlan(GymPlan gymPlan) throws Exception;
	public boolean removeGymPlan(int planId) throws Exception;
	public boolean updateGymPlan(int planId, GymPlan gymPlan) throws Exception;
	public int getNextPlanID() throws Exception;
}
