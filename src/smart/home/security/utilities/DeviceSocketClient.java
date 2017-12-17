package smart.home.security.utilities;

import java.net.URI;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 * A socket based client endpoint manager.
 * @author archana
 */
@ClientEndpoint
public class DeviceSocketClient {

    /**
     * The user session of the socket connection.
     */
    Session userSession = null;
    
    /**
     * The message handler to be invoked after receiving a message.
     */
    private MessageHandler messageHandler;

    /**
     * The DeviceSocketClient constructor given a URI endpoint.
     * @param endpointURI - the URI to create a socket connection with.
     */
    public DeviceSocketClient(URI endpointURI) {
        try {
            // Connect to the URI endpoint with a web socket container.
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            // Print the exection throw.
            System.out.println("Unable to connect to: " + endpointURI.getPath());
        }
    }

    /**
     * Callback hook for Connection open events.
     *
     * @param userSession the userSession which is opened.
     */
    @OnOpen
    public void onOpen(Session userSession) {
        System.out.println("opening websocket");
        // Hold onto the user session when the connection has been opened.
        this.userSession = userSession;
        // Set the idle timeout to unlimited.
        this.userSession.setMaxIdleTimeout(-1);
    }

    /**
     * Callback hook for Connection close events.
     *
     * @param userSession the userSession which is getting closed.
     * @param reason the reason for connection close
     */
    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        System.out.println("closing websocket");
        // Set the userSession to nil when closing the connection.
        this.userSession = null;
    }

    /**
     * Callback hook for Message Events. This method will be invoked when a
     * client send a message.
     *
     * @param message The text message
     */
    @OnMessage
    public void onMessage(String message) {
        // Validate that the message handler exists before invoking it.
        if (this.messageHandler != null) {
            // Let the message handler handle the given recieved message.
            this.messageHandler.handleMessage(message);
        }
    }

    /**
     * register message handler
     *
     * @param msgHandler
     */
    public void addMessageHandler(MessageHandler msgHandler) {
        this.messageHandler = msgHandler;
    }

    /**
     * Send a message.
     *
     * @param message
     */
    public void sendMessage(String message) {
        // Sends the given message asynchronously.
        this.userSession.getAsyncRemote().sendText(message);
    }

    /**
     * An interface to handle messages.
     */
    public static interface MessageHandler {
        /**
         * Handle the given message.
         * @param message - the String message that needs to be handled.
         */
        public void handleMessage(String message);
    }
}
