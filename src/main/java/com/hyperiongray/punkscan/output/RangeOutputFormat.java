package com.hyperiongray.punkscan.output;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.OutputCommitter;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class RangeOutputFormat<K, V> extends TextOutputFormat<K, V> {

	/** Output committer */
    protected FileOutputCommitter committer;

    /** Flag to track whether anything was output */
    protected boolean outputWritten = false;

    
	@Override
	public RecordWriter<K, V> getRecordWriter(TaskAttemptContext job) throws IOException, InterruptedException {

		final RecordWriter<K, V> recWriter = super.getRecordWriter(job);
        
        // wrap writer to track that something was output
        return new RecordWriter<K, V>() {
            @Override
            public void write(K key, V value) throws IOException,
                    InterruptedException {
//                outputWritten = true;
              outputWritten = false; //--> set to false to avoid saving results!
                recWriter.write(key, value);
//                RangeOutputFormat.getFoundedPasswordPath(job);
//                job.getConfiguration().g
            }
 
            @Override
            public void close(TaskAttemptContext context) throws IOException,
                    InterruptedException {
                recWriter.close(context);
            }
        };
	}

    /**
     * Implementation copied from the parent but amended to override the
     * {@link OutputCommitter#needsTaskCommit(TaskAttemptContext)} method
     */
    @Override
    public synchronized OutputCommitter getOutputCommitter(
            TaskAttemptContext context) throws IOException {
        // annoyingly committer is private in the parent class
        if (committer == null) {
 
           	Path output = getOutputPath(context);
            committer = new FileOutputCommitter(output, context) {
                @Override
                public boolean needsTaskCommit(TaskAttemptContext context)
                        throws IOException {
                    return outputWritten && super.needsTaskCommit(context);
                }
            };
        }
        return committer;
    }

	public static Path getFoundedPasswordPath(TaskAttemptContext context){
		Configuration conf = context.getConfiguration();
		String foundedPasswordFilename = conf.get("punkcracker.password.founded.filename");
		String outputFolder = conf.get("mapred.output.dir");
		Path file = new Path(outputFolder,foundedPasswordFilename);
		return file;
	}

}