package hu.bme.mit.train.system;

import hu.bme.mit.train.controller.TrainControllerImpl;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.sensor.TrainSensorImpl;
import hu.bme.mit.train.user.TrainUserImpl;

import java.util.Timer;
import java.util.TimerTask;

public class TrainSystem {

	private Timer timer;
	private TrainController controller;
	private TrainUser user;
	private TrainSensor sensor;

	public TrainSystem() {
		controller = new TrainControllerImpl();
		user = new TrainUserImpl(controller);
		sensor = new TrainSensorImpl(controller, user);
		this.timer = new Timer();
		this.timer.schedule((TimerTask)controller,0,250);
	}

	public TrainController getController() {
		return controller;
	}

	public TrainSensor getSensor() {
		return sensor;
	}

	public TrainUser getUser() {
		return user;
	}

}
