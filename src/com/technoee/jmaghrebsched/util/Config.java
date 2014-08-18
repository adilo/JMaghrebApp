package com.technoee.jmaghrebsched.util;

import java.util.ArrayList;
import java.util.List;

import com.technoee.jmaghrebsched.ui.adapter.SimpleSectionedListAdapter;
import com.technoee.jmaghrebsched.ui.adapter.SimpleSectionedListAdapter.Section;

public class Config {

	public static List<Section> sections = new ArrayList<SimpleSectionedListAdapter.Section>();

	public static String[] sponsorsDegree = { "PLATINUM", "GOLD", "BRONZE", "TECHNOLOGY PARTNERS", "MEDIA PARTNERS" };
	public static String[] platinumSponsors = { "http://moroccojug.org/jmaghreb_1/themes/corporate/images/morpho.jpeg", };
	public static String[] goldSponsors = { "http://www.jmaghreb.org/jmag20/images/medit.png?version=1&modificationDate=1340623419230",
			"http://moroccojug.org/jmaghreb_1/themes/corporate/images/Oracle2.gif?version=1&modificationDate=1340623419230", };
	public static String[] bronzeSponsors = { "http://moroccojug.org/jmaghreb_1/themes/corporate/images/logo-objis.png" };
	public static String[] technologyPartners = { "http://www.jmaghreb.org/jmag20/images/ibm.jpeg", "http://www.jmaghreb.org/jmag20/images/github.png",
			"http://www.jmaghreb.org/jmag20/images/pivotal.png", "http://moroccojug.org/jmaghreb_1/themes/corporate/images/ZeroTurnaround_logo.jpeg" };
	public static String[] mediaPartners = { "http://www.jmaghreb.org/jmag20/images/logono.png", "http://www.jmaghreb.org/jmag20/images/logoMedZ.jpg",
			"http://moroccojug.org/jmaghreb_1/themes/corporate/images/logo_tera.png" };
}
