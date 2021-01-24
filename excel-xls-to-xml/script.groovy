import com.sap.gateway.ip.core.customdev.util.Message;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.ByteArrayInputStream
import java.io.InputStream
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;




def Message processData(Message message) {
    
    Map<String, String> headerToFieldMap = new HashMap<String, String>() {
		{
			put("Date", "Date");
			put("Order Number", "OrderNumber");
			put("Order Item Number", "OrderItemNumber");
			put("Order Detail", "OrderDetail");

		}
	};

	Map<Integer, String> indexToFieldMap = new HashMap<Integer, String>();

	String NSURL = "https://pizug.com";
	
	
		// Base64 Body
		def body = message.getBody(String.class);
		byte[] data = java.util.Base64.getDecoder().decode(body);
		Workbook workbook = new HSSFWorkbook( new ByteArrayInputStream(data) );
     
		// Binary Body
		//def body = message.getBody(InputStream.class);
		//Workbook workbook = new HSSFWorkbook( body);

		Document doc = newDocument();
		
		Element rootEl = doc.createElementNS(NSURL, "ns1:MT_3RD_CPI_ORDER" );  //" syntax highlighting bug
		doc.appendChild(rootEl);

		

		String sheetName = "Order";

		// workbook.getSheetAt(1);
		Sheet sheet = workbook.getSheet(sheetName);

		for (Row row : sheet) {

			// System.out.println(row.getRowNum());
			// if(row.getRowNum()==0) {
			// continue;
			// }

			// ADD A NEW ROW
			Element itemEl = doc.createElementNS("", "order");

			for (Cell cell : row) {
				String cellValue = null;
				switch (cell.getCellTypeEnum()) {
				case CellType.STRING:
					// System.out.println(cell.getStringCellValue());
					cellValue = cell.getStringCellValue();
					break;
				case CellType.NUMERIC:
					// System.out.println(cell.getNumericCellValue());
					cellValue = String.valueOf(cell.getNumericCellValue());
					break;
				// case BOOLEAN: ... break;
				// case FORMULA: ... break;
				default:
					System.out.println("CELL TYPE NOT USED!");
				}

				if (cellValue != null) {
					// use the first row for header -> fieldname mapping
					if (row.getRowNum() == 0) {
						indexToFieldMap.put(cell.getColumnIndex(),
								headerToFieldMap.get(cellValue));
						continue;
					}
					// add item if it is not header.
					rootEl.appendChild(itemEl);
					// System.out.println(cellValue);
					Element fieldEl = doc.createElementNS("", indexToFieldMap.get(cell.getColumnIndex()));
					itemEl.appendChild(fieldEl);
					fieldEl.setTextContent(cellValue);
				}
			}
		}

        message.setBody(doc)

       return message;
}

	public static Document newDocument() throws ParserConfigurationException {
		DocumentBuilderFactory factory;
		DocumentBuilder builder;
		try {
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			
			return builder.newDocument();

		} finally {
			builder = null;
			factory = null;
		}
	}
