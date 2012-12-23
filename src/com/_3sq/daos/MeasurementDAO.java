/**
 * 
 */
package com._3sq.daos;

import java.util.ArrayList;

import com._3sq.domainobjects.MeasurementInfo;

/**
 * @author Vishal b
 * Use : Common DAO for the MeasurementDetails and BodyComposition
 *
 */

public interface MeasurementDAO {
	public boolean addBodyMeasurement(int memberId, MeasurementInfo combinedMsrInfo);
	public MeasurementInfo getLatestBodyMeasurement(int memberId);
	public ArrayList<MeasurementInfo> getBodyMeasurementHistory(int memberId);
	
	
}
