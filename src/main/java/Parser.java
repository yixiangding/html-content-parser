import java.io.*;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

public class Parser {
    private String htmlFolderPath = "/";
    private int totalNumFiles = 0;

    public Parser(String path) {
        htmlFolderPath = path;
    }

    public void parseAll() throws IOException, TikaException, SAXException {
        File folder = new File(htmlFolderPath);
        File[] files = folder.listFiles();
        totalNumFiles = files.length;
        int fileCounter = 0;
        for (File file : files) {
            parseContent(file);
            System.out.println(String.format("Parsed: %d, Total: %d", ++fileCounter, totalNumFiles));
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
        BufferedWriter writer = new BufferedWriter(new FileWriter("big.txt", true));
        String rawContent = handler.toString();
        rawContent = rawContent.replaceAll("\\s+", " ");
        writer.write(rawContent);
        writer.flush();
        writer.close();
    }
}
