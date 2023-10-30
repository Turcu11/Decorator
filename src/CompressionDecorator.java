import java.io.*;
import java.util.zip.*;
public class CompressionDecorator implements DataSource {
    private DataSource dataSource;

    public CompressionDecorator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String read(String filePath) {
        String compressedContent = dataSource.read(filePath); // Read the content
        // Decompress the content
        return decompress(compressedContent);
    }

    @Override
    public void write(String filePath, String content) {
        // Compress the content
        String compressedContent = compress(content);
        dataSource.write(filePath, compressedContent); // Write the compressed content
    }

    public String compress(String content) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(bos);
            gzip.write(content.getBytes("UTF-8"));
            gzip.close();
            byte[] compressed = bos.toByteArray();
            bos.close();
            return new String(compressed);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decompress(String content) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(content.getBytes("UTF-8"));
            GZIPInputStream gzip = new GZIPInputStream(bis);
            BufferedReader reader = new BufferedReader(new InputStreamReader(gzip, "UTF-8"));
            StringBuilder decompressedContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                decompressedContent.append(line);
            }
            return decompressedContent.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
