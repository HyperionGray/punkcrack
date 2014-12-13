package com.hyperiongray.punkscan.input;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableUtils;
import org.apache.hadoop.mapreduce.InputSplit;

public class RangeInputSplit extends InputSplit implements Writable {

	public String keyC;
	public String valueC;

	public RangeInputSplit() {
	}
	
	public RangeInputSplit(char[] keyC, char[] valueC) {
		this.keyC= new String(keyC);
		this.valueC = new String(valueC);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		WritableUtils.writeString(out, keyC);
		WritableUtils.writeString(out, valueC);
		
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		keyC = WritableUtils.readString(in);
		valueC = WritableUtils.readString(in);
	}

	@Override
	public long getLength() throws IOException, InterruptedException {
		return 0;
	}

	@Override
	public String[] getLocations() throws IOException, InterruptedException {
		return new String[] {};
	}
}