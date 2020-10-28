/**
 * Copyright (C) 2020 Metropolitan Transportation Authority
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.onebusaway_stif_transformer_impl.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.FileUtils;
import org.onebusaway.onebusaway_stif_transformer_impl.StifFileLoader;
import org.onebusaway.onebusaway_stif_transformer_impl.StifSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StifLoaderImpl {


	private static Logger _log = LoggerFactory.getLogger(StifLoaderImpl.class);

	private Boolean _excludeNonRevenue = true;
	public void setExcludeNonRevenue(Boolean excludeNonRevenue) {
		_excludeNonRevenue = excludeNonRevenue;
	}

	public void load(List<File> stifPaths, HashMap<String, StifSupport> supportsByDirectory){
		File workingDir = new File("tmp"+System.currentTimeMillis());
		workingDir.mkdir();

		for (File path : stifPaths) {
			StifFileLoader stifFileLoader = new StifFileLoader();
			loadStif(path, stifFileLoader, workingDir, supportsByDirectory);
		}
		try {
			FileUtils.deleteDirectory(workingDir);
		}
		catch (java.io.IOException exception){
			_log.error("Did not delete working directory: ", exception);
		}

	}

	public void loadStif(File path, StifFileLoader loader,File workingDir, HashMap<String, StifSupport> supportsByDirectory) {


		if (path.getName().startsWith("."))
			return;

		if (path.isDirectory()) {
			StifFileLoader stifFileLoader = new StifFileLoader();
			for (String filename : path.list()) {
				File contained = new File(path, filename);
				loadStif(contained, stifFileLoader, workingDir, supportsByDirectory);
			}
		}

		else if (path.getName().endsWith(".zip")){
			try {
				ZipFile zipFile = new ZipFile(path);
				File currentStifDir = new File (workingDir.getAbsolutePath()+"/"+path.getName());
				zipFile.extractAll(currentStifDir.getAbsolutePath());
				path = workingDir;
				StifFileLoader stifFileLoader = new StifFileLoader();
				loadStif(path, stifFileLoader, workingDir, supportsByDirectory);
			} catch (ZipException e) {
				e.printStackTrace();
			}
		}

		 else {
			StifFileLoader stifFileLoader = new StifFileLoader();
			stifFileLoader.run(path);
			supportsByDirectory.put(path.getAbsolutePath(), stifFileLoader.getSupport());
		}
	}



}


