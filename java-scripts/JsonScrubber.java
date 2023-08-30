import java.io.*;
import java.util.*;

public class JsonScrubber {
    public static void main(String[] args) {
        String inputFilename = "data/" + System.getenv("INPUT_FILE");
        String outputFilename = "data/" + System.getenv("OUTPUT_FILE");
        String keepIfMatchedStr = System.getenv("KEEP_IF_MATCHED");
        List<String> keepIfMatched = new ArrayList<>();

        if (keepIfMatchedStr != null) {
            keepIfMatched = Arrays.asList(keepIfMatchedStr.split(","));
        }

        try {
            // Read data from the input file
            BufferedReader br = new BufferedReader(new FileReader(inputFilename));
            StringBuilder dataBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                dataBuilder.append(line);
            }
            br.close();

            // Parse JSON manually
            String dataString = dataBuilder.toString();
            int sourceIndex = dataString.indexOf("\"_source\"");
            int endIndex = dataString.lastIndexOf("}");
            String jsonData = dataString.substring(sourceIndex, endIndex + 1);

            // Process the data
            String metadataString = jsonData.split("\"metadata\":")[1];
            int metadataEndIndex = metadataString.indexOf("]");
            String metadataJson = metadataString.substring(0, metadataEndIndex + 1);
            metadataJson = metadataJson.replace("},", "}}");

            String[] metadataItems = metadataJson.split("\\},");
            StringBuilder newMetadataJson = new StringBuilder("[");
            boolean firstItem = true;
            for (String item : metadataItems) {
                if (!item.contains("\"name\"")) {
                    continue;
                }
                if (!firstItem) {
                    newMetadataJson.append(",");
                }
                newMetadataJson.append(item);
                firstItem = false;
            }
            newMetadataJson.append("]");

            String modifiedJson = jsonData.replace(metadataJson, newMetadataJson.toString());

            // Export the modified data to an output file
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilename));
            bw.write(modifiedJson);
            bw.close();

            System.out.println("Modified data exported to " + outputFilename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
