import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import java.io.IOException;

public class Main {
    public static void main(String args[]) throws TikaException, IOException, SAXException {
        Parser parser = new Parser("/Users/yixiangding/Sites/csci572_solr/FOX_News/HTML_files");
        parser.parseAll();
    }
}
