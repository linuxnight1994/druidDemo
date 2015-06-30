package utry.psd.call.center.websocket.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import utry.psd.call.center.monitor.Bo.RumTimeVo;
import utry.psd.call.center.test.RumTimeTest;
import utry.psd.call.center.util.MakeDataUtil;
import utry.psd.call.center.util.UUIDUtils;
import utry.psd.call.center.util.WebSocketConstants;
import utry.psd.call.center.util.WebSocketManager;
import utry.psd.call.center.websocket.bo.WebSocketBo;

/**
 * deprecated: 实时监控
 * 
 * date 2015-03-22
 * 
 * @author sharkTang
 */
@ServerEndpoint(value = "/generalOverviewServer")
@Component
public class GeneralOverviewServerWebSocketServer extends UtryWebSocketServer {
	private Logger logger_;

	public GeneralOverviewServerWebSocketServer() {
		logger_ = Logger.getLogger(this.getClass().getName());
	}

	// 锁处理
	private final static ReentrantLock lock = new ReentrantLock();
	/**
	 * 保存全局session
	 */
	public static Session daPingWebsocketSession;
	/**
	 * 保存全局websocket对象
	 */
	public static Map<Integer, WebSocketBo> webSocketMap = new HashMap<Integer, WebSocketBo>();
	public static Map<Integer, Session> webSessionMap = new HashMap<Integer, Session>(
			16);

	@Override
	public String getSentData() {
		List<List<Object>> objs = new ArrayList<List<Object>>();
		List<Object> rtvos=RumTimeTest.setDataByTest();
		List<Object> chartDataBoList=RumTimeTest.makeChartDataBoDate();
		//System.out.println(chartDataBoList.size());
		objs.add(rtvos);
		objs.add(chartDataBoList);
		String dataSend=MakeDataUtil.makeDataListObj(objs);
		//System.out.println(dataSend);
		return dataSend;
	}

	@Override
	public Map<Integer, WebSocketBo> getWebSocketMap() {
		return webSocketMap;
	}
	@Override
	public Map<Integer, Session> getWebSesssionMap() {
		// TODO Auto-generated method stub
		return webSessionMap;
	}

	/**
	 * deprecated: 接收到客户端连接请求时调用
	 * 
	 * date 2015-03-06
	 * 
	 * @author sharkTang
	 * @param session
	 */
	@OnOpen
	public void onOpen(Session session) {
		try {
			// 每次open一个session时，赋值到全局WebsocketSession，保持统一的全局session数据
			daPingWebsocketSession = session;
			webSocketMapAction(webSessionMap, session,
					WebSocketConstants.ADD_STR);
			// 往websocketManger添加一份数据
			WebSocketManager.webSocketClientCount++;
			WebSocketManager.webQueryStringSet.add(session.getQueryString());
			/*
			 * 每创建一个连接，将session信息保存到全局HashMap Key: session的hashcode,
			 * value:组装的sessionBo 并对全局Map数据加锁
			 */
			WebSocketBo webSocketBo = new WebSocketBo();
			webSocketBo.setWsId(UUIDUtils.getUUID());
			webSocketBo.setMessageStatus(WebSocketConstants.MESSAGESTATUSNO);// 初始化时设为消息状态为no状态
			int webSocketHashCode = session.hashCode();
			webSocketBo.setWsHashCode(webSocketHashCode);
			String pageType = session.getQueryString();
			if (null == pageType) {
				pageType = WebSocketConstants.SPACESTR_ENG;
			}
			webSocketBo.setPageType(pageType);
			webSocketAction(webSocketMap, session, WebSocketConstants.ADD_STR,
					webSocketBo);
			// 发送初始化数据
		//	String sendDataString = DataInitServer.getNewInitData();
		//	session.getAsyncRemote().sendText(sendDataString);
		} catch (Exception e) {
		}
	}

