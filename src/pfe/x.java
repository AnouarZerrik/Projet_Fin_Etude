package pfe;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;
import java.io.File;

public class x {
	
	static void xml () throws ParserConfigurationException, TransformerException{
		

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();

		Element rootElement = doc.createElement("root");
		doc.appendChild(rootElement);

		Element childElement = doc.createElement("child");
		rootElement.appendChild(childElement);

		Attr attr = doc.createAttribute("attributeName");
		attr.setValue("attributeValue");
		childElement.setAttributeNode(attr);

		childElement.appendChild(doc.createTextNode("childElementValue"));

		File xmlFile = new File("myFile1.xml");
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(xmlFile);
		transformer.transform(source, result);

	}
	

	public static void main(String[] args) throws ParserConfigurationException, TransformerException {
		// TODO Auto-generated method stub
		//xml();
	}

}
