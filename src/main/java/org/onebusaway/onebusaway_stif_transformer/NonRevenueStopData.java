package org.onebusaway.onebusaway_stif_transformer;

import java.io.Serializable;

//import com.vividsolutions.jts.geom.Coordinate;
import org.onebusaway.onebusaway_stif_transformer.model.Coordinate;

public class NonRevenueStopData implements Serializable {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private NonRevenueStopOrder nonRevenueStopOrder;
  
  private Coordinate location;
  
  private int scheduleTime;

  public NonRevenueStopOrder getNonRevenueStopOrder() {
    return nonRevenueStopOrder;
  }

  public void setNonRevenueStopOrder(NonRevenueStopOrder nonRevenueStopOrder) {
    this.nonRevenueStopOrder = nonRevenueStopOrder;
  }

  public Coordinate getLocation() {
    return location;
  }

  public void setLocation(Coordinate location) {
    this.location = location;
  }

  public int getScheduleTime() {
    return scheduleTime;
  }

  public void setScheduleTime(int scheduleTime) {
    this.scheduleTime = scheduleTime;
  }

}