	/**
	 * deprecated: 接收到客户端发送的消息时调用
	 * 
	 * date 2015-03-06
	 * 
	 * @author sharkTang
	 * @param message
	 * @param session
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		try {
			// 握手确认操作
			webSocketMessage(message, session);
		} catch (Exception ex) {

		}
	}

	/**
	 * deprecated: 接收到客户端关闭时调用
	 * 
	 * date 2015-03-06
	 * 
	 * @author sharkTang
	 * @param session
	 * @param closeReason
	 */
	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		try {
			logger_.info("Web-socket session " + session.getId()
					+ "  sessionHashCode" + session.hashCode()
					+ " closed, reason: " + closeReason.toString());
			WebSocketManager.webSocketClientCount--;
			WebSocketManager.webQueryStringSet.remove(session.getQueryString());
			// 关闭客户端 赋值全局session，并删除websocket对象
			webSocketAction(webSocketMap, session,
					WebSocketConstants.REMOVE_STR, null);
			daPingWebsocketSession = session;
			webSocketMapAction(webSessionMap, session,
					WebSocketConstants.REMOVE_STR);
		} catch (Exception ex) {

		}
	}

	/**
	 * deprecated: 对保存seesion信息的map操作
	 * 
	 * 2015-03-20
	 * 
	 * @author sharkTang
	 * @param webSocketMap
	 *            : socket对象集合
	 * @param session
	 *            ： javax.websocket.Session
	 * @param actionString
	 *            : action
	 */
	public static void webSocketMapAction(Map<Integer, Session> webSessionMap,
			Session session, String actionString) {
		try {
			lock.lock();
			if (WebSocketConstants.ADD_STR.equals(actionString)) {
				webSessionMap.put(session.hashCode(), session);
			} else if (WebSocketConstants.REMOVE_STR.equals(actionString)) {
				webSessionMap.remove(session.hashCode());
			}
		} catch (Exception e) {
			lock.unlock();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * deprecated: 对全局websocket对象的集合操作进行加锁操作
	 * 
	 * @author sharkTang
	 * @param webSocketMap
	 *            : socket对象集合
	 * @param session
	 *            ： websocketSession
	 * @param actionString
	 *            : action
	 * @param webSocketBo
	 *            : websocket object
	 */
	public static void webSocketAction(Map<Integer, WebSocketBo> webSocketMap,
			Session session, String actionString, WebSocketBo webSocketBo) {
		try {
			// add lock to webSocketMap
			lock.lock();
			// something to do
			daPingWebsocketSession = session;
			Integer webSocketHashCode = session.hashCode();
			if (WebSocketConstants.ADD_STR.equals(actionString)) {
				webSocketMap.put(webSocketHashCode, webSocketBo);
			} else if (WebSocketConstants.REMOVE_STR.equals(actionString)) {
				webSocketMap.remove(webSocketHashCode);
			}
		} catch (Exception ex) {
			lock.unlock();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * deprecated:客户端消息发送状态确认
	 * 
	 * 2015-03-11
	 * 
	 * @author sharkTang
	 * @param webSocketMap
	 *            : socket对象集合
	 * @param session
	 *            ： websocketSession
	 * @param message
	 *            :Client message
	 */
	private void webSocketMessage(String message, Session session) {
		// 握手确认操作
		// 获得session hashcode和消息 判断message是不是消息确认
		if (null != message && !"".equals(message)
				&& WebSocketConstants.MESSAGESTATUSOK.equals(message)) {
			Integer sessionHashCode = session.hashCode();
			// 取出webSocketMap中的socket对象
			WebSocketBo webSocketTemp = webSocketMap.get(sessionHashCode);
			webSocketAction(webSocketMap, session,
					WebSocketConstants.REMOVE_STR, webSocketTemp);
			if (null != webSocketTemp) {
				webSocketTemp.setMessageStatus(message);
			}
			webSocketAction(webSocketMap, session, WebSocketConstants.ADD_STR,
					webSocketTemp);
		}

	}

}
