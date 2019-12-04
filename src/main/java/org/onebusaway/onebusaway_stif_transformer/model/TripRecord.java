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
	private String midtripReliefTime;
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
	private String recoveryTimeafterThisTrip;
	private String signCodeRouteForThisTrip;
	private String previousTripOperatorRunNumber;
	private String previousTripOperatorRoute;
	private String previousTripOriginTime;
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
	public void setMidtripReliefTime (String midtripReliefTime){
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
	public void setRecoveryTimeAfterThisTrip (String recoveryTimeAfterThisTrip){
		this.recoveryTimeafterThisTrip = recoveryTimeAfterThisTrip;
	}
	public void setSignCodeRouteForThisTrip (String signCodeRouteForThisTrip){
		this.signCodeRouteForThisTrip = signCodeRouteForThisTrip;
	}
	public void setPreviousTripOperatorRunNumber (String previousTripOperatorRunNumber){
		this.previousTripOperatorRunNumber = previousTripOperatorRunNumber;
	}
	public void setPreviousTripOperatorRoute (String previousTripOperatorRoute){
		this.previousTripOperatorRoute = previousTripOperatorRoute;
	}
	public void setPreviousTripOriginTime (String previousTripOriginTime){
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
	public String getMidtripReliefRunRoute (){ return this.midtripReliefRunRoute; }
	public String getMidtripReliefTime (){
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
	public String getLastTripInSequence (){
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
	public String getNextTripOperatorRoute (){ return this.nextTripOperatorRoute; }
	public String getNextTripOriginTime (){
		return this.nextTripOriginTime;
	}
	public String getRecoveryTimeAfterThisTrip (){
		return this.recoveryTimeafterThisTrip;
	}
	public String getSignCodeRouteForThisTrip (){
		return this.signCodeRouteForThisTrip;
	}
	public String getPreviousTripOperatorRunNumber (){
		return this.previousTripOperatorRunNumber;
	}
	public String getPreviousTripOperatorRoute (){
		return this.previousTripOperatorRoute;
	}
	public String getPreviousTripOriginTime (){
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




	String fileName = null;
	public void addFileName(String fileName){
		this.fileName = fileName;
	}
	public String getFileName(String fileName){
		return this.fileName;
	}

	@Override
	public String toString()
	{
		String out = "";
		int n = 3 - 1;
		String formatter = "%-"+ n + "s";
		out += String.format(formatter,this.getRecordType());


		n = 7 - 3;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,this.getOriginLocation());


		n = 15 - 7;
		formatter = "%0"+ n + "d";
		out += String.format(formatter,this.getOriginTime());


		n = 17 - 15;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,this.getDirection());


		n = 19 - 17;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,this.getTripType());


		n = 23 - 19;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,this.getDestinationLocation());


		n = 31 - 23;
		formatter = "%0"+ n + "d";
		out += String.format(formatter,this.getDestinationTime());


		n = 35 - 31;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,this.getPickCode());


		n = 41 - 35;
		formatter = "%"+ n + "s";
		out += String.format(formatter,this.getPrimaryRunNumber());


		n = 53 - 41;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,this.getPathCode());


		n = 59 - 53;
		formatter = "%"+ n + "s";
		out += String.format(formatter,this.getPrimaryRunRoute());


		n = 65 - 59;
		formatter = "%"+ n + "s";
		out += String.format(formatter,this.getMidtripReliefRunNumber());


		n = 71 - 65;
		formatter = "%"+ n + "s";
		out += String.format(formatter,this.getMidtripReliefRunRoute());


		n = 79 - 71;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,this.getMidtripReliefTime());


		n = 83 - 79;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,this.getMidtripReliefLocation());


		n = 84 - 83;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,this.getBusTypeCode());


		n = 88 - 84;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,this.getSignCode());


		n = 89 - 88;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,"");

		n = 90 - 89;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,this.getFirstTripInSequence());


		n = 91 - 90;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,"");

		n = 92 - 91;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,this.getLastTripInSequence());


		n = 93 - 92;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,this.getPrimaryReliefStatus());

		n = 99 - 93;
		formatter = "%"+ n + "s";
		out += String.format(formatter,this.getNextOperatorRunNumber());


		n = 100 - 99;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,"");

		n = 106 - 100;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,this.getNextOperatorRoute());


		n = 107 - 106;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,"");

		n = 112 - 107;
		formatter = "%"+ n + "s";
		out += String.format(formatter,this.getTripMileage());


		n = 113 - 112;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,"");

		n = 115 - 113;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,this.getDepotCode());


		n = 116 - 115;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,"");

		n = 126 - 116;
		formatter = "%"+ n + "s";
		out += String.format(formatter,this.getBlockNumber());


		n = 127 - 126;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,"");

		n = 133 - 127;
		formatter = "%"+ n + "s";
		out += String.format(formatter,this.getNextTripOperatorRunNumber());


		n = 134 - 133;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,"");

		n = 140 - 134;
		formatter = "%"+ n + "s";
		out += String.format(formatter,this.getNextTripOperatorRoute());


		n = 141 - 140;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,"");

		n = 149 - 141;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,this.getNextTripOriginTime());


		n = 150 - 149;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,"");

		n = 154 - 150;
		formatter = "%"+ n + "s";
		out += String.format(formatter,this.getRecoveryTimeAfterThisTrip());


		n = 155 - 154;
		formatter = "%"+ n + "s";
		out += String.format(formatter,"");

		n = 161 - 155;
		formatter = "%"+ n + "s";
		out += String.format(formatter,this.getSignCodeRouteForThisTrip());

		n = 162 - 161;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,"");

		n = 168 - 162;
		formatter = "%"+ n + "s";
		out += String.format(formatter,this.getPreviousTripOperatorRunNumber());


		n = 169 - 168;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,"");

		n = 175 - 169;
		formatter = "%"+ n + "s";
		out += String.format(formatter,this.getPreviousTripOperatorRoute());


		n = 176 - 175;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,"");

		n = 184 - 176;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,this.getPreviousTripOriginTime());


		n = 185 - 184;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,"");

		n = 191 - 185;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,this.getOriginLocationBoxID());


		n = 192 - 191;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,"");

		n = 198 - 192;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,this.getDestinationLocationBoxID());


		n = 199 - 198;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,"");

		n = 205 - 199;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,this.getReliefLocationBoxID());


		n = 206 - 205;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,"");

		n = 208 - 206;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,this.getMidtripReliefDepot());


		n = 209 - 208;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,"");

		n = 211 - 209;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,this.getNextOperatorDepot());


		n = 212 - 211;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,"");

		n = 214 - 212;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,this.getNextTripOperatorDepot());


		n = 215 - 214;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,"");

		n = 217 - 215;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,this.getPreviousTripOperatorDepot());


		n = 218 - 217;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,"");

		n = 258 - 218;
		formatter = "%-"+ n + "s";
		out += String.format(formatter,this.getGTFSTripID());

		return out;
	}
}
