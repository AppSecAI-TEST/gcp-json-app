package app;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class JsonAdapter {

	public static int PRETTY_PRINT_INDENT_FACTOR = 4;
	public static String TEST_XML_STRING = "<?xml version=\"1.0\" ?><test tradeId=\"TX123456\">PRODUCT:Credit-Default-Swap</test>";

	public static void main(String[] args) {

		System.out.println("Creating a bucket on Google Cloud Storage...");
		if (args.length > 0) {
			createBucket(args[0]);
		} else {
			createBucket("saikat3");
		}

		try {
			JSONObject xmlJSONObj = XML.toJSONObject(TEST_XML_STRING);
			String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
			System.out.println("The converted JSON data is being printed...");
			System.out.println(jsonPrettyPrintString);
		} catch (JSONException je) {
			System.out.println(je.toString());
		}
	}

	public static void createBucket(String bucketName) {
		// Instantiates a client
		Storage storage = StorageOptions.getDefaultInstance().getService();

		// Creates the new bucket
		Bucket bucket = storage.create(BucketInfo.of(bucketName));

		System.out.printf("Bucket %s created.%n", bucket.getName());
	}

}
