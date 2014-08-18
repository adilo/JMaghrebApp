package com.technoee.jmaghrebsched.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import android.content.Context;

public abstract class JSONHandler {

	protected static Context mContext;

	public JSONHandler(Context context) {
		mContext = context;
	}

	public static String parseResource(Context context, int resource) throws IOException {
		InputStream is = context.getResources().openRawResource(resource);
		Writer writer = new StringWriter();
		char[] buffer = new char[1024];
		try {
			Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			int n;
			while ((n = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, n);
			}
		} finally {
			is.close();
		}

		return writer.toString();
	}
}
