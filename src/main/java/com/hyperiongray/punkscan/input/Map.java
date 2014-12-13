package com.hyperiongray.punkscan.input;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.hyperiongray.punkscan.output.CustomFileHandler;
import com.hyperiongray.punkscan.utils.EncryptorFactory;
import com.hyperiongray.punkscan.utils.Encryptor;
import com.hyperiongray.punkscan.utils.Encryptor;
import com.hyperiongray.punkscan.utils.HexConverter;

public class Map extends Mapper<Text, Text, Text, Text> {

	private Text word = new Text();
	private Text out = new Text();
	private boolean isFinished;
	private Encryptor encryptor;

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public void setFinished() {
		this.isFinished = true;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void map(Text key, Text value, Context context) throws IOException, InterruptedException {

		if (Arrays.equals(key.getBytes(), encryptor.encode(value.getBytes()))) {
			System.out.println("EUREKA!!!!-->>" + new String(value.toString()));
			context.write(new Text(HexConverter.bytesToHex(key.getBytes())), value);
			this.setFinished();
			CustomFileHandler.writeSuccessfulOutput(new Text(HexConverter.bytesToHex(key.getBytes())), value, context);
		}

	}

	public void run(Context context) throws IOException, InterruptedException {

		//check whatever the results are already there, so now need to keep processing
		// couldn't find a way to stop the Maps earlier :/
		
		String encryptorCode = context.getConfiguration().get("punkcracker.hashing.type");
		
		System.out.println("decrypting with " + encryptorCode);
		this.encryptor = EncryptorFactory.getEncryptor(encryptorCode);
		boolean fileExists = CustomFileHandler.checkIfFileExists(context);
		if (!fileExists) {
			setup(context);
			while (context.nextKeyValue() && !isFinished()) {
				map(context.getCurrentKey(), context.getCurrentValue(), context);
			}
			cleanup(context);
		}
	}

}
