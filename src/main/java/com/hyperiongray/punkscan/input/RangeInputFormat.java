package com.hyperiongray.punkscan.input;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;
import com.hyperiongray.punkscan.password.CharsetFetcher;
import com.hyperiongray.punkscan.utils.PasswordCharsTypesHelper;

public class RangeInputFormat extends InputFormat<Text, Text> {

	@Override
	public List<InputSplit> getSplits(JobContext context) throws IOException, InterruptedException {

		String charsetCode = context.getConfiguration().get("punkcracker.charsetrange.code");
		char[] charset = PasswordCharsTypesHelper.getCharsByCharsTypeOptionCode(charsetCode).toCharArray();

		int minPasswordLength = context.getConfiguration().getInt("punkcracker.password.length.min", 8); // 8
		int maxPasswordLength = context.getConfiguration().getInt("punkcracker.password.length.max", 12); // 12
		Assert.isTrue(minPasswordLength <= maxPasswordLength);

		List<char[]> splitList = this.createSplitList(minPasswordLength, maxPasswordLength, charset);
		List<InputSplit> splits = new ArrayList<InputSplit>();
		for (int i = 0; i < splitList.size()-1; i++) {
			splits.add(new RangeInputSplit(splitList.get(i), splitList.get(i+1)));
		}
		return splits;
	}

	private List<char[]> createSplitList(final int minPasswordLength, final int maxPasswordLength, final char[] charset) {
		CharsetFetcher charsetFetcher;
		List<char[]> splits = Lists.newArrayList();
		for (int passwordLength = minPasswordLength; passwordLength <= maxPasswordLength; passwordLength++) {
			charsetFetcher = new CharsetFetcher(charset, passwordLength);
			while (charsetFetcher.hasNext()) {
				char[] currentChars = charsetFetcher.nextBigStep();
				splits.add(currentChars);
//				System.out.println(currentChars);
			}
		}
		return splits;
	}

	@Override
	public RecordReader<Text, Text> createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException,
			InterruptedException {
		return new RangeRecordReader();
	}

	public static void main(String[] args) {
		String charsetCode = "1111";
		char[] charset = PasswordCharsTypesHelper.getCharsByCharsTypeOptionCode(charsetCode).toCharArray();
		int minPasswordLength = 8;
		int maxPasswordLength = 8;
		RangeInputFormat rangeInputFormat = new RangeInputFormat();

		List<char[]> splitList = rangeInputFormat.createSplitList(minPasswordLength, maxPasswordLength, charset);

		for (char[] cs : splitList) {
			System.out.println(new String(cs));
		}
	}

}