package uo.ri.cws.application.service.util.sql;

//import java.io.IOException;
//import java.io.InputStream;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
//import uo.ri.cws.application.persistence.util.Conf;

public class PersistenceXmlScanner {

	public static ConnectionData scan() {
		ConnectionData res = new ConnectionData();
		res.driver = "org.hsqldb.jdbcDriver";
		res.url = "jdbc:hsqldb:hsql://localhost";//"jdbc:hsqldb:mem:test";
		res.user = "sa";
		res.pass = "";
		return res;
//		Document document = loadPersistenceXml();
//		NodeList properties = locatePropertiesIn( document );
//		return extractJdbcConnectionDataFrom(properties);
	}

//	private static NodeList locatePropertiesIn(Document document) {
//		Element root = document.getDocumentElement();
//		Element persistenceUnit = (Element) root
//				.getElementsByTagName("persistence-unit")
//				.item(0);
//		Element properties = (Element) persistenceUnit
//				.getElementsByTagName("properties")
//				.item(0);
//		return properties.getElementsByTagName("property");
//	}
//
//	private static Document loadPersistenceXml() {
//		try {
//
//			return noisyLoad();
//
//		} catch (ParserConfigurationException | SAXException | IOException e) {
//			throw new RuntimeException( e );
//		}
//	}
//
//	private static Document noisyLoad()
//			throws ParserConfigurationException, SAXException, IOException {
//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder builder = factory.newDocumentBuilder();
//
//		try (InputStream in = PersistenceXmlScanner.class.
//				getClassLoader()
//				.getResourceAsStream("META-INF/persistence.xml")) {
//
//			Document document = builder.parse(in);
//			document.getDocumentElement().normalize();
//			return document;
//		}
//	}
//
//	private static ConnectionData extractJdbcConnectionDataFrom(
//			NodeList properties) {
//		ConnectionData res = new ConnectionData();
//
//		for(int i = 0; i < properties.getLength(); i++) {
//			Element property = (Element) properties.item( i );
//			String name = property.getAttribute("name");
//			String value = property.getAttribute("value");
//
//			if ( "javax.persistence.jdbc.url".equals( name )) {
//				res.url = value;
//			} else if ( "javax.persistence.jdbc.driver".equals( name )) {
//				res.driver = value;
//			} else if ( "javax.persistence.jdbc.user".equals( name )) {
//				res.user = value;
//			} else if ( "javax.persistence.jdbc.password".equals( name )) {
//				res.pass = value;
//			}
//		}
//		return res;
//	}

}
