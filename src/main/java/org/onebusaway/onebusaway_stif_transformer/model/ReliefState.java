package org.onebusaway.onebusaway_stif_transformer.model;

/** 
 * For a trip-run pair, the possible relief cases
 * @author novalis
 *
 */
public enum ReliefState {
	NO_RELIEF, //the trip is on one run all the way through
	BEFORE_RELIEF, //this is the part of the trip before relief
	AFTER_RELIEF //this is the part of the trip after relief
}
