package com.hyperiongray.punkscan;

import java.util.Calendar;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.springframework.util.Assert;

import com.hyperiongray.punkscan.input.Map;
import com.hyperiongray.punkscan.input.RangeInputFormat;
import com.hyperiongray.punkscan.output.RangeOutputFormat;

public class MRexample extends Configured implements Tool {

	private static final String FOUNDED_PASSWORD ="results.pwd";
	@Override
	public int run(String[] args) throws Exception {

		String output = args[0];// dir + "/output";
		String charsetCode = args[1];
		Integer passwordLengthMin = Integer.parseInt(args[2]);
		Integer passwordLengthMax = Integer.parseInt(args[3]);
		String hashingAlgorithm = args[4];
		String hashingSalt = args[5];
		String hashes = args[6];

		Configuration conf = new Configuration();
		conf.setBoolean("mapred.mapper.new-api", true);
		conf.set("punkcracker.charsetrange.code", charsetCode);
		conf.setInt("punkcracker.password.length.min", passwordLengthMin); // 8
		conf.setInt("punkcracker.password.length.max", passwordLengthMax); // 12
		conf.set("punkcracker.hashing.type", hashingAlgorithm);
		conf.set("punkcracker.hashing.salt", hashingSalt);
		conf.set("mapred.output.dir",output);
		conf.set("punkcracker.password.founded.filename", FOUNDED_PASSWORD);
		// TODO --> this may be passed as other file!
		conf.set("punkcracker.password.hashes", hashes); // "blah1","bla2"

		Job job = new Job(conf);
		job.setJobName("PunkCracker");
		job.setInputFormatClass(RangeInputFormat.class);
		job.setMapperClass(Map.class);
		// conf.setCombinerClass(Reduce.class);
		// job.setReducerClass(Reduce.class);
		job.setNumReduceTasks(0);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
//		job.setOutputFormatClass(TextOutputFormat.class);
		job.setOutputFormatClass(RangeOutputFormat.class);
		
		
		
		job.setJarByClass(MRexample.class);

		FileOutputFormat.setOutputPath(job, new Path(output));
		System.out.println("submitting");
		job.submit();
		System.out.println("waiting");
		job.waitForCompletion(true);
		System.out.println("done");
		return 0;
	}

	private static void printUsage() {
		StringBuilder sb = new StringBuilder();

		sb.append("USAGE:\n");
		sb.append("-basedir <basedir> ");
		sb.append("-charsetcode <charsetcode> ");
		sb.append("-minpasswordlenght minpasswordlenght ");
		sb.append("-maxpasswordlenght maxpasswordlenght ");
		sb.append("-encryptiontype encryptiontype ");
		sb.append("-salt salt ");
		sb.append("-hash hash ");
		
		sb.append("\n");
		sb.append("EXAMPLE:\n");
		sb.append("-basedir /home/tomas/Proyecto/PunkCrack/workspace/punkcrack/hadoop/ ");
		sb.append("-charsetcode 1000 ");
		sb.append("-minpasswordlenght 8 ");
		sb.append("-maxpasswordlenght 8 ");
		sb.append("-encryptiontype md5 ");
//		sb.append("-salt ");
		sb.append("-hash 5F4DCC3B5AA765D61D8327DEB882CF99");
		
		System.out.println(sb.toString());
	}

	public static void main(String[] args) throws Exception {

		String basedir = "";
		String charsetcode = "";
		String minPasswordLenght = "";
		String maxPasswordLenght = "";
		String encryptiontype = "";
		String salt = "";
		String hash = "";

		System.out.println("Starting job for:" +  args.toString());
		try {

			for (int i = 0; i < args.length; i++) {
				if ("-basedir".equals(args[i])) {
					basedir = args[i + 1];
					i++;
				} else if ("-charsetcode".equals(args[i])) {
					charsetcode = args[i + 1];
					i++;
				} else if ("-minpasswordlenght".equals(args[i])) {
					minPasswordLenght = args[i + 1];
					i++;
				} else if ("-maxpasswordlenght".equals(args[i])) {
					maxPasswordLenght = args[i + 1];
					i++;
				} else if ("-encryptiontype".equals(args[i])) {
					encryptiontype = args[i + 1];
					i++;
				} else if ("-salt".equals(args[i])) {
					salt = args[i + 1];
					i++;
				} else if ("-hash".equals(args[i])) {
					hash = args[i + 1];
					i++;
				} else {
					System.out.println(args[i] + "is not defined");
				}
			}

			Assert.hasText(basedir);
			Assert.hasText(charsetcode);
			Assert.hasText(minPasswordLenght);
			Assert.hasText(maxPasswordLenght);
			Assert.hasText(encryptiontype);
//			Assert.hasText(salt);
			Assert.hasText(hash);
		} catch (Exception e) {
			printUsage();
			System.out.println(e);
			return;
		}
		long timeInMillis = Calendar.getInstance().getTimeInMillis();
		String output = basedir + "output_" + timeInMillis;
		args = new String[] { output, charsetcode, minPasswordLenght, maxPasswordLenght, encryptiontype, salt, hash };

		Configuration conf = new Configuration();
		int res = ToolRunner.run(new MRexample(), args);
		System.exit(res);
	}
}
