package org.onebusaway.onebusaway_stif_transformer.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.FileUtils;
import org.onebusaway.onebusaway_stif_transformer.NonRevenueStopData;
import org.onebusaway.onebusaway_stif_transformer.StifTrip;
import org.onebusaway.onebusaway_stif_transformer.StifFileLoader;
import org.onebusaway.onebusaway_stif_transformer.StifSupport;
import org.onebusaway.onebusaway_stif_transformer.model.GeographyRecord;
import org.onebusaway.onebusaway_stif_transformer.model.ServiceCode;



public class StifLoaderImpl {




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
			supportsByDirectory.put(path.getAbsolutePath(), stifFileLoader.getSupport());
		}
		try {
			FileUtils.deleteDirectory(workingDir);
		}
		catch (java.io.IOException exception){
			System.out.print("Did not delete working directory: " + exception.toString());
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
			supportsByDirectory.put(path.getAbsolutePath(), stifFileLoader.getSupport());
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
			loader.run(path);
		}
	}
	public static void main(){

	}

}


