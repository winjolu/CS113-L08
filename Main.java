public class Main {

    private static final String ORIGINAL_FILE = "original.txt";
    private static final String ENCODED_FILE = "encoded.txt";
    private static final String DECODED_FILE = "decoded.txt";

    public static void main(String[] args) {
        String url = "https://github.com/timburks/gott/blob/master/test/gettysburg-address.txt"; // gettysburg address
        HuffmanTree huffmanTree;
        String originalText, encodedText, decodedText;
        int originalBits, encodedBits, decodedBits;
        double compressionPercentage;

        // Fetch and clean the content from the URL
        try {
            TextFileGenerator.makeCleanFile(url, ORIGINAL_FILE);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Read the cleaned original file
        originalText = readFile(ORIGINAL_FILE);

        // Build the Huffman tree using the content of the original file
        huffmanTree = new HuffmanTree(originalText);

        // Encode and create encoded file
        encodedText = huffmanTree.encode(originalText);
        createFile(ENCODED_FILE, encodedText);

        // Read encoded file
        encodedText = readFile(ENCODED_FILE);

        // Decode encoded file with Huffman tree
        decodedText = huffmanTree.decode(encodedText);

        // Create the decoded file
        createFile(DECODED_FILE, decodedText);

        // Count the number of bits used in each file
        originalBits = originalText.length() * 16; // 16 bits per character
        encodedBits = encodedText.length(); // Each character is either '0' or '1'
        decodedBits = decodedText.length() * 16; // 16 bits per character

        // Calculate percent of compression
        compressionPercentage = ((double) encodedBits / originalBits) * 100;

        // Display Bits and percentage to the console
        System.out.println("Original file bits: " + originalBits);
        System.out.println("Encoded file bits: " + encodedBits);
        System.out.println("Decoded file bits: " + decodedBits);
        System.out.println("Compression percentage: " + compressionPercentage + "%");
    }

    private static void createFile(String fileName, String contents) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}