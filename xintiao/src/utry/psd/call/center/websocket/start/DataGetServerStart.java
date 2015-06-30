package utry.psd.call.center.websocket.start;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import utry.psd.call.center.websocket.thread.DataFindAction;
import utry.psd.call.center.websocket.thread.DataInitThreadAction;

/**
 * 跟随服务器启动，数据获取
 * 
 * date 2015-03-16
 * 
 * @author sharkTang
 * 
 */
@Component
public class DataGetServerStart {
	@PostConstruct
	public void dataInitFunciton() throws Exception {
		// TODO Auto-generated method stub
		DataFindAction dataInitThreadAction = new DataFindAction();
		Thread thread = new Thread(dataInitThreadAction);
		thread.start();
	}
}
