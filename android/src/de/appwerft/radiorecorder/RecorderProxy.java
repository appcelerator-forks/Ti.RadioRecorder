/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package de.appwerft.radiorecorder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.util.Log;
import org.appcelerator.titanium.util.TiConfig;

import android.os.AsyncTask;

// This proxy can be created by calling Radiorecorder.createExample({message: "hello world"})
@Kroll.proxy(creatableInModule = RadiorecorderModule.class)
public class RecorderProxy extends KrollProxy {
	// Standard Debugging variables
	private static final String LCAT = "ExampleProxy";
	private static final boolean DBG = TiConfig.LOGD;
	private String url;
	private String file;
	private int duration;

	// Constructor
	public RecorderProxy() {
		super();
	}

	// Handle creation options
	@Override
	public void handleCreationDict(KrollDict options) {
		super.handleCreationDict(options);

	}

	@Kroll.method
	public void start() {
		AsyncTask<Void, Void, Void> doRequest = new RecordHandler();
		doRequest.execute();

	}

	private final class RecordHandler extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... empty) {
			try {
				URLConnection conn = new URL(url).openConnection();
				InputStream is = conn.getInputStream();
				OutputStream outstream = new FileOutputStream(new File(file));
				byte[] buffer = new byte[4096];
				int len;
				long t = System.currentTimeMillis();
				while ((len = is.read(buffer)) > 0
						&& System.currentTimeMillis() - t <= duration) {
					outstream.write(buffer, 0, len);
				}
				outstream.close();
			} catch (Exception e) {
				System.out.print(e);
			}
			return null;
		}

		protected void onPostExecute() {
		}
	}

}