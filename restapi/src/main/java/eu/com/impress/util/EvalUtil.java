package eu.com.impress.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.jboss.resteasy.mock.MockDispatcherFactory;

import eu.com.impress.model.MonitorPerformance;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

public class EvalUtil {

	public static String setTime(String point, String message, String d1,
			String d2) {

		String[] vector = message.split(d1);

		for (int i = 0; i < vector.length; i++) {

			String[] tmp = vector[i].split(d2);

			if (tmp[0].equalsIgnoreCase(point)) {
				tmp[1] = String.valueOf(System.currentTimeMillis());
				vector[i] = tmp[0] + "=" + tmp[1];
				break;
			}
		}
		String ret = "";
		for (int i = 0; i < vector.length; i++) {
			if (i < vector.length - 1) {
				ret += vector[i] + ";";
			} else {
				ret += vector[i];
			}
		}

		return ret;

	}

	public static String getParameter(String param, String message, String d1,
			String d2) {

		String[] vector = message.split(d1);

		String ret = null;

		for (int i = 0; i < vector.length; i++) {

			String[] tmp = vector[i].split(d2);

			if (tmp[0].equalsIgnoreCase(param)) {
				ret = tmp[1];
				break;
			}
		}

		return ret;

	}

	public static MonitorPerformance getMonitorPerformance() {

		MonitorPerformance mp = new MonitorPerformance();

		SystemInfo si = new SystemInfo();
		// OperatingSystem os = si.getOperatingSystem();
		// System.out.println(os);

		HardwareAbstractionLayer hal = si.getHardware();
		// double usage_processor = hal.getProcessor().getSystemCpuLoad();
		mp.setProcessorUsage(hal.getProcessor().getSystemCpuLoad());
		mp.setMemoryAvaliable(hal.getMemory().getAvailable());
		mp.setMemoryTotal(hal.getMemory().getTotal());

		// double memory_avaliable = hal.getMemory().getAvailable();
		// double memory_total = hal.getMemory().getTotal();

		HWDiskStore disk[] = hal.getDiskStores();
		mp.setDiskReads(disk[0].getReads());
		mp.setDiskWrites(disk[0].getWrites());
		// for (int i = 0; i < disk.length; i++){
		// System.out.println(disk[i].getName());
		// System.out.println(disk[i].getWrites());
		// System.out.println(disk[i].getReads());
		// }

		// System.out.println(usage_processor);
		// System.out.println(memory_avaliable);
		// System.out.println(memory_total);
		return mp;

	}

	public static long getDateDiff(Timestamp t1, Timestamp t2) {

		long diff = 0;

		if (t2.getTime() > t1.getTime()) {

			// Eval Diff
			diff = t2.getTime() - t1.getTime();
		}

		return diff;
	}

}
