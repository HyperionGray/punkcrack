package com.hyperiongray.punkscan.output;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class CustomFileHandler {

	public static void writeSuccessfulOutput(Text key, Text value, Context context) {
		String utf8 = "UTF-8";
		Path file = getFoundedPasswordPath(context);
		FileSystem fs;
		FSDataOutputStream out;
		char tab = '\t';
		char newline = '\n';

		try {
			fs = file.getFileSystem(context.getConfiguration());
			out = fs.create(file, false);
			out.write(key.toString().getBytes(utf8));
			out.write(tab);
			out.write(value.toString().getBytes(utf8));
			out.write(newline);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static boolean checkIfFileExists(Context context) {

		Path file = getFoundedPasswordPath(context);
		FileSystem fs;
		boolean isExists=true;
		try {
			fs = FileSystem.get(context.getConfiguration());
			isExists = fs.exists(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isExists;
	}

	private static Path getFoundedPasswordPath(Context context) {
		Configuration conf = context.getConfiguration();
		String foundedPasswordFilename = conf.get("punkcracker.password.founded.filename");
		String outputFolder = conf.get("mapred.output.dir");
		Path file = new Path(outputFolder, foundedPasswordFilename);
		return file;
	}
}
