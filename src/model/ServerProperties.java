package model;

import java.io.Serializable;

public class ServerProperties implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2116984091812999593L;
	private String SolverServerAddress;
	private int SolverServerPort, SolverServerMethod, SolverServerDepth;

	public ServerProperties(String address, int port, int method, int depth) {
		SolverServerAddress = address;
		SolverServerPort = port;
		SolverServerMethod = method;
		SolverServerDepth = depth;
	}

	public ServerProperties(ServerProperties arg1) {
		this.SolverServerAddress = arg1.SolverServerAddress;
		this.SolverServerPort = arg1.SolverServerPort;
		this.SolverServerMethod = arg1.SolverServerMethod;
		this.SolverServerDepth = arg1.SolverServerDepth;
	}

	public String getSolverServerAddress() {
		return SolverServerAddress;
	}

	public void setSolverServerAddress(String solverServerAddress) {
		SolverServerAddress = solverServerAddress;
	}

	public int getSolverServerPort() {
		return SolverServerPort;
	}

	public void setSolverServerPort(int solverServerPort) {
		this.SolverServerPort = solverServerPort;
	}

	public int getSolverServerMethod() {
		return SolverServerMethod;
	}

	public void setSolverServerMethod(int solverServerMethod) {
		SolverServerMethod = solverServerMethod;
	}

	public int getSolverServerDepth() {
		return SolverServerDepth;
	}

	public void setSolverServerDepth(int solverServerDepth) {
		SolverServerDepth = solverServerDepth;
	}
}
