package mycodelearn.undergrowth.lucene.analyze.tika;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年7月22日
 * @version 1.0.0
 */
public class TikaParser {

	private Logger logger = LoggerFactory.getLogger(TikaParser.class);

	public String parseTikaString(String filePath) {
		try {
			logger.info("filePath:"+filePath);
			Tika tika = new Tika();
			String content = tika.parseToString(new File(filePath));
			return content;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TikaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String parseFileToString(String filePath) {
		try {
			logger.info("filePath:"+filePath);
			Parser parser = new AutoDetectParser();
			InputStream inStream = new FileInputStream(filePath);
			ContentHandler contentHandler = new BodyContentHandler();
			Metadata metaData = new Metadata();
			ParseContext parseContext = new ParseContext();
			parser.parse(inStream, contentHandler, metaData, parseContext);
			String content = contentHandler.toString();
			for (String meta : metaData.names()) {
				logger.info(meta + ":" + metaData.get(meta));
			}
			return content;
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TikaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
