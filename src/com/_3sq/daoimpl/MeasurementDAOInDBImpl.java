/**
 * 
 */
package com._3sq.daoimpl;

import java.util.ArrayList;

import com._3sq.daos.MeasurementDAO;
import com._3sq.domainobjects.MeasurementInfo;

/**
 * @author shani
 *
 */
public class MeasurementDAOInDBImpl implements MeasurementDAO {

	/* (non-Javadoc)
	 * @see com._3sq.daos.MeasurementDAO#addBodyMeasurement(int, com._3sq.domainobjects.MeasurementInfo)
	 */
	@Override
	public boolean addBodyMeasurement(int memberId,
			MeasurementInfo combinedMsrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com._3sq.daos.MeasurementDAO#getLatestBodyMeasurement(int)
	 */
	@Override
	public MeasurementInfo getLatestBodyMeasurement(int memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com._3sq.daos.MeasurementDAO#getBodyMeasurementHistory(int)
	 */
	@Override
	public ArrayList<MeasurementInfo> getBodyMeasurementHistory(int memberId) {
		// TODO Auto-generated method stub
		return null;
	}

}
