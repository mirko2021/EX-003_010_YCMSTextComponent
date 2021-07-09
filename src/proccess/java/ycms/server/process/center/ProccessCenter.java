package ycms.server.process.center;

import ycms.server.process.controller.ProccessController;

/**
 * Општи апликациони и серверски центар када 
 * је у питању процеди баратања биљешкама за текстове. 
 * @author VM
 * @version 1.0
 */
public final class ProccessCenter {
	public static final ProccessController textApplicationProccessCenter = new  ProccessController();
	private ProccessCenter() {}
}
