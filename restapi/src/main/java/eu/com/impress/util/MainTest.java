//package eu.com.impress.util;
//
////import org.eclipse.persistence.internal.oxm.Marshaller;
////import org.eclipse.persistence.jaxb.JAXBContext;
////import org.eclipse.persistence.jaxb.MarshallerProperties;
//
//import com.google.gson.Gson;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Marshaller;
//import javax.xml.bind.PropertyException;
//
//import org.eclipse.persistence.jaxb.MarshallerProperties;
//
//import eu.com.impress.facade.PlaceFacade;
//import eu.com.impress.model.Place;
//
//public class MainTest {
//
//	public static void main(String[] args) {
//		Gson gson = new Gson();
//		Place place = new PlaceFacade().find(1);
//		//System.out.println(gson.toJson(place));
//
//		// Create a JaxBContext
//		JAXBContext jc = null;
//		try {
//			jc = JAXBContext.newInstance(Place.class);
//		} catch (JAXBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		// Create the Marshaller Object using the JaxB Context
//		Marshaller marshaller = null;
//		try {
//			marshaller = jc.createMarshaller();
//		} catch (JAXBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		// Set the Marshaller media type to JSON or XML
//		try {
//			marshaller.setProperty(MarshallerProperties.MEDIA_TYPE,
//					"application/json");
//		} catch (PropertyException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		// Set it to true if you need to include the JSON root element in the
//		// JSON output
//		try {
//			marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
//		} catch (PropertyException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		// Set it to true if you need the JSON output to formatted
//		try {
//			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//		} catch (PropertyException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		// Marshal the employee object to JSON and print the output to console
//		try {
//			marshaller.marshal(place, System.out);
//		} catch (JAXBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//}
