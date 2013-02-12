package com._3sq.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import com._3sq.datatransporter.LightWeightMember;

public class RendererClasses {

	public static ListitemRenderer<LightWeightMember>   listItemRendererForListBox = new ListitemRenderer<LightWeightMember>(){
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		@Override
		public void render(Listitem item, LightWeightMember data, int index)
				throws Exception {

			item.appendChild(new Listcell("" + data.getMemberId()));
			item.appendChild(new Listcell(data.getMemberName()));
			Date testDate = data.getDateOfBirth();

			if(testDate!=null)
				item.appendChild(new Listcell(""+df.format(testDate)));
			else
				item.appendChild(new Listcell("NA"));
		}
	};
	
}
