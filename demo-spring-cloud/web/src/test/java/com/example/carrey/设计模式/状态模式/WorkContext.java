package com.example.carrey.设计模式.状态模式;

/**
 * @author Carrey
 * @version 0.0.1
 * @description WorkContext
 * @create 2020-01-22 14:53
 */
public class WorkContext {

  /**
   * 状态依据
   */
  private Double hour;

  /**
   * 加班状态
   */
  private State current;

  public State getState() {
    return current;
  }

  public void setState(State state) {
    this.current = state;
  }

  public Double getHour() {
    return hour;
  }

  public void setHour(Double hour) {
    this.hour = hour;
  }

  public void writeProgram() {
    current.writeProgram(this);
  }

  public WorkContext(Double hour, State current) {
    this.hour = hour;
    this.current = current;
  }
}
