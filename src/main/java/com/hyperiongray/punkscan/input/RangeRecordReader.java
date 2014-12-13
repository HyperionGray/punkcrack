package com.hyperiongray.punkscan.input;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import com.hyperiongray.punkscan.password.CharsetIterator;
import com.hyperiongray.punkscan.utils.HexConverter;
import com.hyperiongray.punkscan.utils.PasswordCharsTypesHelper;

public class RangeRecordReader extends RecordReader<Text, Text> {
//	private int counter = 0;
//	private String keyC;
//	private String valueC;
	// LongWritable key = null;
	private Text key = new Text();
	private Text value = new Text();
	private CharsetIterator charsetIterator;
	private char[] passwordGuessLast;
	private float progress=0;
	
	public RangeRecordReader() {
	}

	public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
		String keyC = ((RangeInputSplit) split).keyC;
		String valueC = ((RangeInputSplit) split).valueC;

		String passwordGuessFirst = keyC;
//		String passwordGuessLast = valueC;
		passwordGuessLast=valueC.toCharArray();
		
		String hashHex= context.getConfiguration().get("punkcracker.password.hashes");
		byte[] hash = HexConverter.hexStringToByteArray(hashHex);
			
		key.set(hash);
				
		String charsetRangeCode = context.getConfiguration().get("punkcracker.charsetrange.code");
		String charsByCharsTypeOptionCode = PasswordCharsTypesHelper.getCharsByCharsTypeOptionCode(charsetRangeCode);
		char[] charset =charsByCharsTypeOptionCode.toCharArray();
		
		charsetIterator = new CharsetIterator(charset, passwordGuessFirst.length(),passwordGuessFirst);

//		System.out.println("key:" + keyC + " value:" + valueC);
		System.out.println("starting with batch "+  passwordGuessFirst);
	}

	public void close() throws IOException {
		// NOTHING
	}

	public Text getCurrentKey() {
		return key;
	}

	public Text getCurrentValue() {
		return value;
	}

	public float getProgress() throws IOException {
		// return finishedRows / (float) totalRows;
		return 0.1234f;
	}

	public boolean nextKeyValue() {
		if(charsetIterator.hasNext()){
			char[] next = charsetIterator.next();
			if (Arrays.equals(next, passwordGuessLast)) {
				return false; // el valor 'hasta' no queda incluido 
			}
			value.set(HexConverter.getCharArrayAsByteArray(next)); 
			return true;
		}
		else{
			return false;
		}
	}

}