package com.hyperiongray.punkscan;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.OutputCommitter;
import org.apache.hadoop.mapreduce.OutputFormat;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class MRexampleSkeleton extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		main3(args);
		return 0;
	}

	public static void main3(String[] args) throws IOException {

		Configuration conf = new Configuration();
		Job job = new Job(conf);
		job.setJobName("PunkCracker");
		job.setInputFormatClass(RangeInputFormat.class);

		job.setMapperClass(Map.class);
		// conf.setCombinerClass(Reduce.class);
		job.setReducerClass(Reduce.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		job.setOutputFormatClass(RangeOutputFormat.class);

		int i = 0;
		String sessionId = args[i++];
//		FileInputFormat.setInputPaths(conf, new Path(args[i++]));
//		FileOutputFormat.setOutputPath(conf, new Path(args[i++]));
//		
		i++;
		i++;
		String charsetCode = args[i++];
		Integer passwordLengthMin = Integer.parseInt(args[i++]);
		Integer passwordLengthMax = Integer.parseInt(args[i++]);
		Integer iterationsPerTask = Integer.parseInt(args[i++]);
		String hashingAlgorithm = args[i++];
		String hashingSalt = args[i++];
		String hashes = args[i++];

		// BigDecimal totalCombinations = new
		// BigDecimal(Math.pow(charset.length(), passwordLengthMax));
		// BigDecimal totalCombinationsExcluded = new
		// BigDecimal(Math.pow(charset.length(), passwordLengthMin));

		conf.set("punkcracker.sessionId", sessionId);
		conf.set("punkcracker.charsetrange.code", charsetCode);
		conf.setInt("punkcracker.password.length.min", passwordLengthMin); // 8
		conf.setInt("punkcracker.password.length.max", passwordLengthMax); // 12
		conf.set("punkcracker.hashing.type", hashingAlgorithm);
		conf.set("punkcracker.hashing.salt", hashingSalt);

		conf.setInt("punkcracker.iterationspertask", iterationsPerTask); // 1000000
		// TODO --> this may be passed as other file!
		conf.set("punkcracker.password.hashes", hashes); // "blah1","bla2"

	
		try {
			job.submit();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		JobClient.runJob(conf);

	}

	public static class Map extends Mapper<LongWritable, Text, Text, IntWritable> {

	}

	public static class Reduce extends Reducer<LongWritable, Text, Text, IntWritable> {

	}

	public static class RangeInputFormat extends InputFormat<LongWritable, Text> {

		class RangeInputSplit extends InputSplit implements Writable {

			@Override
			public void write(DataOutput out) throws IOException {
				// TODO Auto-generated method stub

			}

			@Override
			public void readFields(DataInput in) throws IOException {
				// TODO Auto-generated method stub

			}

			@Override
			public long getLength() throws IOException, InterruptedException {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String[] getLocations() throws IOException, InterruptedException {
				// TODO Auto-generated method stub
				return null;
			}
		}

		@Override
		public List<InputSplit> getSplits(JobContext context) throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public RecordReader<LongWritable, Text> createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException,
				InterruptedException {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public static class RangeOutputFormat extends OutputFormat<LongWritable, Text> {

		@Override
		public RecordWriter<LongWritable, Text> getRecordWriter(TaskAttemptContext context) throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void checkOutputSpecs(JobContext context) throws IOException, InterruptedException {
			// TODO Auto-generated method stub

		}

		@Override
		public OutputCommitter getOutputCommitter(TaskAttemptContext context) throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public static void main(String[] args) throws Exception {

		// * ***** Convert Hex to ASCII *****
		// Hex : 70617373776f7264313233
		// Decimal : 11297115115119111114100495051
		// ASCII : password123

		String dir = "/home/tomas/Proyecto/PunkCrack/workspace/punkscan/";
		String input = dir + "hadoop/input";
		String output = dir + "hadoop/output";
		FileUtils.deleteDirectory(new File(output));
		args = new String[] { "sesssion1", input, output, "1111", "8", "12", "10000000", "md5", "salt", "482C811DA5D5B4BC6D497FFA98491E38",
				"hash2", "hash3" };

		// ToolRunner.run(new MapReduceExample(), args);
		// main2(args);

		int res = ToolRunner.run(new Configuration(), new MRexampleSkeleton(), args);
		System.exit(res);
	}
}
