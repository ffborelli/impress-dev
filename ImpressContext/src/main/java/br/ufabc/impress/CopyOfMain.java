package br.ufabc.impress;

import br.ufabc.impress.facade.ContextCountFacade;


public class CopyOfMain {
	public static long startTime;
	public static long finishTime;

	public static void main(String[] args) {

		ContextCountFacade c = new ContextCountFacade();
		c.searchContext();

	}
}
