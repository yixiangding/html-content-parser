import java.io.*;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

public class Parser {
    private String htmlFolderPath = "/";

    public Parser(String path) {
        htmlFolderPath = path;
    }

    public void parseAll() throws IOException, TikaException, SAXException {
        File folder = new File(htmlFolderPath);
        for (File file : folder.listFiles()) {
            parseContent(file);
        }
    }

    private void parseContent(File htmlFile) throws IOException, TikaException, SAXException {
        //detecting the file type
        BodyContentHandler handler = new BodyContentHandler(-1);
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(htmlFile);
        ParseContext pcontext = new ParseContext();

        //Html parser
        HtmlParser htmlparser = new HtmlParser();
        htmlparser.parse(inputstream, handler, metadata, pcontext);
        System.out.println("Contents of the document:" + handler.toString());
        BufferedWriter writer = new BufferedWriter(new FileWriter("big.txt"));
        writer.write(handler.toString().trim());
        writer.flush();
        writer.close();
    }
}
