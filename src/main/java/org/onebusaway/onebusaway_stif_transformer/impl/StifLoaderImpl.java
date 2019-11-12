package org.onebusaway.onebusaway_stif_transformer.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.onebusaway.onebusaway_stif_transformer.NonRevenueStopData;
import org.onebusaway.onebusaway_stif_transformer.StifTrip;
import org.onebusaway.onebusaway_stif_transformer.StifTripLoader;
import org.onebusaway.onebusaway_stif_transformer.StifSupport;
import org.onebusaway.onebusaway_stif_transformer.model.GeographyRecord;
import org.onebusaway.onebusaway_stif_transformer.model.ServiceCode;



public class StifLoaderImpl {


	// for unit tests
	private StifTripLoader _loader = null;
	public void setStifTripLoader(StifTripLoader loader) {
		_loader = loader;
	}



	private Boolean _excludeNonRevenue = true;
	public void setExcludeNonRevenue(Boolean excludeNonRevenue) {
		_excludeNonRevenue = excludeNonRevenue;
	}

	public void load(List<File> stifPaths){
		if (_loader == null) {
			// we let the unit tests inject a custom loader
			_loader = new StifTripLoader();
			_loader.setExcludeNonRevenue(_excludeNonRevenue);

			for (File path : stifPaths) {
				loadStif(path, _loader);
			}

		}
	}

	public void loadStif(File path, StifTripLoader loader) {
		// Exclude files and directories like .svn
		if (path.getName().startsWith("."))
			return;

		if (path.isDirectory()) {
			for (String filename : path.list()) {
				File contained = new File(path, filename);
				loadStif(contained, loader);
			}
		} else {
			loader.run(path);
		}
	}

	public Map<ServiceCode, List<StifTrip>> getRawStifData(){
		return _loader.getRawStifData();
	}

	public StifSupport getSupport(){
		return _loader.getSupport();
	}



	public int getTripsCount(){
		return _loader.getTripsCount();
	}

	public static void main(){
		System.out.println("Demo!");
	}

}


