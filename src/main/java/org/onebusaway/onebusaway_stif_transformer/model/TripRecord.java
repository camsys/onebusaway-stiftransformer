/**
 * Copyright (c) 2011 Metropolitan Transportation Authority
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.onebusaway.onebusaway_stif_transformer.model;

public class TripRecord implements StifRecord {



	private String recordType;
	private String originLocation;
	private int originTime;
	private String direction;
	private int tripType;
	private String destinationLocation;
	private int destinationTime;
	private String pickCode;
	private String primaryRunNumber;
	private String pathCode;
	private String primaryRunRoute;
	private String midtripReliefRunNumber;
	private String midtripReliefRunRoute;
	private int midtripReliefTime;
	private String midtripReliefLocation;
	private String busTypeCode;
	private String signCode;
	private String firsttripinSequence;
	private String lastTripinSequence;
	private String primaryReliefStatus;
	private String nextOperatorRunNumber;
	private String nextOperatorRoute;
	private String tripMileage;
	private String depotCode;
	private String blockNumber;
	private String nextTripOperatorRunNumber;
	private String nextTripOperatorRoute;
	private String nextTripOriginTime;
	private int recoveryTimeafterThisTrip;
	private String signCodeRouteforThisTrip;
	private String previousTripOperatorRunNumber;
	private String previousTripOperatorRoute;
	private int previousTripOriginTime;
	private String originLocationBoxID;
	private String destinationLocationBoxID;
	private String reliefLocationBoxID;
	private String midtripReliefDepot;
	private String nextOperatorDepot;
	private String nextTripOperatorDepot;
	private String previousTripOperatorDepot;
	private String gTFSTripID;



	public void setRecordType (String recordType){
		this.recordType = recordType;
	}
	public void setOriginLocation (String originLocation){
		this.originLocation = originLocation;
	}
	public void setOriginTime (int originTime){
		this.originTime = originTime;
	}
	public void setDirection (String direction){
		this.direction = direction;
	}
	public void setTripType (int tripType){
		this.tripType = tripType;
	}
	public void setDestinationLocation (String destinationLocation){
		this.destinationLocation = destinationLocation;
	}
	public void setDestinationTime (int destinationTime){
		this.destinationTime = destinationTime;
	}
	public void setPickCode (String pickCode){
		this.pickCode = pickCode;
	}
	public void setPrimaryRunNumber (String primaryRunNumber){
		this.primaryRunNumber = primaryRunNumber;
	}
	public void setPathCode (String pathCode){
		this.pathCode = pathCode;
	}
	public void setPrimaryRunRoute (String primaryRunRoute){
		this.primaryRunRoute = primaryRunRoute;
	}
	public void setMidtripReliefRunNumber (String midtripReliefRunNumber){
		this.midtripReliefRunNumber = midtripReliefRunNumber;
	}
	public void setMidtripReliefRunRoute (String midtripReliefRunRoute){
		this.midtripReliefRunRoute = midtripReliefRunRoute;
	}
	public void setMidtripReliefTime (int midtripReliefTime){
		this.midtripReliefTime = midtripReliefTime;
	}
	public void setMidtripReliefLocation (String midtripReliefLocation){
		this.midtripReliefLocation = midtripReliefLocation;
	}
	public void setBusTypeCode (String busTypeCode){
		this.busTypeCode = busTypeCode;
	}
	public void setSignCode (String signCode){
		this.signCode = signCode;
	}
	public void setFirstTripInSequence (String firsttripinSequence){
		this.firsttripinSequence = firsttripinSequence;
	}
	public void setLastTripInSequence (String lastTripinSequence){
		this.lastTripinSequence = lastTripinSequence;
	}
	public void setPrimaryReliefStatus (String primaryReliefStatus){
		this.primaryReliefStatus = primaryReliefStatus;
	}
	public void setNextOperatorRunNumber (String nextOperatorRunNumber){
		this.nextOperatorRunNumber = nextOperatorRunNumber;
	}
	public void setNextOperatorRoute (String nextOperatorRoute){
		this.nextOperatorRoute = nextOperatorRoute;
	}
	public void setTripMileage (String tripMileage){
		this.tripMileage = tripMileage;
	}
	public void setDepotCode (String depotCode){
		this.depotCode = depotCode;
	}
	public void setBlockNumber (String blockNumber){
		this.blockNumber = blockNumber;
	}
	public void setNextTripOperatorRunNumber (String nextTripOperatorRunNumber){
		this.nextTripOperatorRunNumber = nextTripOperatorRunNumber;
	}
	public void setNextTripOperatorRoute (String nextTripOperatorRoute){
		this.nextTripOperatorRoute = nextTripOperatorRoute;
	}
	public void setNextTripOriginTime (String nextTripOriginTime){
		this.nextTripOriginTime = nextTripOriginTime;
	}
	public void setRecoveryTimeAfterThisTrip (int recoveryTimeAfterThisTrip){
		this.recoveryTimeafterThisTrip = recoveryTimeafterThisTrip;
	}
	public void setSignCodeRouteForThisTrip (String signCodeRouteForThisTrip){
		this.signCodeRouteforThisTrip = signCodeRouteforThisTrip;
	}
	public void setPreviousTripOperatorRunNumber (String previousTripOperatorRunNumber){
		this.previousTripOperatorRunNumber = previousTripOperatorRunNumber;
	}
	public void setPreviousTripOperatorRoute (String previousTripOperatorRoute){
		this.previousTripOperatorRoute = previousTripOperatorRoute;
	}
	public void setPreviousTripOriginTime (int previousTripOriginTime){
		this.previousTripOriginTime = previousTripOriginTime;
	}
	public void setOriginLocationBoxID (String originLocationBoxID){
		this.originLocationBoxID = originLocationBoxID;
	}
	public void setDestinationLocationBoxID (String destinationLocationBoxID){
		this.destinationLocationBoxID = destinationLocationBoxID;
	}
	public void setReliefLocationBoxID (String reliefLocationBoxID){
		this.reliefLocationBoxID = reliefLocationBoxID;
	}
	public void setMidtripReliefDepot (String midtripReliefDepot){
		this.midtripReliefDepot = midtripReliefDepot;
	}
	public void setNextOperatorDepot (String nextOperatorDepot){
		this.nextOperatorDepot = nextOperatorDepot;
	}
	public void setNextTripOperatorDepot (String nextTripOperatorDepot){
		this.nextTripOperatorDepot = nextTripOperatorDepot;
	}
	public void setPreviousTripOperatorDepot (String previousTripOperatorDepot){
		this.previousTripOperatorDepot = previousTripOperatorDepot;
	}
	public void setGTFSTripID (String gTFSTripID){
		this.gTFSTripID = gTFSTripID;
	}
	public String getRecordType (){
		return this.recordType;
	}
	public String getOriginLocation (){
		return this.originLocation;
	}
	public int getOriginTime (){
		return this.originTime;
	}
	public String getDirection (){
		return this.direction;
	}
	public int getTripType (){
		return this.tripType;
	}
	public String getDestinationLocation (){
		return this.destinationLocation;
	}
	public int getDestinationTime (){
		return this.destinationTime;
	}
	public String getPickCode (){
		return this.pickCode;
	}
	public String getPrimaryRunNumber (){
		return this.primaryRunNumber;
	}
	public String getPathCode (){
		return this.pathCode;
	}
	public String getPrimaryRunRoute (){
		return this.primaryRunRoute;
	}
	public String getMidtripReliefRunNumber (){
		return this.midtripReliefRunNumber;
	}
	public String getMidtripReliefRunRoute (){
		return this.midtripReliefRunRoute;
	}
	public int getMidtripReliefTime (){
		return this.midtripReliefTime;
	}
	public String getMidtripReliefLocation (){
		return this.midtripReliefLocation;
	}
	public String getBusTypeCode (){
		return this.busTypeCode;
	}
	public String getSignCode (){
		return this.signCode;
	}
	public String getFirstTripInSequence (){
		return this.firsttripinSequence;
	}
	public String getLastTripinSequence (){
		return this.lastTripinSequence;
	}
	public String getPrimaryReliefStatus (){
		return this.primaryReliefStatus;
	}
	public String getNextOperatorRunNumber (){
		return this.nextOperatorRunNumber;
	}
	public String getNextOperatorRoute (){
		return this.nextOperatorRoute;
	}
	public String getTripMileage (){
		return this.tripMileage;
	}
	public String getDepotCode (){
		return this.depotCode;
	}
	public String getBlockNumber (){
		return this.blockNumber;
	}
	public String getNextTripOperatorRunNumber (){
		return this.nextTripOperatorRunNumber;
	}
	public String getNextTripOperatorRoute (){
		return this.nextTripOperatorRoute;
	}
	public String getNextTripOriginTime (){
		return this.nextTripOriginTime;
	}
	public int getRecoveryTimeAfterThisTrip (){
		return this.recoveryTimeafterThisTrip;
	}
	public String getSignCodeRouteForThisTrip (){
		return this.signCodeRouteforThisTrip;
	}
	public String getPreviousTripOperatorRunNumber (){
		return this.previousTripOperatorRunNumber;
	}
	public String getPreviousTripOperatorRoute (){
		return this.previousTripOperatorRoute;
	}
	public int getPreviousTripOriginTime (){
		return this.previousTripOriginTime;
	}
	public String getOriginLocationBoxID (){
		return this.originLocationBoxID;
	}
	public String getDestinationLocationBoxID (){
		return this.destinationLocationBoxID;
	}
	public String getReliefLocationBoxID (){
		return this.reliefLocationBoxID;
	}
	public String getMidtripReliefDepot (){
		return this.midtripReliefDepot;
	}
	public String getNextOperatorDepot (){
		return this.nextOperatorDepot;
	}
	public String getNextTripOperatorDepot (){
		return this.nextTripOperatorDepot;
	}
	public String getPreviousTripOperatorDepot (){
		return this.previousTripOperatorDepot;
	}
	public String getGTFSTripID (){
		return this.gTFSTripID;
	}

}
