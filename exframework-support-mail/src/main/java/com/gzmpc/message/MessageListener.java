package com.gzmpc.message;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public interface MessageListener {
  public void receive(MessageEngine engine,MessageBody message);
  public void error(MessageEngine engine,EventBody event);
}